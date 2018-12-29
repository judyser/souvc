package souvc.weixin.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import souvc.weixin.pojo.Token;



/**
 * 通用工具类
 * @author xjw
 *
 */
public class CommonUtil {
	private static Logger log =LoggerFactory.getLogger(CommonUtil.class);
	//获取凭证
	public final static String token_url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 发送https请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		
		try{
			//创建sslcontext对象，使用给我们只等的信任管理器初始化
			TrustManager []tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null,tm, new java.security.SecureRandom());
			//从上述sslcontext中获取sslsocketfactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			conn.setRequestMethod(requestMethod);
			
			//当outputStr不为null 时 想输出流写数据
			if(null!= outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//注意编码格式
				outputStream.write(outputStr.getBytes("utf-8"));
				
				outputStream.close();
				
			}
			//从输入流读取数据
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			StringBuffer buffer = new StringBuffer();
			
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			
			
			//释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream= null;
			
			conn.disconnect();
			
			jsonObject = JSON.parseObject(buffer.toString());
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(jsonObject.toJSONString());
		System.out.println(jsonObject.toString());
		
		return jsonObject;
	}
	/**
	 * 获取接口凭证
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getToken(String appid,String appsecret){
		Token token = null;
		String requestUrl=token_url.replace("APPID", appid).replace("APPSECRET",appsecret);
		//发起请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try{
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token") );
				token.setExpiresIn(jsonObject.getInteger("expires_in"));
				
				
			}catch(Exception e){
				token = null;
				log.error("获取token失败");
				System.out.println(jsonObject.toString());
			}
			
		}
		return token;
	}
		
}
