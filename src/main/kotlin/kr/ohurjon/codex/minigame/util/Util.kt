package kr.ohurjon.codex.minigame.util

import com.google.gson.stream.JsonWriter
import kr.ohurjon.codex.minigame.CODEX
import kr.ohurjon.codex.minigame.leaderboard.LeaderBoardManager
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import java.io.FileWriter
import java.io.Writer


class Util : Default() {
    fun randomRange(n1: Int, n2: Int): Int {
        return (Math.random() * (n2 - n1 + 1)).toInt() + n1
    }

    fun ArmorStand(text : String,location : Location) : ArmorStand{
        val armorstand = location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorstand.setGravity(false)
        armorstand.setCustomName(ChatColor.translateAlternateColorCodes('&', text))
        armorstand.setCustomNameVisible(true)
        armorstand.setVisible(false)

        CODEX.armorstands.add(armorstand)
        return armorstand
    }
}