package com.example.domain.model

data class Report(
    val reportNo: String,
    var reportType: String,
    var reportTitle: String,
    val reportTypeCode: String,
    val reportDt: String,
    var reportDtSaved: String,
    var reportDtSynced: String? = null,
    val isSleeve: String? = null,
    val isPDFAvailable: String? = null,
    val pdfURL: String? = null,
    val imageURL: String? = null,
    val content: Map<String, String>,
    val inscription: String? = null,
) {
    fun isImageUrl(contentKey: String) : Boolean {
        if (content.containsKey(contentKey)){
            return content[contentKey]!!.contains("images")
        }
        return false
    }

    fun getCarat(defaultValue: String) : String {
        content["WEIGHT"]?.let {
            if (it.isNotEmpty()){
                return it.split(" ")[0]
            }
        }
        content["IDENT_TBL_WEIGHT"]?.let {
            if (it.isNotEmpty()){
                return it.split(" ")[0]
            }
        }
        return defaultValue
    }

    fun getColor(defaultValue: String) : String {
        val colorGrade = getField("COLOR_GRADE", "")
        return colorGrade.ifEmpty {
            getField("IDENT_TBL_COLOR", defaultValue)
        }
    }

    fun getClarity(defaultValue: String) : String {
        return getField("CLARITY_GRADE", defaultValue)
    }

    fun getCut(defaultValue: String) : String {
        return getField("CUT_GRADE", defaultValue)
    }

    private fun getField(name: String, defaultValue: String) : String{
        content[name]?.let {
            if(it.isNotEmpty()){
                return it
            }
        }
        return defaultValue
    }

}
