package maxhyper.dtbwg.growthlogic;

import com.dtteam.dynamictrees.systems.growthlogic.ConiferLogic;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKitConfiguration;
import com.dtteam.dynamictrees.systems.growthlogic.context.PositionalSpeciesContext;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.utility.CoordUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class ZelkovaLogic extends ConiferLogic {

    public ZelkovaLogic(ResourceLocation registryName) { super(registryName); }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(ENERGY_DIVISOR, 10F)
                .with(HORIZONTAL_LIMITER, 2F)
                .with(VariateHeightLogic.LOWEST_BRANCH_VARIATION, 7);
    }

    @Override
    protected void registerProperties() {
        this.register(ENERGY_DIVISOR, HORIZONTAL_LIMITER, HEIGHT_VARIATION, VariateHeightLogic.LOWEST_BRANCH_VARIATION, VARIATE_LOWEST_BRANCH);
    }

    @Override
    public int getLowestBranchHeight(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        Species species = context.species();
        BlockPos pos = context.pos();
        long day = context.level().getGameTime() / 24000L;
        int month = (int) day / 30;//Change the hashs every in-game month

        return (int)(super.getLowestBranchHeight(configuration, context) * species.biomeSuitability(context.level(), pos) +
                (CoordUtils.coordHashCode(pos.above(month), 2) % configuration.get(VariateHeightLogic.LOWEST_BRANCH_VARIATION)));//Vary the height energy by a psuedorandom hash function
    }

}
