package souvc.weixin.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;



import com.alibaba.fastjson.JSONObject;

import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.MyX509TrustManager;

public class TokenTest {
	
	public static void main(String[]args)throws Exception{
/*		String appID,secret;
		//�޸�appID,secret
		String tokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx0e14fbcce2b76485&secret=e8cc964e10bce524ddefb25ef8f7a421";
		//��������
		URL url = new URL(tokenUrl);
		HttpsURLConnection httpUrlConn =(HttpsURLConnection) url.openConnection();
		System.out.println();
		//����sslCnetext���󣬲�ʹ�������ƶ����ι�������ʼ��
		TrustManager[] tm = {new MyX509TrustManager()};
		SSLContext sslContext =SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		//������sslContext����õ�sslSocketFactory����
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		httpUrlConn.setSSLSocketFactory(ssf);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setDoOutput(true);
		
		//��������ʽ
		httpUrlConn.setRequestMethod("GET");
		
		//ȡ��������
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);		
		
		//��ȡ��Ӧ����
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		httpUrlConn.disconnect();
		System.out.println(buffer);
		
		JSONObject json = null;
		json = JSONObject.parseObject(buffer.toString());
		String access_token =json.getString("access_token");
		int expires_in = json.getInteger("expires_in");
		System.out.println("-----------------");
		
		System.out.println(json);
		System.out.println(access_token);
		System.out.println(expires_in);*/
		

	        Token token = CommonUtil.getToken("wx0e14fbcce2b76485","e8cc964e10bce524ddefb25ef8f7a421");
	        System.out.println("access_token:"+token.getAccessToken());
	        System.out.println("expires_in:"+token.getExpiresIn());
		
		
	}


}
