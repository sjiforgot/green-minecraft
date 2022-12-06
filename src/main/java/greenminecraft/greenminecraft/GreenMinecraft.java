package greenminecraft.greenminecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import greenminecraft.greenminecraft.handlers.JoinEvent;
import greenminecraft.greenminecraft.handlers.BlockHandler;
import greenminecraft.greenminecraft.handlers.PlayerHandler;

import java.util.Arrays;

public final class GreenMinecraft extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Hello World");
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);

        new BlockHandler(this);
        new PlayerHandler(this);
        grassRecipe();
        Bukkit.getLogger().info("Green Minecraft ready");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting down");
    }

    public void grassRecipe() {
        /* weed, or bake grass*/
        ItemStack baked_grass = new ItemStack(Material.WHEAT);
        ItemMeta grassMeta = baked_grass.getItemMeta();
        grassMeta.setCustomModelData(1912234);
        grassMeta.setDisplayName(ChatColor.GREEN + "Baked Grass");
        grassMeta.setLore(Arrays.asList("GREEN-out time", "right?"));
        baked_grass.setItemMeta(grassMeta);

        NamespacedKey key = new NamespacedKey(this, "baked_grass");
        FurnaceRecipe weedCookingRecipeF = new FurnaceRecipe(key, baked_grass, Material.GRASS, 1.0f, 20);
        Bukkit.addRecipe(weedCookingRecipeF);

        /* Block of green, aka stash */
        ItemStack weed_block = new ItemStack(Material.HAY_BLOCK);
        ItemMeta weed_blockMeta = weed_block.getItemMeta();
        weed_blockMeta.setDisplayName(ChatColor.GREEN + "Stash");
        weed_blockMeta.setLore(Arrays.asList("large", "GREEN", "box"));
        weed_blockMeta.setCustomModelData(1912345);
        weed_block.setItemMeta(weed_blockMeta);

        /* Crafting recipe for stash */
        ShapedRecipe weed_blockShapedRecipe = new ShapedRecipe(new NamespacedKey(this, "one_green_block"), weed_block);
        weed_blockShapedRecipe.shape("XXX");
        weed_blockShapedRecipe.setIngredient('X', new RecipeChoice.ExactChoice(baked_grass));

        ItemStack double_weed_block = weed_block.clone();
        double_weed_block.setAmount(2);
        ShapedRecipe weed_blockMShapedRecipe = new ShapedRecipe(new NamespacedKey(this, "two_green_blocks"), double_weed_block);
        weed_blockMShapedRecipe.shape("XXX", "XXX");
        weed_blockMShapedRecipe.setIngredient('X', new RecipeChoice.ExactChoice(baked_grass));

        ItemStack triple_weed_block = weed_block.clone();
        triple_weed_block.setAmount(3);
        ShapedRecipe weed_blockLShapedRecipe = new ShapedRecipe(new NamespacedKey(this, "three_green_blocks"), triple_weed_block);
        weed_blockLShapedRecipe.shape("XXX", "XXX", "XXX");
        weed_blockLShapedRecipe.setIngredient('X', new RecipeChoice.ExactChoice(baked_grass));

        ItemStack weed_blockReverse = new ItemStack(baked_grass);
        weed_blockReverse.setAmount(3);
        ShapedRecipe baked_grassShapedRecipe = new ShapedRecipe(new NamespacedKey(this, "reverse_green_block"), weed_blockReverse);
        baked_grassShapedRecipe.shape("X");
        baked_grassShapedRecipe.setIngredient('X', new RecipeChoice.ExactChoice(weed_block));

        Bukkit.addRecipe(weed_blockShapedRecipe);
        Bukkit.addRecipe(baked_grassShapedRecipe);
        Bukkit.addRecipe(weed_blockMShapedRecipe);
        Bukkit.addRecipe(weed_blockLShapedRecipe);

        /* Green Cookie */
        ItemStack green_cookie = new ItemStack(Material.COOKIE);
        ItemMeta green_cookieMeta = green_cookie.getItemMeta();
        green_cookieMeta.setDisplayName(ChatColor.GREEN + "Green Cookie");
        green_cookieMeta.setLore(Arrays.asList("Cookies are for closers", "This is the spiced up version"));
        green_cookieMeta.setCustomModelData(5319009);
        green_cookieMeta.addEnchant(Enchantment.KNOCKBACK, 6, true);
        green_cookie.setItemMeta(green_cookieMeta);

        /* Crafting recipe for cookie */
        ShapedRecipe green_cookieRecipe = new ShapedRecipe(new NamespacedKey(this, "green_cookie"), green_cookie);
        green_cookieRecipe.shape("W W", "XCX", "W W");
        green_cookieRecipe.setIngredient('X', new RecipeChoice.ExactChoice(baked_grass));
        green_cookieRecipe.setIngredient('C', Material.COCOA_BEANS);
        green_cookieRecipe.setIngredient('W', Material.WHEAT);
        Bukkit.addRecipe(green_cookieRecipe);
        Bukkit.getLogger().info("Items added");
    }
}
