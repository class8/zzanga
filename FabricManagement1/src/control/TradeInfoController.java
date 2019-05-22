package control;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import model.AccountVO;
import model.TradeVO;

public class TradeInfoController implements Initializable {

	@FXML
	TextField t_txtNumber;
	@FXML
	TextField f_txtNumber;
	@FXML
	TextField c_txtNumber;
	@FXML
	TextField c_txtName;
	@FXML
	TextField t_txtAmount;
	@FXML
	TextField t_txtPrice;
	@FXML
	TextField t_txtDeposit;
	@FXML
	TextField t_txtPenalty;
	@FXML
	TextField t_txtBalance;
	@FXML
	TextField t_txtReceipt;
	@FXML
	TextField t_txtUnpaid;
	@FXML
	TextField t_txtStatus;
	@FXML
	TextField t_txtPhone;
	@FXML
	DatePicker t_dpDate;
	@FXML
	TextField t_txtAddress;
	@FXML
	TextArea t_txtRemarks;
	@FXML
	Button t_btnInit;
	@FXML
	Button t_btnUpdate;
	@FXML
	Button t_btnDelete;
	@FXML
	Button t_btnExit;
	@FXML
	DatePicker t_dpStart;
	@FXML
	DatePicker t_dpFinish;
	@FXML
	TextField t_txtSearch;
	@FXML
	Button t_btnSearch;
	@FXML
	ComboBox<String> t_cbStatus;
	@FXML
	Button t_btnChange;
	@FXML
	Button t_or_btnRegist;

	@FXML
	private TableView<TradeVO> t_tableView = new TableView<>();

