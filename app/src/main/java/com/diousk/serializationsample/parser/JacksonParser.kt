package com.diousk.serializationsample.parser

import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JacksonParser {
    val type = object: TypeReference<List<MockData>>() {}
    val adapter = object : Adapter<List<MockData>> {
        private val adapter = jacksonObjectMapper()
        override fun fromJson(jsonString: String): List<MockData>? {
            return adapter.readValue(mockJsonString, type)
        }

        override fun toJson(obj: List<MockData>): String {
            return adapter.writeValueAsString(mockDataList)
        }
    }
}