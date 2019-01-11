package souvc.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 校验signature 工具类
 * @author xjw
 *
 */
public class SignUtil {
	//与接口配置信息中的token 要一致
	private static String token ="souvcweixin";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//将token，timestamp，nonce三个参数字典排序
		String []arr= new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		
		//2.将三个字符串拼接长一个字符串sha1加密
		StringBuilder conent = new StringBuilder();
		for(String i :arr){
			conent.append(i);
		}
		System.out.println(conent);
		MessageDigest md = null;
		String tmpStr = null;
		try{
			md = MessageDigest.getInstance("SHA-1");
			//密将三个参数字符串凭借从一个字符串进行sha1 加
			byte[] digest = md.digest(conent.toString().getBytes());
			tmpStr = byteToStr(digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		conent = null;
		System.out.println("jiamichuan: "+tmpStr);
		System.out.println("signature:"+signature);
		// 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr !=null ?tmpStr.equals(signature.toUpperCase()):false;
	}
	/**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 */
	public static String byteToStr(byte [] byteArray){
		String strDigest ="";
		for(byte s :byteArray){
			strDigest +=byteToHexStr(s);
		}
		return strDigest;
	}
	
	public static String byteToHexStr(byte mByte){
		char []Digit ={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
		char []tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String (tempArr);
		return s;
	}
	
}
