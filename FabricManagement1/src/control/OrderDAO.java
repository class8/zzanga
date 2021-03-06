package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AccountVO;
import model.OrderVO;
import model.TradeVO;

public class OrderDAO {

	// 주문 등록
	public boolean getOrderRegist(OrderVO ovo) throws Exception {
		boolean sucess = false;
		String sql = "insert into order1 values " + "(order_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ovo.getA_number());
			pstmt.setString(2, ovo.getF_number());
			pstmt.setInt(3, ovo.getC_number());
			pstmt.setString(4, ovo.getO_email());
			pstmt.setString(5, ovo.getO_address());
			pstmt.setInt(6, ovo.getO_amount());
			pstmt.setInt(7, ovo.getO_total());
			pstmt.setString(8, ovo.getO_status());
			pstmt.setString(9, ovo.getO_remarks());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("주문 입력");
				alert.setHeaderText("주문이 성공적으로 추가되었습니다.");
				alert.setContentText("다음 주문을 입력하세요.");
				alert.showAndWait();
				sucess = true;
			}
		} catch (SQLIntegrityConstraintViolationException sqle) {
			System.out.println("e=[" + sqle + "]");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("주문 등록 실패");
			alert.setHeaderText("거래처번호가 없으므로 주문 등록에 실패하였습니다.");
			alert.setContentText("다시 한번 확인후 시도하세요.");
			alert.showAndWait();
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("주문 정보 등록 실패");
			alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
			alert.setContentText("주문 정보 등록에 실패하였습니다.");
			alert.showAndWait();
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
		return sucess;
	}

	// 전체 목록
	public ArrayList<OrderVO> getOrderTotalList() throws Exception {

		ArrayList<OrderVO> list = new ArrayList<>();

		String sql = "select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number order by o_number";
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
				oVo.setC_number(rs.getInt("c_number"));
				oVo.setO_email(rs.getString("o_email"));
				oVo.setO_address(rs.getString("o_address"));
				oVo.setO_amount(rs.getInt("o_amount"));
				oVo.setO_total(rs.getInt("o_total"));
				oVo.setO_status(rs.getString("o_status"));
				oVo.setO_registdate(rs.getDate("o_registdate") + "");
				oVo.setO_remarks(rs.getString("o_remarks"));
				oVo.setC_name(rs.getString("c_name"));
				oVo.setC_phone(rs.getString("c_phone"));
				oVo.setF_name(rs.getString("f_name"));

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

	// 검색
	public String getSearchName(String name) throws Exception {

		String sql = "select a_cname from account where a_number=?";
		String result = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AccountVO aVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				aVo = new AccountVO();
				aVo.setA_cname(rs.getString("a_cname"));
				result = aVo.getA_cname();
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
		return result;
	}

	public boolean getStatusUpdate(int o_number, String o_status) throws Exception {
		boolean sucess = false;

		String sql = "update order1 set o_status=? where o_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, o_status);
			pstmt.setInt(2, o_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상태 정보 변경");
				alert.setHeaderText(o_number + "번 주문 상태 변경 완료.");
				alert.setContentText("상태 정보 변경 성공!");
				alert.showAndWait();
				sucess = true;
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("상태 정보 변경");
				alert.setHeaderText("상태 정보 변경 실패");
				alert.setContentText("상태 정보 변경 실패!");
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
		return sucess;
	}

	// 날짜와 고객명을 입력받아 정보 조회
	public ArrayList<OrderVO> getOrderCheck(String name, String startdate, String enddate) throws Exception {
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();

		String sql = "select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number and o_registdate>? and o_registdate<? and c_name like ? order by o_number";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);
			pstmt.setString(3, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setO_number(rs.getInt("o_number"));
				oVo.setA_number(rs.getInt("a_number"));
				oVo.setF_number(rs.getString("f_number"));
				oVo.setC_number(rs.getInt("c_number"));
				oVo.setO_email(rs.getString("o_email"));
				oVo.setO_address(rs.getString("o_address"));
				oVo.setO_amount(rs.getInt("o_amount"));
				oVo.setO_total(rs.getInt("o_total"));
				oVo.setO_status(rs.getString("o_status"));
				oVo.setO_registdate(rs.getDate("o_registdate") + "");
				oVo.setO_remarks(rs.getString("o_remarks"));
				oVo.setC_name(rs.getString("c_name"));
				oVo.setC_phone(rs.getString("c_phone"));
				oVo.setF_name(rs.getString("f_name"));

				list.add(oVo);
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

	// 고객명을 입력받아 정보 조회
	public ArrayList<OrderVO> getOrderCheck(String name) throws Exception {
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();

		String sql = "select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number and c_name like ? order by o_number";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setO_number(rs.getInt("o_number"));
				oVo.setA_number(rs.getInt("a_number"));
				oVo.setF_number(rs.getString("f_number"));
				oVo.setC_number(rs.getInt("c_number"));
				oVo.setO_email(rs.getString("o_email"));
				oVo.setO_address(rs.getString("o_address"));
				oVo.setO_amount(rs.getInt("o_amount"));
				oVo.setO_total(rs.getInt("o_total"));
				oVo.setO_status(rs.getString("o_status"));
				oVo.setO_registdate(rs.getDate("o_registdate") + "");
				oVo.setO_remarks(rs.getString("o_remarks"));
				oVo.setC_name(rs.getString("c_name"));
				oVo.setC_phone(rs.getString("c_phone"));
				oVo.setF_name(rs.getString("f_name"));

				list.add(oVo);
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

	// 정보 수정
	public boolean getOrderUpdate(String o_number, String a_number, String f_number, String c_number, String o_email,
			String o_address, String o_amount, String o_total, String o_status, String o_registdate, String o_remarks)
			throws Exception {

		String sql = "update order1 set a_number=?, f_number=?, c_number=?, o_email=?, o_address=?, o_amount=?, o_total=?, o_status=?, o_registdate=?, o_remarks=? where o_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, a_number);
			pstmt.setString(2, f_number);
			pstmt.setString(3, c_number);
			pstmt.setString(4, o_email);
			pstmt.setString(5, o_address);
			pstmt.setString(6, o_amount);
			pstmt.setString(7, o_total);
			pstmt.setString(8, o_status);
			pstmt.setString(9, o_registdate);
			pstmt.setString(10, o_remarks);
			pstmt.setString(11, o_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("주문 내역 수정");
				alert.setHeaderText(o_number + "번 주문 수정 완료.");
				alert.setContentText("주문 내역 수정 성공!");
				alert.showAndWait();
				accountUpdateSucess = true;
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("주문 정보 수정 실패");
			alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
			alert.setContentText("주문 정보 수정에 실패하였습니다.");
			alert.showAndWait();
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
	public boolean getOrderDelete(int o_number) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from order1 where o_number = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, o_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("주문 정보 삭제");
				alert.setHeaderText(o_number + "번 주문 정보 삭제 완료.");
				alert.setContentText("주문 정보 삭제 성공!!!");
				alert.showAndWait();
				accountDeleteSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("주문 정보 삭제");
				alert.setHeaderText("주문 정보 삭제 실패");
				alert.setContentText("주문 정보 삭제 실패!");
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
}
