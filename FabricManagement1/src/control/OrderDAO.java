package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderVO;

public class OrderDAO {
	// 데이터베이스에서 거래처 테이블의 컬럼 갯수
	public ArrayList<String> getOrderColumnName() throws Exception {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select o_number,o.a_number,f_number,o_name,o_ph one,o_address,o_amount,o_price,o_status,o_registdate,o_remarks,a_email from order1 o, account a where o.a_number=a.a_number";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {

				columnName.add(rsmd.getColumnName(i));
			}

		} catch (SQLException se) {

			System.out.println(se);

		} catch (Exception e) {

			System.out.println(e);

		} finally {

			try {

				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제한다.
				if (rs != null)
					rs.close();

				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (SQLException se) {

			}
		}

		return columnName;
	}

	// 전체 목록
	public ArrayList<OrderVO> getOrderTotalList() throws Exception {

		ArrayList<OrderVO> list = new ArrayList<>();

		String sql = "select o_number,o.a_number,f_number,o_name,o_ph one,o_address,o_amount,o_price,o_status,o_registdate,o_remarks,a_email from order1 o, account a where o.a_number=a.a_number";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setO_number(rs.getInt("o_number"));
				oVo.setA_number(rs.getInt("a_number"));
				oVo.setF_number(rs.getString("f_number"));
				oVo.setO_name(rs.getString("o_name"));
				oVo.setO_phone(rs.getString("o_phone"));
				oVo.setO_address(rs.getString("o_address"));
				oVo.setO_amount(rs.getInt("o_amount"));
				oVo.setO_price(rs.getInt("o_price"));
				oVo.setO_status(rs.getString("o_status"));
				oVo.setO_registdate(rs.getDate("a_registdate") + "");
				oVo.setO_remarks(rs.getString("o_remarks"));
				oVo.setA_email(rs.getString("a_email"));

				list.add(oVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제한다.
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				System.out.println(se);
			}
		}
		return list;
	}
}
