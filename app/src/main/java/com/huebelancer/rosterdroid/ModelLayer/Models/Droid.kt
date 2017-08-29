package com.huebelancer.rosterdroid.ModelLayer.Models

import com.huebelancer.rosterdroid.ModelLayer.Enums.Attributes
import com.huebelancer.rosterdroid.ModelLayer.Enums.PlayerType
import java.io.Serializable


class Droid(val name: String?, var strength: Int, var speed: Int, var agility: Int, var isStarter: Boolean) : Serializable {

    fun totalAttributes(): Int {
        return strength + speed + agility
    }

    fun playerType(): PlayerType {
        if (isStrong()) {
            return PlayerType.STRENGTH

        } else if(isAgile()) {
            return PlayerType.AGILE

        } else if (isSpeedy()) {
            return PlayerType.SPEED

        } else {
            return PlayerType.BALANCE
        }
    }


    fun isStrong(): Boolean {
        return (strength - speed) > 2 && (strength - agility) > 2
    }

    fun isSpeedy(): Boolean {
        return (speed - strength) > 2 && (speed - agility) > 2
    }

    fun isAgile(): Boolean {
        return (agility - speed) > 2 && (agility - strength) > 2
    }

    fun biggestWeakness(): Attributes? {
        when {
            strength <= agility && strength <= speed -> {
                return Attributes.STRENGTH
            }
            speed <= agility && speed <= strength -> {
                return Attributes.SPEED
            }
            agility <= speed && agility <= strength -> {
                return Attributes.AGILITY
            }
        }
        return null
    }

    fun biggestStrength(): Attributes? {
        when {
            strength >= agility && strength >= speed -> {
                return Attributes.STRENGTH
            }
            speed >= agility && speed >= strength -> {
                return Attributes.SPEED
            }
            agility >= speed && agility >= strength -> {
                return Attributes.AGILITY
            }
        }

        return null
    }
}