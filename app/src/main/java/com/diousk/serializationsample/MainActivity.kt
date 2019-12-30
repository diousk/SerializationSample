package com.diousk.serializationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString
import com.diousk.serializationsample.parser.GsonParser
import com.diousk.serializationsample.parser.JacksonParser
import com.diousk.serializationsample.parser.MoshiParser
import com.fasterxml.jackson.module.kotlin.readValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup click event to test parse
        moshi.setOnClickListener { moshiParse() }

        gson.setOnClickListener { gsonParse() }

        jackson.setOnClickListener { jacksonParse() }
    }

    private val moshiAdapter by lazy { MoshiParser.adapter }
    private fun moshiParse() {
        val adapter = moshiAdapter
        Log.d("Main", "moshiParse start parsing")
        var mockData: List<MockData>? = null
        val deserializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                mockData = adapter.fromJson(mockJsonString)
            }
        }
        Log.d("Main", "moshiParse mockData $mockData")
        var json: String? = null
        val serializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                json = adapter.toJson(mockDataList)
            }
        }
        Log.d("Main", "moshiParse json $json")
        Log.d("Main", "moshiParse deserializeTime $deserializeTime ms")
        Log.d("Main", "moshiParse serializeTime $serializeTime ms")
        result.text = "result: \n" +
            "moshiParse loop $LOOP_TIME, " +
            "deserializeTime $deserializeTime ms, " +
            "serializeTime $serializeTime ms"
    }

    private val gsonAdapter by lazy { GsonParser.adapter }
    private fun gsonParse() {
        val adapter = gsonAdapter
        Log.d("Main", "gsonParse start parsing")
        var mockData: List<MockData>? = null
        val deserializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                mockData = adapter.fromJson(mockJsonString)
            }
        }
        Log.d("Main", "gsonParse mockData $mockData")
        var json: String? = null
        val serializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                json = adapter.toJson(mockDataList)
            }
        }
        Log.d("Main", "gsonParse json $json")
        Log.d("Main", "gsonParse deserializeTime $deserializeTime ms")
        Log.d("Main", "gsonParse serializeTime $serializeTime ms")
        result.text = "result: \n" +
            "gsonParse loop $LOOP_TIME, " +
            "deserializeTime $deserializeTime ms, " +
            "serializeTime $serializeTime ms"
    }

    private val jacksonAdapter by lazy { JacksonParser.adapter }
    private fun jacksonParse() {
        val adapter = jacksonAdapter
        val type = JacksonParser.type
        Log.d("Main", "jacksonParse start parsing")
        var mockData: List<MockData>? = null
        val deserializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                mockData = adapter.readValue(mockJsonString, type)
            }
        }
        Log.d("Main", "jacksonParse mockData $mockData")
        var json: String? = null
        val serializeTime = measureTimeMillis {
            (1..LOOP_TIME).forEach { _ ->
                json = adapter.writeValueAsString(mockDataList)
            }
        }
        Log.d("Main", "jacksonParse json $json")
        Log.d("Main", "jacksonParse deserializeTime $deserializeTime ms")
        Log.d("Main", "jacksonParse serializeTime $serializeTime ms")
        result.text = "result: \n" +
            "jacksonParse loop $LOOP_TIME, " +
            "deserializeTime $deserializeTime ms, " +
            "serializeTime $serializeTime ms"
    }

    companion object {
        const val LOOP_TIME = 3000L
    }
}