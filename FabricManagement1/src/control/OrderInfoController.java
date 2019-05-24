package control;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.OrderVO;
import model.TradeVO;

public class OrderInfoController implements Initializable {

	@FXML
	TextField o_txtNumber;
	@FXML
	TextField a_txtNumber;
	@FXML
	TextField f_txtNumber;
	@FXML
	TextField f_txtName;
	@FXML
	TextField o_txtAmount;
	@FXML
	TextField o_txtPrice;
	@FXML
	TextField o_txtName;
	@FXML
	TextField o_txtPhone;
	@FXML
	TextField o_txtStatus;
	@FXML
	DatePicker o_dpDate;
	@FXML
	TextField o_txtEmail;
	@FXML
	TextField o_txtAddress;
	@FXML
	TextArea o_txtRemarks;
	@FXML
	Button o_btnInit;
	@FXML
	Button o_btnUpdate;
	@FXML
	Button o_btnDelete;
	@FXML
	Button o_btnExit;
	@FXML
	DatePicker o_dpStart;
	@FXML
	DatePicker o_dpFinish;
	@FXML
	TextField o_txtSearch;
	@FXML
	Button o_btnSearch;
	@FXML
	ComboBox<String> o_cbStatus;
	@FXML
	Button o_btnChange;
	@FXML
	TableView<OrderVO> o_tableView;

