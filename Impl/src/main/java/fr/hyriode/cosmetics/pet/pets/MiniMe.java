package fr.hyriode.cosmetics.pet.pets;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.pet.AbstractMinionPet;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.game.team.HyriGameTeamColor;
import fr.hyriode.hyrame.item.ItemBuilder;
import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

public class MiniMe extends AbstractMinionPet<Color> {

    private Color color;

    public MiniMe(CosmeticUser user) {
        super(user, Cosmetic.MINI_ME, true);
    }

    @Override
    protected void spawn() {
        minion.setHelmet(ItemBuilder.asHead().withPlayerHead(getPlayer().getUniqueId()).build());
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

    @Override
    public String getDefaultVariant() {
        return "red";
    }

    @Override
    public void updateVariant() {
        color = getVariants().get(variant);
        minion.setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).withLeatherArmorColor(color).build());
        minion.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).withLeatherArmorColor(color).build());
        minion.setBoots(new ItemBuilder(Material.LEATHER_BOOTS).withLeatherArmorColor(color).build());
    }

    @Override
    public Map<String, Pair<ItemStack, Color>> initVariants() {
        final Map<String, Pair<ItemStack, Color>> resultMap = new LinkedHashMap<>();
        for (HyriGameTeamColor color : HyriGameTeamColor.values()) {
            this.addVariantColor(resultMap, color);
        }
        return resultMap;
    }

    @SuppressWarnings("deprecation")
    private void addVariantColor(Map<String, Pair<ItemStack, Color>> variantsMap, HyriGameTeamColor color) {
        variantsMap.put(color.name().toLowerCase(),
                new Pair<>(
                        new ItemBuilder(Material.INK_SACK).withName(color.getChatColor() + color.getDisplay().getValue(getPlayer())).withData(color.getDyeColor().getDyeData()).build(),
                        color.getDyeColor().getColor()
                )
        );
    }
}
