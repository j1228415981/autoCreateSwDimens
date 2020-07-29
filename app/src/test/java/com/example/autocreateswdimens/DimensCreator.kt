package com.example.autocreateswdimens

import DimenValues
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.DecimalFormat

/**
 * Created by adaJQD on 2017/1/12.
 */
class DimensCreator(
    private val DIRPath: String,
    private val defaultDimenValues: List<DimenValues?>,
    private val defaultDimenWidth: Int
) {
    private val xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>"
    private val xmlFooter = "</resources>"
    private val SMALL_WIDTH_DIR = "values-sw%sdp" //values-sw375dp
    private val WIDTH_DIR = "values-w%sdp"
    private val dimenTemplate = "<dimen name=\"%s\">%sdp</dimen>"
    private val dimenTemplateSp = "<dimen name=\"%s\">%ssp</dimen>"

    fun createSmallWidth() {
        val len = Config.supportDevices.size
        for (i in 0 until len) {
            val strings = DIRPath.split("values").toTypedArray()
            var scale = Config.supportDevices[i] * 1.0f / defaultDimenWidth
            createSingleFile(
                strings[0] + File.separator + String.format(
                    SMALL_WIDTH_DIR,
                    "" + Config.supportDevices[i]
                ), scale
            )
        }
    }

    fun createWidth() {
        val len = Config.supportDevices.size
        for (i in 0 until len) {
            val strings = DIRPath.split("values").toTypedArray()
            var scale = Config.supportDevices[i] * 1.0f / defaultDimenWidth
            createSingleFile(
                strings[0] + File.separator + String.format(
                    WIDTH_DIR,
                    "" + Config.supportDevices[i]
                ), scale
            )
        }
    }

    private fun createSingleFile(file: String, scale: Float) {
        val dirFile = File(file)
        if (dirFile.exists()) {
            dirFile.delete()
        }
        dirFile.mkdirs()
        val file = File(dirFile.path + File.separator + "dimens.xml")
        createSingleFile(file, scale)
    }

    private fun createSingleFile(file: File, scale: Float) {
        var allData = StringBuilder(xmlHeader)
        for (values in defaultDimenValues) {
            var itemValue: String?
            values?.value?.run {
                if (contains("dp")) {
                    val v: Float = replace("dp", "").trim().toFloat()
                    itemValue = formatDimen(v * scale)
                    allData.append(String.format(dimenTemplate, values.name, itemValue))
                        .append("\n")
                } else if (contains("sp")) {
                    val v: Float = replace("sp", "").trim().toFloat()
                    itemValue = formatDimen(v * scale)
                    allData.append(String.format(dimenTemplateSp, values.name, itemValue))
                        .append("\n")
                } else {

                }
            }
        }
        allData.append(xmlFooter)
        var outputStream: FileOutputStream? = null
        var inputStream: ByteArrayInputStream? = null
        try {
            outputStream = FileOutputStream(file)
            inputStream = ByteArrayInputStream(allData.toString().toByteArray())
            val buffer = ByteArray(inputStream.available())
            while (inputStream.read(buffer) != -1) {
                outputStream.write(buffer)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun formatDimen(dimen: Float): String {
        val decimalFormat = DecimalFormat("0.00")
        return decimalFormat.format(dimen.toDouble())
    }
}