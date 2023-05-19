package fr.hyriode.cosmetics.command;

import fr.hyriode.api.HyriAPI;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.player.model.IHyriTransaction;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.transaction.CosmeticTransaction;
import fr.hyriode.hyrame.command.CommandContext;
import fr.hyriode.hyrame.command.CommandInfo;
import fr.hyriode.hyrame.command.CommandUsage;
import fr.hyriode.hyrame.command.HyriCommand;
import fr.hyriode.hyrame.language.HyrameMessage;
import org.bukkit.entity.Player;

public class CosmeticGiveCommand extends HyriCommand<HyriCosmeticsPlugin> {

    public CosmeticGiveCommand(HyriCosmeticsPlugin plugin) {
        super(plugin, new CommandInfo("cgive")
                .withAliases("cosmeticgive")
                .withDescription("Give a cosmetic to a player")
                .withUsage(new CommandUsage().withStringMessage(player -> "/cgive <player> <cosmetic>")));
    }

    @Override
    public void handle(CommandContext ctx) {
        final Player player = ctx.getSender();

        if (!IHyriPlayer.get(player.getUniqueId()).getRank().isSuperior(StaffRank.ADMINISTRATOR)) {
            player.sendMessage(HyrameMessage.PERMISSION_ERROR.asString(player));
            return;
        }

        ctx.registerArgument("%player% %input%", output -> {
            final IHyriPlayer target = output.get(IHyriPlayer.class);
            final String comseticId = output.get(String.class);

            final CosmeticInfo cosmetic = HyriCosmetics.get().getRegistry().getCosmetic(comseticId);
            if (cosmetic == null) {
                player.sendMessage("§cCosmetic not found");
                return;
            }

            if (target.getTransactions().getAll(CosmeticTransaction.TYPE) != null) {
                for (final IHyriTransaction transaction : target.getTransactions().getAll(CosmeticTransaction.TYPE)) {
                    if (transaction.loadContent(new CosmeticTransaction()).getCosmeticId().equals(cosmetic.getId())) {
                        player.sendMessage("§cThis player already has this cosmetic");
                        return;
                    }
                }
            }

            final IHyriPlayer account = HyriAPI.get().getPlayerManager().getPlayer(target.getUniqueId());
            account.getTransactions().add(CosmeticTransaction.TYPE, new CosmeticTransaction(cosmetic.getId()));
            account.update();
            player.sendMessage("§aCosmetic given to " + target.getName() + " : " + cosmetic);
        });

        super.handle(ctx);
    }
}
