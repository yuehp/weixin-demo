package yuehp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yuehp.domain.WXRequest;
import yuehp.utils.MessageUtil;
import yuehp.utils.TextMessageUtil;

/**
 * <p>微信公众平台 - 测试 Controller</p>
 * 
 * <p><a href="https://blog.csdn.net/shenbug/article/details/78748346">参考：SpringBoot开发微信公众号</a></p>
 * 
 * @author yuehp,yuanjun
 * @version 20180816
 */
@Controller
@RequestMapping("wx")
@ResponseBody
public class WeixinController {

	/**
	 * <p>
	 * 处理微信信息
	 * </p>
	 * 
	 * @param request
	 * @param response
	 */
	@PostMapping
	public void backend(HttpServletRequest request, HttpServletResponse response) {
		/* response 默认难道不是 utf-8 */
		response.setCharacterEncoding("utf-8");
		/* 把 微信 request 中的 XML 转为 Map */
		Map<String, String> map = MessageUtil.xmlToMap(request);
		String ToUserName = map.get("ToUserName");
		String FromUserName = map.get("FromUserName");
		String MsgType = map.get("MsgType");
		String Content = map.get("Content");

		/* 要发送的消息 */
		String message = null;
		/* 处理文本类型,回复用户输入的内容 */
		if ("text".equals(MsgType)) {
			TextMessageUtil textMessage = new TextMessageUtil();
			message = textMessage.initMessage(FromUserName, ToUserName, Content);
		}

		/* 写出到 response */
		try (PrintWriter out = response.getWriter()) {
			out.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 微信对接登陆验证
	 * </p>
	 * <p>请求中的字段：签名（signature）、时间戳（timestamp）、随机数（nonce）</p>
	 * 
	 * <h3>处理步骤：</h3>
	 * <ol>
	 * 		<li>请求中的2个字段 + 约定的 token 按字典排序</li>
	 * 		<li>3个字段拼接成一个字符串</li>
	 * 		<li>SHA1计算签名</li>
	 * 		<li>比较签名</li>
	 * 		<li>签名一致，处理消息</li>
	 * </ol>
	 * 
	 * @return
	 */
	@GetMapping
	public String test(WXRequest wx) {
		/* 取出 request 中的字段 */
		String signature = wx.getSignature();
		String timestamp = wx.getTimestamp();
		String nonce = wx.getNonce();
		String echostr = wx.getEchostr();
		/* 约定的 token */
		String token = "tianjin2017";
		/* 排序 */
		List<String> list = new ArrayList<>();
		list.add(nonce);
		list.add(timestamp);
		list.add(token);
		Collections.sort(list);
		
		/* 3个字段拼接成要给字符串 */
		String joinedStr = list.get(0) + list.get(1) + list.get(2);
		/* 计算出的签名 */
		String localSignature = DigestUtils.sha1Hex(joinedStr);
		
		/* 如果签名匹配，返回 echostr */
		if (Objects.equals(signature, localSignature)) {
			return echostr;
		}
		/* 不匹配，返回其他字符串 */
		return "try again";
	}

}
