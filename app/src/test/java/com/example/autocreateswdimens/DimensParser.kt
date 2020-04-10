import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.xml.parsers.ParserConfigurationException
import javax.xml.parsers.SAXParserFactory

/**
 * Created by adaJQD on 2017/1/12.
 */
class DimensParser {
    private val list: MutableList<DimenValues?> =
        ArrayList()

    @Throws(
        ParserConfigurationException::class,
        SAXException::class,
        IOException::class
    )
    fun parse(inputStream: InputStream?): List<DimenValues?> {
        val parserFactory =
            SAXParserFactory.newInstance()
        val parser = parserFactory.newSAXParser()
        parser.parse(inputStream, InnerHandler())
        return list
    }

    private inner class InnerHandler : DefaultHandler() {
        private var dimenValues: DimenValues? = null
        private val stringBuilder = StringBuilder()
        private var tempName: String? = null

        @Throws(SAXException::class)
        override fun startElement(
            uri: String,
            localName: String,
            qName: String,
            attributes: Attributes
        ) {
            if (qName == "dimen") {
                tempName = qName
                dimenValues = DimenValues()
                stringBuilder.setLength(0)
                dimenValues!!.name = attributes.getValue("name")
            }
        }

        @Throws(SAXException::class)
        override fun endElement(
            uri: String,
            localName: String,
            qName: String
        ) {
            if (qName == "dimen") {
                list.add(dimenValues)
            }
        }

        @Throws(SAXException::class)
        override fun characters(ch: CharArray, start: Int, length: Int) {
            stringBuilder.append(ch, start, length)
            if (tempName != null && tempName == "dimen") {
                dimenValues!!.value = stringBuilder.toString()
            }
        }

        @Throws(SAXException::class)
        override fun startDocument() {
            super.startDocument()
        }

        @Throws(SAXException::class)
        override fun endDocument() {
            super.endDocument()
        }
    }
}