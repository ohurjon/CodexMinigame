package kr.ohurjon.codex.minigame.games

import org.bukkit.Material
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack

enum class GameType(val title: String, val item : ItemStack, val index : Int) {
    USIGN("Unsigned", ItemStack(Material.AIR),0),
    JUMP("Jump", ItemStack(Material.GRASS),10),
    SHULKER("Shulker", ItemStack(Material.SHULKER_SHELL),13),
    TAKGU("Takgu", ItemStack(Material.FIREBALL),16);

    @JvmName("getItem1")
    fun getItem() : ItemStack {
        val item = item
        val data = item.itemMeta
        data.displayName = title
        item.itemMeta = data

        return item
    }

}

