package yuehp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>微信公众平台接口开发 - demo</p>
 * 
 * <h3>参考文章</h3>
 * <ul>
 * 		<li><a href="https://blog.csdn.net/shenbug/article/details/78757976">SpringBoot开发微信公众号</a></li>
 * 		<li><a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1472017492_58YV5">入门指引-微信公众平台技术文档</a></li>
 * </ul>
 * 
 * @author yuehp
 * @version 20180816
 * @since 20180816
 */
@SpringBootApplication
public class WeixinApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinApplication.class, args);
	}
}
