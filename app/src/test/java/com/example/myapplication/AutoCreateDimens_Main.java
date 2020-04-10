import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * 根据一个已知的dimens文件生成各sw文件
 */
public class AutoCreateDimens_Main {

	public static void main(String[] args) {
		DimensParser parser = new DimensParser();
		try {
			//一直dimen.xml文件路径
			List<DimenValues> list = parser.parse(new FileInputStream(Config.path));
			DimensCreator creator = new DimensCreator(Config.path, list, Config.EXISTS_SMALL_WIDTH);
			creator.createAll();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
