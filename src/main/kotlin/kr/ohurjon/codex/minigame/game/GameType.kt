package kr.ohurjon.codex.minigame.game

import kr.ohurjon.codex.minigame.CODEX
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

enum class GameType(val title: String, val item : ItemStack, val index : Int) {
    USIGN("Unsigned", ItemStack(Material.AIR),0),
    JUMP("Jump", ItemStack(Material.GRASS),10),
    SHULKER("Shulker", ItemStack(Material.SHULKER_SHELL),13),
    TAKGU("Takgu", ItemStack(Material.FIREBALL),16);

    fun getGuiItem() : ItemStack {
        val item = item
        val data = item.itemMeta
        data.displayName = "§e" + getName()
        item.itemMeta = data

        return item
    }

    fun getName() : String{
        val plugin = CODEX.instance
        val config = plugin.config

        return config.getString(title+".name")
    }

    fun getMaxTick() : Long {
        val plugin = CODEX.instance
        val config = plugin.config
        return config.getLong(title+".tick")
    }

}

