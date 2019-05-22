package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	Button o_btnUpdate1;
	@FXML
	Button o_btnDelete1;
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
	Button o_btnUpdate2;
	@FXML
	Button o_btnDelete2;
	@FXML
	TableView<OrderVO> o_tableView;

	ObservableList<OrderVO> orderDataList = FXCollections.observableArrayList();
	ObservableList<OrderVO> selectOrder = null;
	int selectedOrderIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void orderTotalList() throws Exception {
		orderDataList.removeAll(orderDataList);
		OrderDAO oDao = new OrderDAO();
		OrderVO oVo = null;
		ArrayList<String> title;
		ArrayList<OrderVO> list;

		title = oDao.getOrderColumnName();
		int columnCount = title.size();

		list = oDao.getOrderTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			oVo = list.get(index);
			orderDataList.add(oVo);
		}
	}
}
