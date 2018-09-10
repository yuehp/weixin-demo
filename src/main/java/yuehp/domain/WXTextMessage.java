package yuehp.domain;

/**
 * <p>文本消息</p>
 * 
 * @author yuehp
 * @version 20180816
 * @since 20180816
 */
public class WXTextMessage extends WXBaseMessage {

	/** 消息内容 */
	private String Content;
	/** 消息id */
	private String MsgId;

	public WXTextMessage() {}
	
	public WXTextMessage(String toUserName, String fromUserName,
					long createTime, String msgType, String content, String msgId) {
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		Content = content;
		MsgId = msgId;
		
	}

	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		return super.toString() + "WXTextMessage [Content=" + Content + ", MsgId=" + MsgId + "]";
	}
	
}
