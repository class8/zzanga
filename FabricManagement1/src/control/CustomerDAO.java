package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CustomerVO;

public class CustomerDAO {

	// 고객 전체 목록

	public ArrayList<CustomerVO> getCustomerTotalList() throws Exception {

		ArrayList<CustomerVO> list = new ArrayList<>();

		String sql = "select * from customer";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				cVo = new CustomerVO();
				cVo.setC_number(rs.getInt("c_number"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_cname(rs.getString("c_cname"));
				cVo.setC_phone(rs.getString("c_phone"));
				cVo.setC_bnumber(rs.getString("c_bnumber"));
				cVo.setC_address(rs.getString("c_address"));
				cVo.setC_email(rs.getString("c_email"));
				cVo.setC_remarks(rs.getString("c_remarks"));
				cVo.setC_registdate(rs.getString("c_registdate"));

				list.add(cVo);
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

		return list;
	}

	public void getCustomerRegiste(CustomerVO cvo) {
		// TODO Auto-generated method stub

	}

	public boolean getCustomerDelete(Object selectedIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getcustomerUpdate(String selectedcustomerindex, String trim, String trim2, String trim3,
			String trim4, String trim5, String trim6, String trim7) {
		// TODO Auto-generated method stub
		return false;
	}

	// 데이터베이스에서 고객 테이블의 컬럼 갯수
	public ArrayList<String> getCsutomerColumnName() throws Exception {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from customer";
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

}
