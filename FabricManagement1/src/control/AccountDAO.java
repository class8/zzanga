package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AccountVO;

public class AccountDAO {

	// 거래처 등록
	public void getAccountRegist(AccountVO avo) throws Exception {

		String sql = "insert into account values " + "(account_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, avo.getA_cname());
			pstmt.setString(2, avo.getA_mname());
			pstmt.setString(3, avo.getA_phone());
			pstmt.setString(4, avo.getA_email());
			pstmt.setString(5, avo.getA_address());
			pstmt.setString(6, avo.getA_bnumber());
			pstmt.setString(7, avo.getA_msubject());
			pstmt.setString(8, avo.getA_remarks());

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 등록");
				alert.setHeaderText(avo.getA_cname() + " 거래처 등록 완료.");
				alert.setContentText("거래처 등록 성공!");
				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 등록");
				alert.setHeaderText("거래처 등록 실패");
				alert.setContentText("거래처 등록 실패!");
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

	// 데이터베이스에서 거래처 테이블의 컬럼 갯수
	public ArrayList<String> getAccountColumnName() throws Exception {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from account order by a_number";
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

	public String getAccountNumber() {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from account order by a_number";
		String result = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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

		return result;
	}

	// 학생 전체 목록
	public ArrayList<AccountVO> getAccountTotalList() throws Exception {

		ArrayList<AccountVO> list = new ArrayList<>();

		String sql = "select a_number, a_cname, a_mname, a_phone, a_email, a_bnumber, a_msubject, a_address, a_remarks, a_registdate "
				+ " from account" + " order by a_number";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				aVo = new AccountVO();
				aVo.setA_number(rs.getInt("a_number"));
				aVo.setA_cname(rs.getString("a_cname"));
				aVo.setA_mname(rs.getString("a_mname"));
				aVo.setA_phone(rs.getString("a_phone"));
				aVo.setA_email(rs.getString("a_email"));
				aVo.setA_bnumber(rs.getString("a_bnumber"));
				aVo.setA_msubject(rs.getString("a_msubject"));
				aVo.setA_address(rs.getString("a_address"));
				aVo.setA_remarks(rs.getString("a_remarks"));
				aVo.setA_registdate(rs.getDate("a_registdate") + "");

				list.add(aVo);
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
}
