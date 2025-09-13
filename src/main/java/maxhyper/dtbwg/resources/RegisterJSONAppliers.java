package maxhyper.dtbwg.resources;

import com.dtteam.dynamictrees.deserialization.PropertyAppliers;
import com.dtteam.dynamictrees.event.ApplierRegistryEvent;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.google.gson.JsonElement;
import maxhyper.dtbwg.DynamicTreesBWG;
import maxhyper.dtbwg.trees.GenOnExtraSoilSpecies;
import maxhyper.dtbwg.trees.LamentSpecies;
import maxhyper.dtbwg.trees.ImbuedLogFamily;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = DynamicTreesBWG.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class RegisterJSONAppliers {

    @SubscribeEvent
    public static void registerAppliersSpecies(final ApplierRegistryEvent.Reload<Species, JsonElement> event) {
        registerSpeciesAppliers(event.getAppliers());
    }

    @SubscribeEvent
    public static void registerAppliersFamily(final ApplierRegistryEvent.Reload<Family, JsonElement> event) {
        registerFamilyAppliers(event.getAppliers());
    }

    @SubscribeEvent
    public static void registerAppliersCapProperties(final ApplierRegistryEvent.Reload<CapProperties, JsonElement> event) {
        registerCapPropertiesAppliers(event.getAppliers());
    }

    public static void registerSpeciesAppliers(PropertyAppliers<Species, JsonElement> appliers) {
        appliers.register("alternative_species", LamentSpecies.class, Species.class,
                LamentSpecies::setAltSpecies)
                .register("extra_soil_for_worldgen", GenOnExtraSoilSpecies.class, Block.class,
                GenOnExtraSoilSpecies::setExtraSoil)
                .register("soil_replacement_for_worldgen", GenOnExtraSoilSpecies.class, Block.class,
                GenOnExtraSoilSpecies::setSoilReplacement);
    }

    public static void registerFamilyAppliers(PropertyAppliers<Family, JsonElement> appliers) {
        appliers.register("primitive_imbued_log", ImbuedLogFamily.class, Block.class,
                ImbuedLogFamily::setPrimitiveImbuedLog);
    }

    public static void registerCapPropertiesAppliers(PropertyAppliers<CapProperties, JsonElement> appliers) {
//        appliers.register("shroomlight_block", WartyCapProperties.class, Block.class,
//                WartyCapProperties::setShroomlightBlock)
//                .register("shroomlight_above_place_chance", WartyCapProperties.class, Float.class,
//                        WartyCapProperties::setShroomlightUpChance)
//                .register("shroomlight_below_place_chance", WartyCapProperties.class, Float.class,
//                        WartyCapProperties::setShroomlightDownChance)
//                .register("shroomlight_requires_support", WartyCapProperties.class, Boolean.class,
//                        WartyCapProperties::setShroomlightRequireSupport);
    }

    @SubscribeEvent public static void registerAppliersSpecies(final ApplierRegistryEvent.GatherData<Species, JsonElement> event) { registerSpeciesAppliers(event.getAppliers()); }
    @SubscribeEvent public static void registerAppliersFamily(final ApplierRegistryEvent.GatherData<Family, JsonElement> event) { registerFamilyAppliers(event.getAppliers()); }

}