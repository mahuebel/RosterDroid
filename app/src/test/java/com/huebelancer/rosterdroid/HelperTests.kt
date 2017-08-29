package com.huebelancer.rosterdroid

import com.huebelancer.rosterdroid.Helpers.Helpers
import com.huebelancer.rosterdroid.ModelLayer.Enums.Attributes
import org.junit.Test
import org.junit.Assert.*

class HelperTests {
    @Test
    fun allocatedCapRoom_returnsInt() {
        assertEquals(
                Helpers.allocatedCapRoom(175, 15) is Int,
                true
        )
    }

    @Test
    fun attributeToString_returnsNotEmptyString() {
        assertNotEquals(
                Helpers.attributeToString(Attributes.STRENGTH),
                ""
        )
    }
}