package com.example.myapplication

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
    l: List<DimenValues>,
    widthDP: Int
) {
    private val list: List<DimenValues>
    private val xmlHeader = """<?xml version="1.0" encoding="utf-8"?>
<resources>"""
    private val xmlFooter = "</resources>"
    private val DIRTEMPLATE = "values-sw%sdp" //values-sw375dp
    private val dimenTemplate = "<dimen name=\"%s\">%sdp</dimen>"
    private val dimenTemplateSp = "<dimen name=\"%s\">%ssp</dimen>"
    private val mWidthDP: Int
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
            createSingleFile(file, Config.supportDevices[i], mWidthDP)
        }
    }

    private fun createSingleFile(file: File, scale: Float) {
        var data: String? = xmlHeader
        for (values in list) {
            var itemValue: String = values.value
            var itemData: String? = null
            if (values.value.contains("dp")) {
                val v: Float = values.value.replace("dp", "").trim().toFloat()
                //                itemValue = String.valueOf(Math.round(v * scale));
                itemValue = formatDimen(v * scale)
                itemData =
                    """
                    ${java.lang.String.format(dimenTemplate, values.name, itemValue)}

                    """.trimIndent()
            } else if (values.value.contains("sp")) {
                val v: Float = values.value.replace("sp", "").trim().toFloat()
                //                itemValue = String.valueOf(Math.round(v * scale));
                itemValue = formatDimen(v * scale)
                itemData = """
                    ${java.lang.String.format(dimenTemplateSp, values.name, itemValue)}

                    """.trimIndent()
            }
            data += itemData
        }
        data += xmlFooter
        var outputStream: FileOutputStream? = null
        var inputStream: ByteArrayInputStream? = null
        try {
            outputStream = FileOutputStream(file)
            inputStream = ByteArrayInputStream(data!!.toByteArray())
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

    private fun createSingleFile(file: File, supportDevices: Int, sw: Int) {
        var data: String? = xmlHeader
        for (values in list) {
            var itemValue: String = values.value
            var itemData: String? = null
            if (values.value.contains("dp")) {
                val v: Float = values.value.replace("dp", "").trim().toFloat()
                itemValue = formatDimen(v * supportDevices / sw)
                itemData =
                    """
                    ${java.lang.String.format(dimenTemplate, values.name, itemValue)}

                    """.trimIndent()
            } else if (values.value.contains("sp")) {
                val v: Float = values.value.replace("sp", "").trim().toFloat()
                itemValue = formatDimen(v * supportDevices / sw)
                itemData = """
                    ${java.lang.String.format(dimenTemplateSp, values.name, itemValue)}

                    """.trimIndent()
            }
            data += itemData
        }
        data += xmlFooter
        var outputStream: FileOutputStream? = null
        var inputStream: ByteArrayInputStream? = null
        try {
            outputStream = FileOutputStream(file)
            inputStream = ByteArrayInputStream(data!!.toByteArray())
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

    init {
        list = l
        mWidthDP = widthDP
    }
}