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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.AccountVO;
import model.CustomerVO;

public class CustomerController implements Initializable {

	@FXML
	private TextField c_txtNumber; // 고객번호
	@FXML
	private TextField c_txtName; // 고객명
	@FXML
	private TextField c_txtCname; // 업체명
	@FXML
	private TextField c_txtPhone; // 연락처
	@FXML
	private TextField c_txtBnumber; // 사업자번호
	@FXML
	private TextField c_txtAddress; // 주소
	@FXML
	private TextField c_txtEmail; // 이메일
	@FXML
	private TextArea c_txtRemarks; // 비고
	@FXML
	private TextField c_registdate; // 등록일
	@FXML
	private Button c_btnRegist; // 등록버튼
	@FXML
	private Button c_btnInit; // 초기화버튼
	@FXML
	private Button c_btnUpdate; // 수정버튼
	@FXML
	private Button c_btnDelete; // 삭제버튼
	@FXML
	private Button c_btnSearch; // 검색버튼
	@FXML
	private TextField c_txtSearch; // 검색
	@FXML
	private Button c_btnExit; // 종료버튼
	@FXML
	private DatePicker dpDate; // 등록일

	@FXML
	private TableView<CustomerVO> c_tableView = new TableView<>();

	CustomerVO customer = new CustomerVO();
	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectCustomer = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedCustomerIndex; // 테이블에서 선택한 정보 인덱스 저장
	private TextInputControl txtSearchWord;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			// 고객등록초기화
			c_btnUpdate.setDisable(true);
			c_btnDelete.setDisable(true);
			c_tableView.setEditable(false);

			TableColumn colC_number = new TableColumn("번호");
			colC_number.setPrefWidth(40);
			colC_number.setCellValueFactory(new PropertyValueFactory<>("c_number"));

			TableColumn colC_name = new TableColumn("고객명");
			colC_name.setPrefWidth(70);
			colC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colC_cname = new TableColumn("업체명");
			colC_cname.setPrefWidth(70);
			colC_cname.setCellValueFactory(new PropertyValueFactory<>("c_cname"));

			TableColumn colC_phone = new TableColumn("연락처");
			colC_phone.setPrefWidth(90);
			colC_cname.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colC_bnumber = new TableColumn("사업자번호");
			colC_bnumber.setPrefWidth(100);
			colC_bnumber.setCellValueFactory(new PropertyValueFactory<>("c_bnumber"));

			TableColumn colC_address = new TableColumn("주소");
			colC_address.setPrefWidth(150);
			colC_address.setCellValueFactory(new PropertyValueFactory<>("c_address"));

