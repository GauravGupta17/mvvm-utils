package com.gaurav.dask.utils.extensions

import java.security.MessageDigest

internal fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

internal fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

internal fun decodeHexString(hexString: String): ByteArray? {
    val bytes = ByteArray(hexString.length / 2)
    var i = 0
    while (i < hexString.length) {
        bytes[i / 2] =
            hexToByte(hexString.substring(i, i + 2))
        i += 2
    }
    return bytes
}

private fun hexToByte(hexString: String): Byte {
    val firstDigit: Int = toDigit(hexString[0])
    val secondDigit: Int = toDigit(hexString[1])
    return ((firstDigit shl 4) + secondDigit).toByte()
}

private fun toDigit(hexChar: Char): Int {
    val digit = Character.digit(hexChar, 16)
    require(digit != -1) { "Invalid Hexadecimal Character: $hexChar" }
    return digit
}
