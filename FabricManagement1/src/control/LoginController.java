package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import control.LoginDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField l_txtId;
	@FXML
	private PasswordField l_txtPassword;
	@FXML
	private Button l_btnLogin;
	@FXML
	private Button l_btnExit;

	public static String idName;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l_txtId.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // 아이디 입력에서 Enter키 이벤트 적용
		l_txtPassword.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // 패스워드 입력에서 Enter키 이벤트 적용
		l_btnLogin.setOnAction(event -> handlerBtnLoginAction(event));
		l_btnExit.setOnAction(event -> handlerBtnExitAction(event));

	}

	// 아이디 입력에서 Enter키 이벤트 적용
	public void handerTxtIdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			l_txtPassword.requestFocus();
		}
	}

	// 패스워드 입력에서 Enter키 이벤트 적용
	public void handerTxtPasswordKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	// 로그인창 닫기
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 로그인
	public void handlerBtnLoginAction(ActionEvent event) {
		login();
	}

	// 로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO();

		boolean sucess = false;

		try {
			idName = loginName();
			sucess = login.getLogin(l_txtId.getText().trim(), l_txtPassword.getText().trim());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Alert alert;
		if (l_txtId.getText().equals("") || l_txtPassword.getText().equals("")) {
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디와 비밀번호 미입력");
			alert.setContentText("아이디와 비밀번호중 입력하지 않은 항목이 있습니다." + "\n 다시 제대로 입력하시오.");
			alert.setResizable(false);
			alert.showAndWait();
		}

		// 로그인 성공시 메인 페이지로 이동
		if (sucess) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/mainView.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scane = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("원단 관리 프로그램");
				mainMtage.setResizable(false);
				mainMtage.setScene(scane);
				Stage oldStage = (Stage) l_btnLogin.getScene().getWindow();
				oldStage.close();
				mainMtage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("오류" + e);
			}
		} else {
			// 아이디패스워드 확인하라는 창
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디와 비밀번호 불일치");
			alert.setContentText("아이디와 비밀번호가 일치하지 않습니다." + "\n 다시 제대로 입력하시오.");
			alert.setResizable(false);
			alert.showAndWait();

			l_txtId.clear();
			l_txtPassword.clear();
		}
	}

	// 아이디 불러오기
	public String loginName() {
		LoginDAO ldao = new LoginDAO();

		String id = null;

		try {
			id = ldao.getLoginId(l_txtId.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
