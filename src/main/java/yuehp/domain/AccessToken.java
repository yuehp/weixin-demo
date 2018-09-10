package yuehp.domain;

/**
 * <p>微信 access_token</p>
 * 
 * @author yuehp
 * @version 20180820
 */
public class AccessToken {
	
	private String accessToken;
	private Integer expiresIn;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
	}
	
	

}
