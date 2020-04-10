import org.xml.sax.SAXException
import java.io.FileInputStream
import java.io.IOException
import javax.xml.parsers.ParserConfigurationException

/**
 * 根据一个已知的dimens文件生成各sw文件
 */
object AutoCreateDimens_Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val parser = DimensParser()
        try {
            //一直dimen.xml文件路径
            val list: List<DimenValues?> =
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