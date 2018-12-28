package souvc.weixin.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.TokenUtil;

public class TokenThread implements Runnable{
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	//�������û�Ψһƾ֤
	public static String appid="";
	//�������û�Ψһƾ֤��Կ
	public static String appsecret="";
	public static Token accessToken=null;

	
	
	public void run() {
		while(true){
			try{
				accessToken = CommonUtil.getToken(appid, appsecret);
				System.out.println(accessToken.getAccessToken());
				if(null!=accessToken){
					//���ô��浽���ݿ�
					TokenUtil.saveToken(accessToken);
					log.info("��ȡaccess_token�ɹ�����Чʱ��{}�� token��{}",accessToken.getExpiresIn(),accessToken.getAccessToken());
					//����7000��
					Thread.sleep((accessToken.getExpiresIn()-200)*1000);
				}else{
					//���access_ TOken Ϊnull,60���ڻ�ȡ
					Thread.sleep(60*1000);
				}
			}catch(InterruptedException e ){
				try{
					Thread.sleep(60*1000);
					
				}catch (InterruptedException e1){
					log.error("{}",e1);
				}
				log.error("{}",e);
			}
		}
		
	}

	
}
