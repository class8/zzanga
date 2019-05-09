package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.JoinVO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class JoinController implements Initializable {

	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private PasswordField txtPasswordRepeat;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnJoin;
	@FXML
	private Button btnOverlap;

	JoinVO joinVO = new JoinVO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);
		txtPassword.setEditable(false);
		txtPasswordRepeat.setEditable(false);

		btnOverlap.setOnAction(event -> handlerBtnOverlapActoion(event)); // ���̵� �ߺ� �˻�
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event));   // ������ ���
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event));  // ���â �ݱ�
	}

	// ���̵� �ߺ� �˻�
	public void handlerBtnOverlapActoion(ActionEvent event) {
		btnJoin.setDisable(false);
		btnOverlap.setDisable(true);

		JoinDAO jDao = null;

		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtId.getText().trim();
			jDao = new JoinDAO();
			searchResult = (boolean) jDao.getIdOverlap(searchId);

			if (!searchResult && !searchId.equals("")) {
				txtId.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText(searchId + "�� ����� �� �ֽ��ϴ�.");
				alert.setContentText("�н���带 �Է��ϼ���.");
				alert.showAndWait();

				btnJoin.setDisable(false);
				btnOverlap.setDisable(true);
				txtPassword.setEditable(true);
				txtPasswordRepeat.setEditable(true);
				txtPassword.requestFocus();

			} else if (searchId.equals("")) {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText("���̵� �Է��Ͻÿ�.");
				alert.setContentText("����� ���̵� �Է��ϼ���!");
				alert.showAndWait();
			} else {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				txtId.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText(searchId + "�� ����� �� �����ϴ�.");
				alert.setContentText("���̵� �ٸ������� �Է��ϼ���.");
				alert.showAndWait();

				txtId.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���̵� �ߺ� �˻� ����");
			alert.setHeaderText("���̵� �ߺ� �˻翡 ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}
	}

	// ���â �ݱ�
	public void handlerBtnCancelActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("������ �α���");
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnJoin.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("����" + e);
		}
	}

	// ������ ���
	public void handlerBtnJoinActoion(ActionEvent event) {

		JoinVO jvo = null;
		JoinDAO jdao = null;

		boolean joinSucess = false;

		// �н����� Ȯ��
		if (txtPassword.getText().trim().equals(txtPasswordRepeat.getText().trim()) && !txtName.getText().trim().equals("")) {
			jvo = new JoinVO(txtId.getText().trim(), txtPassword.getText().trim(), txtName.getText().trim());
			jdao = new JoinDAO();
			try {
				joinSucess = jdao.getManagerRegiste(jvo);
				if (joinSucess) {
					handlerBtnCancelActoion(event);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			txtPassword.clear();
			txtPasswordRepeat.clear();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("�н�����, �̸� Ȯ��");
			alert.setHeaderText("�н�����, �̸� Ȯ�� �˻翡 ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�н������ �̸��� �ٽ� �Է��ϼ���.");
			alert.showAndWait();
		}
	}
}
