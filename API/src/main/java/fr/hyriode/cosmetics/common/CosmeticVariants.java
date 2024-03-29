package fr.hyriode.cosmetics.common;

import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface CosmeticVariants<T> {

    boolean hasVariants();

    String getDefaultVariant();

    void setVariant(String variant);

    String getVariant();

    void updateVariant();

    void reloadVariants();

    Map<String, Pair<ItemStack, T>> initVariants();

    Map<String, ItemStack> getVariantsItem();

    Map<String, T> getVariants();

    T getVariant(String variant);
}
