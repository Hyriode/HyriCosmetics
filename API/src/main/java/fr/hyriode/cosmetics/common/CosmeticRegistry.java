package fr.hyriode.cosmetics.common;

import fr.hyriode.cosmetics.user.CosmeticUser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by AstFaster
 * on 19/05/2023 at 13:22
 */
public interface CosmeticRegistry {

    @SuppressWarnings("all")
    void registerCosmetic(CosmeticInfo cosmeticInfo, Function<CosmeticUser, AbstractCosmetic> handlerProvider);

    CosmeticInfo getCosmetic(String id);

    @SuppressWarnings("all")
    <T extends AbstractCosmetic> T createCosmetic(String id, CosmeticUser user);

    Map<CosmeticCategory, List<CosmeticInfo>> getCosmetics();

    List<CosmeticInfo> getCosmetics(CosmeticCategory category);

    List<CosmeticInfo> getFilteredCosmetics(CosmeticUser user, CosmeticCategory category);

    Set<CosmeticCategory> getCategories();

}
