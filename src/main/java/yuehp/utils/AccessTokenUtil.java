package yuehp.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import yuehp.domain.AccessToken;

/**
 * <p>获取微信公众平台的 access_token</p>
 * 
 * <p><a href="http://blog.51cto.com/zero01/2136341">参考文章：access_token的获取 - 微信公众号开发-素材/消息管理接口</a></p>
 * 
 * @author yuehp
 * @version 20180820
 * @version 20180820
 */
@Component
public class AccessTokenUtil {
    
    /**
     * <p>通过 get 方法获取JSON响应</p>
     * 
     * @param url - URL地址
     * @return JSON 对象
     * @throws IOException
     */
    public static JSONObject doGet(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = client.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");

        return new JSONObject(result);
    }
    
    /**
     * <p>通过 post 方法获取JSON响应</p>
     * 
     * @param url - URL地址
     * @param outStr
     * @return JSON 对象
     * @throws IOException
     */
    public static JSONObject doPost(String url, String outStr) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(outStr, "utf-8"));

        HttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");

        return new JSONObject(result);
    }
    
    /**
     * <p>获取 access_token</p>
     * 
     * @return 微信 AccessToken 对象
     * @throws IOException
     */
    public static AccessToken getAccessToken() throws IOException {
        AccessToken token = new AccessToken();

        /* 硬编码的URL  */
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7a2872dd6fbf852f&secret=9eb1cfb1dbc1e628272c0e9090191e66";
        JSONObject jsonObject = doGet(url);
        token.setAccessToken(jsonObject.getString("access_token"));
        token.setExpiresIn(jsonObject.getInt("expires_in"));

        return token;
    }

}
