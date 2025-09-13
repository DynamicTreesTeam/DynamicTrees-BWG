package maxhyper.dtbwg.trees;

import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.sapling.DynamicSaplingBlock;
import com.dtteam.dynamictrees.item.Seed;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.worldgen.DynamicTreeGenerationContext;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure.GenerationContext;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

/**
 * This species will place another alternative species,
 * if the soil is acceptable for the alternative species.
 *
 * @author Max Hyper
 */
public class LamentSpecies extends Species {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultType(LamentSpecies::new);

    private Species altSpecies = Species.NULL_SPECIES;

    public void setAltSpecies(Species altSpecies) {
        if (altSpecies != this)
            this.altSpecies = altSpecies;
    }

    public LamentSpecies(ResourceLocation name, Family family, LeavesProperties leavesProperties) {
        super(name, family, leavesProperties);
    }

    @Override
    public boolean generate(DynamicTreeGenerationContext context) {
        LevelAccessor level = context.level();
        BlockPos rootPos = context.rootPos();
        if (altSpecies.isAcceptableSoilForWorldgen(level, rootPos, level.getBlockState(rootPos)))
            return altSpecies.generate(context);
        return super.generate(context);
    }

    @Override
    public boolean isAcceptableSoilForWorldgen(LevelAccessor level, BlockPos pos, BlockState soilBlockState) {
        return super.isAcceptableSoilForWorldgen(level, pos, soilBlockState) || altSpecies.isAcceptableSoilForWorldgen(level, pos, soilBlockState);
    }
    @Override
    public boolean isAcceptableSoil(LevelReader level, BlockPos pos, BlockState soilBlockState) {
        return super.isAcceptableSoil(level, pos, soilBlockState) || altSpecies.isAcceptableSoil(level, pos, soilBlockState);
    }

    @Override
    protected boolean transitionToTree(Level level, BlockPos pos, Family family) {
        if (altSpecies.isAcceptableSoil(level, pos.below(), level.getBlockState(pos.below())))
            return altSpecies.transitionToTree(level, pos);
        return super.transitionToTree(level, pos, family);
    }

    @Override
    public Species generateSeed() {
        return !this.shouldGenerateSeed() || this.seed != null ? this :
                this.setSeed(RegistryHandler.addItem(getSeedName(), () -> new Seed(this, new Item.Properties().fireResistant()) {
                }));
    }

    @Override
    public boolean plantSapling(LevelAccessor level, BlockPos pos, boolean locationOverride) {
        FluidState fluidState = level.getFluidState(pos);
        FluidState fluidStateUp = level.getFluidState(pos.above());

        final DynamicSaplingBlock sapling = this.getSapling().orElse(null);

        if (sapling != null && fluidState.getType() == Fluids.LAVA && fluidStateUp.getType() == Fluids.EMPTY){
            return super.plantSapling(level, pos.above(), locationOverride);
        }
        return super.plantSapling(level, pos, locationOverride);
    }

}
