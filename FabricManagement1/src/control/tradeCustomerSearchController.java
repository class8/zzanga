package control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import model.CustomerVO;

public class tradeCustomerSearchController implements Initializable {

	@FXML
	Button cs_btnRegist;
	@FXML
	Button cs_btnCancel;

	@FXML
	TableView<CustomerVO> cs_tableView = new TableView<CustomerVO>();

	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectCustomer = null;
	int selectedCustomerIndex;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {

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
			colC_phone.setPrefWidth(70);
			colC_phone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colC_bnumber = new TableColumn("사업자번호");
			colC_bnumber.setPrefWidth(70);
			colC_bnumber.setCellValueFactory(new PropertyValueFactory<>("c_bnumber"));

			TableColumn colC_address = new TableColumn("주소");
			colC_address.setPrefWidth(70);
			colC_address.setCellValueFactory(new PropertyValueFactory<>("c_address"));

			TableColumn colC_email = new TableColumn("이메일");
			colC_email.setPrefWidth(70);
			colC_email.setCellValueFactory(new PropertyValueFactory<>("c_email"));

			TableColumn colC_remarks = new TableColumn("비고");
			colC_remarks.setPrefWidth(70);
			colC_remarks.setCellValueFactory(new PropertyValueFactory<>("c_remarks"));

			TableColumn colC_registdate = new TableColumn("등록일");
			colC_registdate.setPrefWidth(70);
			colC_registdate.setCellValueFactory(new PropertyValueFactory<>("c_registdate"));

			cs_tableView.setItems(customerDataList);

			cs_tableView.getColumns().addAll(colC_number, colC_name, colC_cname, colC_phone, colC_bnumber, colC_address,
					colC_email, colC_remarks, colC_registdate);
			// 고객 전체 목록
			customerTotalList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 원단 전체 리스트
	public void customerTotalList() throws Exception {

		customerDataList.removeAll(customerDataList);

		CustomerDAO cDao = new CustomerDAO();
		CustomerVO cVo = null;
		ArrayList<CustomerVO> list;

		list = cDao.getCustomerTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			cVo = list.get(index);
			customerDataList.add(cVo);
		}
	}

}
