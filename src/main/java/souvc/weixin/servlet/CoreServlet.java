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
 * ������΢�ŷ�������������Ϣ
 * @author xjw
 *
 */
@WebServlet(urlPatterns="/coreServlet")
public class CoreServlet extends HttpServlet{

	private static final long serialVersionUID = 4323197796926899691L;
	/**
	 * ȷ������΢�ŷ�����
	 * 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//΢�ż���ǩ��
		String signature = request.getParameter("signature");
		//ʱ���
		String timestamp = request.getParameter("timestamp");
		//�����
		String nonce = request.getParameter("nonce");
		//����ַ���
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		//ͨ��У��signatrue ���������У�飬��У��ɹ�ԭ������echostr ����ʾ����ɹ����������ʧ��
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
			out.print(echostr);
			
		}
		out.close();
		out = null;
		
	}
	/**
	 * ����΢�ŷ�����������Ϣ
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws
		ServletException,IOException{
		
		//TODO ��Ϣ���գ�������Ӧ
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//���ú���ҵ���������Ϣ��������Ϣ
		String respXml = CoreService.ProcessRequest(request);
		
		//��Ӧ��Ϣ
		PrintWriter out = response.getWriter();
		out.println(respXml);
		out.close();
		
	}
}
