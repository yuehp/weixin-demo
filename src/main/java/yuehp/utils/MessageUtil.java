package yuehp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>处理消息：把微信请求中的 参数 转成 Map</p>
 * 
 * @author yuanjun,yuehp
 * @version 20180816
 * @since 2017年12月8日下午3:20:48
 */
public class MessageUtil {

	/**
	 * <p>参数 => Map</p>
	 * <p>把微信请求中的 参数 转成 Map</p>
	 * 
	 * @param request - HttpServletRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		try (InputStream in = request.getInputStream()) {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

}
