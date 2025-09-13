package maxhyper.dtbwg.growthlogic;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;

import maxhyper.dtbwg.DynamicTreesBWG;
import net.minecraft.resources.ResourceLocation;

public class DTBWGGrowthLogicKits {

    public static final GrowthLogicKit POPLAR = new PoplarLogic(DynamicTreesBWG.location("poplar"));
    public static final GrowthLogicKit MAPLE = new MapleLogic(DynamicTreesBWG.location("maple"));
    public static final GrowthLogicKit ASPEN = new AspenLogic(DynamicTreesBWG.location("aspen"));
    public static final GrowthLogicKit TAPERED = new TaperedOakLogic(DynamicTreesBWG.location("tapered"));
    public static final GrowthLogicKit DIAGONAL_PALM = new DiagonalPalmLogic(DynamicTreesBWG.location("diagonal_palm"));
    public static final GrowthLogicKit ZELKOVA = new ZelkovaLogic(DynamicTreesBWG.location("zelkova"));
    public static final GrowthLogicKit THIN_CONIFER = new PineLogic(DynamicTreesBWG.location("thin_conifer"));
    public static final GrowthLogicKit MEGA_PINE = new MegaPineLogic(DynamicTreesBWG.location("mega_pine"));
    public static final GrowthLogicKit EBONY = new EbonyLogic(DynamicTreesBWG.location("ebony"));
    public static final GrowthLogicKit REDWOOD = new RedwoodLogic(DynamicTreesBWG.location("redwood"));
    public static final GrowthLogicKit SMALL_REDWOOD = new SmallRedwoodLogic(DynamicTreesBWG.location("small_redwood"));
    public static final GrowthLogicKit BAOBAB = new BaobabLogic(DynamicTreesBWG.location("baobab"));
    public static final GrowthLogicKit VARIATE_HEIGHT = new VariateHeightLogic(DynamicTreesBWG.location("variate_height"));
    public static final GrowthLogicKit SYTHIAN_FUNGUS = new SythianLogic(DynamicTreesBWG.location("sythian_fungus"));
    public static final GrowthLogicKit CYPRESS = new CypressLogic(DynamicTreesBWG.location("cypress"));
    public static final GrowthLogicKit MANGROVE = new MangroveLogic(DynamicTreesBWG.location("mangrove"));
    public static final GrowthLogicKit TAPERED_WITHERED = new TaperedWitheredOakLogic(DynamicTreesBWG.location("tapered_withered"));
    public static final GrowthLogicKit ANCIENT_LOGIC = new AncientLogic(DynamicTreesBWG.location("ancient"));
    public static final GrowthLogicKit MEGA_RAINBOW_EUCALYPTUS = new MegaRainbowEucalyptusLogic(DynamicTreesBWG.location("mega_rainbow_eucalyptus"));
    public static final GrowthLogicKit WILLOW = new WillowLogic(DynamicTreesBWG.location("willow"));
    public static final GrowthLogicKit ARAUCARIA = new AraucariaLogic(DynamicTreesBWG.location("araucaria"));
    public static final GrowthLogicKit TWISTING = new TwistingTreeLogic(DynamicTreesBWG.location("twisting"));
    public static final GrowthLogicKit ETHER = new EtherTreeLogic(DynamicTreesBWG.location("ether"));
    public static final GrowthLogicKit ENCHANTED = new EnchantedTreeLogic(DynamicTreesBWG.location("enchanted"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(POPLAR, MAPLE, ASPEN, TAPERED, DIAGONAL_PALM,
                ZELKOVA, THIN_CONIFER, MEGA_PINE, EBONY, REDWOOD, SMALL_REDWOOD,
                BAOBAB, VARIATE_HEIGHT, SYTHIAN_FUNGUS, CYPRESS, MANGROVE, TAPERED_WITHERED,
                ANCIENT_LOGIC, MEGA_RAINBOW_EUCALYPTUS, WILLOW, ARAUCARIA, TWISTING, ETHER, ENCHANTED);
    }

}
