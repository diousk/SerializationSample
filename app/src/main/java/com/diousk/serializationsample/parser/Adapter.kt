package com.diousk.serializationsample.parser

interface Adapter<T> {
    fun fromJson(jsonString: String): T?
    fun toJson(obj: T): String
}