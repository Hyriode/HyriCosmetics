package fr.hyriode.cosmetics.common;

import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface CosmeticVariants<T> {

    String getDefaultVariant();

    void setVariant(String variant);

    String getVariant();

    void updateVariant();

    Map<String, Pair<ItemStack, T>> initVariants();

    Map<String, ItemStack> getVariantsItem();

    Map<String, T> getVariants();

    T getVariant(String variant);
}
