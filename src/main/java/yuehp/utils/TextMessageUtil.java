package yuehp.utils;

import java.util.Date;

import com.thoughtworks.xstream.XStream;

import yuehp.domain.WXTextMessage;

/**
 * <p>工具：消息封装</p>
 * 
 * @author yuanjun,yuehp
 * @version 20180816
 */
public class TextMessageUtil implements BaseMessageUtil<WXTextMessage> {

	/**
	 * <p>entity对象 => XML</p>
	 * <p>把“要发送的消息” 封装成 “微信的 XML 格式”</p>
	 */
	@Override
	public String messageToXML(WXTextMessage message) {
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * <p>封装消息，内容固定</p>
	 * 
	 * @param FromUserName
	 * @param ToUserName
	 */
	@Override
	public String initMessage(String FromUserName, String ToUserName) {
		WXTextMessage text = new WXTextMessage();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent("欢迎关注");
		text.setCreateTime(new Date().getTime());
		text.setMsgType("text");
		return messageToXML(text);
	}

	/**
	 * <p>封装消息，内容由参数指定</p>
	 * 
	 * @param FromUserName
	 * @param ToUserName
	 * @param Content 消息内容
	 * @return 微信 XML 格式的消息
	 */
	public String initMessage(String FromUserName, String ToUserName, String Content) {
		WXTextMessage text = new WXTextMessage();
		/* 交换发送、接收 */
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent("您输入的内容是：" + Content);
		text.setCreateTime(new Date().getTime());
		text.setMsgType("text");
		return messageToXML(text);
	}
}
