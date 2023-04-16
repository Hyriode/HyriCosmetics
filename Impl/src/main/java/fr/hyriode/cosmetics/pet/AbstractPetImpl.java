package fr.hyriode.cosmetics.pet;

import com.google.gson.Gson;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSilverfish;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractPetImpl extends AbstractPet {

    public AbstractPetImpl(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic, (Silverfish) user.asBukkit().getWorld().spawnEntity(user.asBukkit().getLocation(), EntityType.SILVERFISH));
        this.setEntitySilent();

        silverfish.setTarget(user.asBukkit());
        silverfish.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
        silverfish.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        super.onEquip(user);
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        super.onUnequip(user);
    }

    @Override
    protected void tick(CosmeticUser user) {
        final double distance = silverfish.getLocation().distance(getPlayer().getLocation());

        if ((isMoving() && distance > 2.5) || distance > 3) {
            if (silverfish.hasPotionEffect(PotionEffectType.SLOW)) {
                silverfish.removePotionEffect(PotionEffectType.SLOW);
            }
            if (distance > 12) {
                silverfish.teleport(getPlayer());
            }
            this.moveAnimationTick();
        } else {
            if (!silverfish.hasPotionEffect(PotionEffectType.SLOW)) {
                silverfish.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 255));
            }
            this.motionlessAnimationTick();
        }
    }

    protected abstract void moveAnimationTick();
    public abstract void motionlessAnimationTick();

    public void setEntitySilent() {
        EntitySilverfish handle = ((CraftSilverfish) silverfish).getHandle();
        NBTTagCompound tag = new NBTTagCompound();
        handle.c(tag);
        tag.setBoolean("Silent", true);
        handle.f(tag);
    }
}
