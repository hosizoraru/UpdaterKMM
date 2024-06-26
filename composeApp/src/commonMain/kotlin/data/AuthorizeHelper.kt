package data

import kotlinx.serialization.Serializable

@Serializable
data class AuthorizeHelper(
    val description: String? = null,
    val location: String? = null,
    val nonce: Long? = null,
    val result: String? = null,
    val ssecurity: String? = null,
    val userId: Long? = null,
)