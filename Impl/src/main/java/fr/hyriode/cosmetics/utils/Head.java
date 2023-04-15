package fr.hyriode.cosmetics.utils;

import com.avaje.ebeaninternal.server.lib.util.NotFoundException;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public enum Head {

    WHITE_MINUS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNlNGI1MzNlNGJhMmRmZjdjMGZhOTBmNjdlOGJlZjM2NDI4YjZjYjA2YzQ1MjYyNjMxYjBiMjVkYjg1YiJ9fX0="),
    WHITE_PLUS("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBiNTVmNzQ2ODFjNjgyODNhMWMxY2U1MWYxYzgzYjUyZTI5NzFjOTFlZTM0ZWZjYjU5OGRmMzk5MGE3ZTcifX19"),
    ;

    private final String texture;

    Head(String texture) {
        this.texture = texture;
    }

    public static Head getByName(String name) {
        for (Head head : Head.values()) {
            if (head.name().equals(name)) {
                return head;
            }
        }
        throw new NotFoundException("Head with name " + name + " not found");
    }

    public String getTexture() {
        return this.texture;
    }

    public ItemStack asItem() {
        return this.asItemBuilder().build();
    }

    public ItemBuilder asItemBuilder() {
        return ItemBuilder.asHead().withHeadTexture(this.texture);
    }
}
