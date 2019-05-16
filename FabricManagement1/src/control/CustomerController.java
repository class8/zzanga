package control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerVO;

public class CustomerController implements Initializable {

	private static final String selectedCustomerIndex = null;
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
	private TableView<CustomerVO> customerTableView = new TableView<>();

	CustomerVO customer = new CustomerVO();
	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectStudent = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedIndex; // 테이블에서 선택한 정보 인덱스 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			// 고객등록초기화
			c_btnUpdate.setDisable(true);
			c_btnDelete.setDisable(true);

			@SuppressWarnings("rawtypes")

			TableColumn colC_no = new TableColumn("고객번호");
			colC_no.setPrefWidth(30);
			colC_no.setStyle("-fx-alignment: CENTER");
			colC_no.setCellValueFactory(new PropertyValueFactory<>("c_no"));

			TableColumn colC_name = new TableColumn("고객명");
			colC_name.setPrefWidth(30);
			colC_name.setStyle("-fx-alignment: CENTER");
			colC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colC_cname = new TableColumn("업체명");
			colC_cname.setPrefWidth(30);
			colC_cname.setStyle("-fx-alignment: CENTER");
			colC_cname.setCellValueFactory(new PropertyValueFactory<>("c_cname"));

			TableColumn colC_phone = new TableColumn("연락처");
			colC_phone.setPrefWidth(30);
			colC_phone.setStyle("-fx-alignment: CENTER");
			colC_cname.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colC_bnumber = new TableColumn("사업자번호");
			colC_bnumber.setPrefWidth(30);
			colC_bnumber.setStyle("-fx-alignment: CENTER");
			colC_bnumber.setCellValueFactory(new PropertyValueFactory<>("c_bnumber"));

			TableColumn colC_address = new TableColumn("주소");
			colC_address.setPrefWidth(30);
			colC_address.setStyle("-fx-alignment: CENTER");
			colC_address.setCellValueFactory(new PropertyValueFactory<>("c_address"));

			TableColumn colC_email = new TableColumn("이메일");
			colC_email.setPrefWidth(30);
			colC_email.setStyle("-fx-alignment: CENTER");
			colC_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));

			TableColumn colC_remarks = new TableColumn("비고");
			colC_remarks.setPrefWidth(30);
			colC_remarks.setStyle("-fx-alignment: CENTER");
			colC_remarks.setCellValueFactory(new PropertyValueFactory<>("c_remarks"));

			TableColumn colC_registdate = new TableColumn("등록일");
			colC_registdate.setPrefWidth(80);
			colC_registdate.setStyle("-fx-alignment: CENTER");
			colC_registdate.setCellValueFactory(new PropertyValueFactory<>("c_registdate"));

			customerTableView.setItems(customerDataList);

			customerTableView.getColumns().addAll(colC_no, colC_name, colC_cname, colC_phone, colC_bnumber,
					colC_address, colC_email, colC_remarks, colC_registdate);

			// 고객 전체 목록
			customerTotalList();

			c_btnRegist.setOnAction(event -> handlerC_btnRegistAction(event));
			c_btnInit.setOnAction(event -> handlerC_btnInitAction(event));
			c_btnUpdate.setOnAction(event -> handlerC_btnUpdateAction(event));
			c_btnDelete.setOnAction(event -> handlerC_btnDelete(event));
			c_btnExit.setOnAction(event -> handlerC_btnExitAction(event));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// 종료버튼 이벤트 핸들러
	public void handlerC_btnExitAction(ActionEvent event) {
	}

	// 삭제버튼 이벤트 핸들러
	public void handlerC_btnDelete(ActionEvent event) {

		try {

			boolean sucess;

			CustomerDAO cDao = new CustomerDAO();
			sucess = cDao.getCustomerDelete(selectedIndex);

			if (sucess) {

				customerDataList.removeAll(customerDataList);
				customerTotalList();

				c_txtNumber.clear();
				c_txtName.clear();
				c_txtCname.clear();
				c_txtPhone.clear();
				c_txtBnumber.clear();
				c_txtAddress.clear();
				c_txtEmail.clear();
				c_txtRemarks.clear();
				c_registdate.clear();

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
					c_txtAddress.getText().trim(), c_txtEmail.getText().trim(), c_txtRemarks.getText().trim());

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

			c_txtNumber.clear();
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
	private void customerTotalList() throws Exception {
		customerDataList.removeAll(customerDataList);

		CustomerDAO cDao = new CustomerDAO();
		CustomerVO cVo = null;
		ArrayList<String> title;
		ArrayList<CustomerVO> list;

		title = cDao.getCsutomerColumnName();
		int columnCount = title.size();

		list = cDao.getCustomerTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			cVo = list.get(index);
			customerDataList.add(cVo);
		}

	}

	/*
	 * c_btnRegist.setDisable(true); c_btnInit.setDisable(true);
	 * c_btnUpdate.setDisable(true); c_btnDelete.setDisable(true);
	 * c_btnExit.setDisable(true); dpDate.setValue(LocalDate.now());
	 */
}
