package yuehp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import yuehp.domain.ArticleItem;
import yuehp.utils.WeChatConstant;
import yuehp.utils.WeChatUtil;

/**
 * <p>WeChat核心服务类</p>
 * 
 * <p><a href="https://www.cnblogs.com/changhai/p/8289808.html">参考文章：基于springboot微信公众号开发,几分钟学会微信自动回复 </a></p>
 * 
 * @author yuehp
 * @version 20180820
 * @since 20180816
 */
@Service
public class WeChatServiceImpl implements WeChatService {

	@Override
	public String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent;
		try {
			/* 解析请求消息 */
			Map<String, String> requestMap = WeChatUtil.parseXml(request);
			/* 消息类型 */
			String msgType = (String) requestMap.get(WeChatConstant.MsgType);
			/* 消息内容 */
			String mes = null;
			/* 文本消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_TEXT)) {
				mes = requestMap.get(WeChatConstant.Content).toString();
				if (mes != null && mes.length() < 2) {
					/* 临时测试 2018年8月20日 */
					if (Objects.equals("1", mes)) {
						/* 文本消息，并不支持 html */
						mes = "<h3>2018年第二季度智能手机各品牌出货量<h3><ol><li>三星</li><li>华为</li><li>苹果</li></ol>";
					}
					if (Objects.equals("2", mes)) {
						// 测试被动回复图文
						List<ArticleItem> items = new ArrayList<>();
	                    ArticleItem item = new ArticleItem();
						
						item = new ArticleItem();
	                    item.setTitle("百度");
	                    item.setDescription("百度一下");
	                    item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505100912368&di=69c2ba796aa2afd9a4608e213bf695fb&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F0634355517-9.jpg");
	                    item.setUrl("http://www.baidu.com");
	                    items.add(item);
	                    
	                    respXml = WeChatUtil.sendArticleMsg(requestMap, items);
					}

				}

			}
			/* 图片消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
			}
			/* 语音消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
				respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
			}
			/* 视频消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
				respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
			}
			/* 地理位置消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
			}
			/* 链接消息 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
			}
			/* 事件推送 */
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
                String eventType = (String) requestMap.get(WeChatConstant.Event);
                // 关注
                if (eventType.equals(WeChatConstant.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }
                // 上报地理位置事件
                if (eventType.equals(WeChatConstant.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 带参数二维码事件
                if (eventType.equals(WeChatConstant.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 自定义菜单
                if (eventType.equals(WeChatConstant.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
				/* TODO */
			}
			
			mes = (mes == null) ? "不知道你在干嘛" : mes;
			if (respXml == null) {
				respXml = WeChatUtil.sendTextMsg(requestMap, mes);
			}
			System.out.println(respXml);
			return respXml;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
