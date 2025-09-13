package maxhyper.dtbwg.mushroomshape;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictreesplus.systems.mushroomlogic.shapekits.MushroomShapeKit;
import maxhyper.dtbwg.DynamicTreesBWG;

public class BWGMushroomShapeKits {

    public static final MushroomShapeKit LINEAR_MUSHROOM_SHAPE = new LinearShape(DynamicTreesBWG.location("linear"));

    public static void register(final Registry<MushroomShapeKit> registry) {
        registry.register(LINEAR_MUSHROOM_SHAPE);
    }

}
