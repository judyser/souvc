package souvc.weixin.message.req;
/**
 * ��Ƶ��Ϣ
 * @author xjw
 *
 */
public class VideoMessage extends BaseMessage {
	//ý��ID
	private String MediaId;
	//������ʽ
	private String ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
