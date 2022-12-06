package greenminecraft.greenminecraft.handlers;

import greenminecraft.greenminecraft.GreenMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockHandler implements Listener {

    public BlockHandler(GreenMinecraft plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    String[] MessageBank = {
            "You dropped something important, rozzers should not see that on the floor.",
            "\"I have always loved marijuana. It has been a source of joy and comfort to me for many years. And I still think of it as a basic staple of life, along with beer and ice and grapefruits – and millions of Americans agree with me.\" – Hunter S. Thompson",
            "\"Of course I know how to roll a joint.\" – Martha Stewart",
            "\"Many artists and writers have used cannabis for creative stimulation – from the writers of the world's religious masterpieces to our most irreverent satirists.\" – Jack Herer",
            "\"When you smoke the herb, it reveals you to yourself.\" – Bob Marley",
            "\"It really puzzles me to see marijuana connected with narcotics ... dope and all that crap. It'’'s a thousand times better than whiskey – it's an assistant – a friend.\" – Louis Armstrong",
            "\"Some of my finest hours have been spent on my back veranda, smoking hemp and observing as far as my eye can see.\" – Thomas Jefferson",
            "\"I got high, and forgot I wasn't supposed to get high.\" – Ricky Williams"
    };

    @EventHandler(ignoreCancelled = false)
    public void onTorchPlace_normal(BlockPlaceEvent event) {
        Block block = event.getBlock();

        // return if unrelated to green stuff
        if (block.getType() != Material.HAY_BLOCK) {
            return;
        }

        if (event.getBlock().getType() == Material.HAY_BLOCK && event.getItemInHand().getItemMeta().getCustomModelData() == 1912345) {
            event.getBlock().setType(Material.AIR);
            event.getPlayer().sendMessage(ChatColor.GREEN + MessageBank[new Random().nextInt() % MessageBank.length]);
            ItemStack temp_stack = event.getItemInHand();

            event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(), temp_stack);
        }

    }
}
