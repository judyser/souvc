package souvc.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import souvc.weixin.message.resp.Image;
import souvc.weixin.message.resp.ImageMessage;
import souvc.weixin.message.resp.TextMessage;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.MessageUtil;

/**
 * ���ķ�����
 * @author xjw
 *
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * @param request
	 * @return
	 */
	public static String ProcessRequest(HttpServletRequest request){
		//xml��ʽ��Ϣ����
		String respXml = null;
		//Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent ="δ֪��Ϣ���ͣ�";
		try{
			//����parseXMl��������������Ϣ
			Map<String ,String >requestMap = MessageUtil.parseXml(request);
			//���ͷ��˺�
			String fromUserName = requestMap.get("FromUserName");
			//������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			//��Ϣ����
			String msgType =requestMap.get("MsgType");
			
			
			//�ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);;
			
			//�ظ�ͼƬ��Ϣ
			ImageMessage imageMessage = new ImageMessage();
			imageMessage.setToUserName(fromUserName);
			imageMessage.setFromUserName(toUserName);
			imageMessage.setCreateTime(new Date().getTime());
			imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
			Image image = new Image();
			image.setMediaId("MnkSxbJjy9cPrgUXmK3SaN0U_nVaSqRK5Ufq6UgujAh3KGoOuaarW4fVvzSN38BG");
			//�ָ�����λ��
			
			
			
			//�ı���Ϣ
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				respContent="�㷢�͵����ı���Ϣ";
				//�����ı���Ϣ����
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
				
				
			}
			// ͼƬ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                //respContent = "�����͵���ͼƬ��Ϣ��";
                imageMessage.setImage(image);
              respXml = MessageUtil.messageToXml(imageMessage);
               
                
            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "�����͵���������Ϣ��";
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "�����͵�����Ƶ��Ϣ��";
            }
            // ��Ƶ��Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "�����͵���С��Ƶ��Ϣ��";
            }
            // ����λ����Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "�����͵��ǵ���λ����Ϣ��";
            }
            // ������Ϣ
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "�����͵���������Ϣ��";
            }
            // �¼�����
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // �¼�����
                String eventType = requestMap.get("Event");
                // ��ע
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "лл���Ĺ�ע��";
                }
                // ȡ����ע
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
                }
                // ɨ���������ά��
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO ����ɨ���������ά���¼�
                }
                // �ϱ�����λ��
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO �����ϱ�����λ���¼�
                }
                // �Զ���˵�
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO ����˵�����¼�
                }
            }
			
			/*imageMessage.setImage(image);
			//���ı���Ϣ����
			respXml = MessageUtil.messageToXml(imageMessage);
			System.out.println(respXml);*/
		}catch (Exception e){
			e.printStackTrace();
		}
		return respXml;
	}
}
