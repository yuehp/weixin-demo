package yuehp.domain;

/**
 * <p>音乐消息</p>
 * 
 * 
 * <p><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">参考：音乐消息格式</a></p>
 * 
 * @author yuehp
 * @version 20180820
 * @since 20180820
 */
public class MusicItem {
	
	/** <p>标题</p> */
	private String Title;
	/** <p>副标题，描述</p> */
	private String Description;
	/** <p>音乐链接</p> */
	private String MusicURL;
	/** <p>高质量音乐链接，WIFI环境优先使用该链接播放音乐</p> */
	private String HQMusicUrl;
	/** <p>缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id</p> */
	private String ThumbMediaId;
	
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
	public String getMusicURL() {
		return MusicURL;
	}
	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	@Override
	public String toString() {
		return "MusicItem [Title=" + Title + ", Description=" + Description + ", MusicURL=" + MusicURL + ", HQMusicUrl="
		                + HQMusicUrl + ", ThumbMediaId=" + ThumbMediaId + "]";
	}
	
	

}
