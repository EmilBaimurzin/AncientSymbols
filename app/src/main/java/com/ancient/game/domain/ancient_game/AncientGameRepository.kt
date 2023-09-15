package com.ancient.game.domain.ancient_game

import com.ancient.game.core.library.random
import com.ancient.game.domain.ancient_game.adapter.GameItem
import kotlin.random.Random

class AncientGameRepository {
    fun generateList(): List<GameItem> {
        val listToReturn = mutableListOf<GameItem>()
        repeat(36) {
            listToReturn.add(GameItem(Random.nextInt(), 1 random 8))
        }
        listToReturn[0].imgValue = null
        listToReturn[5].imgValue = null
        listToReturn[35].imgValue = null
        listToReturn[30].imgValue = null
        return listToReturn
    }

    fun getRandomItem(): GameItem {
        return GameItem(Random.nextInt(), 1 random 8)
    }
}