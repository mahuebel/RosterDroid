package com.huebelancer.rosterdroid.Helpers


class Constants {

    companion object {
        val SALARY_CAP = 175
        val ATTR_STRENGTH = "strength"
        val ATTR_AGILITY  = "agility"
        val ATTR_SPEED    = "speed"

        val DROID_NAMES = listOf<String>(
                "Speakr", "Andro", "Roidand", "Nando", "Star",
                "D'Roid", "Andy",  "Beepr",   "Buzz",  "Ringr",
                "Frag",   "Oid",   "Andrew",  "Albot", "Botoid"
        )

        val STRENGTHS = listOf<String>(
                "· Can calculate a million digits of pi in the blink of an eye\n",
                "· Has multiple ring modes, including vibrate\n",
                "· Has a date with a new model every year\n",
                "· Possesses long battery life \n",
                "· Never missed a game due to injury\n",
                "· Great communicator\n",
                "· Plays with alertness and notifies others when necessary\n"
        )

        val WEAKNESSES = listOf<String>(
                "· Afraid of water and heights\n",
                "· Poor instructions can make him freeze under pressure\n",
                "· He has a limited amount of memory with which to work\n",
                "· Can sometimes get lost out there on the field\n",
                "· Sometimes he comes out a bit flat\n",
                "· You can never be sure which version of him you'll get from day to day\n",
                "· Can't take a hit without proper padding\n"
        )
    }

}