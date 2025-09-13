package maxhyper.dtbwg.growthlogic;

import com.dtteam.dynamictrees.systems.GrowSignal;
import com.dtteam.dynamictrees.systems.growthlogic.ConiferLogic;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKitConfiguration;
import com.dtteam.dynamictrees.systems.growthlogic.context.DirectionManipulationContext;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class TaperedOakLogic extends ConiferLogic {

    public TaperedOakLogic(ResourceLocation registryName) { super(registryName); }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(ENERGY_DIVISOR, 4F)
                .with(HORIZONTAL_LIMITER, 3F)
                .with(HEIGHT_VARIATION, 7);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final GrowSignal signal = context.signal();
        final int[] probMap = context.probMap();
        Direction originDir = signal.dir.getOpposite();

        //Alter probability map for direction change
        probMap[0] = 0;//Down is always disallowed
        probMap[1] = signal.isInTrunk() ? context.species().getUpProbability(): 1;
        probMap[2] = probMap[3] = probMap[4] = probMap[5] = //Only allow turns when we aren't in the trunk(or the branch is not a twig)
                !signal.isInTrunk() || context.radius() > 1 ? 2 : 0;
        probMap[originDir.ordinal()] = 0;//Disable the direction we came from
        probMap[signal.dir.ordinal()] += signal.isInTrunk() ? 0 : signal.numTurns == 1 ? 2 : 1;//Favor current travel direction

        return probMap;
    }
}
