package com.diousk.serializationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis
import com.diousk.serializationsample.parser.fastjsonAdapter as fastjsonAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup click event to test parse
        moshi.setOnClickListener { moshiParse() }

        gson.setOnClickListener { gsonParse() }

        jackson.setOnClickListener { jacksonParse() }

        fastjson.setOnClickListener { fastjsonParse() }
    }

    private val moshiAdapter by lazy { MoshiParser.adapter }
    private fun moshiParse() {
        lifecycleScope.launch(Dispatchers.Default) {
            val adapter = moshiAdapter
            Log.d("Main", "moshiParse start parsing")

            // start deserialize & serialize
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

            withContext(Dispatchers.Main) {
                result.text = "result: \n" +
                    "moshiParse loop $LOOP_TIME, \n" +
                    "deserializeTime $deserializeTime ms, \n" +
                    "serializeTime $serializeTime ms"
            }
        }
    }

    private val gsonAdapter by lazy { GsonParser.adapter }
    private fun gsonParse() {
        lifecycleScope.launch(Dispatchers.Default) {
            val adapter = gsonAdapter
            Log.d("Main", "gsonParse start parsing")

            // start deserialize & serialize
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

            withContext(Dispatchers.Main) {
                result.text = "result: \n" +
                    "gsonParse loop $LOOP_TIME, \n" +
                    "deserializeTime $deserializeTime ms, \n" +
                    "serializeTime $serializeTime ms"
            }
        }
    }

    private val jacksonAdapter by lazy { JacksonParser.adapter }
    private fun jacksonParse() {
        lifecycleScope.launch(Dispatchers.Default) {
            val adapter = jacksonAdapter
            val type = JacksonParser.type
            Log.d("Main", "jacksonParse start parsing")

            // start deserialize & serialize
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

            withContext(Dispatchers.Main) {
                result.text = "result: \n" +
                    "jacksonParse loop $LOOP_TIME, \n" +
                    "deserializeTime $deserializeTime ms, \n" +
                    "serializeTime $serializeTime ms"
            }
        }
    }

    private fun fastjsonParse() {
        lifecycleScope.launch(Dispatchers.Default) {
            val type = JacksonParser.type
            Log.d("Main", "fastjsonParse start parsing")

            // start deserialize & serialize
            var mockData: List<MockData>? = null
            val deserializeTime = measureTimeMillis {
                (1..LOOP_TIME).forEach { _ ->
                    mockData = fastjsonAdapter.parseArray(mockJsonString, MockData::class.java)
                }
            }
            Log.d("Main", "fastjsonParse mockData $mockData")
            var json: String? = null
            val serializeTime = measureTimeMillis {
                (1..LOOP_TIME).forEach { _ ->
                    json = fastjsonAdapter.toJSONString(mockDataList)
                }
            }
            Log.d("Main", "fastjsonParse json $json")

            withContext(Dispatchers.Main) {
                result.text = "result: \n" +
                    "fastjsonParse loop $LOOP_TIME, \n" +
                    "deserializeTime $deserializeTime ms, \n" +
                    "serializeTime $serializeTime ms"
            }
        }
    }

    companion object {
        const val LOOP_TIME = 5000L
    }
}