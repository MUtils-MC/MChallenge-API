package de.miraculixx.challenge.api.utils

import kotlinx.serialization.Serializable

/**
 * Define a constant icon for a challenge
 * @param material The material key (Paper - Material, Fabric - Items)
 * @param texture The custom head style for player heads in base64 format
 * @param naming The name and lore for the icon
 */
@Serializable
data class Icon(
    val material: String,
    val texture: String? = null,
    val naming: IconNaming? = null,
)