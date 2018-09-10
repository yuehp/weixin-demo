package yuehp.domain;

/**
 * <p>微信公众号请求entity</p>
 * 
 * @author yuehp
 * @version 20180816
 * @since 20180816
 */
public class WXRequest {

	/** 签名 */
	private String signature;
	/** 时间戳 */
	private String timestamp;
	/** 随机数 */
	private String nonce;
	/** 响应字符串 */
	private String echostr;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	@Override
	public String toString() {
		return "WXRequest [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
		                + echostr + "]";
	}
	
	
	
	
}
