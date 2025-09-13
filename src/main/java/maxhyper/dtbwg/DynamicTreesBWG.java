package maxhyper.dtbwg;

import com.dtteam.dynamictrees.api.registry.RegistryHandler;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictrees.treepack.Resources;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import maxhyper.dtbwg.init.DTBWGClient;
import maxhyper.dtbwg.init.DTBWGRegistries;
import maxhyper.dtbwg.init.SideBranchPlaceEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DynamicTreesBWG.MOD_ID)
public class DynamicTreesBWG
{
    public static final String MOD_ID = "dtbwg";

    public DynamicTreesBWG(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);
//        VegetationReplacement.register(eventBus);

        // NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(SideBranchPlaceEventHandler.class);

        //MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, DTBWGRegistries::onBiomeLoading);

        NeoForgeRegistryHandler.setup(MOD_ID, modEventBus);
        DTBWGRegistries.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DTBWGRegistries.setupBlocks();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DTBWGClient.setup();
    }

    private void gatherData(final GatherDataEvent event) {
        Resources.MANAGER.gatherData();
        GatherDataHelper.gatherAllData(
                MOD_ID,
                event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY,
                CapProperties.REGISTRY
        );
    }

    public static ResourceLocation location(final String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
