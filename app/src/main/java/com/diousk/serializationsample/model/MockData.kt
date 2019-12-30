package com.diousk.serializationsample.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MockData(
    val id: Long, val name: String, val age: Int = -1, val child: Child? = null
)

@JsonClass(generateAdapter = true)
data class Child(
    val name: String = "unknown"
) {
    fun crash() : Unit = throw Exception("hello")
}

val mockDataList = listOf(
    MockData(1, "hello1", 1),
    MockData(2, "hello2", 2),
    MockData(3, "hello3", 3, Child("world3"))
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