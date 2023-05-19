package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.DefaultCosmetics;
import fr.hyriode.cosmetics.pet.AbstractMinionPet;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;

public class GhostPet extends AbstractMinionPet<Color> {

    public GhostPet(CosmeticUser user) {
        super(user, DefaultCosmetics.GHOST, false);
    }

    @Override
    protected void spawn() {
        minion.setHelmet(Head.GHOST.asItem());
        minion.setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(Color.WHITE).build());
        minion.setVisible(false);
        this.updateVariant();
    }

    @Override
    protected void remove() {
    }

    @Override
    protected void moveAnimationTick() {
        this.motionlessAnimationTick();
    }

    @Override
    public void motionlessAnimationTick() {
        minion.teleport(this.getReferenceLocation());
    }

}
