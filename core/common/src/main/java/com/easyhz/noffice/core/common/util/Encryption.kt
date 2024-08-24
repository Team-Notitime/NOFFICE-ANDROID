package com.easyhz.noffice.core.common.util

import android.util.Base64
import android.util.Log
import com.easyhz.noffice.core.common.BuildConfig
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Encryption {

    private const val TAG = "[Encryption object]"
    private val secretKeySpec = SecretKeySpec(BuildConfig.ENCRYPTION_KEY.toByteArray(Charsets.UTF_8), "AES")

    fun encrypt(text: String): String? = try {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = generateIV()
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
        val encryptedBytes = cipher.doFinal(text.toByteArray(Charsets.UTF_8))

        val combined = iv + encryptedBytes
        Base64.encodeToString(combined, Base64.URL_SAFE)
    } catch (e: Exception) {
        Log.e(TAG, "encrypt - $e")
        null
    }

    fun decrypt(encryptedText: String): String? = try {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        val decodedBytes = Base64.decode(encryptedText, Base64.URL_SAFE)

        val iv = decodedBytes.copyOfRange(0, 16)
        val encryptedBytes = decodedBytes.copyOfRange(16, decodedBytes.size)

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(iv))
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        String(decryptedBytes, Charsets.UTF_8)
    } catch (e: Exception) {
        Log.e(TAG, "decrypt - $e")
        null
    }

    private fun generateIV(): ByteArray {
        val iv = ByteArray(16)
        SecureRandom().nextBytes(iv)
        return iv
    }
}