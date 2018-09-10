package yuehp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>测试 natapp 内网穿透</p>
 * 
 * <p><a href="https://natapp.cn/article/natapp_newbie">参考：NATAPP1分钟快速新手图文教程</a></p>
 * 
 * @author Administrator
 * @version 20180820
 */
@RestController
@RequestMapping("")
public class IndexController {
	
	@RequestMapping("")
	public String index() {
		return "hello world!";
	}

}
