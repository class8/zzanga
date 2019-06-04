package control;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil { // 데이터베이스 연동

	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@192.168.0.104:1521:orcl";

	public static Connection getConnection() throws Exception {

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "scott", "tiger");
		System.out.println("DB 연결에 성공하였습니다.");
		return con;
	}

}