			TableColumn colC_email = new TableColumn("이메일");
			colC_email.setPrefWidth(100);
			colC_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));

			TableColumn colC_remarks = new TableColumn("비고");
			colC_remarks.setPrefWidth(150);
			colC_remarks.setCellValueFactory(new PropertyValueFactory<>("c_remarks"));

			TableColumn colC_registdate = new TableColumn("등록일");
			colC_registdate.setPrefWidth(80);
			colC_registdate.setCellValueFactory(new PropertyValueFactory<>("c_registdate"));

			c_tableView.setItems(customerDataList);

			c_tableView.getColumns().addAll(colC_number, colC_name, colC_cname, colC_phone, colC_bnumber, colC_address,
					colC_email, colC_remarks, colC_registdate);

			// 고객 전체 목록
			customerTotalList();

			c_btnRegist.setOnAction(event -> handlerC_btnRegistAction(event)); // 고객 등록 이벤트
			c_btnInit.setOnAction(event -> handlerC_btnInitAction(event)); // 초기화버튼 이벤트
			c_btnUpdate.setOnAction(event -> handlerC_btnUpdateAction(event)); // 수정버튼 이벤트
			c_btnDelete.setOnAction(event -> handlerC_btnDelete(event)); // 삭제버튼 이벤트
			c_btnExit.setOnAction(event -> handlerC_btnExitAction(event)); // 종료버튼 이벤트
			c_tableView.setOnMouseClicked(event -> handlerCustomerTableViewAction(event)); // 테이블뷰 마우스클릭 이벤트
			c_btnSearch.setOnAction(event -> handlerBtnSearchAction(event));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// 검색버튼 이벤트
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<CustomerVO> searchList = new ArrayList<CustomerVO>();
		CustomerVO cVo = null;
		CustomerDAO cDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = c_txtSearch.getText().trim();
			cDao = new CustomerDAO();
			searchList = cDao.getCustomerCheck(searchName);
			searchList = cDao.getCustomerCheck(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("고객 정보 검색");
				alert.setHeaderText("고객명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
			}

			if (searchList != null) {
				int rowCount = searchList.size();

				c_txtSearch.clear();
				customerDataList.removeAll(customerDataList);
				for (int index = 0; index < rowCount; index++) {
					cVo = searchList.get(index);
					customerDataList.add(cVo);
					searchResult = true;
				}
			}

			if (!searchResult) {
				c_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				customerTotalList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객 정보 검색 오류");
			alert.setHeaderText("고객 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

	// 테이블뷰마우스
	public void handlerCustomerTableViewAction(MouseEvent event) {

		if (event.getClickCount() == 2) {

			try {

				selectCustomer = c_tableView.getSelectionModel().getSelectedItems();
				selectedCustomerIndex = selectCustomer.get(0).getC_number();
				String selectedC_name = selectCustomer.get(0).getC_name();
				String selectedC_cname = selectCustomer.get(0).getC_cname();
				String selectedC_phone = selectCustomer.get(0).getC_phone();
				String selectedC_address = selectCustomer.get(0).getC_address();
				String selectedC_email = selectCustomer.get(0).getC_email();
				String selectedC_bnumber = selectCustomer.get(0).getC_bnumber();
				String selectedC_remarks = selectCustomer.get(0).getC_remarks();

				c_txtName.setText(selectedC_name);
				c_txtCname.setText(selectedC_cname);
				c_txtPhone.setText(selectedC_phone);
				c_txtAddress.setText(selectedC_address);
				c_txtEmail.setText(selectedC_email);
				c_txtBnumber.setText(selectedC_bnumber);
				c_txtRemarks.setText(selectedC_remarks);

				c_btnRegist.setDisable(true);
				c_btnUpdate.setDisable(false);
				c_btnInit.setDisable(false);
				c_btnExit.setDisable(false);
				c_btnDelete.setDisable(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 종료버튼 이벤트 핸들러
	public void handlerC_btnExitAction(ActionEvent event) {
		Platform.exit();

	}

	// 삭제버튼 이벤트 핸들러
	public void handlerC_btnDelete(ActionEvent event) {

		try {

			boolean sucess;

			CustomerDAO cDao = new CustomerDAO();
			sucess = cDao.getCustomerDelete(selectedCustomerIndex);

			if (sucess) {

				customerDataList.removeAll(customerDataList);
				customerTotalList();

				//c_txtNumber.clear();
				c_txtName.clear();
				c_txtCname.clear();
				c_txtPhone.clear();
				c_txtBnumber.clear();
				c_txtAddress.clear();
				c_txtEmail.clear();
				c_txtRemarks.clear();
				//c_registdate.clear();

				c_btnRegist.setDisable(false);
				c_btnUpdate.setDisable(true);
				c_btnDelete.setDisable(true);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 수정버튼 이벤트 핸들러
	public void handlerC_btnUpdateAction(ActionEvent event) {

		try {

			boolean sucess;

			CustomerDAO cdao = new CustomerDAO();

			sucess = cdao.getcustomerUpdate(selectedCustomerIndex, c_txtName.getText().trim(),
					c_txtCname.getText().trim(), c_txtPhone.getText().trim(), c_txtBnumber.getText().trim(),
					c_txtAddress.getText().trim(), c_txtEmail.getText().trim(), c_txtRemarks.getText().trim(), null);

			if (sucess) {
				customerDataList.removeAll(customerDataList);
				customerTotalList();

				c_txtName.clear();
				c_txtCname.clear();
				c_txtPhone.clear();
				c_txtBnumber.clear();
				c_txtAddress.clear();
				c_txtEmail.clear();
				c_txtRemarks.clear();
				c_txtName.requestFocus();

				c_btnDelete.setDisable(true);
				c_btnInit.setDisable(true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 초기화버튼 이벤트 핸들러
	public void handlerC_btnInitAction(ActionEvent event) {

		try {
			customerDataList.removeAll(customerDataList);
			customerTotalList();

			c_txtName.clear();
			c_txtCname.clear();
			c_txtPhone.clear();
			c_txtBnumber.clear();
			c_txtAddress.clear();
			c_txtEmail.clear();
			c_txtRemarks.clear();
			c_txtName.requestFocus();

			c_btnUpdate.setDisable(true);
			c_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 고객등록버튼 이벤트 핸들러
	public void handlerC_btnRegistAction(ActionEvent event) {

		try {

			customerDataList.removeAll(customerDataList);

			CustomerVO cvo = null;
			CustomerDAO cdao = null;

			cvo = new CustomerVO(c_txtName.getText().trim(), c_txtCname.getText().trim(), c_txtPhone.getText().trim(),
					c_txtBnumber.getText().trim(), c_txtAddress.getText().trim(), c_txtEmail.getText().trim(),
					c_txtRemarks.getText().trim());

			cdao = new CustomerDAO();
			cdao.getCustomerRegiste(cvo);

			if (cdao != null) {
				customerTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객 등록");
				alert.setHeaderText(c_txtName.getText() + " 고객님이 성공적으로 추가되었습니다.");
				alert.setContentText("다음 고객님을 입력하세요.");
				alert.showAndWait();

				c_txtNumber.clear();
				c_txtName.clear();
				c_txtCname.clear();
				c_txtPhone.clear();
				c_txtBnumber.clear();
				c_txtAddress.clear();
				c_txtEmail.clear();
				c_txtRemarks.clear();
				c_txtName.requestFocus();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("고객 등록");
			alert.setHeaderText("고객 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();

		}
	}

	// 고객전체 목록 메소드
	public void customerTotalList() throws Exception {
		customerDataList.removeAll(customerDataList);

		CustomerDAO cDao = new CustomerDAO();
		CustomerVO cVo = null;
		ArrayList<String> title;
		ArrayList<CustomerVO> list;

		title = cDao.getCustomerColumnName();
		int columnCount = title.size();

		list = cDao.getCustomerTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			cVo = list.get(index);
			customerDataList.add(cVo);
		}

	}

}