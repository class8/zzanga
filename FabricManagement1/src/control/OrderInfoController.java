package control;

import java.net.URL;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AccountVO;
import model.CustomerVO;
import model.FabricVO;
import model.OrderVO;
import model.TradeVO;

public class OrderInfoController implements Initializable {

	@FXML
	TextField o_txtNumber;
	@FXML
	TextField a_txtNumber;
	@FXML
	Button a_btnNumber;
	@FXML
	TextField f_txtNumber;
	@FXML
	Button f_btnNumber;
	@FXML
	TextField f_txtName;
	@FXML
	TextField o_txtAmount;
	@FXML
	TextField o_txtPrice;
	@FXML
	TextField c_txtNumber;
	@FXML
	Button c_btnNumber;
	@FXML
	TextField c_txtName;
	@FXML
	TextField c_txtPhone;
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

	// Account
	ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();
	ObservableList<AccountVO> selectAccount = null;
	int selectedAccountIndex;

	// Fabric
	@FXML
	TableView<FabricVO> f_tableView = new TableView<>();
	ObservableList<FabricVO> fabricDataList = FXCollections.observableArrayList();
	ObservableList<FabricVO> selectFabric = null;
	// String selectedFabricIndex = "";
	String selectedFabricIndex1;
	String selectedFabricName;

