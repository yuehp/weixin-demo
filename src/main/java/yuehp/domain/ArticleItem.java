package yuehp.domain;

/**
 * <p>图文消息</p>
 * 
 * <p><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543">参考：图文消息格式</a></p>
 * 
 * @author yuehp
 * @version 20180816
 */
public class ArticleItem {

	/** <p>标题</p> */
	private String Title;
	/** <p>副标题，描述</p> */
	private String Description;
	/** <p>图片地址</p> */
	private String PicUrl;
	/** <p>文章URL</p> */
	private String Url;

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

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	@Override
	public String toString() {
		return "ArticleItem [Title=" + Title + ", Description=" + Description + ", PicUrl=" + PicUrl + ", Url=" + Url
		                + "]";
	}

}
