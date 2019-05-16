package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountVO;
import model.StudentVO;

public class AccountController implements Initializable {

	@FXML
	TextField a_txtNumber;
	@FXML
	TextField a_txtCname;
	@FXML
	TextField a_txtMname;
	@FXML
	TextField a_txtPhone;
	@FXML
	TextField a_txtEmail;
	@FXML
	TextField a_txtAddress;
	@FXML
	TextField a_txtBnumber;
	@FXML
	TextField a_txtMsubject;
	@FXML
	TextArea a_txtRemarks;
	@FXML
	Button a_btnRegist;
	@FXML
	Button a_btnInit;
	@FXML
	Button a_btnUpdate;
	@FXML
	Button a_btnDelete;
	@FXML
	Button a_btnExit;
	@FXML
	TextField a_txtSearch;
	@FXML
	Button a_btnSearch;
	@FXML
	private TableView<AccountVO> accountTableView = new TableView<>();

	ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();
	ObservableList<AccountVO> selectAccount = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedAccountIndex; // 학생 등록 탭에서 선택한 학과 정보 인덱스 저장
	String accountNumber = "";
	private String selectSubjectNum; // 선택한 학과명의 학과코드

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);

			TableColumn colAnumber = new TableColumn("거래처번호");
			colAnumber.setPrefWidth(30);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colAcname = new TableColumn("거래처명");
			colAcname.setPrefWidth(70);
			colAcname.setStyle("-fx-alignment: CENTER");
			colAcname.setCellValueFactory(new PropertyValueFactory<>("a_cname"));

			TableColumn colAmname = new TableColumn("담당자명");
			colAmname.setPrefWidth(80);
			colAmname.setStyle("-fx-alignment: CENTER");
			colAmname.setCellValueFactory(new PropertyValueFactory<>("a_mname"));

			TableColumn colAphone = new TableColumn("연락처");
			colAphone.setPrefWidth(80);
			colAphone.setStyle("-fx-alignment: CENTER");
			colAphone.setCellValueFactory(new PropertyValueFactory<>("a_phone"));

			TableColumn colAemail = new TableColumn("이메일");
			colAemail.setPrefWidth(80);
			colAemail.setStyle("-fx-alignment: CENTER");
			colAemail.setCellValueFactory(new PropertyValueFactory<>("a_email"));

			TableColumn colAbnumber = new TableColumn("사업자번호");
			colAbnumber.setPrefWidth(80);
			colAbnumber.setStyle("-fx-alignment: CENTER");
			colAbnumber.setCellValueFactory(new PropertyValueFactory<>("a_bnumber"));

			TableColumn colAmsubject = new TableColumn("주종목");
			colAmsubject.setPrefWidth(80);
			colAmsubject.setStyle("-fx-alignment: CENTER");
			colAmsubject.setCellValueFactory(new PropertyValueFactory<>("a_msubject"));

			TableColumn colAaddress = new TableColumn("주소");
			colAaddress.setPrefWidth(70);
			colAaddress.setStyle("-fx-alignment: CENTER");
			colAaddress.setCellValueFactory(new PropertyValueFactory<>("a_address"));

			TableColumn colAremarks = new TableColumn("비고");
			colAremarks.setPrefWidth(80);
			colAremarks.setStyle("-fx-alignment: CENTER");
			colAremarks.setCellValueFactory(new PropertyValueFactory<>("a_remarks"));

			TableColumn colAregistdate = new TableColumn("등록일");
			colAregistdate.setPrefWidth(80);
			colAregistdate.setStyle("-fx-alignment: CENTER");
			colAregistdate.setCellValueFactory(new PropertyValueFactory<>("a_registdate"));

			accountTableView.setItems(accountDataList);

			accountTableView.getColumns().addAll(colAnumber, colAcname, colAmname, colAphone, colAemail, colAbnumber,
					colAmsubject, colAaddress, colAremarks, colAregistdate);

			// 전체 목록
			// accountTotalList();

			a_btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
			a_btnInit.setOnAction(event -> handlerBtnInitAction(event));
			a_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event));
			a_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
			a_btnExit.setOnAction(event -> handlerBtnExitAction(event));
			a_btnSearch.setOnAction(event -> handlerBtnSearchAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 등록 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);

			AccountVO svo = null;
			AccountDAO sdao = null;

			svo = new AccountVO(a_txtCname.getText().trim(), a_txtMname.getText().trim(), a_txtPhone.getText().trim(),
					a_txtEmail.getText().trim(), a_txtAddress.getText().trim(), a_txtBnumber.getText().trim(),
					a_txtMsubject.getText().trim(), a_txtRemarks.getText().trim());
			sdao = new AccountDAO();
			sdao.getAccountRegist(svo);

			if (sdao != null) {
				accountTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 입력");
				alert.setHeaderText(a_txtCname.getText() + " 거래처가 성공적으로 추가되었습니다.");
				alert.setContentText("다음 학생을 입력하세요.");
				alert.showAndWait();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				selectSubjectNum = "";
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();
				txtsd_name.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText("학과 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	private Object handlerBtnInitAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnUpdateAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnDeleteAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	private Object handlerBtnSearchAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
