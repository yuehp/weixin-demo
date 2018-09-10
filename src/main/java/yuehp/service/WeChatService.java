package yuehp.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>微信服务类</p>
 * 
 * @author yuehp
 * @version 20180816
 */
public interface WeChatService {

	/**
	 * <p>处理微信服务器的消息转发</p>
	 * 
	 * @param request
	 * @return
	 */
	String processRequest(HttpServletRequest request);
}
