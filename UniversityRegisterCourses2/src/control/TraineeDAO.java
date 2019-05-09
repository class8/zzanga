package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.StudentVO;
import model.TraineeVO;

public class TraineeDAO {

	// �α����� �л��� ����
	public StudentVO getStudentSubjectName(String sd_id) throws Exception {

		String sql = "select sd_num, sd_name, (select s_name from subject where s_num = (select s_num from student where sd_id = ?)) as s_num from student where sd_id = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentVO studentInfo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd_id);
			pstmt.setString(2, sd_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				studentInfo = new StudentVO();
				studentInfo.setSd_num(rs.getString("sd_num"));
				studentInfo.setSd_name(rs.getString("sd_name"));
				studentInfo.setS_num(rs.getString("s_num"));
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
		return studentInfo;
	}

	// ������ ������� ���� ��ȣ
	public String getLessonNum(String lessonName) throws Exception {
		String l_num = "";

		String sql = "select l_num from lesson where l_name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lessonName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				l_num = rs.getString("l_num");
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ������ ���� ��ȣ");
				alert.setHeaderText("������ " + lessonName + " ������ �����ȣ�� �����ϴ�.");
				alert.setContentText("���� �˻� ����");
				alert.showAndWait();
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
		return l_num;
	}

	// ���� ��û
	public void getTraineeRegiste(TraineeVO tvo) throws Exception {

		String sql = "insert into trainee " + "(no, sd_num, l_num, t_section, t_date)" + " values "
				+ "(trainee_seq.nextval, ?, ?, ?, sysdate)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tvo.getSd_num());
			pstmt.setString(2, tvo.getL_num());
			pstmt.setString(3, tvo.getT_section());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ��û");
				alert.setHeaderText("���� ��û �Ϸ�.");
				alert.setContentText("���� ��û ����!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("���� ��û");
				alert.setHeaderText("���� ��û ����.");
				alert.setContentText("���� ��û ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// �α��� �� �л� ���� ��û ��ü ���(�й����� �˻�)
	public ArrayList<TraineeVO> getTraineeTotalList(String sd_num) throws Exception {
		ArrayList<TraineeVO> list = new ArrayList<>();

		String sql = "select tr.no as no, sd_num, le.l_name as l_num, t_section, t_date "
				+ "from trainee tr, lesson le " + "where tr.l_num = le.l_num and tr.sd_num = ? " + "order by t_date";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tVo = new TraineeVO();
				tVo.setNo(rs.getInt("no"));
				tVo.setSd_num(rs.getString("sd_num"));
				tVo.setL_num(rs.getString("l_num"));
				tVo.setT_section(rs.getString("t_section"));
				tVo.setT_date(rs.getString("t_date"));

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

	public ArrayList<String> getTraineeColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from trainee";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ResultSetMetaData ��ü ���� ����
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

	// ���� ��û ����
	public boolean getTraineeDelete(int no) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from trainee where no = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean subjectDeleteSucess = false;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ��û ���");
				alert.setHeaderText("���� ��û ��� �Ϸ�.");
				alert.setContentText("���� ��û ��� ����!!!");
				alert.showAndWait();
				subjectDeleteSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ��û ���");
				alert.setHeaderText("���� ��û ��� ����.");
				alert.setContentText("���� ��û ��� ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// �� �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return subjectDeleteSucess;
	}

	// ���� ��û ��ü ���
	public ArrayList<TraineeVO> getTraineeTotalList() throws Exception {
		ArrayList<TraineeVO> list = new ArrayList<>();

		String sql = "select tr.no as no, tr.sd_num, le.l_name as l_num, st.sd_name as sd_name, t_section, t_date "
				+ "from trainee tr, lesson le , student st "
				+ "where tr.l_num = le.l_num and tr.sd_num = st.sd_num order by t_date";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tVo = new TraineeVO();
				tVo.setNo(rs.getInt("no"));
				tVo.setSd_num(rs.getString("sd_num"));
				tVo.setSd_name(rs.getString("sd_name"));
				tVo.setL_num(rs.getString("l_num"));
				tVo.setT_section(rs.getString("t_section"));
				tVo.setT_date(rs.getString("t_date"));

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

	// ��ü ���� �ǿ��� �й� �˻�
	public ArrayList<TraineeVO> getTraineeStudentNumSearchList(String sd_num) throws Exception {
		ArrayList<TraineeVO> list = new ArrayList<>();

		String sql = "select tr.no as no, tr.sd_num, le.l_name as l_num, st.sd_name as sd_name, t_section, t_date "
				+ "from trainee tr, lesson le , student st "
				+ "where tr.l_num = le.l_num and tr.sd_num = st.sd_num and tr.sd_num = ? order by t_date";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tVo = new TraineeVO();
				tVo.setNo(rs.getInt("no"));
				tVo.setSd_num(rs.getString("sd_num"));
				tVo.setSd_name(rs.getString("sd_name"));
				tVo.setL_num(rs.getString("l_num"));
				tVo.setT_section(rs.getString("t_section"));
				tVo.setT_date(rs.getString("t_date"));

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

	// ��ü ���� �ǿ��� ���� �˻�
	public ArrayList<TraineeVO> getTraineeSubjectSearchList(String l_name) throws Exception {
		String l_num = getLessonNum(l_name);
		
		ArrayList<TraineeVO> list = new ArrayList<>();

		String sql = "select tr.no as no, tr.sd_num, le.l_name as l_num, st.sd_name as sd_name, t_section, t_date "
				+ "from trainee tr, lesson le , student st "
				+ "where tr.l_num = le.l_num and tr.l_num = ? and tr.sd_num = st.sd_num order by t_date";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, l_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tVo = new TraineeVO();
				tVo.setNo(rs.getInt("no"));
				tVo.setSd_num(rs.getString("sd_num"));
				tVo.setSd_name(rs.getString("sd_name"));
				tVo.setL_num(rs.getString("l_num"));
				tVo.setT_section(rs.getString("t_section"));
				tVo.setT_date(rs.getString("t_date"));

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

	// ��ü ���� �ǿ��� �л��̸� �˻�
	public ArrayList<TraineeVO> getTraineeStudentNameSearchList(String sd_name) throws Exception {
		ArrayList<TraineeVO> list = new ArrayList<>();

		String sql = "select tr.no as no, tr.sd_num, le.l_name as l_num, st.sd_name as sd_name, t_section, t_date "
				+ "from trainee tr, lesson le , student st "
				+ "where tr.l_num = le.l_num and tr.sd_num = st.sd_num and st.sd_name = ? order by t_date";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TraineeVO tVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sd_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tVo = new TraineeVO();
				tVo.setNo(rs.getInt("no"));
				tVo.setSd_num(rs.getString("sd_num"));
				tVo.setSd_name(rs.getString("sd_name"));
				tVo.setL_num(rs.getString("l_num"));
				tVo.setT_section(rs.getString("t_section"));
				tVo.setT_date(rs.getString("t_date"));

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
}
