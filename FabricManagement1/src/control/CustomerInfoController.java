package control;

import java.net.URL;
import java.text.MessageFormat;
import java.text.ParsePosition;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.CustomerVO;

public class CustomerInfoController implements Initializable {

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
	private TableView<CustomerVO> c_tableView = new TableView<>(); // customer테이블뷰

	CustomerVO customer = new CustomerVO(); // customerVO 호출
	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList(); // 학생등록테이블 어레이리스트
	ObservableList<CustomerVO> selectCustomer = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedCustomerIndex; // 테이블에서 선택한 정보 인덱스 저장

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		c_txtName.setPrefColumnCount(5);

		try {

			// 고객등록초기화
			c_btnUpdate.setDisable(true); // 수정버튼 설정
			c_btnDelete.setDisable(true); // 삭제버튼 설정
			c_tableView.setEditable(false); // 테이블뷰 설정

			// 고객테이블뷰에 들어갈 컬럼이름 설정
			TableColumn colC_number = new TableColumn("번호"); // 컬럼명
			colC_number.setPrefWidth(40); // 컬럼의 가로 길이 설정
			colC_number.setStyle("-fx-alignment: CENTER"); // 컬럼값 가운데 정렬
			colC_number.setCellValueFactory(new PropertyValueFactory<>("c_number"));// 컬럼 값 설정

			TableColumn colC_name = new TableColumn("고객명");
			colC_name.setPrefWidth(100);
			colC_name.setStyle("-fx-alignment: CENTER");
			colC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colC_cname = new TableColumn("업체명");
			colC_cname.setPrefWidth(100);
			colC_cname.setStyle("-fx-alignment: CENTER");
			colC_cname.setCellValueFactory(new PropertyValueFactory<>("c_cname"));

			TableColumn colC_phone = new TableColumn("연락처");
			colC_phone.setPrefWidth(100);
			colC_phone.setStyle("-fx-alignment: CENTER");
			colC_phone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colC_bnumber = new TableColumn("사업자번호");
			colC_bnumber.setPrefWidth(100);
			colC_bnumber.setStyle("-fx-alignment: CENTER");
			colC_bnumber.setCellValueFactory(new PropertyValueFactory<>("c_bnumber"));

			TableColumn colC_address = new TableColumn("주소");
			colC_address.setPrefWidth(100);
			colC_address.setStyle("-fx-alignment: CENTER");
			colC_address.setCellValueFactory(new PropertyValueFactory<>("c_address"));

			TableColumn colC_email = new TableColumn("이메일");
			colC_email.setPrefWidth(100);
			colC_email.setStyle("-fx-alignment: CENTER");
			colC_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));

			TableColumn colC_remarks = new TableColumn("비고");
			colC_remarks.setPrefWidth(100);
			colC_remarks.setStyle("-fx-alignment: CENTER");
			colC_remarks.setCellValueFactory(new PropertyValueFactory<>("c_remarks"));

			TableColumn colC_registdate = new TableColumn("등록일");
			colC_registdate.setPrefWidth(100);
			colC_registdate.setStyle("-fx-alignment: CENTER");
			colC_registdate.setCellValueFactory(new PropertyValueFactory<>("c_registdate"));

			c_tableView.setItems(customerDataList); // 고객정보테이블뷰에 고객정보 전체를 설정

			c_tableView.getColumns().addAll(colC_number, colC_name, colC_cname, colC_phone, colC_bnumber, colC_address,
					colC_email, colC_remarks, colC_registdate); // 고객테이블뷰에 컬럼 설정

			// 고객 전체 목록
			customerTotalList();

			c_btnRegist.setOnAction(event -> handlerC_btnRegistAction(event)); // 고객 등록 이벤트
			c_btnInit.setOnAction(event -> handlerC_btnInitAction(event)); // 초기화버튼 이벤트
			c_btnUpdate.setOnAction(event -> handlerC_btnUpdateAction(event)); // 수정버튼 이벤트
			c_btnDelete.setOnAction(event -> handlerC_btnDelete(event)); // 삭제버튼 이벤트
			c_btnExit.setOnAction(event -> handlerC_btnExitAction(event)); // 종료버튼 이벤트
			c_tableView.setOnMouseClicked(event -> handlerCustomerTableViewAction(event)); // 테이블뷰 마우스클릭 이벤트
			c_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 고객정보 검색 이벤트

		} catch (Exception e) { // 오류 발생

			e.printStackTrace(); // 오류발생시 오류코드를 출력

		}
	}

	// 검색버튼 이벤트
	public void handlerBtnSearchAction(ActionEvent event) {

		ArrayList<CustomerVO> searchList = new ArrayList<CustomerVO>(); // 검색된 고객의 정보를 저장할 어레이리스트를 생성
		CustomerVO cVo = null; // customerVO를 null값으로 초기화
		CustomerDAO cDao = null; // customerDAO를 null값으로 초기화

		String searchName = ""; // 입력된 고객이름을 저장
		boolean searchResult = false; // 검색결과 초기화

		try {

			searchName = c_txtSearch.getText().trim(); // 찾으려는 고객명과 고객정보의 고객명을
			cDao = new CustomerDAO(); // 고객DAO를 호출
			searchList = cDao.getCustomerCheck(searchName); // 검색된 고객명을 고객정보리스트에 넣어 줌

			if (searchName.equals("")) { // 고객명을 입력하지 않고 검색버튼 클릭시 고객명을 입력하라는 알람창을 띄움
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("고객 정보 검색");
				alert.setHeaderText("고객명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
			}

			if (searchList != null) { // 고객정보리스트에 고객명이 하나이상 존재 하는지에 대한 조건문
				int rowCount = searchList.size(); // 검색된 고객명만큼의 출력사이즈를 설정

				c_txtSearch.clear(); // 검색텍스트필드를 초기화
				customerDataList.removeAll(customerDataList); // 고객정보데이터리스트를
				for (int index = 0; index < rowCount; index++) { // 선택된 고객명에따른 칼럼정보들을 전부 불러옴
					cVo = searchList.get(index); // 불러온 고객의 정보를 초기화된 cVo에 넣음
					customerDataList.add(cVo); // 고객정보리스트에 cVo에 저장된 고객의 정보를 보여줌
					searchResult = true; // 검색결과를 수정할 수 없음
				}
			}

			if (!searchResult) { // 검색텍스트필드에 입력된 이름이 고객정보에 없을 때 고객정보가 존재하지 않음을 알려줌
				c_txtSearch.clear(); // 검색버튼 클릭시 검색텍스트필드를 초기화
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				customerTotalList(); // 고객전체정보를 테이블뷰에 불러옴
			}
		} catch (Exception e) { // 고객정보검색과정에서 오류가 발생하면 알람을 띄움
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객 정보 검색 오류");
			alert.setHeaderText("고객 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

	// 테이블뷰마우스
	public void handlerCustomerTableViewAction(MouseEvent event) {

		if (event.getClickCount() == 2) { // 테이블뷰에서의 특정 행에서 마우스클릭을 두번 했을 때

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

				// c_txtNumber.clear();
				c_txtName.clear();
				c_txtCname.clear();
				c_txtPhone.clear();
				c_txtBnumber.clear();
				c_txtAddress.clear();
				c_txtEmail.clear();
				c_txtRemarks.clear();
				// c_registdate.clear();

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
			if (c_txtName.getLength() != 0 && c_txtPhone.getLength() != 0) {

				sucess = cdao.getcustomerUpdate(selectedCustomerIndex, c_txtName.getText().trim(), c_txtCname.getText(),
						c_txtPhone.getText().trim(), c_txtAddress.getText(), c_txtBnumber.getText(),
						c_txtEmail.getText(), c_txtRemarks.getText());

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

					c_btnRegist.setDisable(false);
					c_btnUpdate.setDisable(true);
					c_btnDelete.setDisable(true);

				} else {
					customerDataList.removeAll(customerDataList);
					customerTotalList();

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("고객 정보 수정 실패");
					alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
					alert.setContentText("고객 정보 수정에 실패하였습니다.");
					alert.showAndWait();
				}
			} else {
				customerDataList.removeAll(customerDataList);
				customerTotalList();

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("고객 정보 수정 ");
				alert.setHeaderText("고객명과 연락처는 필수 입력사항입니다.");
				alert.setContentText("고객명과 연락처를 입력하신 후 수정을 눌러주세요!");
				alert.showAndWait();
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

			c_btnRegist.setDisable(false);
			c_btnUpdate.setDisable(true);
			c_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 고객등록버튼 이벤트 핸들러
	public void handlerC_btnRegistAction(ActionEvent event) {
		boolean sucess;
		try {

			customerDataList.removeAll(customerDataList);

			CustomerVO cvo = null;
			CustomerDAO cdao = null;

			if (c_txtName.getLength() != 0 && c_txtPhone.getLength() != 0) {

				cvo = new CustomerVO(c_txtName.getText().trim(), c_txtCname.getText().trim(),
						c_txtPhone.getText().trim(), c_txtAddress.getText().trim(), c_txtBnumber.getText().trim(),
						c_txtEmail.getText().trim(), c_txtRemarks.getText().trim());

				cdao = new CustomerDAO();
				sucess = cdao.getCustomerRegiste(cvo);

				customerTotalList();
				if (sucess) {
					c_txtName.clear();
					c_txtCname.clear();
					c_txtPhone.clear();
					c_txtBnumber.clear();
					c_txtAddress.clear();
					c_txtEmail.clear();
					c_txtRemarks.clear();
					c_txtName.requestFocus();

					customerDataList.removeAll(customerDataList);

					customerTotalList();
				} else {
					customerDataList.removeAll(customerDataList);

					customerTotalList();

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("고객 정보 등록 실패");
					alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
					alert.setContentText("고객 정보 등록에 실패하였습니다.");
					alert.showAndWait();
				}
			} else {
				customerDataList.removeAll(customerDataList);
				customerTotalList();

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("고객 정보 등록 ");
				alert.setHeaderText("고객명과 연락처는 필수 입력사항입니다.");
				alert.setContentText("고객명과 연락처를 입력하신 후 등록을 눌러주세요!");
				alert.showAndWait();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("고객 정보 등록");
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