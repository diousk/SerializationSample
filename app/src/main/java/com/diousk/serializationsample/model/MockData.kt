package com.diousk.serializationsample.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MockData(
    val id: Long, val name: String, val age: Int = -1, val child: Child? = null
)

@JsonClass(generateAdapter = true)
data class Child(
    @Json(name = "name")
    val childName: String = "unknown"
)

val mockDataList = listOf(
    MockData(1, "hello1", 1),
    MockData(2, "hello2", 2),
    MockData(3, "hello3", 3, Child("world3"))
)

val mockDataJavaList = listOf(
    MockDataJava().apply {
        setId(1)
        setName("hello1")
        setAge(1)
    },
    MockDataJava().apply {
        setId(2)
        setName("hello2")
        setAge(2)
    },
    MockDataJava().apply {
        setId(3)
        setName("hello3")
        setAge(3)
        setChild(Child("world3"))
    }
)

// List<MockData>
val mockJsonString = "[\n" +
    "  {\n" +
    "    \"id\": 1,\n" +
    "    \"name\": \"John\",\n" +
    "    \"age\": 38\n" +
    "  },\n" +
    "  {\n" +
    "    \"id\": 8,\n" +
    "    \"name\": \"Lisa\",\n" +
    "    \"child\": {\"name\":\"json\"},\n" +
    "    \"age\": 23\n" +
    "  },\n" +
    "  {\n" +
    "    \"id\": 23,\n" +
    "    \"name\": \"Karen\"\n" +
    "  }\n" +
    "]"