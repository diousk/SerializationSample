package com.diousk.serializationsample.parser

import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken

object GsonParser {
    private val type = TypeToken.getParameterized(List::class.java, MockData::class.java)
    private val gson = GsonBuilder().create()
    val adapter = object : Adapter<List<MockData>> {
        private val adapter = gson.getAdapter(type) as TypeAdapter<List<MockData>>
        override fun fromJson(jsonString: String): List<MockData>? {
            return adapter.fromJson(mockJsonString)
        }

        override fun toJson(obj: List<MockData>): String {
            return adapter.toJson(mockDataList)
        }
    }
}