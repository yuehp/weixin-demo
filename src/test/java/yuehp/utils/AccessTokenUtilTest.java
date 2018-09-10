package yuehp.utils;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import yuehp.domain.AccessToken;

/**
 * <p>微信公众平台 - 获取 access_token</p>
 * 
 * <p><a href="http://blog.51cto.com/zero01/2136341">access_token的获取 - 微信公众号开发-素材/消息管理接口</a></p>
 * 
 * @author yuehp
 * @version 20180820
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTokenUtilTest {
	
	/**
	 * <p>获取 access_token</p>
	 * 
	 * @throws IOException
	 */
	@Test
    public void getAccessToken() throws IOException {
		/* 2018年8月20日 15:45
		 * access_token: 12_Yz8YdFzB2TBKIeyxdGamtyi7qqaqLVZMSmJ9MKqRvQyd3vJmRKXTl42N1Y0ZrW1yBe0nwlM05E5VXFGnJUg9bY9GCD1Z090OKq6MG4myAuFjLJV621RbRHa66Q4KNevlcyorjyJOaXw6a-GMRKWfAAALEQ
		 */  
		AccessToken accessToken = AccessTokenUtil.getAccessToken();
        System.out.println("access_token: " + accessToken.getAccessToken());
        System.out.println("有效时间: " + accessToken.getExpiresIn());
    }

}
