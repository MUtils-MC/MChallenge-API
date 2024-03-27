package de.miraculixx.challenge.api.settings

import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    var debug: Boolean = false,
    var language: String = "en_US",
    var gui: SettingsGUI = SettingsGUI(),
    var reset: Boolean = false,
    val worlds: MutableSet<String> = mutableSetOf()
)

@Serializable
data class SettingsGUI(
    var textTheme: String = "default",
    var itemTheme: String = "default",
    var compact: Boolean = false
)
