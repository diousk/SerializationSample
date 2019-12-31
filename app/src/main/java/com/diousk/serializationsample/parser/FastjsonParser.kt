package com.diousk.serializationsample.parser

import com.alibaba.fastjson.JSON
import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.MockDataJava
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString

object FastjsonParser {
    val adapter = object : Adapter<List<MockData>> {
        override fun fromJson(jsonString: String): List<MockData>? {
            return JSON.parseArray(mockJsonString, MockData::class.java)
        }

        override fun toJson(obj: List<MockData>): String {
            return JSON.toJSONString(mockDataList)
        }
    }

    val javaAdapter = object : Adapter<List<MockDataJava>> {
        override fun fromJson(jsonString: String): List<MockDataJava>? {
            return JSON.parseArray(mockJsonString, MockDataJava::class.java)
        }

        override fun toJson(obj: List<MockDataJava>): String {
            return JSON.toJSONString(mockDataList)
        }
    }
}