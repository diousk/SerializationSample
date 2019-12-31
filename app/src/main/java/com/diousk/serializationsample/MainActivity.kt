package com.diousk.serializationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.lifecycleScope
import com.diousk.serializationsample.databinding.ActivityMainBinding
import com.diousk.serializationsample.model.MockData
import com.diousk.serializationsample.model.mockDataJavaList
import com.diousk.serializationsample.model.mockDataList
import com.diousk.serializationsample.model.mockJsonString
import com.diousk.serializationsample.parser.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val useJavaModel = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        // setup click event to test parse
        moshi.setOnClickListener {
            if (useJavaModel.get()) {
                jsonParse(MoshiParser.javaAdapter, mockDataJavaList)
            } else {
                jsonParse(MoshiParser.adapter, mockDataList)
            }
        }
        gson.setOnClickListener {
            if (useJavaModel.get()) {
                jsonParse(GsonParser.javaAdapter, mockDataJavaList)
            } else {
                jsonParse(GsonParser.adapter, mockDataList)
            }
        }
        jackson.setOnClickListener {
            if (useJavaModel.get()) {
                jsonParse(JacksonParser.javaAdapter, mockDataJavaList)
            } else {
                jsonParse(JacksonParser.adapter, mockDataList)
            }
        }
        fastjson.setOnClickListener {
            if (useJavaModel.get()) {
                jsonParse(FastjsonParser.javaAdapter, mockDataJavaList)
            } else {
                jsonParse(FastjsonParser.adapter, mockDataList)
            }
        }
    }

    private fun <T> jsonParse(adapter: Adapter<T>, jsonObj: T) {
        lifecycleScope.launch(Dispatchers.Default) {
            Log.d("Main", "adapter $adapter start parsing")

            // start deserialize
            var mockData: T? = null
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
                    json = adapter.toJson(jsonObj)
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