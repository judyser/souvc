package souvc.weixin.message.req;

/**
 * ������ϢͼƬ��Ϣ
 * @author xjw
 *
 */
public class ImageMessage extends BaseMessage {
	//ͼƬ����
	private String PicUrl;
	private String MediaId;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
