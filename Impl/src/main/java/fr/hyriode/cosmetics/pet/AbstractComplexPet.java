package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import net.minecraft.server.v1_8_R3.EntitySilverfish;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSilverfish;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffectType;

public abstract class AbstractComplexPet extends AbstractPetImpl {

    protected Silverfish referenceEntity;

    public AbstractComplexPet(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic);
        this.referenceEntity = (Silverfish) user.asBukkit().getWorld().spawnEntity(user.asBukkit().getLocation(), EntityType.SILVERFISH);
        this.setEntitySilent();

        referenceEntity.setTarget(user.asBukkit());
        referenceEntity.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
        referenceEntity.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));
        referenceEntity.setMetadata("COSMETICS-PET", new FixedMetadataValue(
                HyriCosmeticsPlugin.getProvidingPlugin(HyriCosmeticsPlugin.class),
                this.getUser().asBukkit().getName())
        );
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
        this.spawn();
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
        this.referenceEntity.remove();
        this.remove();
    }

    @Override
    protected void tick(CosmeticUser user) {
        final double distance = referenceEntity.getLocation().distance(getPlayer().getLocation());
        if (referenceEntity.getTarget() == null || !referenceEntity.getTarget().getName().equals(getPlayer().getName())) {
            referenceEntity.setTarget(getPlayer());
        }
        if ((isMoving() && distance > 2.5) || distance > 3) {
            if (referenceEntity.hasPotionEffect(PotionEffectType.SLOW)) {
                referenceEntity.removePotionEffect(PotionEffectType.SLOW);
            }
            if (distance > 12) {
                referenceEntity.teleport(getPlayer());
            }
            this.moveAnimationTick();
        } else {
            if (!referenceEntity.hasPotionEffect(PotionEffectType.SLOW)) {
                referenceEntity.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 255));
            }
            this.motionlessAnimationTick();
        }
    }

    public void setEntitySilent() {
        EntitySilverfish handle = ((CraftSilverfish) referenceEntity).getHandle();
        NBTTagCompound tag = new NBTTagCompound();
        handle.c(tag);
        tag.setBoolean("Silent", true);
        handle.f(tag);
    }

    @Override
    public Location getReferenceLocation() {
        return referenceEntity.getLocation();
    }
}
