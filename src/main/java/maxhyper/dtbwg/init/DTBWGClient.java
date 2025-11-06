package maxhyper.dtbwg.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class DTBWGClient {

    @SuppressWarnings("deprecation")
    public static void setup() {
        for (Block block : new Block[]{DTBWGRegistries.WITCH_HAZEL_BRANCH.get()
                //,DTBWGRegistries.ARISIAN_BLOOM_BRANCH.get(), DTBWGRegistries.EMBUR_GEL_BRANCH.get(),
                //DTBWGRegistries.IMPARIUS_MUSHROOM_BRANCH.get(), DTBWGRegistries.FUNGAL_IMPARIUS_FILAMENT.get()
                }){
            ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        }
//        Block embur_sapling = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtbwg","embur_sapling"));
//        if (embur_sapling != null)
//            ItemBlockRenderTypes.setRenderLayer(embur_sapling, RenderType.translucent());
//        Block embur_gel = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtbwg","embur_gel"));
//        Block embur_gel_center = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtbwg","embur_gel_center"));
//        if (embur_gel != null && embur_gel_center != null){
//            ItemBlockRenderTypes.setRenderLayer(embur_gel, RenderType.translucent());
//            ItemBlockRenderTypes.setRenderLayer(embur_gel_center, RenderType.translucent());
//        }
    }

}
