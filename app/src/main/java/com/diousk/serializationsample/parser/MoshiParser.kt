package com.diousk.serializationsample.parser

import com.diousk.serializationsample.model.MockData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object MoshiParser {
    private val type = Types.newParameterizedType(List::class.java, MockData::class.java)
    private val moshi = Moshi.Builder().build()
    val adapter = moshi.adapter<List<MockData>>(type)
}