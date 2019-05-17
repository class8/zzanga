package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import control.DBUtil;
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

	// 정보 수정
	public boolean getAccountUpdate(int a_number, String a_cname, String a_mname, String a_phone, String a_email,
			String a_address, String a_bnumber, String a_msubject, String a_remarks) throws Exception {

		String sql = "update account set a_cname=?, a_mname=?, a_phone=?, a_email=?, a_address=?, a_bnumber=?, a_msubject=?, a_remarks=? where a_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_cname);
			pstmt.setString(2, a_mname);
			pstmt.setString(3, a_phone);
			pstmt.setString(4, a_email);
			pstmt.setString(5, a_address);
			pstmt.setString(6, a_bnumber);
			pstmt.setString(7, a_msubject);
			pstmt.setString(8, a_remarks);
			pstmt.setInt(9, a_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 수정");
				alert.setHeaderText(a_number + "번 거래처 수정 완료.");
				alert.setContentText("거래처 정보 수정 성공!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 정보 수정");
				alert.setHeaderText("거래처 정보 수정 실패");
				alert.setContentText("거래처 정보 수정 실패!");
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
		return accountUpdateSucess;
	}

	// 정보 수정
	public boolean getAccountUpdate(int a_number, String a_cname, String a_mname, String a_phone, String a_email,
			String a_address, String a_bnumber, String a_msubject) throws Exception {

		String sql = "update account set a_cname=?, a_mname=?, a_phone=?, a_email=?, a_address=?, a_bnumber=?, a_msubject=? where a_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_cname);
			pstmt.setString(2, a_mname);
			pstmt.setString(3, a_phone);
			pstmt.setString(4, a_email);
			pstmt.setString(5, a_address);
			pstmt.setString(6, a_bnumber);
			pstmt.setString(7, a_msubject);
			pstmt.setInt(8, a_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 수정");
				alert.setHeaderText(a_number + "번 거래처 수정 완료.");
				alert.setContentText("거래처 정보 수정 성공!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 정보 수정");
				alert.setHeaderText("거래처 정보 수정 실패");
				alert.setContentText("거래처 정보 수정 실패!");
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
		return accountUpdateSucess;
	}

	// 정보 삭제
	public boolean getAccountDelete(int a_number) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from account where a_number = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, a_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 삭제");
				alert.setHeaderText(a_number + "번 거래처 정보 삭제 완료.");
				alert.setContentText("거래처 정보 삭제 성공!!!");
				alert.showAndWait();
				accountDeleteSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 정보 삭제");
				alert.setHeaderText("거래처 정보 삭제 실패");
				alert.setContentText("거래처 정보 삭제 실패!");
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
				System.out.println(e);
			}
		}
		return accountDeleteSucess;
	}

	// 거래처명을 입력받아 정보 조회
	public ArrayList<AccountVO> getStudentCheck(String name) throws Exception {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();

		String sql = "select * from account where a_cname like ? order by a_number desc";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

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
