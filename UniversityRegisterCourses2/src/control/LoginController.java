package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;

public class LoginController implements Initializable {

	@FXML
	private Label lblLogin;
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnJoin;
	@FXML
	private ToggleGroup loginGroup;
	@FXML
	private RadioButton rbManager;
	@FXML
	private RadioButton rbStudent;
	@FXML
	private Label lblIconImg;
	@FXML
	private ImageView iconImg;

	public static String managerName ="";
	public static String studentId = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		txtId.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // ���̵� �Է¿��� EnterŰ �̺�Ʈ ����
		txtPassword.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // �н����� �Է¿��� EnterŰ �̺�Ʈ ����
		btnJoin.setOnAction(event -> handerBtnJoinAction(event)); // ������ ���â���� ��ȯ
		btnLogin.setOnAction(event -> handlerBtnLoginActoion(event)); // �α���
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // �α���â �ݱ�
		rbManager.setOnAction(event -> handlerRbManagerActoion(event));
		rbStudent.setOnAction(event -> handlerRbStudentActoion(event));

	}

	// �л� ���� ��ư �̺�Ʈ �ڵ鷯
	public void handlerRbStudentActoion(ActionEvent event) {
		URL srtImg = getClass().getResource("../image/student.png");
		Image image = new Image(srtImg.toString());
		iconImg.setImage(image);
		lblLogin.setText("�л� �α���");
		btnJoin.setDisable(true);
		btnLogin.setText("�л� �α���");
	}

	// ������ ���� ��ư �̺�Ʈ �ڵ鷯
	public void handlerRbManagerActoion(ActionEvent event) {
		URL srtImg = getClass().getResource("../image/manager.png");
		Image image = new Image(srtImg.toString());
		iconImg.setImage(image);
		lblLogin.setText("������ �α���");
		btnJoin.setDisable(false);
		btnLogin.setText("������ �α���");
	}

	// ���̵� �Է¿��� EnterŰ �̺�Ʈ ����
	public void handerTxtIdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtPassword.requestFocus();
		}
	}

	// �н����� �Է¿��� EnterŰ �̺�Ʈ ����
	public void handerTxtPasswordKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	// ������ ���â���� ��ȯ
	public void handerBtnJoinAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/join.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("������ ���");
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnLogin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("����" + e);
		}
	}

	// �α���â �ݱ�
	public void handlerBtnCancelActoion(ActionEvent event) {
		Platform.exit();
	}

	// �α���
	public void handlerBtnLoginActoion(ActionEvent event) {
		login();
	}

	// �α��� �޼ҵ�
	public void login() {
		LoginDAO login = new LoginDAO();
		StudentDAO sLogin = new StudentDAO();

		boolean sucess = false;

		try {
			if ("manager".equals(loginGroup.getSelectedToggle().getUserData().toString())) {
				managerName = managerLoginName();
				sucess = login.getLogin(txtId.getText().trim(), txtPassword.getText().trim());
				// �α��� ������ ���� �������� �̵�
				if (sucess) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
						Parent mainView = (Parent) loader.load();
						Scene scane = new Scene(mainView);
						Stage mainMtage = new Stage();
						mainMtage.setTitle("�̷� ���б� �л����");
						mainMtage.setResizable(false);
						mainMtage.setScene(scane);
						Stage oldStage = (Stage) btnLogin.getScene().getWindow();
						oldStage.close();
						mainMtage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.err.println("����" + e);
					}
				} else {
					// ���̵��н����� Ȯ���϶�� â
					Alert alert;
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("�α��� ����");
					alert.setHeaderText("���̵�� ��й�ȣ ����ġ");
					alert.setContentText("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�." + "\n �ٽ� ����� �Է��Ͻÿ�.");
					alert.setResizable(false);
					alert.showAndWait();

					txtId.clear();
					txtPassword.clear();
				}

			} else if("student".equals(loginGroup.getSelectedToggle().getUserData().toString())){
				sucess = sLogin.getLogin(txtId.getText().trim(), txtPassword.getText().trim());
				// �α��� ������ ���� �������� �̵�
				if (sucess) {
					try {
						studentId= txtId.getText();
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/trainee.fxml"));
						Parent mainView = (Parent) loader.load();
						Scene scane = new Scene(mainView);
						Stage mainMtage = new Stage();
						mainMtage.setTitle("�̷� ���б� �л����");
						mainMtage.setResizable(false);
						mainMtage.setScene(scane);
						Stage oldStage = (Stage) btnLogin.getScene().getWindow();
						oldStage.close();
						mainMtage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.err.println("����" + e);
					}
				} else {
					// ���̵��н����� Ȯ���϶�� â
					Alert alert;
					alert = new Alert(AlertType.WARNING);
					alert.setTitle("�α��� ����");
					alert.setHeaderText("���̵�� ��й�ȣ ����ġ");
					alert.setContentText("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�." + "\n �ٽ� ����� �Է��Ͻÿ�.");
					alert.setResizable(false);
					alert.showAndWait();

					txtId.clear();
					txtPassword.clear();
				}

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		
		if (txtId.getText().equals("") || txtPassword.getText().equals("")) {
			Alert alert;
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("�α��� ����");
			alert.setHeaderText("���̵�� ��й�ȣ ���Է�");
			alert.setContentText("���̵�� ��й�ȣ�� �Է����� ���� �׸��� �ֽ��ϴ�." + "\n �ٽ� ����� �Է��Ͻÿ�.");
			alert.setResizable(false);
			alert.showAndWait();
		}

		
	}

	public String managerLoginName() {
		LoginDAO ldao = new LoginDAO();

		String name = null;

		try {
			name = ldao.getLoginName(txtId.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	public String studentLoginName() {
		StudentDAO sdao = new StudentDAO();

		String name = null;

		try {
			name = sdao.getLoginName(txtId.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
}
