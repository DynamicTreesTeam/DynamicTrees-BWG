//package maxhyper.dtbwg.cancellers;
//
//import com.dtteam.dynamictrees.api.TreeRegistry;
//import maxhyper.dtbwg.DynamicTreesBWG;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
//import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
//import potionstudios.biomeswevegone.common.block.biomeswevegoneBlocks;
//import potionstudios.biomeswevegone.common.block.biomeswevegoneWoodTypes;
//import potionstudios.biomeswevegone.common.world.biome.biomeswevegoneBiomes;
//import potionstudios.biomeswevegone.common.world.feature.features.end.biomeswevegoneEndVegetationFeatures;
//import potionstudios.biomeswevegone.common.world.feature.features.nether.biomeswevegoneNetherVegetationFeatures;
//import potionstudios.biomeswevegone.common.world.feature.features.overworld.biomeswevegoneOverworldVegetationFeatures;
//import potionstudios.biomeswevegone.common.world.feature.placement.biomeswevegonePlacedFeaturesUtil;
//
//public class VegetationReplacement {
//
//    public static void replaceNyliumFungiFeatures() {
//        TreeRegistry.findSpecies(DynamicTreesBWG.location("shulkren")).getSapling().ifPresent((shulkrenSapling) ->
//                TreeRegistry.findSpecies(DynamicTreesBWG.location("embur")).getSapling().ifPresent((emburSapling) ->
//                        TreeRegistry.findSpecies(DynamicTreesBWG.location("sythian")).getSapling().ifPresent((sythianSapling) -> {
//                            replacePatchConfig(biomeswevegoneEndVegetationFeatures.SHULKREN_FUNGUS.value(), shulkrenSapling, biomeswevegoneBlocks.SHULKREN_FUNGUS.get());
//                            replaceFeatureConfig(biomeswevegoneNetherVegetationFeatures.EMBUR_BOG_VEGETATION.value(), emburSapling, biomeswevegoneWoodTypes.EMBUR.growerItem().get());
//                            replaceFeatureConfig(biomeswevegoneNetherVegetationFeatures.SYTHIAN_VEGETATION.value(), sythianSapling, biomeswevegoneWoodTypes.SYTHIAN.growerItem().get());
//                        })));
//    }
//
//    @SuppressWarnings("unchecked")
//    private static void replaceFeatureConfig(ConfiguredFeature<RandomFeatureConfiguration,?> configuredFeature, Block dynamicSapling, Block basicSapling) {
//        replacePatchConfig((ConfiguredFeature<RandomPatchConfiguration,?>)configuredFeature.config().defaultFeature.value().feature().value(), dynamicSapling, basicSapling);
//    }
//    private static void replacePatchConfig(ConfiguredFeature<RandomPatchConfiguration,?> configuredFeature, Block dynamicSapling, Block basicSapling) {
//        var f2 = configuredFeature.config().feature().value().feature().value();
//        if (f2.config() instanceof SimpleBlockConfiguration sbc && sbc.toPlace() instanceof SimpleStateProvider ssp && ssp.state.is(basicSapling))
//            ssp.state = dynamicSapling.defaultBlockState();
//
//    }
//
////    private static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, DynamicTreesbiomeswevegone.MOD_ID);
////    private static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, DynamicTreesbiomeswevegone.MOD_ID);
////
////    public static final BlockPredicateFilter SAND_FILTER = BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.SAND, BlockPos.ZERO.below()));
////    public static RegistryObject<ConfiguredFeature<?,?>> WINDSWEPT_DESERT_VEGETATION =
////            CONFIGURED_FEATURES.register("windswept_desert_vegetation_no_vanilla_cacti",
////                    () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(
////                            new WeightedPlacedFeature(biomeswevegonePlacedFeaturesUtil.createPlacedFeature(biomeswevegoneOverworldVegetationFeatures.MINI_CACTI, SAND_FILTER), 0.15F),
////                            new WeightedPlacedFeature(biomeswevegonePlacedFeaturesUtil.createPlacedFeature(biomeswevegoneOverworldVegetationFeatures.PRICKLY_PEAR_CACTI, SAND_FILTER), 0.15F),
////                            new WeightedPlacedFeature(biomeswevegonePlacedFeaturesUtil.createPlacedFeature(biomeswevegoneOverworldVegetationFeatures.ALOE_VERA, SAND_FILTER), 0.3F)
////                    ), biomeswevegonePlacedFeaturesUtil.createPlacedFeature(biomeswevegoneOverworldVegetationFeatures.GOLDEN_SPINED_CACTI, SAND_FILTER))));
////
////    public static RegistryObject<PlacedFeature> WINDSWEPT_DESERT_VEGETATION_HOLDER = PLACED_FEATURES.register("windswept_desert_vegetation_no_vanilla_cacti", ()-> new PlacedFeature( WINDSWEPT_DESERT_VEGETATION.getHolder().orElseThrow(), VegetationPlacements.worldSurfaceSquaredWithCount(8)));
////
////    public static void OnBiomeLoadingEvent (final BiomeLoadingEvent event){
////        if (biomeswevegoneBiomes.WINDSWEPT_DESERT.location().equals(event.getName()) &&
////                WINDSWEPT_DESERT_VEGETATION_HOLDER.getHolder().isPresent()){
////            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WINDSWEPT_DESERT_VEGETATION_HOLDER.getHolder().get());
////        }
////    }
////
////    public static void register(IEventBus bus) {
////        CONFIGURED_FEATURES.register(bus);
////        PLACED_FEATURES.register(bus);
////    }
//
//}
