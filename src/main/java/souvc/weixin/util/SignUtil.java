package souvc.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * У��signature ������
 * @author xjw
 *
 */
public class SignUtil {
	//��ӿ�������Ϣ�е�token Ҫһ��
	private static String token ="souvcweixin";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		//��token��timestamp��nonce���������ֵ�����
		String []arr= new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		
		//2.�������ַ���ƴ�ӳ�һ���ַ���sha1����
		StringBuilder conent = new StringBuilder();
		for(String i :arr){
			conent.append(i);
		}
		System.out.println(conent);
		MessageDigest md = null;
		String tmpStr = null;
		try{
			md = MessageDigest.getInstance("SHA-1");
			//�ܽ����������ַ���ƾ���һ���ַ�������sha1 ��
			byte[] digest = md.digest(conent.toString().getBytes());
			tmpStr = byteToStr(digest);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		conent = null;
		System.out.println("jiamichuan: "+tmpStr);
		System.out.println("signature:"+signature);
		// 3.��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
		return tmpStr !=null ?tmpStr.equals(signature.toUpperCase()):false;
	}
	/**
	 * ���ֽ�����ת��Ϊʮ�������ַ���
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
