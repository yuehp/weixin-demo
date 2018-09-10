package yuehp.domain;

/**
 * <p>回复消息的基类</p>
 * 
 * @author yuehp
 * @version 20180816
 * @since 20180816
 */
public class WXBaseMessage {
	
	/** 接收方 */
	protected String ToUserName;
	/** 发送方 */
	protected String FromUserName;
	/** 创建时间 */
	protected long CreateTime;
	/** 消息类型 */
	protected String MsgType;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	@Override
	public String toString() {
		return "WXBaseMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime="
		                + CreateTime + ", MsgType=" + MsgType + "]";
	}
	
	

}
