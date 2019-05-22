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

public class AccountInfoController implements Initializable {

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
	ObservableList<AccountVO> selectAccount = null;
	int selectedAccountIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			AccountDAO dao = new AccountDAO();
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);

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

			a_btnRegist.setOnAction(event -> handlerBtnRegistAction(event)); // 등록버튼 이벤트
			a_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			a_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			a_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			a_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			a_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			a_tableView.setOnMouseClicked(event -> handlerAccountTableViewAction(event)); // 테이블뷰 더블클릭 이벤트

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerAccountTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {

			try {
				selectAccount = a_tableView.getSelectionModel().getSelectedItems();
				selectedAccountIndex = selectAccount.get(0).getA_number();
				String selectedA_cname = selectAccount.get(0).getA_cname();
				String selectedA_mname = selectAccount.get(0).getA_mname();
				String selectedA_phone = selectAccount.get(0).getA_phone();
				String selectedA_email = selectAccount.get(0).getA_email();
				String selectedA_address = selectAccount.get(0).getA_address();
				String selectedA_bnumber = selectAccount.get(0).getA_bnumber();
				String selectedA_msubject = selectAccount.get(0).getA_msubject();
				String selectedA_remarks = selectAccount.get(0).getA_remarks();

				a_txtCname.setText(selectedA_cname);
				a_txtMname.setText(selectedA_mname);
				a_txtPhone.setText(selectedA_phone);
				a_txtEmail.setText(selectedA_email);
				a_txtAddress.setText(selectedA_address);
				a_txtBnumber.setText(selectedA_bnumber);
				a_txtMsubject.setText(selectedA_msubject);
				a_txtRemarks.setText(selectedA_remarks);

				a_txtCname.setEditable(false);

				a_btnUpdate.setDisable(false);
				a_btnDelete.setDisable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 등록버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);

			AccountVO avo = null;
			AccountDAO adao = null;

			if (a_txtCname.getLength() != 0 && a_txtMname.getLength() != 0 && a_txtPhone.getLength() != 0
					&& a_txtEmail.getLength() != 0 && a_txtAddress.getLength() != 0 && a_txtBnumber.getLength() != 0
					&& a_txtMsubject.getLength() != 0) {

				avo = new AccountVO(a_txtCname.getText().trim(), a_txtMname.getText().trim(),
						a_txtPhone.getText().trim(), a_txtEmail.getText().trim(), a_txtAddress.getText().trim(),
						a_txtBnumber.getText().trim(), a_txtMsubject.getText().trim(), a_txtRemarks.getText().trim());
				adao = new AccountDAO();
				adao.getAccountRegist(avo);

				accountTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 입력");
				alert.setHeaderText(a_txtCname.getText() + " 거래처가 성공적으로 추가되었습니다.");
				alert.setContentText("다음 거래처를 입력하세요.");
				alert.showAndWait();

				a_txtCname.clear();
				a_txtMname.clear();
				a_txtPhone.clear();
				a_txtEmail.clear();
				a_txtAddress.clear();
				a_txtBnumber.clear();
				a_txtMsubject.clear();
				a_txtRemarks.clear();
				a_txtCname.requestFocus();
			} else {
				accountDataList.removeAll(accountDataList);

				accountTotalList();

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 정보 미입력");
				alert.setHeaderText("거래처 정보중에 미입력된 항목이 있습니다.");
				alert.setContentText("거래처 정보를 정확히 입력하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 입력");
			alert.setHeaderText("거래처 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 전체 목록
	public void accountTotalList() throws Exception {

		accountDataList.removeAll(accountDataList);
		AccountDAO aDao = new AccountDAO();
		AccountVO aVo = null;
		ArrayList<String> title;
		ArrayList<AccountVO> list;

		title = aDao.getAccountColumnName();
		int columnCount = title.size();

		list = aDao.getAccountTotalList();
		int rowCount = list.size();

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

			a_txtCname.clear();
			a_txtMname.clear();
			a_txtPhone.clear();
			a_txtEmail.clear();
			a_txtAddress.clear();
			a_txtBnumber.clear();
			a_txtMsubject.clear();
			a_txtRemarks.clear();

			a_txtCname.setEditable(true);
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			AccountDAO sdao = new AccountDAO();
			sucess = sdao.getAccountUpdate(selectedAccountIndex, a_txtCname.getText().trim(),
					a_txtMname.getText().trim(), a_txtPhone.getText().trim(), a_txtEmail.getText().trim(),
					a_txtAddress.getText().trim(), a_txtBnumber.getText().trim(), a_txtMsubject.getText().trim(),
					a_txtRemarks.getText());

			if (sucess) {
				accountDataList.removeAll(accountDataList);
				accountTotalList();

				a_txtCname.clear();
				a_txtMname.clear();
				a_txtPhone.clear();
				a_txtEmail.clear();
				a_txtAddress.clear();
				a_txtBnumber.clear();
				a_txtMsubject.clear();
				a_txtRemarks.clear();

				a_txtCname.setEditable(true);
				a_btnUpdate.setDisable(true);
				a_btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnDeleteAction(ActionEvent event) {
		try {
			boolean sucess;
			AccountDAO sDao = new AccountDAO();
			sucess = sDao.getAccountDelete(selectedAccountIndex);

			if (sucess) {

				accountDataList.removeAll(accountDataList);
				accountTotalList();

				a_txtCname.clear();
				a_txtMname.clear();
				a_txtPhone.clear();
				a_txtEmail.clear();
				a_txtAddress.clear();
				a_txtBnumber.clear();
				a_txtMsubject.clear();
				a_txtRemarks.clear();

				a_txtCname.setEditable(true);
				a_btnUpdate.setDisable(true);
				a_btnDelete.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<AccountVO> searchList = new ArrayList<AccountVO>();
		AccountVO aVo = null;
		AccountDAO aDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = a_txtSearch.getText().trim();
			aDao = new AccountDAO();
			searchList = aDao.getAccountCheck(searchName);
			searchList = aDao.getAccountCheck(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 정보 검색");
				alert.setHeaderText("거래처명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
			}

			if (searchList != null) {
				int rowCount = searchList.size();

				a_txtSearch.clear();
				accountDataList.removeAll(accountDataList);
				for (int index = 0; index < rowCount; index++) {
					aVo = searchList.get(index);
					accountDataList.add(aVo);
					searchResult = true;
				}
			}

			if (!searchResult) {
				a_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				accountTotalList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("거래처 정보 검색 오류");
			alert.setHeaderText("거래처 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

}
