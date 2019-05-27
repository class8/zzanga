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
import model.FabricVO;

public class OrderFabricSearchController implements Initializable {

	@FXML
	Button fs_btnRegist;
	@FXML
	Button fs_btnCancel;

	@FXML
	TableView<FabricVO> fs_tableView = new TableView<FabricVO>();

	ObservableList<FabricVO> fabricDataList = FXCollections.observableArrayList();
	ObservableList<FabricVO> selectFabric = null;
	int selectedFabricIndex1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			TableColumn colFnumber = new TableColumn("제품코드");
			colFnumber.setPrefWidth(40);
			colFnumber.setCellValueFactory(new PropertyValueFactory<>("f_number"));

			TableColumn colFsort = new TableColumn("종류");
			colFsort.setPrefWidth(100);
			colFsort.setCellValueFactory(new PropertyValueFactory<>("f_sort"));

			TableColumn colFname = new TableColumn("제품명");
			colFname.setPrefWidth(100);
			colFname.setCellValueFactory(new PropertyValueFactory<>("f_name"));

			TableColumn colFcolor = new TableColumn("색상");
			colFcolor.setPrefWidth(100);
			colFcolor.setCellValueFactory(new PropertyValueFactory<>("f_color"));

			TableColumn colFsize = new TableColumn("사이즈");
			colFsize.setPrefWidth(100);
			colFsize.setCellValueFactory(new PropertyValueFactory<>("f_size"));

			TableColumn colFweight = new TableColumn("중량");
			colFweight.setPrefWidth(100);
			colFweight.setCellValueFactory(new PropertyValueFactory<>("f_weight"));

			TableColumn colForigin = new TableColumn("원산지");
			colForigin.setPrefWidth(100);
			colForigin.setCellValueFactory(new PropertyValueFactory<>("f_origin"));

			TableColumn colFcname = new TableColumn("제조사");
			colFcname.setPrefWidth(100);
			colFcname.setStyle("-fx-alignment: CENTER");
			colFcname.setCellValueFactory(new PropertyValueFactory<>("f_cname"));

			TableColumn colFprice = new TableColumn("가격");
			colFprice.setPrefWidth(100);
			colFprice.setCellValueFactory(new PropertyValueFactory<>("f_price"));

			TableColumn colFphone = new TableColumn("연락처");
			colFphone.setPrefWidth(100);
			colFphone.setCellValueFactory(new PropertyValueFactory<>("f_phone"));

			TableColumn colFmaterial = new TableColumn("소재");
			colFmaterial.setPrefWidth(100);
			colFmaterial.setCellValueFactory(new PropertyValueFactory<>("f_material"));

			TableColumn colFtrait = new TableColumn("특징");
			colFtrait.setPrefWidth(100);
			colFtrait.setCellValueFactory(new PropertyValueFactory<>("f_trait"));

			TableColumn colFremarks = new TableColumn("비고");
			colFremarks.setPrefWidth(100);
			colFremarks.setCellValueFactory(new PropertyValueFactory<>("f_remarks"));

			TableColumn colFregistdate = new TableColumn("등록일");
			colFregistdate.setPrefWidth(80);
			colFregistdate.setCellValueFactory(new PropertyValueFactory<>("f_registdate"));

			TableColumn colImageFileName = new TableColumn("이미지");
			colImageFileName.setMinWidth(260);
			colImageFileName.setCellValueFactory(new PropertyValueFactory<>("filename"));

			fs_tableView.setItems(fabricDataList);

			fs_tableView.getColumns().addAll(colFnumber, colFsort, colFname, colFcolor, colFsize, colFweight,
					colForigin, colFcname, colFprice, colFphone, colFmaterial, colFtrait, colFremarks, colFregistdate,
					colImageFileName);

			// 전체 목록
			fabricTotalList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 원단 전체 리스트
	public void fabricTotalList() throws Exception {

		fabricDataList.removeAll(fabricDataList);

		FabricDAO fDao = new FabricDAO();
		FabricVO fVo = null;
		ArrayList<FabricVO> list;

		list = fDao.getFabricTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			fVo = list.get(index);
			fabricDataList.add(fVo);
		}
	}

}
