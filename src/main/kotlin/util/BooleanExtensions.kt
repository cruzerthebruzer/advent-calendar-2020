package util

fun Boolean.xor(other: Boolean): Boolean = this && !other || !this && other
