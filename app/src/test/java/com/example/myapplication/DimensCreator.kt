package com.example.myapplication

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
    private val DIRTEMPLATE = "values-sw%sdp" //values-sw375dp
    private val dimenTemplate = "<dimen name=\"%s\">%sdp</dimen>"
    private val dimenTemplateSp = "<dimen name=\"%s\">%ssp</dimen>"

    fun createAll() {
        val len = Config.supportDevices.size
        for (i in 0 until len) {
            val strings = DIRPath.split("values").toTypedArray()
            val dir =
                strings[0] + File.separator + String.format(
                    DIRTEMPLATE,
                    "" + Config.supportDevices[i]
                )
            val dirFile = File(dir)
            if (dirFile.exists()) {
                dirFile.delete()
            }
            dirFile.mkdirs()
            val file =
                File(dirFile.path + File.separator + "dimens.xml")
            //            float scale = (float) i / Config.defaultValue;
            println(i)
            //            float scale = Config.supportScale[i];
//            createSingleFile(file, scale);
            createSingleFile(file, Config.supportDevices[i], defaultDimenWidth)
        }
    }

    private fun createSingleFile(file: File, supportDevices: Int, sw: Int) {
        createSingleFile(file, supportDevices * 1.0f / sw)
    }

    private fun createSingleFile(file: File, scale: Float) {
        var allData = StringBuilder(xmlHeader)
        for (values in defaultDimenValues) {
            var itemValue: String?
            values?.value?.run {
                if (contains("dp")) {
                    val v: Float = replace("dp", "").trim().toFloat()
                    itemValue = formatDimen(v * scale)
                    allData.append(String.format(dimenTemplate, values.name, itemValue)).append("\n")
                } else if (contains("sp")) {
                    val v: Float = replace("sp", "").trim().toFloat()
                    itemValue = formatDimen(v * scale)
                    allData.append(String.format(dimenTemplateSp, values.name, itemValue)).append("\n")
                } else{

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