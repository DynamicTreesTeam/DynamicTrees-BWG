package maxhyper.dtbwg.genfeatures;

import com.dtteam.dynamictrees.api.configuration.ConfigurationProperty;
import com.dtteam.dynamictrees.systems.genfeature.AlternativeLeavesGenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.dtteam.dynamictrees.systems.genfeature.context.PostGrowContext;
import net.minecraft.resources.ResourceLocation;

public class AlternativeLeavesWithHeightLimitGenFeature extends AlternativeLeavesGenFeature {

    public static final ConfigurationProperty<Integer> MIN_HEIGHT = ConfigurationProperty.integer("min_height");

    public AlternativeLeavesWithHeightLimitGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(ALT_LEAVES, ALT_LEAVES_BLOCK, PLACE_CHANCE, QUANTITY, MIN_HEIGHT);
    }

    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(MIN_HEIGHT, 0);
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        int minHeight = configuration.get(MIN_HEIGHT);
        int rootHeight = context.pos().getY();
        if (rootHeight < minHeight) return false;
        return super.postGenerate(configuration, context);
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        int minHeight = configuration.get(MIN_HEIGHT);
        int rootHeight = context.pos().getY();
        if (rootHeight < minHeight) return false;
        return super.postGrow(configuration, context);
    }
}