	ObservableList<TradeVO> tradeDataList = FXCollections.observableArrayList();
	ObservableList<TradeVO> selectTrade = null;
	int selectedTradeIndex;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {

			TradeDAO dao = new TradeDAO();
			t_btnUpdate.setDisable(true);
			t_btnDelete.setDisable(true);

			t_cbStatus.setItems(FXCollections.observableArrayList("주문확인", "입금완료", "배송중", "거래완료"));

			TableColumn colTnumber = new TableColumn("거래번호");
			colTnumber.setPrefWidth(40);
			colTnumber.setStyle("-fx-alignment: CENTER");
			colTnumber.setCellValueFactory(new PropertyValueFactory<>("t_number"));

			TableColumn colFnumber = new TableColumn("제품코드");
			colFnumber.setPrefWidth(90);
			colFnumber.setStyle("-fx-alignment: CENTER");
			colFnumber.setCellValueFactory(new PropertyValueFactory<>("f_number"));

			TableColumn colCnumber = new TableColumn("고객번호");
			colCnumber.setPrefWidth(90);
			colCnumber.setStyle("-fx-alignment: CENTER");
			colCnumber.setCellValueFactory(new PropertyValueFactory<>("c_number"));

			TableColumn colCname = new TableColumn("고객명");
			colCname.setPrefWidth(100);
			colCname.setStyle("-fx-alignment: CENTER");
			colCname.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colTamount = new TableColumn("총 수량");
			colTamount.setPrefWidth(100);
			colTamount.setStyle("-fx-alignment: CENTER");
			colTamount.setCellValueFactory(new PropertyValueFactory<>("t_amount"));

			TableColumn colTprice = new TableColumn("총 가격");
			colTprice.setPrefWidth(100);
			colTprice.setStyle("-fx-alignment: CENTER");
			colTprice.setCellValueFactory(new PropertyValueFactory<>("t_price"));

			TableColumn colTdeposit = new TableColumn("선금");
			colTdeposit.setPrefWidth(100);
			colTdeposit.setStyle("-fx-alignment: CENTER");
			colTdeposit.setCellValueFactory(new PropertyValueFactory<>("t_deposit"));

			TableColumn colTpenalty = new TableColumn("위약금");
			colTpenalty.setPrefWidth(150);
			colTpenalty.setStyle("-fx-alignment: CENTER");
			colTpenalty.setCellValueFactory(new PropertyValueFactory<>("t_penalty"));

			TableColumn colTbalance = new TableColumn("잔금");
			colTbalance.setPrefWidth(150);
			colTbalance.setStyle("-fx-alignment: CENTER");
			colTbalance.setCellValueFactory(new PropertyValueFactory<>("t_balance"));

			TableColumn colTreceipt = new TableColumn("수령액");
			colTreceipt.setPrefWidth(90);
			colTreceipt.setStyle("-fx-alignment: CENTER");
			colTreceipt.setCellValueFactory(new PropertyValueFactory<>("t_receipt"));

			TableColumn colTunpaid = new TableColumn("미납금");
			colTunpaid.setPrefWidth(150);
			colTunpaid.setStyle("-fx-alignment: CENTER");
			colTunpaid.setCellValueFactory(new PropertyValueFactory<>("t_unpaid"));

			TableColumn colTstatus = new TableColumn("거래상태");
			colTstatus.setPrefWidth(150);
			colTstatus.setStyle("-fx-alignment: CENTER");
			colTstatus.setCellValueFactory(new PropertyValueFactory<>("t_status"));

			TableColumn colTphone = new TableColumn("연락처");
			colTphone.setPrefWidth(150);
			colTphone.setStyle("-fx-alignment: CENTER");
			colTphone.setCellValueFactory(new PropertyValueFactory<>("t_phone"));

			TableColumn colTregistdate = new TableColumn("거래일");
			colTregistdate.setPrefWidth(150);
			colTregistdate.setStyle("-fx-alignment: CENTER");
			colTregistdate.setCellValueFactory(new PropertyValueFactory<>("t_registdate"));

			TableColumn colTaddress = new TableColumn("배달주소");
			colTaddress.setPrefWidth(150);
			colTaddress.setStyle("-fx-alignment: CENTER");
			colTaddress.setCellValueFactory(new PropertyValueFactory<>("t_address"));

			TableColumn colTremarks = new TableColumn("비고");
			colTremarks.setPrefWidth(150);
			colTremarks.setStyle("-fx-alignment: CENTER");
			colTremarks.setCellValueFactory(new PropertyValueFactory<>("t_remarks"));

			t_tableView.setItems(tradeDataList);

			t_tableView.getColumns().addAll(colTnumber, colFnumber, colCnumber, colCname, colTamount, colTprice,
					colTdeposit, colTbalance, colTreceipt, colTunpaid, colTstatus, colTregistdate, colTaddress,
					colTremarks);

			// 전체 목록
			tradeTotalList();

			t_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			t_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			t_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			t_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			t_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			t_tableView.setOnMouseClicked(event -> handlerTradeTableViewAction(event)); // 테이블뷰 더블클릭 이벤트
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<TradeVO> searchList = new ArrayList<TradeVO>();
		TradeVO tVo = null;
		TradeDAO tDao = null;

		String searchName = "";
		boolean searchResult = false;
		String dateStart = t_dpStart.getValue().toString();
		String dateEnd = t_dpFinish.getValue().toString();

		try {
			searchName = t_txtSearch.getText().trim();
			tDao = new TradeDAO();
			searchList = tDao.getTradeCheck(searchName, dateStart, dateEnd);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래 정보 검색");
				alert.setHeaderText("고객명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
				tradeDataList.removeAll(tradeDataList);
				tradeTotalList();
			}

			if (searchList != null) {
				int rowCount = searchList.size();

				t_txtSearch.clear();
				tradeDataList.removeAll(tradeDataList);
				for (int index = 0; index < rowCount; index++) {
					tVo = searchList.get(index);
					tradeDataList.add(tVo);
					searchResult = true;
				}
			}

			if (!searchResult) {
				t_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				tradeTotalList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("거래 정보 검색 오류");
			alert.setHeaderText("거래정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

	// 전체 목록
	public void tradeTotalList() throws Exception {

		tradeDataList.removeAll(tradeDataList);
		TradeDAO tDao = new TradeDAO();
		TradeVO tVo = null;
		ArrayList<String> title;
		ArrayList<TradeVO> list;

		title = tDao.getTradeColumnName();
		int columnCount = title.size();

		list = tDao.getTradeTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			tVo = list.get(index);
			tradeDataList.add(tVo);
		}
	}

	// 수정 버튼
	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			TradeDAO tdao = new TradeDAO();
			sucess = tdao.getTradeUpdate(selectedTradeIndex, f_txtNumber.getText().trim(), c_txtNumber.getText().trim(),
					c_txtName.getText().trim(), t_txtAmount.getText().trim(), t_txtPrice.getText().trim(),
					t_txtDeposit.getText().trim(), t_txtPenalty.getText().trim(), t_txtBalance.getText().trim(),
					t_txtReceipt.getText().trim(), t_txtUnpaid.getText().trim(), t_txtStatus.getText().trim(),
					t_txtPhone.getText().trim(), String.valueOf(t_dpDate), t_txtAddress.getText().trim(),
					t_txtRemarks.getText().trim());

			if (sucess) {
				tradeDataList.removeAll(tradeDataList);
				tradeTotalList();

				t_txtNumber.clear();
				f_txtNumber.clear();
				c_txtNumber.clear();
				c_txtName.clear();
				t_txtAmount.clear();
				t_txtPrice.clear();
				t_txtDeposit.clear();
				t_txtPenalty.clear();
				t_txtBalance.clear();
				t_txtReceipt.clear();
				t_txtUnpaid.clear();
				t_txtStatus.clear();
				t_txtPhone.clear();
				t_dpDate.setValue(null);
				t_txtAddress.clear();
				t_txtRemarks.clear();

				t_txtNumber.setEditable(true);
				f_txtNumber.setEditable(true);
				c_txtNumber.setEditable(true);
				c_txtName.setEditable(true);

				t_btnUpdate.setDisable(true);
				t_btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제 버튼
	public void handlerBtnDeleteAction(ActionEvent event) {
		try {
			boolean sucess;
			TradeDAO sDao = new TradeDAO();
			sucess = sDao.getTradeDelete(selectedTradeIndex);

			if (sucess) {

				tradeDataList.removeAll(tradeDataList);
				tradeTotalList();

				t_txtNumber.clear();
				f_txtNumber.clear();
				c_txtNumber.clear();
				c_txtName.clear();
				t_txtAmount.clear();
				t_txtPrice.clear();
				t_txtDeposit.clear();
				t_txtPenalty.clear();
				t_txtBalance.clear();
				t_txtReceipt.clear();
				t_txtUnpaid.clear();
				t_txtStatus.clear();
				t_txtPhone.clear();
				t_dpDate.setValue(null);
				t_txtAddress.clear();
				t_txtRemarks.clear();

				t_txtNumber.setEditable(true);
				f_txtNumber.setEditable(true);
				c_txtNumber.setEditable(true);
				c_txtName.setEditable(true);

				t_btnUpdate.setDisable(true);
				t_btnDelete.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 종료 버튼
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 초기화 버튼
	public void handlerBtnInitAction(ActionEvent event) {
		try {
			tradeDataList.removeAll(tradeDataList);
			tradeTotalList();

			t_txtNumber.clear();
			f_txtNumber.clear();
			c_txtNumber.clear();
			c_txtName.clear();
			t_txtAmount.clear();
			t_txtPrice.clear();
			t_txtDeposit.clear();
			t_txtPenalty.clear();
			t_txtBalance.clear();
			t_txtReceipt.clear();
			t_txtUnpaid.clear();
			t_txtStatus.clear();
			t_txtPhone.clear();
			t_dpDate.setValue(null);
			t_txtAddress.clear();
			t_txtRemarks.clear();

			t_txtNumber.setEditable(true);
			f_txtNumber.setEditable(true);
			c_txtNumber.setEditable(true);
			c_txtName.setEditable(true);

			t_btnUpdate.setDisable(true);
			t_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerTradeTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectTrade = t_tableView.getSelectionModel().getSelectedItems();
				selectedTradeIndex = selectTrade.get(0).getT_number();
				String selectedF_number = selectTrade.get(0).getF_number();
				int selectedC_number = selectTrade.get(0).getC_number();
				String selectedC_name = selectTrade.get(0).getC_name();
				int selectedT_amount = selectTrade.get(0).getT_amount();
				int selectedT_price = selectTrade.get(0).getT_price();
				int selectedT_deposit = selectTrade.get(0).getT_deposit();
				int selectedT_penalty = selectTrade.get(0).getT_penalty();
				int selectedT_balance = selectTrade.get(0).getT_balance();
				int selectedT_receipt = selectTrade.get(0).getT_receipt();
				int selectedT_unpaid = selectTrade.get(0).getT_unpaid();
				String selectedT_status = selectTrade.get(0).getT_status();
				String selectedT_phone = selectTrade.get(0).getC_phone();
				String selectedT_registdate = selectTrade.get(0).getT_registdate(); // 문자열에 날짜 넣기
				String selectedT_address = selectTrade.get(0).getT_address();
				String selectedT_remarks = selectTrade.get(0).getT_remarks();

				t_txtNumber.setText(String.valueOf(selectedTradeIndex));
				f_txtNumber.setText(selectedF_number);
				c_txtNumber.setText(String.valueOf(selectedC_number));
				c_txtName.setText(selectedC_name);
				t_txtAmount.setText(String.valueOf(selectedT_amount));
				t_txtPrice.setText(String.valueOf(selectedT_price));
				t_txtDeposit.setText(String.valueOf(selectedT_deposit));
				t_txtPenalty.setText(String.valueOf(selectedT_penalty));
				t_txtBalance.setText(String.valueOf(selectedT_balance));
				t_txtReceipt.setText(String.valueOf(selectedT_receipt));
				t_txtUnpaid.setText(String.valueOf(selectedT_unpaid));
				t_txtStatus.setText(selectedT_status);
				t_txtPhone.setText(selectedT_phone);
				t_txtAddress.setText(selectedT_address);
				t_dpDate.setValue(LocalDate.parse(selectedT_registdate));
				t_txtRemarks.setText(selectedT_remarks);

				t_txtNumber.setEditable(false);
				f_txtNumber.setEditable(false);
				c_txtNumber.setEditable(false);
				c_txtName.setEditable(false);

				t_btnUpdate.setDisable(false);
				t_btnDelete.setDisable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
