package kr.ohurjon.codex.minigame

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity


class Npc(location : Location) {
    val npc : Entity = location.world.spawnEntity(location,EntityType.VILLAGER)
    init {
        (npc as LivingEntity).setAI(false)
        npc.customName = "CODEX NPC"
    }
}