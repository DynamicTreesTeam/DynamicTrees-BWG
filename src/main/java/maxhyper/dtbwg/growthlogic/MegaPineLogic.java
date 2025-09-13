package maxhyper.dtbwg.growthlogic;

import com.dtteam.dynamictrees.systems.GrowSignal;
import com.dtteam.dynamictrees.systems.growthlogic.ConiferLogic;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKitConfiguration;
import com.dtteam.dynamictrees.systems.growthlogic.context.DirectionManipulationContext;
import com.dtteam.dynamictrees.tree.TreeHelper;
import com.dtteam.dynamictrees.utility.CoordUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class MegaPineLogic extends ConiferLogic {

    public MegaPineLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(ENERGY_DIVISOR, 3.5f);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final GrowSignal signal = context.signal();
        final int[] probMap = context.probMap();
        final BlockPos pos = context.pos();
        Direction originDir = signal.dir.getOpposite();
        int absDistance = Math.max(Math.abs(signal.delta.getX()), Math.abs(signal.delta.getZ()));

        if (signal.isInTrunk()){
            probMap[0] = 0;
            probMap[1] = context.species().getUpProbability();
            probMap[2] = probMap[3] = probMap[4] = probMap[5] = signal.numSteps % 3 == 1 && context.radius() > 1 ? 1 : 0;
        } else {
            if (absDistance == 1){
                int[] prob = new int[]{0,0,0,0,0,0};
                prob[signal.dir.ordinal()] = 1;
                return prob;
            }
            boolean isBranchAbove = TreeHelper.isBranch(context.level().getBlockState(pos.above()))
                    || Math.abs(CoordUtils.coordHashCode(pos, 2)) % 4 == 0;
            probMap[1] = 0;
            probMap[0] = !isBranchAbove ? 1 : 0;
            probMap[2] = probMap[3] = probMap[4] = probMap[5] = isBranchAbove ? 1 : 0;
        }

        probMap[originDir.ordinal()] = 0;//Disable the direction we came from

        return probMap;
    }
}
