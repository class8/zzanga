package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CustomerVO;

public class CustomerDAO {

	// 고객 전체 목록

	public ArrayList<CustomerVO> getCustomerTotalList() throws Exception {

		ArrayList<CustomerVO> list = new ArrayList<>();

		String sql = "select c_number, c_name, c_cname, c_phone, c_address, c_bnumber, c_email, c_remarks, c_registdate from customer";
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
	public ArrayList<String> getCustomerColumnName() throws Exception {

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

	// 고객 등록
	public void getCustomerRegiste(CustomerVO cvo) throws Exception {

		String sql = "insert into customer values " + "(customer_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getC_name());
			pstmt.setString(2, cvo.getC_cname());
			pstmt.setString(3, cvo.getC_phone());
			pstmt.setString(4, cvo.getC_address());
			pstmt.setString(5, cvo.getC_bnumber());
			pstmt.setString(6, cvo.getC_email());
			pstmt.setString(7, cvo.getC_remarks());

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객 등록");
				alert.setHeaderText(cvo.getC_name() + " 고객 등록 완료.");
				alert.setContentText("고객 등록 성공!!!");
				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("고객 등록");
				alert.setHeaderText("고객 등록 실패");
				alert.setContentText("고객 등록 실패!");
				alert.showAndWait();
			}

		} catch (SQLException e) {

			System.out.println("e=[" + e + "]");

		} catch (Exception e) {

			System.out.println("e=[" + e + "]");

		} finally {

			try {

				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제한다.
				if (pstmt != null)
					pstmt.close();

				if (con != null)
					con.close();

			} catch (SQLException e) {

			}
		}

	}

}