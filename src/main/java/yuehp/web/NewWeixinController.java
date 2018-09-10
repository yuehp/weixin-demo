package yuehp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yuehp.service.WeChatService;
import yuehp.utils.WeChatUtil;

/**
 * <p>微信 Controller 的另一种写法</p>
 * 
 * <p><a href="https://www.cnblogs.com/changhai/p/8289808.html">参考文章：基于springboot微信公众号开发,几分钟学会微信自动回复 </a></p>
 * 
 * 
 * @author yuehp
 * @version 20180820
 * @since 20180816
 */
@RestController
public class NewWeixinController {
	
	@Autowired
    private WeChatService weChatService;
	
	/**
	 * <p>处理微信服务器发来的get请求，进行签名的验证
	 * 
	 * <h3>微信端发来的字段</h3>
	 * <ul>
	 * 		<li>signature 微信端发来的签名</li>
	 * 		<li>timestamp 微信端发来的时间戳</li>
	 * 		<li>nonce	      微信端发来的随机字符串</li>
	 * 		<li>echostr   微信端发来的验证字符串</li>
	 * </ul>
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@GetMapping(value="wx2")
	public String validate(@RequestParam(value = "signature") String signature,
					@RequestParam(value = "timestamp") String timestamp,
					@RequestParam(value = "nonce") String nonce,
					@RequestParam(value = "echostr") String echostr) {
		return WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;
	}
	
	/**
	 * <p>处理微信服务器的消息转发</p>
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value="wx2")
	public String processMsg(HttpServletRequest request) {
		/* 调用核心服务类接收处理请求 */
		return weChatService.processRequest(request);
	}

}
