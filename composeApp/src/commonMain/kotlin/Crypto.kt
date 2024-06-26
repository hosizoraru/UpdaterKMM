import dev.whyoleg.cryptography.DelicateCryptographyApi
import dev.whyoleg.cryptography.algorithms.symmetric.AES
import io.ktor.utils.io.core.String
import io.ktor.utils.io.core.toByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

val iv = "0102030405060708".toByteArray()

expect suspend fun miuiCipher(securityKey: ByteArray): AES.CBC.Cipher

@OptIn(DelicateCryptographyApi::class, ExperimentalEncodingApi::class)
suspend fun miuiEncrypt(jsonRequest: String, securityKey: ByteArray): String {
    val cipher = miuiCipher(securityKey)
    val encrypted = cipher.encrypt(iv, jsonRequest.toByteArray())
    return Base64.UrlSafe.encode(encrypted)
}

@OptIn(DelicateCryptographyApi::class, ExperimentalEncodingApi::class)
suspend fun miuiDecrypt(encryptedText: String, securityKey: ByteArray): String {
    val cipher = miuiCipher(securityKey)
    val encryptedTextBytes = Base64.Mime.decode(encryptedText)
    val decryptedTextBytes = cipher.decrypt(iv, encryptedTextBytes)
    return String(decryptedTextBytes)
}