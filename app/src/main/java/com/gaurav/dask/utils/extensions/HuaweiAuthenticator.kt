package com.gaurav.dask.utils.extensions

import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec

import kotlin.random.Random

private const val RESERVED = "Reserved"
private const val SUFFIX = "CTC"
private const val SEPARATOR = "$"

private const val Algorithm = "DESede"
private const val Transformation = "DESede/ECB/PKCS5Padding"

object HuaweiAuthenticator {

    fun generateToken(
        userID: String,
        password: String,
        encryptionToken: String,
        deviceID: String
    ): String {
        val stringToEncrypt = listOf(
            randomString(), encryptionToken,
            userID, deviceID, deviceID, deviceID,
            RESERVED,
            SUFFIX
        ).joinToString(separator = SEPARATOR)

        val encPassword = huaweiMD5Encrypt(password)
        val encMessage = encrypt(stringToEncrypt, encPassword)

        return encMessage
    }

    fun encrypt(message: String, key: String): String {
        val keyBytes = unwrapKey(key)
        val dataBytes = message.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance(Transformation)
        val secretKey =
            SecretKeyFactory.getInstance(Algorithm).generateSecret(DESedeKeySpec(keyBytes))
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val doFinal = cipher.doFinal(dataBytes)
        return doFinal.toHex()
    }

    fun decrypt(hexedMessage: String, key: String): String {
        val keyBytes = unwrapKey(key)
        val dataBytes = decodeHexString(hexedMessage)
        val cipher = Cipher.getInstance(Transformation)
        val secretKey =
            SecretKeyFactory.getInstance(Algorithm).generateSecret(DESedeKeySpec(keyBytes))
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val final = cipher.doFinal(dataBytes)
        return String(final, Charset.forName("utf-8"))
    }

    private fun huaweiMD5Encrypt(string: String): String {
        val suffix = "99991231"
        val md5String = "$string$suffix".toMD5().toList().map { it.toString() }
        val cutmd5String = md5String.reduceIndexed { index, acc, str ->
            if (index % 2 == 0 && str == "0") {
                acc
            } else {
                acc + str
            }
        }.take(8)
        return cutmd5String
    }

    private fun randomString(): String {
        return "99"
        return Random.nextInt(9999999).toString()
    }

    private fun unwrapKey(password: String): ByteArray {
        val byteArray = password.toByteArray(Charsets.UTF_8)
        val bytesForAppending = 24 - byteArray.count()
        var result = byteArray
        val zeroByte: Byte = 0
        repeat(bytesForAppending) {
            result += zeroByte
        }

        return result
    }
}