	// Customer
	CustomerVO customer = new CustomerVO();
	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectCustomer = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedCustomerIndex; // 테이블에서 선택한 정보 인덱스 저장
	String selectedCustomerName;
	String selectedCustomerEmail;
	String selectedCustomerAddress;
	String selectedCustomerPhone;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 텍스트필드 글자수 제한
		MessageFormat format = new MessageFormat("{0}");
		o_txtAmount.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 10) {
				return null;
			} else {
				return event;
			}
		}));
		o_txtPrice.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 10) {
				return null;
			} else {
				return event;
			}
		}));
		c_txtName.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 15) {
				return null;
			} else {
				return event;
			}
		}));
		c_txtPhone.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 15) {
				return null;
			} else {
				return event;
			}
		}));
		o_txtEmail.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 50) {
				return null;
			} else {
				return event;
			}
		}));
		o_txtAddress.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 100) {
				return null;
			} else {
				return event;
			}
		}));
		o_txtRemarks.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 50) {
				return null;
			} else {
				return event;
			}
		}));
		o_txtSearch.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 15) {
				return null;
			} else {
				return event;
			}
		}));

		// 수량입력에 숫자만 입력할수 있게해줌
		o_txtAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					o_txtAmount.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// 금액입력에 숫자만 입력할수 있게해줌
		o_txtPrice.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					o_txtPrice.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		try {
			// 초기값 설정
			o_txtNumber.setDisable(true);
			a_txtNumber.setDisable(true);
			f_txtNumber.setDisable(true);
			f_txtName.setDisable(true);
			o_txtAmount.setDisable(true);
			o_txtPrice.setDisable(true);
			c_txtNumber.setDisable(true);
			c_txtName.setDisable(true);
			c_txtPhone.setDisable(true);
			o_txtStatus.setDisable(true);
			o_dpDate.setDisable(true);
			o_txtEmail.setDisable(true);
			o_txtAddress.setDisable(true);
			o_txtRemarks.setDisable(true);

			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);
			c_btnNumber.setDisable(true);
			f_btnNumber.setDisable(true);
			a_btnNumber.setDisable(true);

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

			TableColumn colCnumber = new TableColumn("고객번호");
			colCnumber.setPrefWidth(80);
			colCnumber.setStyle("-fx-alignment: CENTER");
			colCnumber.setCellValueFactory(new PropertyValueFactory<>("c_number"));

			TableColumn colCname = new TableColumn("고객명");
			colCname.setPrefWidth(80);
			colCname.setStyle("-fx-alignment: CENTER");
			colCname.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colCphone = new TableColumn("연락처");
			colCphone.setPrefWidth(80);
			colCphone.setStyle("-fx-alignment: CENTER");
			colCphone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colOemail = new TableColumn("이메일");
			colOemail.setPrefWidth(80);
			colOemail.setStyle("-fx-alignment: CENTER");
			colOemail.setCellValueFactory(new PropertyValueFactory<>("o_email"));

			TableColumn colOstatus = new TableColumn("상태");
			colOstatus.setPrefWidth(80);
			colOstatus.setStyle("-fx-alignment: CENTER");
			colOstatus.setCellValueFactory(new PropertyValueFactory<>("o_status"));

			TableColumn colOregistdate = new TableColumn("주문일");
			colOregistdate.setPrefWidth(80);
			colOregistdate.setStyle("-fx-alignment: CENTER");
			colOregistdate.setCellValueFactory(new PropertyValueFactory<>("o_registdate"));

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
					colCnumber, colCname, colCphone, colOemail, colOstatus, colOregistdate, colOaddress, colOremarks);

			// 전체 목록
			orderTotalList();

			o_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			o_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			o_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			o_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			o_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			o_tableView.setOnMouseClicked(event -> handlerOrderTableViewAction(event)); // 테이블뷰 더블클릭 이벤트
			o_btnChange.setOnAction(event -> handlerBtnChangeAction(event));
			c_btnNumber.setOnAction(event -> handlerBtnCustomerSearchAction(event));
			a_btnNumber.setOnAction(event -> handlerBtnAccountSearchAction(event));
			f_btnNumber.setOnAction(event -> handlerBtnFabricSearchAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 거래처 정보 변경버튼 이벤트
	public void handlerBtnAccountSearchAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderAccountSearch.fxml"));
			Parent accountView = (Parent) loader.load();

			Scene scene = new Scene(accountView);
			Stage mainStage = new Stage();
			mainStage.setTitle("거래처 정보 변경");
			mainStage.setResizable(false);
			mainStage.setScene(scene);
			mainStage.show();

			Button as_btnCancel = (Button) accountView.lookup("#as_btnCancel");
			Button as_btnRegist = (Button) accountView.lookup("#as_btnRegist");
			TableView<AccountVO> as_tableView = (TableView) accountView.lookup("#as_tableView");

			as_btnCancel.setOnAction(e -> {
				mainStage.close();
			});

			as_btnRegist.setOnAction(e -> {

				try {

					a_txtNumber.setText(selectedAccountIndex + "");

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				mainStage.close();
			});

			as_tableView.setOnMouseClicked(e -> {

				if (e.getClickCount() == 1) {
					try {
						selectAccount = as_tableView.getSelectionModel().getSelectedItems();
						selectedAccountIndex = selectAccount.get(0).getA_number();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (e.getClickCount() == 2) {
					try {
						selectAccount = as_tableView.getSelectionModel().getSelectedItems();
						selectedAccountIndex = selectAccount.get(0).getA_number();

						a_txtNumber.setText(selectedAccountIndex + "");

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					mainStage.close();
				}
			});

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// 제품코드 변경 버튼 이벤트
	public void handlerBtnFabricSearchAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderFabricSearch.fxml"));
			Parent fabricView = (Parent) loader.load();

			Scene scene = new Scene(fabricView);
			Stage mainStage = new Stage();
			mainStage.setTitle("제품코드 정보 변경");
			mainStage.setResizable(false);
			mainStage.setScene(scene);
			mainStage.show();

			Button fs_btnCancel = (Button) fabricView.lookup("#fs_btnCancel");
			Button fs_btnRegist = (Button) fabricView.lookup("#fs_btnRegist");
			TableView<FabricVO> fs_tableView = (TableView) fabricView.lookup("#fs_tableView");

			fs_btnCancel.setOnAction(e -> {
				mainStage.close();
			});

			fs_btnRegist.setOnAction(e -> {

				try {

					f_txtNumber.setText(selectedFabricIndex1);
					f_txtName.setText(selectedFabricName);

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				mainStage.close();
			});

			fs_tableView.setOnMouseClicked(e -> {

				if (e.getClickCount() == 1) {

					try {
						selectFabric = fs_tableView.getSelectionModel().getSelectedItems();

						selectedFabricIndex1 = selectFabric.get(0).getF_number();
						selectedFabricName = selectFabric.get(0).getF_name();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (e.getClickCount() == 2) {

					try {
						selectFabric = fs_tableView.getSelectionModel().getSelectedItems();
						selectedFabricIndex1 = selectFabric.get(0).getF_number();

						f_txtNumber.setText(selectedFabricIndex1);
						f_txtName.setText(selectedFabricName);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					mainStage.close();
				}
			});

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// 고객정보변경 버튼 이벤트
	public void handlerBtnCustomerSearchAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderCustomerSearch.fxml"));
			Parent customerView = (Parent) loader.load();

			Scene scene = new Scene(customerView);
			Stage mainStage = new Stage();
			mainStage.setTitle("고객 정보 변경");
			mainStage.setResizable(false);
			mainStage.setScene(scene);
			mainStage.show();

			Button cs_btnCancel = (Button) customerView.lookup("#cs_btnCancel");
			Button cs_btnRegist = (Button) customerView.lookup("#cs_btnRegist");
			TableView<CustomerVO> cs_tableView = (TableView) customerView.lookup("#cs_tableView");

			cs_btnCancel.setOnAction(e -> {
				mainStage.close();
			});

			cs_btnRegist.setOnAction(e -> {

				try {

					c_txtNumber.setText(selectedCustomerIndex + "");
					c_txtName.setText(selectedCustomerName);
					c_txtPhone.setText(selectedCustomerPhone);
					o_txtEmail.setText(selectedCustomerEmail);
					o_txtAddress.setText(selectedCustomerAddress);

				} catch (Exception e1) {

					e1.printStackTrace();
				}

				mainStage.close();
			});

			cs_tableView.setOnMouseClicked(e -> {

				if (e.getClickCount() == 1) {
					try {
						selectCustomer = cs_tableView.getSelectionModel().getSelectedItems();
						selectedCustomerIndex = selectCustomer.get(0).getC_number();
						selectedCustomerName = selectCustomer.get(0).getC_name();
						selectedCustomerEmail = selectCustomer.get(0).getC_email();
						selectedCustomerAddress = selectCustomer.get(0).getC_address();
						selectedCustomerPhone = selectCustomer.get(0).getC_phone();

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if (e.getClickCount() == 2) {
					try {
						selectCustomer = cs_tableView.getSelectionModel().getSelectedItems();
						selectedCustomerIndex = selectCustomer.get(0).getC_number();

						c_txtNumber.setText(selectedCustomerIndex + "");
						c_txtName.setText(selectedCustomerName);
						c_txtPhone.setText(selectedCustomerPhone);
						o_txtEmail.setText(selectedCustomerEmail);
						o_txtAddress.setText(selectedCustomerAddress);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					mainStage.close();
				}
			});

		} catch (Exception e1) {
			e1.printStackTrace();
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
			c_txtNumber.clear();
			c_txtName.clear();
			c_txtPhone.clear();
			o_txtStatus.clear();
			o_dpDate.setValue(null);
			o_txtEmail.clear();
			o_txtAddress.clear();
			o_txtRemarks.clear();
			o_dpStart.setValue(null);
			o_dpFinish.setValue(null);
			o_cbStatus.getSelectionModel().clearSelection();
			o_txtNumber.requestFocus();

			o_txtNumber.setDisable(true);
			a_txtNumber.setDisable(true);
			f_txtNumber.setDisable(true);
			f_txtName.setDisable(true);
			o_txtAmount.setDisable(true);
			o_txtPrice.setDisable(true);
			c_txtNumber.setDisable(true);
			c_txtName.setDisable(true);
			c_txtPhone.setDisable(true);
			o_txtStatus.setDisable(true);
			o_dpDate.setDisable(true);
			o_txtEmail.setDisable(true);
			o_txtAddress.setDisable(true);
			o_txtRemarks.setDisable(true);

			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);
			c_btnNumber.setDisable(true);
			f_btnNumber.setDisable(true);
			a_btnNumber.setDisable(true);
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
				c_txtNumber.setText(selectedC_number + "");
				c_txtName.setText(selectedC_name);
				c_txtPhone.setText(selectedC_phone);
				o_txtEmail.setText(selectedO_email);
				o_txtStatus.setText(selectedO_status);
				o_dpDate.setValue(LocalDate.parse(selectedO_registdate));
				o_txtAddress.setText(selectedO_address);
				o_txtRemarks.setText(selectedO_remarks);

				o_txtAmount.setDisable(false);
				o_txtPrice.setDisable(false);
				o_dpDate.setDisable(false);
				o_txtEmail.setDisable(false);
				o_txtAddress.setDisable(false);
				o_txtRemarks.setDisable(false);

				o_btnUpdate.setDisable(false);
				o_btnDelete.setDisable(false);
				c_btnNumber.setDisable(false);
				f_btnNumber.setDisable(false);
				a_btnNumber.setDisable(false);

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
				c_txtNumber.clear();
				c_txtName.clear();
				c_txtPhone.clear();
				o_txtStatus.clear();
				o_dpDate.setValue(null);
				o_txtEmail.clear();
				o_txtAddress.clear();
				o_txtRemarks.clear();
				o_dpStart.setValue(null);
				o_dpFinish.setValue(null);
				o_txtNumber.requestFocus();

				o_txtNumber.setDisable(true);
				a_txtNumber.setDisable(true);
				f_txtNumber.setDisable(true);
				f_txtName.setDisable(true);
				o_txtAmount.setDisable(true);
				o_txtPrice.setDisable(true);
				c_txtNumber.setDisable(true);
				c_txtName.setDisable(true);
				c_txtPhone.setDisable(true);
				o_txtStatus.setDisable(true);
				o_dpDate.setDisable(true);
				o_txtEmail.setDisable(true);
				o_txtAddress.setDisable(true);
				o_txtRemarks.setDisable(true);

				o_btnUpdate.setDisable(true);
				o_btnDelete.setDisable(true);
				c_btnNumber.setDisable(true);
				f_btnNumber.setDisable(true);
				a_btnNumber.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess = false;
			OrderDAO odao = new OrderDAO();
			sucess = odao.getOrderUpdate(o_txtNumber.getText().trim(), a_txtNumber.getText().trim(),
					f_txtNumber.getText().trim(), c_txtNumber.getText().trim(), o_txtEmail.getText(),
					o_txtAddress.getText().trim(), o_txtAmount.getText().trim(), o_txtPrice.getText().trim(),
					o_txtStatus.getText().trim(), o_dpDate.getValue().toString(), o_txtRemarks.getText());

			if (sucess) {
				orderDataList.removeAll(orderDataList);
				orderTotalList();

				o_txtNumber.clear();
				a_txtNumber.clear();
				f_txtNumber.clear();
				f_txtName.clear();
				o_txtAmount.clear();
				o_txtPrice.clear();
				c_txtNumber.clear();
				c_txtName.clear();
				c_txtPhone.clear();
				o_txtStatus.clear();
				o_dpDate.setValue(null);
				o_txtEmail.clear();
				o_txtAddress.clear();
				o_txtRemarks.clear();
				o_dpStart.setValue(null);
				o_dpFinish.setValue(null);
				o_txtNumber.requestFocus();

				o_txtNumber.setDisable(true);
				a_txtNumber.setDisable(true);
				f_txtNumber.setDisable(true);
				f_txtName.setDisable(true);
				o_txtAmount.setDisable(true);
				o_txtPrice.setDisable(true);
				c_txtNumber.setDisable(true);
				c_txtName.setDisable(true);
				c_txtPhone.setDisable(true);
				o_txtStatus.setDisable(true);
				o_dpDate.setDisable(true);
				o_txtEmail.setDisable(true);
				o_txtAddress.setDisable(true);
				o_txtRemarks.setDisable(true);

				o_btnUpdate.setDisable(true);
				o_btnDelete.setDisable(true);
				c_btnNumber.setDisable(true);
				f_btnNumber.setDisable(true);
				a_btnNumber.setDisable(true);
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
			c_txtNumber.clear();
			c_txtName.clear();
			c_txtPhone.clear();
			o_txtStatus.clear();
			o_dpDate.setValue(null);
			o_txtEmail.clear();
			o_txtAddress.clear();
			o_txtRemarks.clear();
			o_dpStart.setValue(null);
			o_dpFinish.setValue(null);
			o_txtNumber.requestFocus();

			o_txtNumber.setDisable(true);
			a_txtNumber.setDisable(true);
			f_txtNumber.setDisable(true);
			f_txtName.setDisable(true);
			o_txtAmount.setDisable(true);
			o_txtPrice.setDisable(true);
			c_txtNumber.setDisable(true);
			c_txtName.setDisable(true);
			c_txtPhone.setDisable(true);
			o_txtStatus.setDisable(true);
			o_dpDate.setDisable(true);
			o_txtEmail.setDisable(true);
			o_txtAddress.setDisable(true);
			o_txtRemarks.setDisable(true);

			o_btnUpdate.setDisable(true);
			o_btnDelete.setDisable(true);
			c_btnNumber.setDisable(true);
			f_btnNumber.setDisable(true);
			a_btnNumber.setDisable(true);

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
