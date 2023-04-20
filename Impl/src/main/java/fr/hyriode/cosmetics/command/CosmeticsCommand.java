package fr.hyriode.cosmetics.command;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.gui.CosmeticsMainGui;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.IHyrame;
import fr.hyriode.hyrame.command.CommandContext;
import fr.hyriode.hyrame.command.CommandInfo;
import fr.hyriode.hyrame.command.CommandUsage;
import fr.hyriode.hyrame.command.HyriCommand;
import fr.hyriode.hyrame.language.HyrameMessage;
import org.bukkit.entity.Player;

public class CosmeticsCommand extends HyriCommand<HyriCosmeticsPlugin> {

    public CosmeticsCommand(HyriCosmeticsPlugin plugin) {
        super(plugin, new CommandInfo("cosmetics")
                .withAliases("cosmetic", "c")
                .withDescription("Command to open the cosmetics menu")
                .withUsage(new CommandUsage().withStringMessage(player -> "/cosmetics")));
    }

    @Override
    public void handle(CommandContext ctx) {
        final Player player = ctx.getSender();

        if (IHyrame.get().getGameManager().getCurrentGame() != null) {
            player.sendMessage(HyrameMessage.PERMISSION_ERROR.asString(player));
            return;
        }

        CosmeticUser cosmeticUser = HyriCosmetics.get().getUserProvider().getUser(player);
        if (cosmeticUser.isUnequipping()) {
            player.sendMessage(HyrameMessage.PERMISSION_ERROR.asString(player));
            return;
        }

        new CosmeticsMainGui(player).open();
    }
}
