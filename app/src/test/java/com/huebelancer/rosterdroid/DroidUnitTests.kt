package com.huebelancer.rosterdroid

import com.huebelancer.rosterdroid.ModelLayer.Enums.PlayerType
import com.huebelancer.rosterdroid.ModelLayer.Models.Droid
import org.junit.Test

import org.junit.Assert.*


class DroidUnitTests {
    @Test
    fun droid_isSpeedy_returnsTrue() {
        val droid = Droid("name", 7, 10, 7, true)
        assertEquals(droid.isSpeedy(), true)
    }

    @Test
    fun droid_isStrong_returnsTrue() {
        val droid = Droid("name", 10, 7, 7, true)
        assertEquals(droid.isStrong(), true)
    }

    @Test
    fun droid_isAgile_returnsTrue() {
        val droid = Droid("name", 7, 7, 10, true)
        assertEquals(droid.isAgile(), true)
    }

    @Test
    fun droid_biggestWeakness_returnsNotNullAttribute() {
        val droid = Droid("name", 7, 7, 10, true)
        assertNotNull(droid.biggestWeakness())
    }

    @Test
    fun droid_biggestStrength_returnsNotNullAttribute() {
        val droid = Droid("name", 7, 7, 10, true)
        assertNotNull(droid.biggestStrength())
    }

    @Test
    fun droid_playerType_returnsBalancedPlayerType() {
        val droid = Droid("name", 7, 7, 8, true)
        assertEquals(droid.playerType(), PlayerType.BALANCE)
    }
}