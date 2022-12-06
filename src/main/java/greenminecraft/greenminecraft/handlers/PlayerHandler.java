package greenminecraft.greenminecraft.handlers;

import greenminecraft.greenminecraft.GreenMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerHandler implements Listener {

    public PlayerHandler(GreenMinecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPoweredFoodEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        // if chomped a green cookie
        if (e.getItem().getItemMeta().getCustomModelData() == 5319009) {
            // does not increase hunger bar
            p.setFoodLevel(p.getFoodLevel() - 2);
            Random rn = new Random();
            int result = rn.nextInt() % 100;
            if (rn.nextInt() % 100 % 2 == 0) {
                PotionEffect amp = p.getPotionEffect(PotionEffectType.HUNGER);
                if (amp != null) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, amp.getDuration() + 150, 4), true);
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 150, 4), true);
                }
            }
            if (result % 3 > 0 || p.hasPotionEffect(PotionEffectType.CONFUSION)) {
                PotionEffect amp = p.getPotionEffect(PotionEffectType.CONFUSION);
                if (amp != null) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, amp.getDuration() + 200, amp.getAmplifier() + 1));
                    if (amp.getAmplifier() + 1 > 4) {
                        // too dizzy, you just go blank
                        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, p.getPotionEffect(PotionEffectType.CONFUSION).getDuration() / 2, amp.getAmplifier()), true);
                    }
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1));
                }
            }
            if (result % 4 == 0) {
                // you blank out for a moment
                p.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 20, 1));
            }
            if (result % 4 == 1) {
                // you are able to hit harder for 5 seconds
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 6));
            }
            if (result % 4 > 3) {
                // you are drowsy, so digs slower
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 1));
            }
        }
    }
}
