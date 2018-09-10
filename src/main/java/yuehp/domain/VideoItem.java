package yuehp.domain;

/**
 * <p>视频消息</p>
 * 
 * 
 * <p><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">参考：视频消息格式</a></p>
 * 
 * @author yuehp
 * @version 20180820
 * @since 20180820
 */
public class VideoItem {
	
	private String Title;
	private String Description;
	private String MediaId;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	@Override
	public String toString() {
		return "VideoItem [Title=" + Title + ", Description=" + Description + ", MediaId=" + MediaId + "]";
	}
	
	

}
