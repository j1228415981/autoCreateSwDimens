package com.example.autocreateswdimens

import DimensParser
import org.junit.Test
import java.io.FileInputStream

/**
 * Example local unit test, which will execute on the development machine (host).
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun generate() {
        val parser = DimensParser()
        try {
            val defaultDimenValues = parser.parse(FileInputStream(Config.DEFAULT_DIMEN_FILE_PATH))
            val creator = DimensCreator(Config.GENERATE_DIMEN_FILE_DIR, defaultDimenValues, Config.DEFAULT_DIMEN_WIDTH)
            //最小宽度
//            creator.createSmallWidth()
            //宽度
            creator.createWidth()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
