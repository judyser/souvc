package souvc.weixin.message.event;
/**
 * 自定义菜单事件
 * @author xjw
 *
 */
public class MenuEvent extends BaseEvent{
	//事件KEY值，与自定义菜单接口中KEY只对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	

}
