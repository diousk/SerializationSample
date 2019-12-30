package com.diousk.serializationsample.parser

import com.diousk.serializationsample.model.MockData
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JacksonParser {
    val type = object: TypeReference<List<MockData>>() {}
    val adapter = jacksonObjectMapper()
}