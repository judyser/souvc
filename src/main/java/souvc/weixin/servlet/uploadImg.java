package souvc.weixin.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
@WebServlet(urlPatterns="/imgupload")
public class uploadImg extends HttpServlet{
	String url ="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Token token =CommonUtil.getToken("wx0e14fbcce2b76485", "e8cc964e10bce524ddefb25ef8f7a421");
		String access_token=token.getAccessToken();
		url = url.replace("ACCESS_TOKEN", access_token).replace("TYPE", "image");
		
		File f = new File("d:/img.jpg");
		JSONObject jsonObject = CommonUtil.uploadMedia("image", "d:/img.jpg", url);
		
		System.out.println(jsonObject);
		
		/*String flength = Long.toString(f.length());
		
		String fname = f.getName();
		System.out.println(json);
		System.out.println("-----------");*/
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

}
