package com.example.data.utils.adapters

import com.example.data.remote.models.ReportResponse
import com.example.data.remote.models.ReportSerial
import com.example.data.remote.models.reportStaticFields
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

class ReportSerialAdapter : JsonDeserializer<ReportResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ReportResponse {
        val dynamicValues = mutableMapOf<String, String>()

        var jsonObj: JsonObject

        try {
            val jsonArray = json!!.asJsonArray
            jsonObj = jsonArray.first().asJsonObject
        } catch (e: Exception) {
            jsonObj = json!!.asJsonObject
        }

        var report: ReportSerial? = null
        var errorCode: String? = null

        for (jsonKey in jsonObj.keySet()) {
            if (jsonKey == "ERROR_CODE") {
                errorCode = jsonObj[jsonKey].asString
            }
        }

        if (errorCode == null) {
            for (jsonKey in jsonObj.keySet()) {
                if (!reportStaticFields.contains(jsonKey)) {
                    try {
                        dynamicValues[jsonKey] = jsonObj[jsonKey].asString
                    } catch (e: Exception) {
                        // cannot guarantee values can be parsed
                        e.printStackTrace()
                    }
                }
            }
            try {
                report = context!!.deserialize<ReportSerial>(jsonObj, ReportSerial::class.java)
                report.content = dynamicValues
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        return ReportResponse(report, errorCode)
    }
}