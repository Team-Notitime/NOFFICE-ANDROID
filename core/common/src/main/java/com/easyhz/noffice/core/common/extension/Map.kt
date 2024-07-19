package com.easyhz.noffice.core.common.extension

import java.util.EnumMap

inline fun <reified K : Enum<K>, V> Map<K, V>.toEnumMap(): EnumMap<K, V> {
    val enumMap = EnumMap<K, V>(K::class.java)
    for ((key, value) in this) {
        enumMap[key] = value
    }
    return enumMap
}