package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractParticleImpl<T> extends AbstractParticle<T> {

    protected TaskNode task;
    protected String variant;

    protected final Map<String, Pair<ItemStack, T>> variants;
    private final boolean hasVariants;

    public AbstractParticleImpl(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        super(user, cosmetic);
        this.variant = getDefaultVariant();
        this.variants = initVariants();
        this.hasVariants = hasVariants;
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
    }

    public abstract void tick(final CosmeticUser user);

    @Override
    public void setVariant(String variant) {
        this.variant = variant;
        this.updateVariant();
    }

    @Override
    public String getVariant() {
        return this.variant;
    }

    @Override
    public Map<String, ItemStack> getVariantsItem() {
        return variants.entrySet().stream().collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().getKey()), Map::putAll);
    }

    @Override
    public Map<String, T> getVariants() {
        return variants.entrySet().stream().collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().getValue()), Map::putAll);
    }

    @Override
    public T getVariant(String variant) {
        return variants.get(variant).getValue();
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public boolean hasVariants() {
        return hasVariants;
    }

    @Override
    public String getDefaultVariant() {
        return null;
    }

    @Override
    public void updateVariant() {}

    @Override
    public Map<String, Pair<ItemStack, T>> initVariants() {
        return Collections.emptyMap();
    }
}
