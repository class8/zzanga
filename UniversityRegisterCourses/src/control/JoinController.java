package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.JoinVO;

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

		btnOverlap.setOnAction(event -> handlerBtnOverlapAction(event));
		btnJoin.setOnAction(event -> handlerBtnJoinAction(event));
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));

		// 아이디 중복 검사
	}

	public void handlerBtnOverlapAction(ActionEvent event) {
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
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 있습니다.");
				alert.setContentText("패스워드를 입력하세요.");
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
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText("아이디를 입력하세요.");
				alert.setContentText("등록할 아이디를 입력하세요.");
				alert.showAndWait();
			} else {
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				txtId.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다.");
				alert.setContentText("패스워드를 입력하세요.");
				alert.showAndWait();
				txtId.requestFocus();
			}
		} catch (Exception e) {

		}

	}

	private Object handlerBtnCancelAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnJoinAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
