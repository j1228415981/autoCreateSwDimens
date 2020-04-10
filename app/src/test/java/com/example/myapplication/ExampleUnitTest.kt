package com.example.myapplication

import Config
import org.junit.Test
import org.xml.sax.SAXException
import java.io.FileInputStream
import java.io.IOException
import javax.xml.parsers.ParserConfigurationException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val parser = DimensParser()
        try {
            //一直dimen.xml文件路径
            val list: List<DimenValues> =
                parser.parse(FileInputStream(Config.path))
            val creator = DimensCreator(Config.path, list, Config.EXISTS_SMALL_WIDTH)
            creator.createAll()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
