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
 * 核心服务类
 * @author xjw
 *
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * @param request
	 * @return
	 */
	public static String ProcessRequest(HttpServletRequest request){
		//xml格式消息数据
		String respXml = null;
		//默认返回的文本消息内容
		String respContent ="未知消息类型！";
		try{
			//调用parseXMl方法解析请求消息
			Map<String ,String >requestMap = MessageUtil.parseXml(request);
			//发送方账号
			String fromUserName = requestMap.get("FromUserName");
			//开发者微信号
			String toUserName = requestMap.get("ToUserName");
			//消息类型
			String msgType =requestMap.get("MsgType");
			
			
			//回复文本信息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);;
			
			//回复图片信息
			ImageMessage imageMessage = new ImageMessage();
			imageMessage.setToUserName(fromUserName);
			imageMessage.setFromUserName(toUserName);
			imageMessage.setCreateTime(new Date().getTime());
			imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
			Image image = new Image();
			image.setMediaId("MnkSxbJjy9cPrgUXmK3SaN0U_nVaSqRK5Ufq6UgujAh3KGoOuaarW4fVvzSN38BG");
			
			
			
			//文本消息
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				respContent="你发送的是文本消息";
				//设置文本消息类容
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
				
				
			}
			// 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                //respContent = "您发送的是图片消息！";
                imageMessage.setImage(image);
              respXml = MessageUtil.messageToXml(imageMessage);
               
                
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
			
			/*imageMessage.setImage(image);
			//将文本消息对象
			respXml = MessageUtil.messageToXml(imageMessage);
			System.out.println(respXml);*/
		}catch (Exception e){
			e.printStackTrace();
		}
		return respXml;
	}
}
