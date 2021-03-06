package yuehp.domain;


/**
 * <p>语音消息</p>
 * 
 * 
 * <p><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">参考：语音消息格式</a></p>
 * 
 * @author yuehp
 * @version 20180820
 * @since 20180820
 */
public class VoiceItem {
	
	/** <p>通过素材管理中的接口上传多媒体文件，得到的id</p> */
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	@Override
	public String toString() {
		return "VoiceItem [MediaId=" + MediaId + "]";
	}
	
	

}
