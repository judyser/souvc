package souvc.weixin.message.event;
/**
 * �Զ���˵��¼�
 * @author xjw
 *
 */
public class MenuEvent extends BaseEvent{
	//�¼�KEYֵ�����Զ���˵��ӿ���KEYֻ��Ӧ
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	

}