	ObservableList<OrderVO> orderDataList = FXCollections.observableArrayList();
	ObservableList<OrderVO> selectOrder = null;
	int selectedOrderIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			TradeDAO dao = new TradeDAO();
			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);

			o_cbStatus.setItems(FXCollections.observableArrayList("주문확인", "배송중", "거래완료"));

			TableColumn colOnumber = new TableColumn("주문번호");
			colOnumber.setPrefWidth(80);
			colOnumber.setStyle("-fx-alignment: CENTER");
			colOnumber.setCellValueFactory(new PropertyValueFactory<>("o_number"));

			TableColumn colAnumber = new TableColumn("거래처번호");
			colAnumber.setPrefWidth(80);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colFnumber = new TableColumn("제품코드");
			colFnumber.setPrefWidth(80);
			colFnumber.setStyle("-fx-alignment: CENTER");
			colFnumber.setCellValueFactory(new PropertyValueFactory<>("f_number"));

			TableColumn colFname = new TableColumn("제품명");
			colFname.setPrefWidth(80);
			colFname.setStyle("-fx-alignment: CENTER");
			colFname.setCellValueFactory(new PropertyValueFactory<>("f_name"));

			TableColumn colOamount = new TableColumn("총수량");
			colOamount.setPrefWidth(80);
			colOamount.setStyle("-fx-alignment: CENTER");
			colOamount.setCellValueFactory(new PropertyValueFactory<>("o_amount"));

			TableColumn colOtotal = new TableColumn("총금액");
			colOtotal.setPrefWidth(80);
			colOtotal.setStyle("-fx-alignment: CENTER");
			colOtotal.setCellValueFactory(new PropertyValueFactory<>("o_total"));

			TableColumn colCname = new TableColumn("주문자명");
			colCname.setPrefWidth(80);
			colCname.setStyle("-fx-alignment: CENTER");
			colCname.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colCphone = new TableColumn("연락처");
			colCphone.setPrefWidth(80);
			colCphone.setStyle("-fx-alignment: CENTER");
			colCphone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colOstatus = new TableColumn("상태");
			colOstatus.setPrefWidth(80);
			colOstatus.setStyle("-fx-alignment: CENTER");
			colOstatus.setCellValueFactory(new PropertyValueFactory<>("o_status"));

			TableColumn colOregistdate = new TableColumn("주문일");
			colOregistdate.setPrefWidth(80);
			colOregistdate.setStyle("-fx-alignment: CENTER");
			colOregistdate.setCellValueFactory(new PropertyValueFactory<>("o_registdate"));

			TableColumn colOemail = new TableColumn("이메일");
			colOemail.setPrefWidth(80);
			colOemail.setStyle("-fx-alignment: CENTER");
			colOemail.setCellValueFactory(new PropertyValueFactory<>("o_email"));

			TableColumn colOaddress = new TableColumn("주소");
			colOaddress.setPrefWidth(80);
			colOaddress.setStyle("-fx-alignment: CENTER");
			colOaddress.setCellValueFactory(new PropertyValueFactory<>("o_address"));

			TableColumn colOremarks = new TableColumn("비고");
			colOremarks.setPrefWidth(80);
			colOremarks.setStyle("-fx-alignment: CENTER");
			colOremarks.setCellValueFactory(new PropertyValueFactory<>("o_remarks"));

			o_tableView.setItems(orderDataList);

			o_tableView.getColumns().addAll(colOnumber, colAnumber, colFnumber, colFname, colOamount, colOtotal,
					colCname, colCphone, colOstatus, colOregistdate, colOemail, colOaddress, colOremarks);

			// 전체 목록
			orderTotalList();

			o_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			o_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			o_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			o_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			o_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			o_tableView.setOnMouseClicked(event -> handlerOrderTableViewAction(event)); // 테이블뷰 더블클릭 이벤트
			o_btnChange.setOnAction(event -> handlerBtnChangeAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerBtnChangeAction(ActionEvent event) {
		try {
			String value;
			OrderDAO tdao = new OrderDAO();
			if (selectedOrderIndex != 0 && o_cbStatus.getValue() != null) {
				value = o_cbStatus.getValue().toString();
				tdao.getStatusUpdate(selectedOrderIndex, value);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("상태 정보 변경");
				alert.setHeaderText("거래 내역 및 거래상태가 선택되지 않았습니다.");
				alert.setContentText("내역 및 상태를 선택해주세요.");
				alert.showAndWait();
				selectedOrderIndex = 0;
			}
			orderDataList.removeAll(orderDataList);
			orderTotalList();

			o_txtNumber.clear();
			a_txtNumber.clear();
			f_txtNumber.clear();
			f_txtName.clear();
			o_txtAmount.clear();
			o_txtPrice.clear();
			o_txtName.clear();
			o_txtPhone.clear();
			o_txtStatus.clear();
			o_dpDate.setValue(null);
			o_txtEmail.clear();
			o_txtAddress.clear();
			o_txtRemarks.clear();
			o_dpStart.setValue(null);
			o_dpFinish.setValue(null);
			o_cbStatus.getSelectionModel().clearSelection();

			f_txtNumber.setEditable(true);
			a_txtNumber.setEditable(true);
			o_txtNumber.setEditable(true);
			f_txtName.setEditable(true);
			o_txtAmount.setEditable(true);
			o_txtPrice.setEditable(true);
			o_txtStatus.setEditable(true);

			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerOrderTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectOrder = o_tableView.getSelectionModel().getSelectedItems();
				selectedOrderIndex = selectOrder.get(0).getO_number();
				int selectedA_number = selectOrder.get(0).getA_number();
				int selectedC_number = selectOrder.get(0).getC_number();
				String selectedF_number = selectOrder.get(0).getF_number();
				String selectedF_name = selectOrder.get(0).getF_name();
				int selectedO_amount = selectOrder.get(0).getO_amount();
				int selectedO_total = selectOrder.get(0).getO_total();
				String selectedC_name = selectOrder.get(0).getC_name();
				String selectedC_phone = selectOrder.get(0).getC_phone();
				String selectedO_email = selectOrder.get(0).getO_email();
				String selectedO_status = selectOrder.get(0).getO_status();
				String selectedO_registdate = selectOrder.get(0).getO_registdate();
				String selectedO_address = selectOrder.get(0).getO_address();
				String selectedO_remarks = selectOrder.get(0).getO_remarks();

				o_txtNumber.setText(selectedOrderIndex + "");
				a_txtNumber.setText(selectedA_number + "");
				f_txtNumber.setText(selectedF_number);
				f_txtName.setText(selectedF_name);
				o_txtAmount.setText(selectedO_amount + "");
				o_txtPrice.setText(selectedO_total + "");
				o_txtName.setText(selectedC_name);
				o_txtPhone.setText(selectedC_phone);
				o_txtStatus.setText(selectedO_status);
				o_dpDate.setValue(LocalDate.parse(selectedO_registdate));
				o_txtEmail.setText(selectedO_email);
				o_txtAddress.setText(selectedO_address);
				o_txtRemarks.setText(selectedO_remarks);

				f_txtNumber.setEditable(false);
				a_txtNumber.setEditable(false);
				o_txtNumber.setEditable(false);
				f_txtName.setEditable(false);
				o_txtAmount.setEditable(false);
				o_txtPrice.setEditable(false);
				o_txtStatus.setEditable(false);

				o_btnUpdate.setDisable(false);
				o_btnDelete.setDisable(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<OrderVO> searchList = new ArrayList<OrderVO>();
		OrderVO oVo = null;
		OrderDAO oDao = null;
		LocalDate startdate = null;
		LocalDate enddate = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = o_txtSearch.getText().trim();

			oDao = new OrderDAO();

			// 시작과 끝 날짜가 공백이 아닐경우
			if (o_dpStart.getValue() != null && o_dpFinish.getValue() != null) {
				startdate = o_dpStart.getValue();
				enddate = o_dpFinish.getValue().plusDays(1);
				searchList = oDao.getOrderCheck(searchName, startdate.toString().trim(), enddate.toString().trim());

				// 검색 텍스트필드가 공백일 경우
				if (searchName.equals("")) {
					searchResult = true;
					orderDataList.removeAll(orderDataList);
					orderTotalList();
				}

				// 시작과 끝 날짜가 공백일경우
			} else {
				searchList = oDao.getOrderCheck(searchName);
				// 검색 텍스트필드가 공백일 경우
				if (searchName.equals("")) {
					searchResult = true;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("주문 정보 검색");
					alert.setHeaderText("고객명을 입력하세요.");
					alert.setContentText("다시 시도해주세요.");
					alert.showAndWait();
					orderDataList.removeAll(orderDataList);
					orderTotalList();
				}
			}

			if (searchList != null) {
				int rowCount = searchList.size();

				o_txtSearch.clear();
				orderDataList.removeAll(orderDataList);
				for (int index = 0; index < rowCount; index++) {
					oVo = searchList.get(index);
					orderDataList.add(oVo);
					searchResult = true;
				}
			}

			if (!searchResult) {
				o_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("주문 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				orderTotalList();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("주문 정보 검색 오류");
			alert.setHeaderText("주문 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	public void handlerBtnDeleteAction(ActionEvent event) {
		try {
			boolean sucess;
			OrderDAO oDao = new OrderDAO();
			sucess = oDao.getOrderDelete(selectedOrderIndex);

			if (sucess) {

				orderDataList.removeAll(orderDataList);
				orderTotalList();

				o_txtNumber.clear();
				a_txtNumber.clear();
				f_txtNumber.clear();
				f_txtName.clear();
				o_txtAmount.clear();
				o_txtPrice.clear();
				o_txtName.clear();
				o_txtPhone.clear();
				o_txtStatus.clear();
				o_dpDate.setValue(null);
				o_txtEmail.clear();
				o_txtAddress.clear();
				o_txtRemarks.clear();
				o_dpStart.setValue(null);
				o_dpFinish.setValue(null);

				f_txtNumber.setEditable(true);
				a_txtNumber.setEditable(true);
				o_txtNumber.setEditable(true);
				f_txtName.setEditable(true);
				o_txtAmount.setEditable(true);
				o_txtPrice.setEditable(true);
				o_txtStatus.setEditable(true);

				o_btnUpdate.setDisable(true);
				o_btnDelete.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess = false;
			OrderDAO odao = new OrderDAO();
			// sucess = odao.getOrderUpdate(o_txtNumber.getText().trim(),
			// a_txtNumber.getText().trim(),
			// f_txtNumber.getText().trim(), o_txtName.getText().trim(),
			// f_txtName.getText().trim(), o_txtAmount.getText().trim(),
			// o_txtPrice.getText().trim(), o_txtPhone.getText().trim(),
			// o_txtStatus.getText().trim(), o_dpDate.getValue().toString(),
			// o_txtEmail.getText().trim(),
			// o_txtAddress.getText().trim(), o_txtRemarks.getText().trim());

			if (sucess) {
				orderDataList.removeAll(orderDataList);
				orderTotalList();

				o_txtNumber.clear();
				a_txtNumber.clear();
				f_txtNumber.clear();
				f_txtName.clear();
				o_txtAmount.clear();
				o_txtPrice.clear();
				o_txtName.clear();
				o_txtPhone.clear();
				o_txtStatus.clear();
				o_dpDate.setValue(null);
				o_txtEmail.clear();
				o_txtAddress.clear();
				o_txtRemarks.clear();
				o_dpStart.setValue(null);
				o_dpFinish.setValue(null);

				f_txtNumber.setEditable(true);
				a_txtNumber.setEditable(true);
				o_txtNumber.setEditable(true);
				f_txtName.setEditable(true);
				o_txtAmount.setEditable(true);
				o_txtPrice.setEditable(true);
				o_txtStatus.setEditable(true);

				o_btnUpdate.setDisable(true);
				o_btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnInitAction(ActionEvent event) {
		try {
			orderDataList.removeAll(orderDataList);
			selectedOrderIndex = 0;

			orderTotalList();

			o_txtNumber.clear();
			a_txtNumber.clear();
			f_txtNumber.clear();
			f_txtName.clear();
			o_txtAmount.clear();
			o_txtPrice.clear();
			o_txtName.clear();
			o_txtPhone.clear();
			o_txtStatus.clear();
			o_dpDate.setValue(null);
			o_txtEmail.clear();
			o_txtAddress.clear();
			o_txtRemarks.clear();
			o_dpStart.setValue(null);
			o_dpFinish.setValue(null);

			f_txtNumber.setEditable(true);
			a_txtNumber.setEditable(true);
			o_txtNumber.setEditable(true);
			f_txtName.setEditable(true);
			o_txtAmount.setEditable(true);
			o_txtPrice.setEditable(true);
			o_txtStatus.setEditable(true);

			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void orderTotalList() throws Exception {
		orderDataList.removeAll(orderDataList);
		OrderDAO oDao = new OrderDAO();
		OrderVO oVo = null;
		ArrayList<OrderVO> list;

		list = oDao.getOrderTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			oVo = list.get(index);
			orderDataList.add(oVo);
		}
	}
}
