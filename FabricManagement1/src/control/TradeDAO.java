package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AccountVO;
import model.TradeVO;

public class TradeDAO {

	// 전체 목록
	public ArrayList<TradeVO> getTradeTotalList() throws Exception {

		ArrayList<TradeVO> list = new ArrayList<>();

		String sql = "select t_number, f.f_number, c.c_number, c_name, t_amount, t_price, t_deposit, t_penalty, t_balance, t_receipt, t_unpaid, t_status, c_phone, t_registdate, t_address, t_remarks,f_sort,f_name,f_color,f_size,f_weight,f_price,f_phone from trade t, customer c, fabric f where t.c_number=c.c_number and t.f_number=f.f_number";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TradeVO tVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tVo = new TradeVO();
				tVo.setT_number(rs.getInt("t_number"));
				tVo.setF_number(rs.getString("f_number"));
				tVo.setC_number(rs.getInt("c_number"));
				tVo.setC_name(rs.getString("c_name"));
				tVo.setT_amount(rs.getInt("t_amount"));
				tVo.setT_price(rs.getInt("t_price"));
				tVo.setT_deposit(rs.getInt("t_deposit"));
				tVo.setT_penalty(rs.getInt("t_penalty"));
				tVo.setT_balance(rs.getInt("t_balance"));
				tVo.setT_receipt(rs.getInt("t_receipt"));
				tVo.setT_unpaid(rs.getInt("t_unpaid"));
				tVo.setT_status(rs.getString("t_status"));
				tVo.setC_phone(rs.getString("c_phone"));
				tVo.setT_registdate(rs.getDate("t_registdate") + "");
				tVo.setT_address(rs.getString("t_address"));
				tVo.setT_remarks(rs.getString("t_remarks"));
				tVo.setF_f_sort(rs.getString("f_sort"));
				tVo.setF_f_name(rs.getString("f_name"));
				tVo.setF_f_color(rs.getString("f_color"));
				tVo.setF_f_size(rs.getString("f_size"));
				tVo.setF_f_weight(rs.getString("f_weight"));
				tVo.setF_f_price(rs.getString("f_price"));
				tVo.setF_f_phone(rs.getString("f_phone"));

				list.add(tVo);
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

	// 정보 수정
	public boolean getTradeUpdate(int t_number, String f_number, String c_number, String t_amount, String t_price,
			String t_deposit, String t_penalty, String t_balance, String t_receipt, String t_unpaid, String t_status,
			String t_registdate, String t_address, String t_remarks) throws Exception {

		String sql = "update trade set f_number=?, c_number=?, t_amount=?, t_price=?, t_deposit=?, t_penalty=?, t_balance=?, t_receipt=?, t_unpaid=?, t_status=?, t_registdate=?, t_address=?, t_remarks=? where t_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountUpdateSucess = false;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, f_number);
			pstmt.setInt(2, Integer.parseInt(c_number));
			pstmt.setInt(3, Integer.parseInt(t_amount));
			pstmt.setInt(4, Integer.parseInt(t_price));
			pstmt.setInt(5, Integer.parseInt(t_deposit));
			pstmt.setInt(6, Integer.parseInt(t_penalty));
			pstmt.setInt(7, Integer.parseInt(t_balance));
			pstmt.setInt(8, Integer.parseInt(t_receipt));
			pstmt.setInt(9, Integer.parseInt(t_unpaid));
			pstmt.setString(10, t_status);
			pstmt.setString(11, t_registdate);
			pstmt.setString(12, t_address);
			pstmt.setString(13, t_remarks);
			pstmt.setInt(14, t_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래 정보 수정");
				alert.setHeaderText(t_number + "번 거래 수정 완료.");
				alert.setContentText("거래 정보 수정 성공!");
				alert.showAndWait();
				accountUpdateSucess = true;
			} else {

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래 정보 수정");
				alert.setHeaderText("거래 정보 수정 실패");
				alert.setContentText("거래 정보 수정 실패!");
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
	public boolean getTradeDelete(int t_number) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from trade where t_number = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean accountDeleteSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, t_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래 정보 삭제");
				alert.setHeaderText(t_number + "번 거래 정보 삭제 완료.");
				alert.setContentText("거래 정보 삭제 성공!!!");
				alert.showAndWait();
				accountDeleteSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래 정보 삭제");
				alert.setHeaderText("거래 정보 삭제 실패");
				alert.setContentText("거래 정보 삭제 실패!");
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

	// 날짜와 고객명을 입력받아 정보 조회
	public ArrayList<TradeVO> getTradeCheck(String name, String startdate, String enddate) throws Exception {
		ArrayList<TradeVO> list = new ArrayList<TradeVO>();

		String sql = "select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number and t_registdate>? and t_registdate<? and c_name like ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TradeVO tVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);
			pstmt.setString(3, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				tVo = new TradeVO();
				tVo.setT_number(rs.getInt("t_number"));
				tVo.setF_number(rs.getString("f_number"));
				tVo.setC_number(rs.getInt("c_number"));
				tVo.setC_name(rs.getString("c_name"));
				tVo.setT_amount(rs.getInt("t_amount"));
				tVo.setT_price(rs.getInt("t_price"));
				tVo.setT_deposit(rs.getInt("t_deposit"));
				tVo.setT_penalty(rs.getInt("t_penalty"));
				tVo.setT_balance(rs.getInt("t_balance"));
				tVo.setT_receipt(rs.getInt("t_receipt"));
				tVo.setT_unpaid(rs.getInt("t_unpaid"));
				tVo.setT_status(rs.getString("t_status"));
				tVo.setC_phone(rs.getString("c_phone"));
				tVo.setT_registdate(rs.getString("t_registdate"));
				tVo.setT_address(rs.getString("t_address"));
				tVo.setT_remarks(rs.getString("t_remarks"));
				list.add(tVo);
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
	public ArrayList<TradeVO> getTradeCheck(String name) throws Exception {
		ArrayList<TradeVO> list = new ArrayList<TradeVO>();

		String sql = "select t_number,f_number,c.c_number,c_name,t_amount,t_price,t_deposit,t_penalty,t_balance,t_receipt,t_unpaid,t_status,c_phone,t_registdate,t_address,t_remarks from trade t, customer c where t.c_number=c.c_number and c_name like ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TradeVO tVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {

				tVo = new TradeVO();
				tVo.setT_number(rs.getInt("t_number"));
				tVo.setF_number(rs.getString("f_number"));
				tVo.setC_number(rs.getInt("c_number"));
				tVo.setC_name(rs.getString("c_name"));
				tVo.setT_amount(rs.getInt("t_amount"));
				tVo.setT_price(rs.getInt("t_price"));
				tVo.setT_deposit(rs.getInt("t_deposit"));
				tVo.setT_penalty(rs.getInt("t_penalty"));
				tVo.setT_balance(rs.getInt("t_balance"));
				tVo.setT_receipt(rs.getInt("t_receipt"));
				tVo.setT_unpaid(rs.getInt("t_unpaid"));
				tVo.setT_status(rs.getString("t_status"));
				tVo.setC_phone(rs.getString("c_phone"));
				tVo.setT_registdate(rs.getString("t_registdate"));
				tVo.setT_address(rs.getString("t_address"));
				tVo.setT_remarks(rs.getString("t_remarks"));
				list.add(tVo);
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

	public boolean getStatusUpdate(int t_number, String t_status) throws Exception {
		boolean sucess = false;

		String sql = "update trade set t_status=? where t_number=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, t_status);
			pstmt.setInt(2, t_number);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("상태 정보 변경");
				alert.setHeaderText(t_number + "번 상태 변경 완료.");
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
}
