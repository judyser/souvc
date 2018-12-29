package souvc.weixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import souvc.weixin.menu.Button;
import souvc.weixin.menu.CommonButton;
import souvc.weixin.menu.ComplexButton;
import souvc.weixin.menu.Menu;
import souvc.weixin.pojo.Token;
import souvc.weixin.thread.TokenThread;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.WeixinUtil;

/**
 * ��ʼ��Servlet
 * @author xjw
 *
 */
public class InitServlet extends HttpServlet{
	private static final long serialVersionUID =1L;
	private static Logger log =LoggerFactory.getLogger(CommonUtil.class);
	
	public void init() throws ServletException{
		//��ȡweb.xml�����õĲ���
		TokenThread.appid = getInitParameter("appid");
		TokenThread.appsecret = getInitParameter("appsecret");
		
		log.info("weixin api appid:{}",TokenThread.appid);
		log.info("weixin api appsecret:{}",TokenThread.appsecret);
		
		//δ����appid��appsecret ʱ������ʾ
		if("".equals(TokenThread.appid)||"".equals(TokenThread.appsecret)){
			log.error("appid and appsecret configuration error ,please check carefully.");
			
		}else{
			//������ʱ����ȡaccess_token���߳�
			
			new Thread(new TokenThread()).start();
		}
		
		
		
		
	}
}
