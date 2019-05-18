package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.FabricVO;

public class FabricDAO {

	// 원단 등록
	public void getFabricRegist(FabricVO fvo) throws Exception {

		String sql = "insert into fabric values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fvo.getF_number());
			pstmt.setString(2, fvo.getF_sort());
			pstmt.setString(3, fvo.getF_name());
			pstmt.setString(4, fvo.getF_color());
			pstmt.setString(5, fvo.getF_size());
			pstmt.setString(6, fvo.getF_weight());
			pstmt.setString(7, fvo.getF_origin());
			pstmt.setString(8, fvo.getF_cname());
			pstmt.setString(9, fvo.getF_price());
			pstmt.setString(10, fvo.getF_material());
			pstmt.setString(11, fvo.getF_trait());
			pstmt.setString(12, fvo.getF_remarks());

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
	public ArrayList<String> getFabricColumnName() throws Exception {

		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from fabric order by f_number";
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
				fVo.setF_weight(rs.getString("f_weight"));
				fVo.setF_origin(rs.getString("f_origin"));
				fVo.setF_cname(rs.getString("f_cname"));
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
	public boolean getFabricUpdate(String f_number, String f_sort, String f_name, String f_color, String f_size,
			String f_origin, String f_cname, String f_phone, String f_weight, String f_price, String f_material,
			String f_trait, String f_remarks) throws Exception {
		
		String sql = "update fabric set f_sort=?, f_name=?, f_color=?, f_size=?, f_origin=?, f_cname=?, f_phone=?, f_weight=?, f_price=?, f_material=?, f_trait=?, f_remarks=? where f_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean fabricUpdateSucess = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, f_sort);
			pstmt.setString(2, f_name);
			pstmt.setString(3, f_color);
			pstmt.setString(4, f_size);
			pstmt.setString(5, f_weight);
			pstmt.setString(6, f_origin);
			pstmt.setString(7, f_cname);
			pstmt.setString(8, f_price);
			pstmt.setString(9, f_material);
			pstmt.setString(10, f_trait);
			pstmt.setString(11, f_remarks);
			pstmt.setString(12, f_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 정보 수정");
				alert.setHeaderText(f_name + " 원단 수정 완료.");
				alert.setContentText("원단 정보 수정 성공!");
				alert.showAndWait();
				fabricUpdateSucess = true;
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 수정");
				alert.setHeaderText("원단 정보 수정 실패");
				alert.setContentText("원단 정보 수정 실패!");
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

	// 정보 삭제
	public boolean getFabricDelete(String f_number) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from fabric where f_number = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, f_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 정보 삭제");
				alert.setHeaderText(f_number + "번 원단 정보 삭제 완료.");
				alert.setContentText("원단 정보 삭제 성공!");
				alert.showAndWait();
				accountDeleteSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
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
				System.out.println(e);
			}
		}
		return accountDeleteSucess;
	}

	// 거래처명을 입력받아 정보 조회
	public ArrayList<FabricVO> getFabricCheck(String name) throws Exception {
		ArrayList<FabricVO> list = new ArrayList<FabricVO>();

		String sql = "select * from fabric where f_name like ? order by f_number desc";

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
}
