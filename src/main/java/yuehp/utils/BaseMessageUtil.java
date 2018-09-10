package yuehp.utils;

/**
 * <p>接口：消息封装</p>
 * <p>采用泛型,方便后期功能扩展</p>
 * 
 * @author yuanjun, yuehp
 * @version 20180816
 * @since 20171206
 */
public interface BaseMessageUtil<T> {
	
	/**
	 * <p>消息：T 格式 => XML格式</p>
	 * 
	 * <p>把回复的信息对象，转为微信的 XML 格式</p>
	 * 
	 * @param message
	 * @return
	 */
	public abstract String messageToXML(T t);

	/**
	 * 封装回复信息
	 * 
	 * @param FromUserName
	 * @param ToUserName
	 * @param Content
	 * @return
	 */
	public abstract String initMessage(String FromUserName, String ToUserName);
}
