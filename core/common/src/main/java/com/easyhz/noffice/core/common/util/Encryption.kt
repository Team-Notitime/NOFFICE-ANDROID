package com.easyhz.noffice.core.common.util

import android.util.Base64
import com.easyhz.noffice.core.common.BuildConfig
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object Encryption {

    private val secretKeySpec = SecretKeySpec(BuildConfig.ENCRYPTION_KEY.toByteArray(Charsets.UTF_8), "AES")

    fun encrypt(text: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        val encryptedBytes = cipher.doFinal(text.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    fun decrypt(encryptedText: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
        val decryptedBytes = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT))
        return String(decryptedBytes, Charsets.UTF_8)
    }
}