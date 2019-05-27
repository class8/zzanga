package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.CustomerVO;
import model.FabricVO;

public class FabricDAO {

	// 원단 전체 목록
	public ArrayList<FabricVO> getFabricTotalList() throws Exception {

		ArrayList<FabricVO> list = new ArrayList<>();

		String sql = "select * from fabric order by f_number";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FabricVO fVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				fVo = new FabricVO();
				fVo.setF_number(rs.getString("f_number"));
				fVo.setF_sort(rs.getString("f_sort"));
				fVo.setF_name(rs.getString("f_name"));
				fVo.setF_color(rs.getString("f_color"));
				fVo.setF_size(rs.getString("f_size"));
				fVo.setF_material(rs.getString("f_material"));
				fVo.setF_origin(rs.getString("f_origin"));
				fVo.setF_cname(rs.getString("f_cname"));
				fVo.setF_phone(rs.getString("f_phone"));
				fVo.setF_weight(rs.getString("f_weight"));
				fVo.setF_trait(rs.getString("f_trait"));
				fVo.setF_price(rs.getString("f_price"));
				fVo.setF_remarks(rs.getString("f_remarks"));
				fVo.setF_registdate(rs.getString("f_registdate"));
				fVo.setFilename(rs.getString("filename"));
				list.add(fVo);

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

	// 원단 등록
	public void getFabricRegist(FabricVO fvo) throws Exception {

		String sql = "insert into fabric values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(1, fvo.getF_number());
			pstmt.setString(2, fvo.getF_sort());
			pstmt.setString(3, fvo.getF_name());
			pstmt.setString(4, fvo.getF_color());
			pstmt.setString(5, fvo.getF_size());
			pstmt.setString(6, fvo.getF_material());
			pstmt.setString(7, fvo.getF_origin());
			pstmt.setString(8, fvo.getF_cname());
			pstmt.setString(9, fvo.getF_phone());
			pstmt.setString(10, fvo.getF_weight());
			pstmt.setString(11, fvo.getF_trait());
			pstmt.setString(12, fvo.getF_price());
			pstmt.setString(13, fvo.getF_remarks());
			pstmt.setString(14, fvo.getFilename());

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 등록");
				alert.setHeaderText(fvo.getF_name() + " 원단 등록 완료.");
				alert.setContentText("원단 등록 성공!!!");
				alert.showAndWait();

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("원단 등록");
				alert.setHeaderText("원단 등록 실패");
				alert.setContentText("원단 등록 실패!");
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
	public ArrayList<String> getFabricColumnName() throws Exception {

		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();

		sql.append("select * from fabric order by f_number");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
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

	// 원단정보삭제 메소드
	public boolean getFabricDelete(String f_number) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from fabric where f_number = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean fabricDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, f_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 정보 삭제");
				alert.setHeaderText("원단 정보 삭제 완료.");
				alert.setContentText("원단 정보 삭제 성공!!!");
				alert.showAndWait();
				fabricDeleteSucess = true;

			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 삭제");
				alert.setHeaderText("원단 정보 삭제 실패");
				alert.setContentText("원단 정보 삭제 실패!");
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

		return fabricDeleteSucess;
	}

	// 거래처명을 입력받아 정보 조회
	public ArrayList<FabricVO> getFabricCheck(String name) throws Exception {

		ArrayList<FabricVO> list = new ArrayList<FabricVO>();

		String sql = "select * from fabric where f_name like ? order by f_number";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FabricVO fVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				fVo = new FabricVO();
				fVo.setF_number(rs.getString("f_number"));
				fVo.setF_sort(rs.getString("f_sort"));
				fVo.setF_name(rs.getString("f_name"));
				fVo.setF_color(rs.getString("f_color"));
				fVo.setF_size(rs.getString("f_size"));
				fVo.setF_origin(rs.getString("f_origin"));
				fVo.setF_cname(rs.getString("f_cname"));
				fVo.setF_phone(rs.getString("f_phone"));
				fVo.setF_weight(rs.getString("f_weight"));
				fVo.setF_price(rs.getString("f_price"));
				fVo.setF_material(rs.getString("f_material"));
				fVo.setF_trait(rs.getString("f_trait"));
				fVo.setF_remarks(rs.getString("f_remarks"));
				list.add(fVo);
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

	// 고객번호를 받아 정보조회

	public ArrayList<String> getSearchNumber(String number) throws Exception {

		ArrayList<String> list = new ArrayList<String>();
		String sql = "select c_name, c_phone, c_email, c_address from customer where c_number=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, number);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				cVo = new CustomerVO();
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_phone(rs.getString("c_phone"));
				cVo.setC_email(rs.getString("c_email"));
				cVo.setC_address(rs.getString("c_address"));

				list.add(cVo.getC_name());
				list.add(cVo.getC_phone());
				list.add(cVo.getC_email());
				list.add(cVo.getC_address());

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

	// 고객정보 수정
	public boolean getfabricUpdate(FabricVO fvo) throws Exception {

		String sql = "update fabric set f_color=?, f_size=?, f_material=?, f_origin=?, f_cname=?, "
				+ "f_phone=?, f_weight=?, f_trait=?, f_price=?, f_remarks=?, filename=? where f_number=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean fabricUpdateSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fvo.getF_color());
			pstmt.setString(2, fvo.getF_size());
			pstmt.setString(3, fvo.getF_material());
			pstmt.setString(4, fvo.getF_origin());
			pstmt.setString(5, fvo.getF_cname());
			pstmt.setString(6, fvo.getF_phone());
			pstmt.setString(7, fvo.getF_weight());
			pstmt.setString(8, fvo.getF_trait());
			pstmt.setString(9, fvo.getF_price());
			pstmt.setString(10, fvo.getF_remarks());
			pstmt.setString(11, fvo.getFilename());
			pstmt.setString(12, fvo.getF_number());

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단정보 수정");
				alert.setHeaderText(" 원단정보 수정 완료.");
				alert.setContentText("원단정보 수정 성공!!!");
				alert.showAndWait();
				fabricUpdateSucess = true;

			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단정보 수정");
				alert.setHeaderText("원단정보 수정 실패");
				alert.setContentText("원단정보 수정 실패!");
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
		return fabricUpdateSucess;
	}
}
