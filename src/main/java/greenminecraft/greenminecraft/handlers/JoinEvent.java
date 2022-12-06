package greenminecraft.greenminecraft.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack("https://www.dropbox.com/scl/fo/hjk3mdkbc6gawu14d8h7l/h?dl=1&rlkey=jwmr9z8nerdnpgytz1va5pt62");
    }

}
