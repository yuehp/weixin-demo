package yuehp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import yuehp.domain.ArticleItem;
import yuehp.domain.ImageItem;
import yuehp.domain.MusicItem;
import yuehp.domain.VideoItem;
import yuehp.domain.VoiceItem;

/**
 * <p>微信公众平台 - 工具类</p>
 * 
 * <p><a href="https://www.cnblogs.com/changhai/p/8289808.html">参考文章：基于springboot微信公众号开发,几分钟学会微信自动回复 </a></p>
 * 
 * @author Administrator
 * @version 20180820
 * @since 20180816
 */
public class WeChatUtil {

	/**
	 * <p>验证微信签名</p>
	 * 
	 * @param signature - 微信 HTTP 请求中的签名
	 * @param timestamp - 微信 HTTP 请求中的时间戳
	 * @param nonce - 微信 HTTP 请求中的随机数
	 * @return 签名是否匹配，true - 匹配，false - 不匹配
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		/* 约定的 token */
		String token = "tianjin2017";
		/* 排序 */
		List<String> list = new ArrayList<>();
		list.add(nonce);
		list.add(timestamp);
		list.add(token);
		Collections.sort(list);
		
		/* -3个字段拼接成要给字符串  - */
		String joinedStr = list.get(0) + list.get(1) + list.get(2);
		/* 计算出的签名 */
		String localSignature = DigestUtils.sha1Hex(joinedStr);
		
		/* 如果签名匹配，返回 echostr */
		if (Objects.equals(signature, localSignature)) {
			return true;
		}
		return false;
	}

	/**
	 * <p>解析 HTTP 请求，得到包含请求参数的 Map</p>
	 * 
	 * @param request - 客户端发来的 HTTP 请求
	 * @return 包含请求参数的 Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) {
		/* 将解析结果存储在HashMap中 */
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		/* 从request中取得输入流 */
		try (InputStream in = request.getInputStream()) {
			/* 读取输入流 */
			Document doc = reader.read(in);
			/* 得到xml根元素 */
			Element root = doc.getRootElement();
			/* 得到根元素的所有子节点 */
			List<Element> list = root.elements();
			/* 遍历所有子节点 */
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


	/**
	 * <p>回复：文本消息</p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param respContent - 要返回的文本内容
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendTextMsg(Map<String, String> requestMap, String respContent) {
		/* 响应 map */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		map.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		map.put("MsgType", WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		map.put("CreateTime", new Date().getTime());
		map.put("Content", respContent);
		return mapToXML(map);
	}
	
	/**
	 * <p>回复：图片消息 2018年8月20日</p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param item - 要返回的 Video 信息
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendImageMsg(Map<String, String> requestMap, ImageItem item) {
		/* 响应 map */
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		responseMap.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		responseMap.put("MsgType", WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		responseMap.put("CreateTime", new Date().getTime());
		
		/* Image Content */
		Map<String, Object> imageContent = new HashMap<>();
		imageContent.put("MediaId", item.getMediaId());
		
		responseMap.put("Voice", imageContent);
		return mapToXML(responseMap);
	}
	
	/**
	 * <p>回复：语音消息 2018年8月20日</p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param item - 要返回的 Video 信息
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendVoiceMsg(Map<String, String> requestMap, VoiceItem item) {
		/* 响应 map */
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		responseMap.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		responseMap.put("MsgType", WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		responseMap.put("CreateTime", new Date().getTime());
		
		/* Voice Content */
		Map<String, Object> voiceContent = new HashMap<>();
		voiceContent.put("MediaId", item.getMediaId());
		
		responseMap.put("Voice", voiceContent);
		return mapToXML(responseMap);
	}
	
	
	/**
	 * <p>回复：视频消息 2018年8月20日</p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param item - 要返回的 Video 信息
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendVideoMsg(Map<String, String> requestMap, VideoItem item) {
		/* 响应 map */
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		responseMap.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		responseMap.put("MsgType", WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		responseMap.put("CreateTime", new Date().getTime());
		
		/* Video Content */
		Map<String, Object> videoContent = new HashMap<>();
		videoContent.put("MediaId", item.getMediaId());
		videoContent.put("Title", item.getTitle());
		videoContent.put("Description", item.getDescription());
		
		responseMap.put("Video", videoContent);
		return mapToXML(responseMap);
	}
	
	/**
	 * <p>回复：音乐消息 2018年8月20日</p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param item - 要返回的 Music 信息
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendMusicMsg(Map<String, String> requestMap, MusicItem item) {
		/* 响应 map */
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		responseMap.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		responseMap.put("MsgType", WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		responseMap.put("CreateTime", new Date().getTime());
		
		/* Music Content */
		Map<String, Object> musicContent = new HashMap<>();
		musicContent.put("Title", item.getTitle());
		musicContent.put("Description", item.getDescription());
		musicContent.put("MusicUrl", item.getMusicURL());
		musicContent.put("HQMusicUrl", item.getHQMusicUrl());
		musicContent.put("ThumbMediaId", item.getThumbMediaId());
		
		responseMap.put("Music", musicContent);

		return mapToXML(responseMap);
	}

	/**
	 * <p>回复：图文消息</p>
	 * 
	 * <p><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">参考：图文消息格式</a></p>
	 * 
	 * @param requestMap - 包含 HTTP 请求参数的 Map
	 * @param items - 要返回的文章列表
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String sendArticleMsg(Map<String, String> requestMap, List<ArticleItem> items) {
		/* 非空判断 */
		if (items == null || items.size() < 1) {
			return "";
		}

		/* 响应 Map */
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ToUserName", requestMap.get(WeChatConstant.FromUserName));
		responseMap.put("FromUserName", requestMap.get(WeChatConstant.ToUserName));
		responseMap.put("MsgType", "news");
		responseMap.put("CreateTime", new Date().getTime());
		/* 文章列表 */
		List<Map<String, Object>> articles = new ArrayList<Map<String, Object>>();
		for (ArticleItem articleItem : items) {
			/* an article */
			Map<String, Object> item = new HashMap<String, Object>();
			Map<String, Object> articleContent = new HashMap<String, Object>();
			articleContent.put("Title", articleItem.getTitle());
			articleContent.put("Description", articleItem.getDescription());
			articleContent.put("PicUrl", articleItem.getPicUrl());
			articleContent.put("Url", articleItem.getUrl());
			item.put("item", articleContent);
			articles.add(item);
		}
		responseMap.put("Articles", articles);
		responseMap.put("ArticleCount", articles.size());

		return mapToXML(responseMap);
	}
	

	/**
	 * <p>由 Map 生成 符合微信格式的 XML 字符串</p>
	 * 
	 * @param map - 包含待转换字段的 Map
	 * @return 符合微信格式的 XML 字符串
	 */
	public static String mapToXML(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
        mapToXML2(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
	}
	
	///////////////////////////////////////////////////////////////
	//////////////   ↓↓↓  Private Methods ↓↓↓     /////////////////
	///////////////////////////////////////////////////////////////
	
	/**
	 * 没看代码 - 20180816
	 * 
	 * @param map
	 * @param sb
	 */
	@SuppressWarnings({ "rawtypes" })
	private static void mapToXML2(Map map, StringBuffer sb) {
		Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
                }

            }

        }
	}

}
