package kr.ohurjon.codex.minigame.util

class Util : Default() {
    fun randomRange(n1: Int, n2: Int): Int {
        return (Math.random() * (n2 - n1 + 1)).toInt() + n1
    }
}