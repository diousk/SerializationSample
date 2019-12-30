package com.diousk.serializationsample.parser

import com.diousk.serializationsample.model.MockData
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken

object GsonParser {
    private val type = TypeToken.getParameterized(List::class.java, MockData::class.java)
    private val gson = GsonBuilder().create()
    val adapter = gson.getAdapter(type) as TypeAdapter<List<MockData>>
}