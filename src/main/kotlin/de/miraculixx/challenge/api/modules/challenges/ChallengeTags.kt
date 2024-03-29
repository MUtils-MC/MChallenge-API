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

    FREE,
    BETA,
    ADDON,
    ;

    companion object {
        fun getRotationFilter(): Array<ChallengeTags> {
            return arrayOf(FUN, MEDIUM, HARD, RANDOMIZER, FORCE, MULTIPLAYER, NO_FILTER)
        }
    }
}