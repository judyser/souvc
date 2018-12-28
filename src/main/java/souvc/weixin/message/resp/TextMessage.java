package souvc.weixin.message.resp;
/**
 * 消息文本消息
 * @author xjw
 *
 */
public class TextMessage extends BaseMessage{
	//回复消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	

}
