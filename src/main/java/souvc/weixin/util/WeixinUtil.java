package souvc.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import souvc.weixin.menu.Menu;

/**
 * 公众平台通用接口工具类
 * @author xjw
 *
 */
public class WeixinUtil {

	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//                                      https://api.weixin.qq.com/cgi-bin/menu/create?access_token=17_fsKXjDOY4mNFYX7qfcIkV3J7SNAowogHzuok-4051Qpf2-5SORr692tTXGZ6wqjKpePhgiqV_puZwWGPpiqiJrBLauqWVeOjLtwKEJS7pHeWXInq9tYUOdjMXbCEAagxCA0qUAaB7EJIzuE9GOGcACAAIA
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/**
	 * 创建菜单
	 * @param menu
	 * @param accessToken
	 * @return 0表示成功，其他表示失败
	 */
	public static int createMenu(Menu menu,String accessToken){
		int result = 0;
		//创建菜单的url
		String url = menu_create_url.replaceAll("ACCESS_TOKEN", accessToken);
		//将菜单对象转换为json字符串
		String jsonMenu =JSONObject.toJSONString(menu);
		//调用接口创建菜单
		JSONObject jsonObject = CommonUtil.httpsRequest(url,"POST",jsonMenu);
		if(null !=jsonObject){
			if(0!=jsonObject.getIntValue("errcode")){
				result = jsonObject.getIntValue("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getIntValue("errcode"),jsonObject.getIntValue("errmsg"));
				
			}
		}
		return result;
	}
	
	
}
