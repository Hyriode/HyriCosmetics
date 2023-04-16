package fr.hyriode.cosmetics.command;

import fr.hyriode.cosmetics.HyriCosmeticsPlugin;
import fr.hyriode.cosmetics.gui.CosmeticsMainGui;
import fr.hyriode.hyrame.IHyrame;
import fr.hyriode.hyrame.command.HyriCommand;
import fr.hyriode.hyrame.command.HyriCommandContext;
import fr.hyriode.hyrame.command.HyriCommandInfo;
import fr.hyriode.hyrame.language.HyrameMessage;
import org.bukkit.entity.Player;

public class CosmeticsCommand extends HyriCommand<HyriCosmeticsPlugin> {

    public CosmeticsCommand(HyriCosmeticsPlugin plugin) {
        super(plugin, new HyriCommandInfo("cosmetics")
                .withAliases("cosmetic", "c")
                .withDescription("Command to open the cosmetics menu")
                .withUsage("/cosmetics"));
    }

    @Override
    public void handle(HyriCommandContext ctx) {
        final Player player = (Player) ctx.getSender();

        if (IHyrame.get().getGameManager().getCurrentGame() != null) {
            player.sendMessage(HyrameMessage.PERMISSION_ERROR.asString(player));
            return;
        }

        new CosmeticsMainGui(player).open();
    }
}
