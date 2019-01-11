package souvc.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import souvc.weixin.service.CoreService;
import souvc.weixin.util.SignUtil;

/**
 * 来接受微信服务器传来的消息
 * @author xjw
 *
 */
@WebServlet(urlPatterns="/coreServlet")
public class CoreServlet extends HttpServlet{

	private static final long serialVersionUID = 4323197796926899691L;
	/**
	 * 确认来自微信服务器
	 * 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println("--------------------------");
		System.out.println("zifuchuan:  "+echostr);
		
		PrintWriter out = response.getWriter();
		
		//通过校验signatrue 对请求进行校验，若校验成果原样返回echostr ，表示接入成功，否则接入失败
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
			System.out.println("|||||||||||||||||||||||||||||");
			System.out.println(echostr);
			out.print(echostr);
			
		}
		out.close();
		out = null;
		
	}
	/**
	 * 处理微信服务器来的消息
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws
		ServletException,IOException{
		
		//TODO 消息接收，处理响应
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//调用核心业务类接收消息、处理消息
		String respXml = CoreService.ProcessRequest(request);
		
		//响应消息
		PrintWriter out = response.getWriter();
		out.println(respXml);
		out.close();
		
	}
}
