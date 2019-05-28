package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import control.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AccountVO;

public class AccountDAO {

	// 거래처 등록
	public void getAccountRegist(AccountVO avo) throws Exception {

		// 거래처 삽입 하는 sql문
		String sql = "insert into account values " + "(account_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// sql문 ? 부분에 해당하는 곳에 추가
			pstmt.setString(1, avo.getA_cname()); // 거래처명
			pstmt.setString(2, avo.getA_mname()); // 거래처 담당자 명
			pstmt.setString(3, avo.getA_phone()); // 거래처 담당자 연락처
			pstmt.setString(4, avo.getA_email()); // 거래처 이메일
			pstmt.setString(5, avo.getA_address()); // 거래처 주소
			pstmt.setString(6, avo.getA_bnumber()); // 거래처 사업자번호
			pstmt.setString(7, avo.getA_msubject()); // 거래처 주종목
			pstmt.setString(8, avo.getA_remarks()); // 비고

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

	// 거래처 전체 목록을 가져오는 메소드
	public ArrayList<AccountVO> getAccountTotalList() throws Exception {

		// VO형태로 데이터를 받을 리스트 생성
		ArrayList<AccountVO> list = new ArrayList<>();

		// 거래처 정보를 가져오는 sql문
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
				aVo.setA_number(rs.getInt("a_number")); // 거래처 번호
				aVo.setA_cname(rs.getString("a_cname")); // 거래처명
				aVo.setA_mname(rs.getString("a_mname")); // 거래처 담당자명
				aVo.setA_phone(rs.getString("a_phone")); // 거래처 담당자 연락처
				aVo.setA_email(rs.getString("a_email")); // 거래처 이메일
				aVo.setA_bnumber(rs.getString("a_bnumber")); // 거래처 사업자번호
				aVo.setA_msubject(rs.getString("a_msubject")); // 거래처 주종목
				aVo.setA_address(rs.getString("a_address")); // 거래처 주소
				aVo.setA_remarks(rs.getString("a_remarks"));// 비고
				aVo.setA_registdate(rs.getDate("a_registdate") + ""); // 등록일
				list.add(aVo); // 준비해둔 리스트에 VO형태로 추가
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
		// 리스트로 리턴
		return list;
	}

	// 거래처 정보 수정
	public boolean getAccountUpdate(int a_number, String a_cname, String a_mname, String a_phone, String a_email,
			String a_address, String a_bnumber, String a_msubject, String a_remarks) throws Exception {

		// 거래처 정보를 수정하는 sql문
		String sql = "update account set a_cname=?, a_mname=?, a_phone=?, a_email=?, a_address=?, a_bnumber=?, a_msubject=?, a_remarks=? where a_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a_cname); // 거래처명
			pstmt.setString(2, a_mname); // 거래처 담당자명
			pstmt.setString(3, a_phone); // 거래처 연락처
			pstmt.setString(4, a_email); // 거래처 이메일
			pstmt.setString(5, a_address); // 거래처 주소
			pstmt.setString(6, a_bnumber); // 거래처 사업자번호
			pstmt.setString(7, a_msubject); // 거래처 주종목
			pstmt.setString(8, a_remarks); // 비고
			pstmt.setInt(9, a_number); // 거래처 번호

			int i = pstmt.executeUpdate();

			// 수정이 정상적으로 이루어졌을 경우
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 수정");
				alert.setHeaderText(a_number + "번 거래처 수정 완료.");
				alert.setContentText("거래처 정보 수정 성공!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else { // 수정에 실패한 경우
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

	// 거래처 정보 삭제
	public boolean getAccountDelete(int a_number) throws Exception {

		// 데이터 베이스에 접근하기 위한 sql문
		StringBuffer sql = new StringBuffer();
		sql.append("delete from account where a_number = ?"); // ?에 해당하는 거래처 번호를 가진 행을 삭제

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, a_number); // 1번째 ? 위치에 거래처번호를 대입

			int i = pstmt.executeUpdate();

			// 삭제에 성공한 경우
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 삭제");
				alert.setHeaderText(a_number + "번 거래처 정보 삭제 완료.");
				alert.setContentText("거래처 정보 삭제 성공!!!");
				alert.showAndWait();
				accountDeleteSucess = true;

			} else { // 삭제에 실패한 경우

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 정보 삭제");
				alert.setHeaderText("거래처 정보 삭제 실패");
				alert.setContentText("거래처 정보 삭제 실패!");
				alert.showAndWait();
			}
		} catch (SQLIntegrityConstraintViolationException sqle) {
			// PK값인 거래처번호가 다른 테이블에서 사용되고 있어서 무결성 제약조건에 위배되는경우
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 삭제");
			alert.setHeaderText("거래처 정보 삭제 실패!");
			alert.setContentText("거래처 정보가 사용되고 있습니다.\n거래내역이나 주문 내역에서 사용중인 거래처 정보를 삭제하고 다시 시도해주세요.");
			alert.showAndWait();
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
	public ArrayList<AccountVO> getAccountCheck(String name) throws Exception {
		// 거래처 정보 형태로 결과를 받을 리스트
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();

		// 거래처 정보를 조회할 sql문
		String sql = "select * from account where a_cname like ? order by a_number"; // 거래처 테이블에서 거래처명에 ?를 포함하는 경우 모두 출력

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			// 1번째 ? 위치에 문자열 대입
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				aVo = new AccountVO();
				aVo.setA_number(rs.getInt("a_number")); // 거래처 번호
				aVo.setA_cname(rs.getString("a_cname")); // 거래처명
				aVo.setA_mname(rs.getString("a_mname")); // 거래처 담당자명
				aVo.setA_phone(rs.getString("a_phone")); // 거래처 연락처
				aVo.setA_email(rs.getString("a_email")); // 거래처 이메일
				aVo.setA_bnumber(rs.getString("a_bnumber")); // 거래처 사업자번호
				aVo.setA_msubject(rs.getString("a_msubject")); // 거래처 주종목
				aVo.setA_address(rs.getString("a_address")); // 거래처 주소
				aVo.setA_remarks(rs.getString("a_remarks")); // 비고
				aVo.setA_registdate(rs.getDate("a_registdate") + ""); // 등록일
				list.add(aVo); // 리스트에 정보 추가
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
		// 거래처 정보를 담은 리스트를 반환한다.
		return list;
	}
}
