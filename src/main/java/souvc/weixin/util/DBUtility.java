package souvc.weixin.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtility {
	private static ComboPooledDataSource dataSource ;
	
	static {
		try{
			dataSource =  new ComboPooledDataSource();
            
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/souvc");
            dataSource.setUser("root");
            dataSource.setPassword("xiang1234");
            dataSource.setMaxPoolSize(40);
            dataSource.setMinPoolSize(2);
            dataSource.setInitialPoolSize(100);
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	   /* public DBUtility() {
	    }

	    public static void init() {

	        Properties dbProps = new Properties();
	        // ȡ�����ļ����Ը���ʵ�ʵĲ�ͬ�޸�
	        try {
	            dbProps.load(DBUtility.class.getClassLoader().getResourceAsStream("db.properties"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      
	        try {
	            String driveClassName = dbProps.getProperty("jdbc.driverClassName");
	            String url = dbProps.getProperty("jdbc.url");
	            String username = dbProps.getProperty("jdbc.username");
	            String password = dbProps.getProperty("jdbc.password");

	            

	            dataSource =  new ComboPooledDataSource();
	            
	            dataSource.setDriverClass("com.mysql.jdbc.Driver");
	            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/souvc");
	            dataSource.setUser("root");
	            dataSource.setPassword("xiang1234");
	            dataSource.setMaxPoolSize(40);
	            dataSource.setMinPoolSize(2);
	            dataSource.setInitialPoolSize(100);


	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("�������ӳ�ʧ��!��������!!!");
	        }
	    }*/

	    /**
	     * ���ݿ�����
	     * 
	     * @return
	     * @throws SQLException
	     */
	/*    public static synchronized Connection getConnection() throws SQLException {
	        if (dataSource == null) {
	            init();
	        }
	        Connection conn = null;
	        if (dataSource != null) {
	            conn = dataSource.getConnection();
	        }
	        return conn;
	    }
*/
	    /**
	     * �ر����ݿ�
	     * 
	     * @param conn
	     */
	    public static void closeConnection(Connection conn) {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                System.out.println("�ر���Դʧ��");
	                e.printStackTrace();
	            }
	        }
	    }
}
