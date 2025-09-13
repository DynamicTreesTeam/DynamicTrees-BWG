package maxhyper.dtbwg.genfeatures;

import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.api.configuration.ConfigurationProperty;
import com.dtteam.dynamictrees.api.network.MapSignal;
import com.dtteam.dynamictrees.block.branch.BranchBlock;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGrowContext;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.nodemapper.FindEndsNode;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.utility.CoordUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class LushVinesGenFeature extends GenFeature {

    protected final BooleanProperty[] sideVineStates = new BooleanProperty[]{null, null, VineBlock.NORTH, VineBlock.SOUTH, VineBlock.WEST, VineBlock.EAST};

    public static final ConfigurationProperty<Integer> MAX_LENGTH = ConfigurationProperty.integer("max_length");
    public static final ConfigurationProperty<Block> BLOCK = ConfigurationProperty.block("block");
    public static final ConfigurationProperty<Block> TIP_BLOCK = ConfigurationProperty.block("tip_block");
    public static final ConfigurationProperty<Integer> BERRIES_CHANCE = ConfigurationProperty.integer("berries_chance");

    public static final BooleanProperty BERRIES = BooleanProperty.create("berries");

    public LushVinesGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(QUANTITY, MAX_LENGTH, VERTICAL_SPREAD, RAY_DISTANCE, BLOCK, TIP_BLOCK, BERRIES_CHANCE, FRUITING_RADIUS);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(QUANTITY, 4)
                .with(MAX_LENGTH, 8)
                .with(VERTICAL_SPREAD, 60f)
                .with(RAY_DISTANCE, 5f)
                .with(BLOCK, Blocks.VINE)
                .with(TIP_BLOCK, null)
                .with(BERRIES_CHANCE, 0)
                .with(FRUITING_RADIUS, -1);
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        if (!context.isWorldGen() || context.endPoints().isEmpty()) {
            return false;
        }

        final int quantity = configuration.get(QUANTITY);

        for (int i = 0; i < quantity; i++) {
            final BlockPos endPoint = context.endPoints().get(context.random().nextInt(context.endPoints().size()));
            this.addVerticalVines(configuration, context.level(), context.species(), context.pos(), endPoint, true);
        }

        return true;
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        final LevelAccessor level = context.level();
        final BlockPos rootPos = context.pos();
        final Species species = context.species();
        final int fruitingRadius = configuration.get(FRUITING_RADIUS);

        if (fruitingRadius < 0 || context.fertility() < 1) {
            return false;
        }

        final BlockState blockState = level.getBlockState(context.treePos());
        final BranchBlock branch = TreeHelper.getBranch(blockState);

        if (branch != null && branch.getRadius(blockState) >= fruitingRadius && context.natural()) {
            if (species.seasonalFruitProductionFactor(context.levelContext(), rootPos) > level.getRandom().nextFloat()) {
                final FindEndsNode endFinder = new FindEndsNode();
                TreeHelper.startAnalysisFromRoot(level, rootPos, new MapSignal(endFinder));
                final List<BlockPos> endPoints = endFinder.getEnds();
                final int qty = configuration.get(QUANTITY);

                if (!endPoints.isEmpty()) {
                    for (int i = 0; i < qty; i++) {
                        BlockPos endPoint = endPoints.get(level.getRandom().nextInt(endPoints.size()));
                        this.addVerticalVines(configuration, level, species, rootPos, endPoint, false);
                    }
                    return true;
                }
            }
        }

        return true;
    }

    protected void addVerticalVines(GenFeatureConfiguration configuration, LevelAccessor level, Species species, BlockPos rootPos, BlockPos branchPos, boolean worldgen) {
        BlockPos vinePos = CoordUtils.getRayTraceFruitPos(level, species, rootPos, branchPos, worldgen);

        if (!worldgen || vinePos == BlockPos.ZERO) {
            return;
        }

        this.placeVines(level, vinePos, configuration.get(BLOCK).defaultBlockState(), configuration.get(MAX_LENGTH),
                        configuration.getAsOptional(TIP_BLOCK)
                                .map(Block::defaultBlockState)
                                .orElse(null),
                        worldgen, configuration);
    }

    protected void placeVines(LevelAccessor level, BlockPos vinePos, BlockState vinesState, int maxLength, @Nullable BlockState tipState, boolean worldGen, GenFeatureConfiguration configuration) {
        final int len = worldGen ? Mth.clamp(level.getRandom().nextInt(maxLength) + 3, 3, maxLength) : 1;
        final BlockPos.MutableBlockPos mPos = new BlockPos.MutableBlockPos(vinePos.getX(), vinePos.getY(), vinePos.getZ());
        final int berriesChance = configuration.get(BERRIES_CHANCE);

        tipState = tipState == null ? vinesState : tipState;

        for (int i = 0; i < len; i++) {
            if (level.isEmptyBlock(mPos)) {
                boolean hasBerries = level.getRandom().nextInt(10) < berriesChance;
                BlockState currentState = (i == len - 1) ? tipState : vinesState;

                if (hasBerries && currentState.hasProperty(BERRIES)) {
                    currentState = currentState.setValue(BERRIES, true);
                }

                level.setBlock(mPos, currentState, 3);
                mPos.setY(mPos.getY() - 1);
            } else {
                if (i > 0) {
                    mPos.setY(mPos.getY() + 1);
                    level.setBlock(mPos, tipState, 3);
                }
                break;
            }
    }
}

}
