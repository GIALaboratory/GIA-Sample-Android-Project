package com.example.data.remote.models

import com.example.domain.model.Report
import com.google.gson.annotations.SerializedName

class ReportSerial(
    @SerializedName("REPORT_NO") var reportNo: String?,
    @SerializedName("REPORT_NUMBER") var reportNumber: String?,

    @SerializedName("REPORT_TYPE") var reportType: String,
    @SerializedName("REPORT_TYPE_CODE") var reportTypeCode: String,

    @SerializedName("REPORT_DT") var reportDt: String?,
    @SerializedName("REPORT_DATE") var reportDate: String?,

    @SerializedName("IS_SLEEVE") var isSleeve: String?,
    @SerializedName("IS_PDF_AVAILABLE") var isPDFAvailable: String?,
    @SerializedName("PDF_URL") var pdfURL: String?,
    @SerializedName("DGTIMG") var imageURL: String?,
    @SerializedName("INSCRIPTION") var inscription: String?,
    
    // Diamond-specific fields
    @SerializedName("SHAPE") var shape: String? = null,
    @SerializedName("MEASUREMENTS") var measurements: String? = null,
    @SerializedName("WEIGHT") var weight: String? = null,
    @SerializedName("COLOR_GRADE") var colorGrade: String? = null,
    @SerializedName("CLARITY_GRADE") var clarityGrade: String? = null,
    @SerializedName("CUT_GRADE") var cutGrade: String? = null,
    @SerializedName("DEPTH") var depth: String? = null,
    @SerializedName("TABLE") var table: String? = null,
    @SerializedName("CROWN_ANGLE") var crownAngle: String? = null,
    @SerializedName("CROWN_HEIGHT") var crownHeight: String? = null,
    @SerializedName("PAVILION_ANGLE") var pavilionAngle: String? = null,
    @SerializedName("PAVILION_DEPTH") var pavilionDepth: String? = null,
    @SerializedName("STAR_LENGTH") var starLength: String? = null,
    @SerializedName("LOWER_HALF") var lowerHalf: String? = null,
    @SerializedName("GIRDLE") var girdle: String? = null,
    @SerializedName("CULET") var culet: String? = null,
    @SerializedName("POLISH") var polish: String? = null,
    @SerializedName("SYMMETRY") var symmetry: String? = null,
    @SerializedName("FLUORESCENCE") var fluorescence: String? = null,
    @SerializedName("CLARITY_CHARACTERISTICS") var clarityCharacteristics: String? = null,
    
    // Colored stone-specific fields
    @SerializedName("COLOR_ORIGIN") var colorOrigin: String? = null,
    @SerializedName("COLOR_DISTRIBUTION") var colorDistribution: String? = null,
    @SerializedName("COUNTRY_OF_ORIGIN") var countryOfOrigin: String? = null,
    @SerializedName("PROPIMG") var propImg: String? = null,
    @SerializedName("PLOTIMG") var plotImg: String? = null,
    @SerializedName("ORGROU") var orgRou: String? = null,
    @SerializedName("ORGPOL") var orgPol: String? = null,
    
    // Emerald/Identification-specific fields
    @SerializedName("IDENT_TBL_WEIGHT") var identTblWeight: String? = null,
    @SerializedName("IDENT_TBL_MEASUREMENTS") var identTblMeasurements: String? = null,
    @SerializedName("IDENT_TBL_SHAPE") var identTblShape: String? = null,
    @SerializedName("IDENT_TBL_CUTTINGSTYLE") var identTblCuttingStyle: String? = null,
    @SerializedName("IDENT_TBL_TRANSPARENCY") var identTblTransparency: String? = null,
    @SerializedName("IDENT_TBL_COLOR") var identTblColor: String? = null,
    @SerializedName("IDENT_TBL_PHENOMENON") var identTblPhenomenon: String? = null,
    @SerializedName("IDENT_TBL_DESCRIPTION") var identTblDescription: String? = null,
    @SerializedName("IDENT_TBL_GROUP") var identTblGroup: String? = null,
    @SerializedName("IDENT_TBL_SPECIES") var identTblSpecies: String? = null,
    @SerializedName("IDENT_TBL_VARIETY") var identTblVariety: String? = null,
    @SerializedName("IDENT_TBL_SOURCETYPE") var identTblSourceType: String? = null,
    @SerializedName("IDENT_TBL_TRADENAME") var identTblTradeName: String? = null,
    @SerializedName("IDENT_TBL_GEOGRAPHICORIGIN") var identTblGeographicOrigin: String? = null,
    @SerializedName("IDENT_TBL_TREATEMENT") var identTblTreatment: String? = null,
    @SerializedName("TREATMENT_URLS") var treatmentUrls: String? = null,
    @SerializedName("MATERIAL") var material: String? = null,
    
    // Common additional fields
    @SerializedName("REPORT_COMMENTS") var reportComments: String? = null,
    @SerializedName("REPORT_SLEEVE_MSG") var reportSleeveMsg: String? = null,
    @SerializedName("INFO_MSG") var infoMsg: String? = null,
    @SerializedName("DIGITAL_RPT_FLG") var digitalRptFlg: String? = null,
    @SerializedName("__TYPENAME") var typeName: String? = null,
) {
    var content: Map<String, String> = mapOf()

    fun parse(): Report? {
        val safeReportNumber = reportNo ?: reportNumber
        if (safeReportNumber == null) return null

        val safeReportDate = reportDt ?: reportDate
        if (safeReportDate == null) return null

        // Populate content map with all dynamic fields
        content = buildDynamicContentMap()

        return Report(
            reportNo = safeReportNumber,
            reportType = reportType,
            reportTitle = "",
            reportTypeCode = reportTypeCode,
            reportDt = safeReportDate,
            reportDtSaved = "",
            reportDtSynced = null,
            isSleeve = isSleeve,
            isPDFAvailable = isPDFAvailable,
            pdfURL = pdfURL,
            imageURL = imageURL,
            content = content,
            inscription = inscription,
        )
    }

    /**
     * Build dynamic content map from all non-static fields
     */
    private fun buildDynamicContentMap(): Map<String, String> {
        val dynamicContent = mutableMapOf<String, String>()
        
        // Add all non-null string fields that are not in the static fields list
        listOfNotNull(
            shape?.let { "SHAPE" to it },
            measurements?.let { "MEASUREMENTS" to it },
            weight?.let { "WEIGHT" to it },
            colorGrade?.let { "COLOR_GRADE" to it },
            clarityGrade?.let { "CLARITY_GRADE" to it },
            cutGrade?.let { "CUT_GRADE" to it },
            depth?.let { "DEPTH" to it },
            table?.let { "TABLE" to it },
            crownAngle?.let { "CROWN_ANGLE" to it },
            crownHeight?.let { "CROWN_HEIGHT" to it },
            pavilionAngle?.let { "PAVILION_ANGLE" to it },
            pavilionDepth?.let { "PAVILION_DEPTH" to it },
            starLength?.let { "STAR_LENGTH" to it },
            lowerHalf?.let { "LOWER_HALF" to it },
            girdle?.let { "GIRDLE" to it },
            culet?.let { "CULET" to it },
            polish?.let { "POLISH" to it },
            symmetry?.let { "SYMMETRY" to it },
            fluorescence?.let { "FLUORESCENCE" to it },
            clarityCharacteristics?.let { "CLARITY_CHARACTERISTICS" to it },
            colorOrigin?.let { "COLOR_ORIGIN" to it },
            colorDistribution?.let { "COLOR_DISTRIBUTION" to it },
            countryOfOrigin?.let { "COUNTRY_OF_ORIGIN" to it },
            propImg?.let { "PROPIMG" to it },
            plotImg?.let { "PLOTIMG" to it },
            orgRou?.let { "ORGROU" to it },
            orgPol?.let { "ORGPOL" to it },
            identTblWeight?.let { "IDENT_TBL_WEIGHT" to it },
            identTblMeasurements?.let { "IDENT_TBL_MEASUREMENTS" to it },
            identTblShape?.let { "IDENT_TBL_SHAPE" to it },
            identTblCuttingStyle?.let { "IDENT_TBL_CUTTINGSTYLE" to it },
            identTblTransparency?.let { "IDENT_TBL_TRANSPARENCY" to it },
            identTblColor?.let { "IDENT_TBL_COLOR" to it },
            identTblPhenomenon?.let { "IDENT_TBL_PHENOMENON" to it },
            identTblDescription?.let { "IDENT_TBL_DESCRIPTION" to it },
            identTblGroup?.let { "IDENT_TBL_GROUP" to it },
            identTblSpecies?.let { "IDENT_TBL_SPECIES" to it },
            identTblVariety?.let { "IDENT_TBL_VARIETY" to it },
            identTblSourceType?.let { "IDENT_TBL_SOURCETYPE" to it },
            identTblTradeName?.let { "IDENT_TBL_TRADENAME" to it },
            identTblGeographicOrigin?.let { "IDENT_TBL_GEOGRAPHICORIGIN" to it },
            identTblTreatment?.let { "IDENT_TBL_TREATEMENT" to it },
            treatmentUrls?.let { "TREATMENT_URLS" to it },
            material?.let { "MATERIAL" to it },
            reportComments?.let { "REPORT_COMMENTS" to it },
            reportSleeveMsg?.let { "REPORT_SLEEVE_MSG" to it },
            infoMsg?.let { "INFO_MSG" to it },
            digitalRptFlg?.let { "DIGITAL_RPT_FLG" to it }
        ).forEach { (key, value) ->
            if (value.isNotEmpty()) {
                dynamicContent[key] = value
            }
        }
        
        return dynamicContent
    }
}

val reportStaticFields = listOf(
    "REPORT_NO",
    "REPORT_TYPE",
    "REPORT_TYPE_CODE",
    "REPORT_DT",
    "IS_SLEEVE",
    "IS_PDF_AVAILABLE",
    "PDF_URL",
    "DGTIMG",
    "INSCRIPTION"
)