package maxhyper.dtbwg.genfeatures;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.genfeature.BiomePredicateGenFeature;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import maxhyper.dtbwg.DynamicTreesBWG;

public class DTBWGGenFeatures {

    public static final GenFeature BIG_BOTTOM_FLARE = new BigBottomFlareGenFeature(DynamicTreesBWG.location("big_bottom_flare"));
    public static final GenFeature EXTRA_BOTTOM_FLARE = new ExtraBottomFlareGenFeature(DynamicTreesBWG.location("extra_bottom_flare"));
    //public static final GenFeature MANGROVELINGS = new MangrovelingsGenFeature(DynamicTreesbiomeswevegone.location("mangrovelings"));
    public static final GenFeature BRANCH_SPROUTS = new BranchSproutsGenFeature(DynamicTreesBWG.location("branch_sprouts"));
    //public static final GenFeature HUGE_MUSHROOMS = new biomeswevegoneHugeMushroomGenFeature(DynamicTreesbiomeswevegone.location("huge_mushrooms"));
    public static final GenFeature BIOME_PREDICATE_2 = new BiomePredicateGenFeature(DynamicTreesBWG.location("biome_predicate"));
    public static final GenFeature SYTHIAN_TOPPER = new SythianTopperGenFeature(DynamicTreesBWG.location("sythian_topper"));
    public static final GenFeature ALTERNATIVE_BRANCH = new AlternativeBranchGenFeature(DynamicTreesBWG.location("alt_branch"));
    public static final GenFeature MUSHROOM_VINES = new MushroomVinesGenFeature(DynamicTreesBWG.location("mushroom_vines"));
    public static final GenFeature LUSH_VINES = new LushVinesGenFeature(DynamicTreesBWG.location("lush_vines"));
    public static final GenFeature ALT_LEAVES_HEIGHT_LIMIT = new AlternativeLeavesWithHeightLimitGenFeature(DynamicTreesBWG.location("alt_leaves_with_heigth_limit"));

    public static void register(final Registry<GenFeature> registry) {
        registry.registerAll(BIG_BOTTOM_FLARE, EXTRA_BOTTOM_FLARE,
                //MANGROVELINGS,
                BRANCH_SPROUTS,
                // HUGE_MUSHROOMS,
                BIOME_PREDICATE_2,
                SYTHIAN_TOPPER, ALTERNATIVE_BRANCH, MUSHROOM_VINES, LUSH_VINES, ALT_LEAVES_HEIGHT_LIMIT);
    }

}
