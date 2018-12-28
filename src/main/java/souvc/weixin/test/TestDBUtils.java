package souvc.weixin.test;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;

import souvc.weixin.pojo.Token;
import souvc.weixin.util.CommonUtil;
import souvc.weixin.util.DBUtility;
import souvc.weixin.util.TokenUtil;

public class TestDBUtils {
	   @Test
	    public void testgetConnection() throws SQLException {
	        DBUtility db = new DBUtility();
	        System.out.println(db.getConnection());
	    }
	   @Test
	   public void testGetToken3(){
		   Map<String,Object> token = TokenUtil.getToken();
		   System.out.println(token.get("access_token"));
		   System.out.println(token.get("expires_in"));
		   
	   }
	   @Test
	   public void TestSaveToken4(){
		   Token token = CommonUtil.getToken("wx0e14fbcce2b76485", "e8cc964e10bce524ddefb25ef8f7a421");
		   TokenUtil.saveToken(token);
		   
	   }

}
