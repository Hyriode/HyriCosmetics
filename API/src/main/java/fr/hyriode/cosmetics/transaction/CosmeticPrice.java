package fr.hyriode.cosmetics.transaction;

import fr.hyriode.api.money.IHyriMoney;
import fr.hyriode.api.player.IHyriPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by AstFaster
 * on 19/05/2023 at 12:28
 */
public class CosmeticPrice {

    private Map<Currency, Integer> values = new HashMap<>();

    public CosmeticPrice newValue(Currency currency, int value) {
        this.values.put(currency, value);
        return this;
    }

    public int getValue(Currency currency) {
        return this.values.getOrDefault(currency, -1);
    }

    public Map<Currency, Integer> getValues() {
        return this.values;
    }

    public CosmeticPrice withValues(Map<Currency, Integer> values) {
        this.values = values;
        return this;
    }

    public boolean empty() {
        return this.values.isEmpty();
    }

    public enum Currency {

        HYRIS(IHyriPlayer::getHyris),
        HYODES(IHyriPlayer::getHyodes);

        private final Function<IHyriPlayer, IHyriMoney> moneyProvider;

        Currency(Function<IHyriPlayer, IHyriMoney> moneyProvider) {
            this.moneyProvider = moneyProvider;
        }

        public boolean hasAmount(IHyriPlayer player, long amount) {
            return this.moneyProvider.apply(player).hasEnough(amount);
        }

        public void removeAmount(IHyriPlayer player, long amount) {
            this.moneyProvider.apply(player).remove(amount);
        }

    }

}
