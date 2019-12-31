package com.diousk.serializationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString
import com.diousk.serializationsample.parser.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup click event to test parse
        moshi.setOnClickListener { jsonParse(MoshiParser.adapter) }

        gson.setOnClickListener { jsonParse(GsonParser.adapter) }

        jackson.setOnClickListener { jsonParse(JacksonParser.adapter) }

        fastjson.setOnClickListener { jsonParse(FastjsonParser.adapter) }
    }

    private fun jsonParse(adapter: Adapter<List<MockData>>) {
        lifecycleScope.launch(Dispatchers.Default) {
            Log.d("Main", "adapter $adapter start parsing")

            // start deserialize
            var mockData: List<MockData>? = null
            val deserializeTime = measureTimeMillis {
                (1..LOOP_TIME).forEach { _ ->
                    mockData = adapter.fromJson(mockJsonString)
                }
            }
            Log.d("Main", "adapter $adapter deserialize $mockData")

            // start serialize
            var json: String? = null
            val serializeTime = measureTimeMillis {
                (1..LOOP_TIME).forEach { _ ->
                    json = adapter.toJson(mockDataList)
                }
            }
            Log.d("Main", "adapter $adapter serialize $json")

            withContext(Dispatchers.Main) {
                result.text = "result: \n" +
                        "adapter ${adapter.javaClass.name} \n" +
                        "loop $LOOP_TIME, \n" +
                        "deserializeTime $deserializeTime ms, \n" +
                        "serializeTime $serializeTime ms"
            }
        }
    }

    companion object {
        const val LOOP_TIME = 5000L
    }
}