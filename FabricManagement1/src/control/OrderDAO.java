package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AccountVO;
import model.OrderVO;

public class OrderDAO {

	// 주문 등록
	public void getOrderRegist(OrderVO ovo) throws Exception {

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

	// 전체 목록
	public ArrayList<OrderVO> getOrderTotalList() throws Exception {

		ArrayList<OrderVO> list = new ArrayList<>();

		String sql = "select o_number, o.a_number, f.f_number, c.c_number, o_email, o_address, o_amount, o_total, o_status, o_registdate, o_remarks, f_name,  c_name, c_phone from order1 o, fabric f, account a, customer c where o.f_number=f.f_number and o.a_number=a.a_number and o.c_number=c.c_number";
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
}
