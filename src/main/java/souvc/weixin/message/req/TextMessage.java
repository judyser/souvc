package souvc.weixin.message.req;
/**
 * 请求文本消息
 * @author xjw
 *
 */
public class TextMessage extends BaseMessage {

	//消息类容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
