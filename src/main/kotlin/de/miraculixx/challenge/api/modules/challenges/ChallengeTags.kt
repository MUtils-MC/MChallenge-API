package de.miraculixx.challenge.api.modules.challenges

enum class ChallengeTags {
    NO_FILTER,

    //CHALLENGE FILTER
    FUN,
    MEDIUM,
    HARD,
    RANDOMIZER,
    FORCE,
    MULTIPLAYER,

    // HARD FILTER
    ADDON,
    PREMIUM,
    FREE,
    BETA,

    ;

    companion object {
        private val rotationFilter = arrayOf(FUN, MEDIUM, HARD, RANDOMIZER, FORCE, MULTIPLAYER)
        fun getRotationFilter() = rotationFilter
    }
}