package maxhyper.dtbwg.growthlogic;

import com.dtteam.dynamictrees.systems.GrowSignal;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKitConfiguration;
import com.dtteam.dynamictrees.systems.growthlogic.context.DirectionManipulationContext;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class EbonyLogic extends GrowthLogicKit {
    public EbonyLogic(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(HEIGHT_VARIATION, 10);
    }

    @Override
    protected void registerProperties() {
        this.register(HEIGHT_VARIATION);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final Species species = context.species();
        final GrowSignal signal = context.signal();
        final int[] probMap = context.probMap();
        Direction originDir = signal.dir.getOpposite();

        probMap[0] = species.getUpProbability();
        probMap[1] = signal.isInTrunk() &&
                signal.energy > configuration.getLowestBranchHeight(context) ?
                0 : probMap[0];

        probMap[2] = probMap[3] = probMap[4] = probMap[5] = 4;

        if (!signal.isInTrunk())
            probMap[signal.dir.ordinal()] += 2;

        // Disable the direction we came from
        probMap[originDir.ordinal()] = 0;

        return probMap;
    }

}
