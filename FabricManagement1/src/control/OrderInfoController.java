package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.OrderVO;

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
			colOnumber.setPrefWidth(40);
			colOnumber.setStyle("-fx-alignment: CENTER");
			colOnumber.setCellValueFactory(new PropertyValueFactory<>("o_number"));

			TableColumn colAnumber = new TableColumn("거래처번호");
			colAnumber.setPrefWidth(90);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colFnumber = new TableColumn("제품코드");
			colFnumber.setPrefWidth(90);
			colFnumber.setStyle("-fx-alignment: CENTER");
			colFnumber.setCellValueFactory(new PropertyValueFactory<>("f_number"));

			TableColumn colFname = new TableColumn("제품명");
			colFname.setPrefWidth(90);
			colFname.setStyle("-fx-alignment: CENTER");
			colFname.setCellValueFactory(new PropertyValueFactory<>("f_name"));

			TableColumn colOamount = new TableColumn("수량");
			colOamount.setPrefWidth(150);
			colOamount.setStyle("-fx-alignment: CENTER");
			colOamount.setCellValueFactory(new PropertyValueFactory<>("o_amount"));

			TableColumn colOprice = new TableColumn("금액");
			colOprice.setPrefWidth(150);
			colOprice.setStyle("-fx-alignment: CENTER");
			colOprice.setCellValueFactory(new PropertyValueFactory<>("o_price"));

			TableColumn colOname = new TableColumn("주문자명");
			colOname.setPrefWidth(100);
			colOname.setStyle("-fx-alignment: CENTER");
			colOname.setCellValueFactory(new PropertyValueFactory<>("o_name"));

			TableColumn colOphone = new TableColumn("연락처");
			colOphone.setPrefWidth(100);
			colOphone.setStyle("-fx-alignment: CENTER");
			colOphone.setCellValueFactory(new PropertyValueFactory<>("o_phone"));

			TableColumn colOstatus = new TableColumn("상태");
			colOstatus.setPrefWidth(90);
			colOstatus.setStyle("-fx-alignment: CENTER");
			colOstatus.setCellValueFactory(new PropertyValueFactory<>("o_status"));

			TableColumn colOregistdate = new TableColumn("주문일");
			colOregistdate.setPrefWidth(150);
			colOregistdate.setStyle("-fx-alignment: CENTER");
			colOregistdate.setCellValueFactory(new PropertyValueFactory<>("o_registdate"));

			TableColumn colAemail = new TableColumn("담당자이메일");
			colAemail.setPrefWidth(150);
			colAemail.setStyle("-fx-alignment: CENTER");
			colAemail.setCellValueFactory(new PropertyValueFactory<>("a_email"));

			TableColumn colOaddress = new TableColumn("주소");
			colOaddress.setPrefWidth(100);
			colOaddress.setStyle("-fx-alignment: CENTER");
			colOaddress.setCellValueFactory(new PropertyValueFactory<>("o_address"));

			TableColumn colOremarks = new TableColumn("비고");
			colOremarks.setPrefWidth(150);
			colOremarks.setStyle("-fx-alignment: CENTER");
			colOremarks.setCellValueFactory(new PropertyValueFactory<>("o_remarks"));

			o_tableView.setItems(orderDataList);

			o_tableView.getColumns().addAll(colOnumber, colAnumber, colFnumber, colOname, colOphone, colOaddress,
					colOamount, colOprice, colOstatus, colOregistdate, colOremarks, colAemail);

			// 전체 목록
			orderTotalList();

			o_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			o_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			o_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			o_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			o_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			o_tableView.setOnMouseClicked(event -> handlerTradeTableViewAction(event)); // 테이블뷰 더블클릭 이벤트
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Object handlerTradeTableViewAction(MouseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnSearchAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnExitAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnDeleteAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnUpdateAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object handlerBtnInitAction(ActionEvent event) {
		// TODO Auto-generated method stub
		return null;
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
