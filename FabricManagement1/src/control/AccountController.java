package control;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import model.AccountVO;

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
	private TableView<AccountVO> a_tableView = new TableView<>();

	ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();
	ObservableList<AccountVO> selectAccount = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedAccountIndex; // 학생 등록 탭에서 선택한 학과 정보 인덱스 저장
	String accountNumber = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);
			a_txtNumber.setEditable(false);
			// a_txtNumber.setText(value);

			TableColumn colAnumber = new TableColumn("번호");
			colAnumber.setPrefWidth(40);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colAcname = new TableColumn("거래처명");
			colAcname.setPrefWidth(90);
			colAcname.setStyle("-fx-alignment: CENTER");
			colAcname.setCellValueFactory(new PropertyValueFactory<>("a_cname"));

			TableColumn colAmname = new TableColumn("담당자명");
			colAmname.setPrefWidth(90);
			colAmname.setStyle("-fx-alignment: CENTER");
			colAmname.setCellValueFactory(new PropertyValueFactory<>("a_mname"));

			TableColumn colAphone = new TableColumn("연락처");
			colAphone.setPrefWidth(100);
			colAphone.setStyle("-fx-alignment: CENTER");
			colAphone.setCellValueFactory(new PropertyValueFactory<>("a_phone"));

			TableColumn colAemail = new TableColumn("이메일");
			colAemail.setPrefWidth(100);
			colAemail.setStyle("-fx-alignment: CENTER");
			colAemail.setCellValueFactory(new PropertyValueFactory<>("a_email"));

			TableColumn colAbnumber = new TableColumn("사업자번호");
			colAbnumber.setPrefWidth(100);
			colAbnumber.setStyle("-fx-alignment: CENTER");
			colAbnumber.setCellValueFactory(new PropertyValueFactory<>("a_bnumber"));

			TableColumn colAmsubject = new TableColumn("주종목");
			colAmsubject.setPrefWidth(100);
			colAmsubject.setStyle("-fx-alignment: CENTER");
			colAmsubject.setCellValueFactory(new PropertyValueFactory<>("a_msubject"));

			TableColumn colAaddress = new TableColumn("주소");
			colAaddress.setPrefWidth(150);
			colAaddress.setStyle("-fx-alignment: CENTER");
			colAaddress.setCellValueFactory(new PropertyValueFactory<>("a_address"));

			TableColumn colAremarks = new TableColumn("비고");
			colAremarks.setPrefWidth(150);
			colAremarks.setStyle("-fx-alignment: CENTER");
			colAremarks.setCellValueFactory(new PropertyValueFactory<>("a_remarks"));

			TableColumn colAregistdate = new TableColumn("등록일");
			colAregistdate.setPrefWidth(90);
			colAregistdate.setStyle("-fx-alignment: CENTER");
			colAregistdate.setCellValueFactory(new PropertyValueFactory<>("a_registdate"));

			a_tableView.setItems(accountDataList);

			a_tableView.getColumns().addAll(colAnumber, colAcname, colAmname, colAphone, colAemail, colAbnumber,
					colAmsubject, colAaddress, colAremarks, colAregistdate);

			// 전체 목록
			accountTotalList();

			a_btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
			a_btnInit.setOnAction(event -> handlerBtnInitAction(event));
			a_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event));
			a_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
			a_btnExit.setOnAction(event -> handlerBtnExitAction(event));
			a_btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
			a_tableView.setOnMouseClicked(event -> handlerAccountTableViewAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object handlerAccountTableViewAction(MouseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	// 등록버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);

			AccountVO avo = null;
			AccountDAO adao = null;

			avo = new AccountVO(a_txtCname.getText().trim(), a_txtMname.getText().trim(), a_txtPhone.getText().trim(),
					a_txtEmail.getText().trim(), a_txtAddress.getText().trim(), a_txtBnumber.getText().trim(),
					a_txtMsubject.getText().trim(), a_txtRemarks.getText().trim());
			adao = new AccountDAO();
			adao.getAccountRegist(avo);

			if (adao != null) {
				accountTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("학생 입력");
				alert.setHeaderText(a_txtCname.getText() + " 거래처가 성공적으로 추가되었습니다.");
				alert.setContentText("다음 거래처를 입력하세요.");
				alert.showAndWait();

				a_txtNumber.clear();
				a_txtCname.clear();
				a_txtMname.clear();
				a_txtPhone.clear();
				a_txtEmail.clear();
				a_txtAddress.clear();
				a_txtBnumber.clear();
				a_txtMsubject.clear();
				a_txtRemarks.clear();
				a_txtCname.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("학과 정보 입력");
			alert.setHeaderText("학과 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 전체 목록
	public void accountTotalList() throws Exception {
		Object[][] totalData;

		AccountDAO aDao = new AccountDAO();
		AccountVO aVo = null;
		ArrayList<String> title;
		ArrayList<AccountVO> list;

		title = aDao.getAccountColumnName();
		int columnCount = title.size();

		list = aDao.getAccountTotalList();
		int rowCount = list.size();

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			accountDataList.add(aVo);
		}
	}

	// 전체 목록
	public void handlerBtnStudentTotalListAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);
			accountTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnInitAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);
			accountTotalList();

			a_txtNumber.clear();
			a_txtCname.clear();
			a_txtMname.clear();
			a_txtPhone.clear();
			a_txtEmail.clear();
			a_txtAddress.clear();
			a_txtBnumber.clear();
			a_txtMsubject.clear();
			a_txtRemarks.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnUpdateAction(ActionEvent event) {

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
