package control;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.FabricVO;
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

	// 여기부터 주문 등록
	@FXML
	TextField or_f_txtNumber;
	@FXML
	TextField or_f_txtSort;
	@FXML
	TextField or_f_txtName;
	@FXML
	TextField or_f_txtColor;
	@FXML
	TextField or_f_txtSize;
	@FXML
	TextField or_f_txtWeight;
	@FXML
	TextField or_f_txtPrice;
	@FXML
	TextField or_f_txtPhone;
	@FXML
	TextField or_c_txtName;
	@FXML
	TextField or_o_txtAmount;
	@FXML
	TextField or_o_txtAddress;
	@FXML
	TextField or_o_txtCprice;
	@FXML
	Button or_o_btnTotal;
	@FXML
	TextField or_o_txtTotal;
	@FXML
	TextArea or_o_txtRemarks;
	@FXML
	Button or_o_btnRegist;
	@FXML
	Button or_o_btnCancel;

	@FXML
	private TableView<TradeVO> t_tableView = new TableView<>();

	ObservableList<TradeVO> tradeDataList = FXCollections.observableArrayList();
	ObservableList<TradeVO> selectTrade = null;
	ObservableList<FabricVO> selectfabric = null;
	int selectedTradeIndex;
	int toOrderIndex;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {

			TradeDAO dao = new TradeDAO();

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

			t_btnUpdate.setDisable(true);
			t_btnDelete.setDisable(true);
			t_or_btnRegist.setDisable(true);

			// 전체 목록
			tradeTotalList();

			t_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			t_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			t_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			t_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			t_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			t_btnChange.setOnAction(event -> handlerBtnChangeAction(event));
			t_tableView.setOnMouseClicked(event -> handlerTradeTableViewAction(event)); // 테이블뷰 더블클릭 이벤트
			t_or_btnRegist.setOnAction(event -> handlerBtnOrderRegistAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void handlerBtnOrderRegistAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderReg.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainStage = new Stage();
			mainStage.setTitle("주문 등록");
			mainStage.setResizable(false);
			mainStage.setScene(scane);
			mainStage.show();

			TextField temp_f_number = (TextField) mainView.lookup("#or_f_txtNumber");
			TextField temp_f_sort = (TextField) mainView.lookup("#or_f_txtSort");
			TextField temp_f_name = (TextField) mainView.lookup("#or_f_txtName");
			TextField temp_f_color = (TextField) mainView.lookup("#or_f_txtColor");
			TextField temp_f_size = (TextField) mainView.lookup("#or_f_txtSize");
			TextField temp_f_weight = (TextField) mainView.lookup("#or_f_txtWeight");
			TextField temp_f_price = (TextField) mainView.lookup("#or_f_txtPrice");
			TextField temp_f_phone = (TextField) mainView.lookup("#or_f_txtPhone");
			TextField temp_c_name = (TextField) mainView.lookup("#or_c_txtName");
			TextField temp_o_amount = (TextField) mainView.lookup("#or_o_txtAmount");
			TextField temp_o_address = (TextField) mainView.lookup("#or_o_txtAddress");
			TextField temp_o_cprice = (TextField) mainView.lookup("#or_o_txtCprice");
			TextField temp_o_total = (TextField) mainView.lookup("#or_o_txtTotal");

			Button btnCancel = (Button) mainView.lookup("#or_o_btnCancel");
			Button btnTotal = (Button) mainView.lookup("#or_o_btnTotal");

			temp_f_number.setText(selectTrade.get(0).getF_number());
			temp_f_sort.setText(selectTrade.get(0).getF_f_sort());
			temp_f_name.setText(selectTrade.get(0).getF_f_name());
			temp_f_color.setText(selectTrade.get(0).getF_f_color());
			temp_f_size.setText(selectTrade.get(0).getF_f_size());
			temp_f_weight.setText(selectTrade.get(0).getF_f_weight());
			temp_f_price.setText(selectTrade.get(0).getF_f_price());
			temp_f_phone.setText(selectTrade.get(0).getF_f_phone());
			temp_c_name.setText(selectTrade.get(0).getC_name());
			temp_o_amount.setText(selectTrade.get(0).getT_amount() + "");
			temp_o_address.setText(selectTrade.get(0).getC_name());

			temp_f_number.setEditable(false);
			temp_f_sort.setEditable(false);
			temp_f_name.setEditable(false);
			temp_f_color.setEditable(false);
			temp_f_size.setEditable(false);
			temp_f_weight.setEditable(false);
			temp_f_price.setEditable(false);
			temp_f_phone.setEditable(false);
			temp_c_name.setEditable(false);
			temp_o_amount.setEditable(false);
			temp_o_address.setEditable(false);
			
			/*
			 * temp_o_name.setText(selectTrade.get(0).getF_number());
			 * temp_o_amount.setText(selectTrade.get(0).getF_number());
			 * temp_o_address.setText(selectTrade.get(0).getF_number());
			 * temp_o_cprice.setText(selectTrade.get(0).getF_number());
			 * temp_o_total.setText(selectTrade.get(0).getF_number());
			 */

			btnTotal.setOnAction(e -> {
				temp_o_total.setText(
						selectTrade.get(0).getT_amount() * Integer.parseInt(selectTrade.get(0).getF_f_price()) + "");
			});
			btnCancel.setOnAction(e -> {
				mainStage.close();
			});

		} catch (IOException e) {
			System.err.println("오류" + e);
		}
	}

	public void handlerBtnChangeAction(ActionEvent event) {
		try {
			boolean sucess = false;
			String value;
			TradeDAO tdao = new TradeDAO();
			if (selectedTradeIndex != 0 && t_cbStatus.getValue() != null) {
				value = t_cbStatus.getValue().toString();
				sucess = tdao.getStatusUpdate(selectedTradeIndex, value);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("상태 정보 변경");
				alert.setHeaderText("거래 내역 및 거래상태가 선택되지 않았습니다.");
				alert.setContentText("내역 및 상태를 선택해주세요.");
				alert.showAndWait();
				selectedTradeIndex = 0;
			}
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
			t_cbStatus.getSelectionModel().clearSelection();

			t_dpStart.setValue(null);
			t_dpFinish.setValue(null);
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

	// 검색 버튼
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<TradeVO> searchList = new ArrayList<TradeVO>();
		TradeVO tVo = null;
		TradeDAO tDao = null;
		LocalDate startdate = null;
		LocalDate enddate = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = t_txtSearch.getText().trim();

			tDao = new TradeDAO();

			// 시작과 끝 날짜가 공백이 아닐경우
			if (t_dpStart.getValue() != null && t_dpFinish.getValue() != null) {
				startdate = t_dpStart.getValue();
				enddate = t_dpFinish.getValue().plusDays(1);
				searchList = tDao.getTradeCheck(searchName, startdate.toString().trim(), enddate.toString().trim());

				// 검색 텍스트필드가 공백일 경우
				if (searchName.equals("")) {
					searchResult = true;
					tradeDataList.removeAll(tradeDataList);
					tradeTotalList();
				}

				// 시작과 끝 날짜가 공백일경우
			} else {
				searchList = tDao.getTradeCheck(searchName);
				// 검색 텍스트필드가 공백일 경우
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
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
		ArrayList<TradeVO> list;

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
					t_txtAmount.getText().trim(), t_txtPrice.getText().trim(), t_txtDeposit.getText().trim(),
					t_txtPenalty.getText().trim(), t_txtBalance.getText().trim(), t_txtReceipt.getText().trim(),
					t_txtUnpaid.getText().trim(), t_txtStatus.getText().trim(), t_dpDate.getValue().toString(),
					t_txtAddress.getText().trim(), t_txtRemarks.getText().trim());

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
				t_dpStart.setValue(null);
				t_dpFinish.setValue(null);

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
				t_dpStart.setValue(null);
				t_dpFinish.setValue(null);

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
			selectedTradeIndex = 0;

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
			t_dpStart.setValue(null);
			t_dpFinish.setValue(null);
			t_txtNumber.setEditable(true);
			f_txtNumber.setEditable(true);
			c_txtNumber.setEditable(true);
			c_txtName.setEditable(true);

			t_or_btnRegist.setDisable(true);
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
				c_txtName.setEditable(false);
				t_txtPhone.setEditable(false);

				t_btnUpdate.setDisable(false);
				t_btnDelete.setDisable(false);

				t_or_btnRegist.setDisable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
