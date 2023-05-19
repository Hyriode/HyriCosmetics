package fr.hyriode.cosmetics.balloon;

import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by AstFaster
 * on 19/05/2023 at 14:03
 */
public class BalloonHook {

    public static void hook() {
        try {
            ArrayList array = new ArrayList();
            Field[] field;
            int j = (field = EntityTypes.class.getDeclaredFields()).length;

            for(int i = 0; i < j; i++) {
                Field f = field[i];
                if(f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    array.add(f.get(null));
                }
            }

            if(((Map)array.get(2)).containsKey((int) EntityType.SLIME.getTypeId())) {
                ((Map)array.get(0)).remove("HyriCosmetics");
                ((Map)array.get(2)).remove((int) EntityType.SLIME.getTypeId());
            }

            Method m = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, Integer.TYPE);
            m.setAccessible(true);
            m.invoke(null, CustomBalloonEntity.class, "HyriCosmetics", (int) EntityType.SLIME.getTypeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
