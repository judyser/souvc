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
 * ͨ�ù�����
 * @author xjw
 *
 */
public class CommonUtil {
	private static Logger log =LoggerFactory.getLogger(CommonUtil.class);
	//��ȡƾ֤
	public final static String token_url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * ����https����
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr outputStr �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		
		try{
			//����sslcontext����ʹ�ø�����ֻ�ȵ����ι�������ʼ��
			TrustManager []tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null,tm, new java.security.SecureRandom());
			//������sslcontext�л�ȡsslsocketfactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			
			conn.setRequestMethod(requestMethod);
			
			//��outputStr��Ϊnull ʱ �������д����
			if(null!= outputStr){
				OutputStream outputStream = conn.getOutputStream();
				//ע������ʽ
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
				
			}
			//����������ȡ����
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			StringBuffer buffer = new StringBuffer();
			
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			
			
			//�ͷ���Դ
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream= null;
			
			conn.disconnect();
			System.out.println(buffer.toString());
			jsonObject = JSON.parseObject(buffer.toString());
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(jsonObject.toJSONString());
		System.out.println(jsonObject.toString());
		
		return jsonObject;
	}
	/**
	 * ��ȡ�ӿ�ƾ֤
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static Token getToken(String appid,String appsecret){
		Token token = null;
		String requestUrl=token_url.replace("APPID", appid).replace("APPSECRET",appsecret);
		//���������ȡƾ֤
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		
		if(null!=jsonObject){
			try{
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token") );
				token.setExpiresIn(jsonObject.getInteger("expires_in"));
				
				
			}catch(Exception e){
				token = null;
				log.error("��ȡtokenʧ��");
				System.out.println(jsonObject.toString());
			}
			
		}
		return token;
	}
		
}
