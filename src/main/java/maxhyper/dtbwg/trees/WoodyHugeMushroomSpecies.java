package maxhyper.dtbwg.trees;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.systems.nodemapper.NetVolumeNode;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import com.dtteam.dynamictreesplus.tree.HugeMushroomSpecies;
import net.minecraft.resources.ResourceLocation;

public class WoodyHugeMushroomSpecies extends HugeMushroomSpecies {

    public static final TypedRegistry.EntryType<Species> TYPE = createDefaultMushroomType(WoodyHugeMushroomSpecies::new);

    public WoodyHugeMushroomSpecies(ResourceLocation name, Family family, CapProperties capProperties) {
        super(name, family, capProperties);
    }

    public LogsAndSticks getLogsAndSticks(NetVolumeNode.Volume volume, boolean silkTouch, int fortuneLevel) {
        return super.getLogsAndSticks(volume, true, fortuneLevel);
    }

}
