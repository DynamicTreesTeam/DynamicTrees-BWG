package maxhyper.dtbwg.cancellers;

import com.dtteam.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.dtteam.dynamictrees.api.worldgen.FeatureCanceller;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BWGTreeFeatureCanceller<T extends FeatureConfiguration> extends FeatureCanceller {

    private final Class<T> treeFeatureConfigClass;

    public BWGTreeFeatureCanceller(final ResourceLocation registryName, Class<T> treeFeatureConfigClass) {
        super(registryName);
        this.treeFeatureConfigClass = treeFeatureConfigClass;
    }

    @Override
    public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        final FeatureConfiguration featureConfig = configuredFeature.config();

        /*  The following code removes vanilla trees from the biome's generator.
            There may be some problems as MultipleRandomFeatures can store other features too,
            so these are currently removed from world gen too. The list is immutable so they can't be removed individually,
            but one (unclean) solution may be to add the non-tree features back to the generator. */

        if (featureConfig instanceof RandomFeatureConfiguration RFC) {
            // Removes configuredFeature if it contains trees.
            return this.doesContainTrees(RFC, featureCancellations);
        } else if (this.treeFeatureConfigClass.isInstance(featureConfig)) {
            String nameSpace = "";
            final ConfiguredFeature<?, ?> nextConfiguredFeature = configuredFeature.getFeatures().findFirst().get();
            final FeatureConfiguration nextFeatureConfig = nextConfiguredFeature.config();
            final ResourceLocation featureRegistryName = BuiltInRegistries.FEATURE.getKey(nextConfiguredFeature.feature());
            if (featureRegistryName != null) {
                nameSpace = featureRegistryName.getNamespace();
            }
            if (this.treeFeatureConfigClass.isInstance(nextFeatureConfig) && !nameSpace.equals("") &&
                    featureCancellations.shouldCancelNamespace(nameSpace)) {
                return true; // Removes any individual trees.
            } else if (nextFeatureConfig instanceof RandomFeatureConfiguration) {
                // Removes configuredFeature if it contains trees.
                return this.doesContainTrees((RandomFeatureConfiguration) nextFeatureConfig, featureCancellations);
            }
        }

        return false;
    }


    private boolean doesContainTrees(RandomFeatureConfiguration featureConfig, BiomePropertySelectors.NormalFeatureCancellation featureCancellations) {
        for (WeightedPlacedFeature feature : featureConfig.features) {
            final PlacedFeature currentPlacedFeature = feature.feature.value();
            final ConfiguredFeature<?,?> currentConfiguredFeature = currentPlacedFeature.feature().value();
            final ResourceLocation featureRegistryName = BuiltInRegistries.FEATURE.getKey(currentConfiguredFeature.getFeatures().findFirst().get().feature());

            if (this.treeFeatureConfigClass.isInstance(currentConfiguredFeature.config()) && featureRegistryName != null &&
                    featureCancellations.shouldCancelNamespace(featureRegistryName.getNamespace())) {
                return true;
            }

            if (currentConfiguredFeature.config() instanceof RandomFeatureConfiguration RFC) {
                return this.doesContainTrees(RFC, featureCancellations);
            }
        }
        return false;
    }

}