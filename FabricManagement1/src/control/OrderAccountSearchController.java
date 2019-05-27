package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AccountVO;

public class OrderAccountSearchController implements Initializable {

	@FXML
	Button as_btnRegist;
	@FXML
	Button as_btnCancel;

	@FXML
	TableView<AccountVO> as_tableView = new TableView<AccountVO>();

	ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();
	ObservableList<AccountVO> selectAccount = null;
	int selectedAccountIndex;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			TableColumn colAnumber = new TableColumn("번호");
			colAnumber.setPrefWidth(40);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colAcname = new TableColumn("거래처명");
			colAcname.setPrefWidth(90);
			colAcname.setStyle("-fx-alignment: CENTER");
			colAcname.setCellValueFactory(new PropertyValueFactory<>("a_cname"));

			TableColumn colAmname = new TableColumn("담당자명");
			colAmname.setPrefWidth(90);
			colAmname.setStyle("-fx-alignment: CENTER");
			colAmname.setCellValueFactory(new PropertyValueFactory<>("a_mname"));

			TableColumn colAphone = new TableColumn("연락처");
			colAphone.setPrefWidth(100);
			colAphone.setStyle("-fx-alignment: CENTER");
			colAphone.setCellValueFactory(new PropertyValueFactory<>("a_phone"));

			TableColumn colAemail = new TableColumn("이메일");
			colAemail.setPrefWidth(100);
			colAemail.setStyle("-fx-alignment: CENTER");
			colAemail.setCellValueFactory(new PropertyValueFactory<>("a_email"));

			TableColumn colAbnumber = new TableColumn("사업자번호");
			colAbnumber.setPrefWidth(100);
			colAbnumber.setStyle("-fx-alignment: CENTER");
			colAbnumber.setCellValueFactory(new PropertyValueFactory<>("a_bnumber"));

			TableColumn colAmsubject = new TableColumn("주종목");
			colAmsubject.setPrefWidth(100);
			colAmsubject.setStyle("-fx-alignment: CENTER");
			colAmsubject.setCellValueFactory(new PropertyValueFactory<>("a_msubject"));

			TableColumn colAaddress = new TableColumn("주소");
			colAaddress.setPrefWidth(150);
			colAaddress.setStyle("-fx-alignment: CENTER");
			colAaddress.setCellValueFactory(new PropertyValueFactory<>("a_address"));

			TableColumn colAremarks = new TableColumn("비고");
			colAremarks.setPrefWidth(150);
			colAremarks.setStyle("-fx-alignment: CENTER");
			colAremarks.setCellValueFactory(new PropertyValueFactory<>("a_remarks"));

			TableColumn colAregistdate = new TableColumn("등록일");
			colAregistdate.setPrefWidth(90);
			colAregistdate.setStyle("-fx-alignment: CENTER");
			colAregistdate.setCellValueFactory(new PropertyValueFactory<>("a_registdate"));

			as_tableView.setItems(accountDataList);

			as_tableView.getColumns().addAll(colAnumber, colAcname, colAmname, colAphone, colAemail, colAbnumber,
					colAmsubject, colAaddress, colAremarks, colAregistdate);

			// 전체 목록
			accountTotalList();

			// 고객 전체 목록
			accountTotalList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 원단 전체 리스트
	public void accountTotalList() throws Exception {

		accountDataList.removeAll(accountDataList);

		AccountDAO aDao = new AccountDAO();
		AccountVO aVo = null;
		ArrayList<AccountVO> list;

		list = aDao.getAccountTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			accountDataList.add(aVo);
		}
	}

}
