package souvc.weixin.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import souvc.weixin.pojo.Token;



/**
 * 封装一个从数据库读取token和保存token的类
 * @author xjw
 *
 */
public class TokenUtil {
	/**
	 * 从数据库获取token
	 * @return
	 */
	public static Map<String ,Object>getToken(){
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from t_token order by createTime desc limit 0,1";
		String access_token ="";
		Map<String ,Object>map = new HashMap<String,Object>();
		
		Integer expires_in =0;
		
		try{
			//创建数据库连接
			conn = DBUtility.getConnection();
			//创建处理器
			stmt = conn.prepareStatement(sql);
			//查询token，读取一条记录
			rs = stmt.executeQuery();
			if(rs.next()){
				access_token = rs.getString("access_token");
				expires_in = rs.getInt("expires_in");
				map.put("access_token", access_token);
				map.put("expires_in", expires_in);
				
			}
		}catch(SQLException ex){
			System.out.println("数据库操作异常："+ex.getMessage());
		}finally{
			DBUtility.closeConnection(conn);
		}
		return map;
	}
	/**
	 * 保存token
	 * @param token
	 */
	public static void saveToken(Token token){
		//存入数据库
		Connection conn= null;
		PreparedStatement pst = null;
		
		try{
			conn = DBUtility.getConnection();
			pst = conn.prepareStatement("insert into t_token(access_token,expires_in,createTime)values(?,?,?)");
			pst.setString(1, token.getAccessToken());
			pst.setInt(2, token.getExpiresIn());
			long now = new Date().getTime();
			pst.setTimestamp(3, new java.sql.Timestamp(now));
			pst.execute();
			
		}catch(SQLException ex){
			System.out.println("数据库操作异常："+ex.getMessage());
		}finally{
			DBUtility.closeConnection(conn);
		}
	}
	
}
