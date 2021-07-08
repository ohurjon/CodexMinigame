package kr.ohurjon.codex.minigame

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.MetadataValue

class Npc(private var location : Location) {
    val npc : Entity = location.world.spawnEntity(location,EntityType.VILLAGER)
    init {
        (npc as LivingEntity).setAI(false)
        npc.customName = "CODEX NPC"
    }
}