package souvc.weixin.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.TokenUtil;

public class TokenThread implements Runnable{
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	//第三方用户唯一凭证
	public static String appid="";
	//第三方用户唯一凭证秘钥
	public static String appsecret="";
	public static Token accessToken=null;

	
	
	public void run() {
		while(true){
			try{
				accessToken = CommonUtil.getToken(appid, appsecret);
				System.out.println(accessToken.getAccessToken());
				if(null!=accessToken){
					//调用储存到数据库
					TokenUtil.saveToken(accessToken);
					log.info("获取access_token成功，有效时常{}秒 token：{}",accessToken.getExpiresIn(),accessToken.getAccessToken());
					//休眠7000秒
					Thread.sleep((accessToken.getExpiresIn()-200)*1000);
				}else{
					//如果access_ TOken 为null,60秒在获取
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
