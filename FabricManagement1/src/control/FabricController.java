package control;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.FabricVO;

public class FabricController implements Initializable {

	@FXML
	TextField f_txtNumber;
	@FXML
	TextField f_txtSort;
	@FXML
	TextField f_txtName;
	@FXML
	TextField f_txtColor;
	@FXML
	TextField f_txtSize;
	@FXML
	TextField f_txtWeight;
	@FXML
	TextField f_txtOrigin;
	@FXML
	TextField f_txtCname;
	@FXML
	TextField f_txtPrice;
	@FXML
	TextField f_txtPhone;
	@FXML
	TextField f_txtMaterial;
	@FXML
	TextField f_txtTrait;
	@FXML
	TextArea f_txtRemarks;
	@FXML
	ImageView imageView;
	@FXML
	Button btnImageFile;
	@FXML
	Button f_btnRegist;
	@FXML
	Button f_btnInit;
	@FXML
	Button f_btnUpdate;
	@FXML
	Button f_btnDelete;
	@FXML
	Button f_btnExit;
	@FXML
	TextField f_txtSearch;
	@FXML
	Button f_btnSearch;
	@FXML
	Button f_btnTrade;
	@FXML
	private TableView<FabricVO> f_tableView = new TableView<>();

	ObservableList<FabricVO> fabricDataList = FXCollections.observableArrayList();
	ObservableList<FabricVO> selectFabric = null;
	String selectedFabricIndex = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			FabricDAO dao = new FabricDAO();
			f_btnUpdate.setDisable(true);
			f_btnDelete.setDisable(true);

			TableColumn colFnumber = new TableColumn("제품코드");
			colFnumber.setPrefWidth(40);
			colFnumber.setStyle("-fx-alignment: CENTER");
			colFnumber.setCellValueFactory(new PropertyValueFactory<>("f_number"));

			TableColumn colFsort = new TableColumn("종류");
			colFsort.setPrefWidth(100);
			colFsort.setStyle("-fx-alignment: CENTER");
			colFsort.setCellValueFactory(new PropertyValueFactory<>("f_sort"));

			TableColumn colFname = new TableColumn("제품명");
			colFname.setPrefWidth(100);
			colFname.setStyle("-fx-alignment: CENTER");
			colFname.setCellValueFactory(new PropertyValueFactory<>("f_name"));

			TableColumn colFcolor = new TableColumn("색상");
			colFcolor.setPrefWidth(100);
			colFcolor.setStyle("-fx-alignment: CENTER");
			colFcolor.setCellValueFactory(new PropertyValueFactory<>("f_color"));

			TableColumn colFsize = new TableColumn("사이즈");
			colFsize.setPrefWidth(100);
			colFsize.setStyle("-fx-alignment: CENTER");
			colFsize.setCellValueFactory(new PropertyValueFactory<>("f_size"));

			TableColumn colFweight = new TableColumn("중량");
			colFweight.setPrefWidth(100);
			colFweight.setStyle("-fx-alignment: CENTER");
			colFweight.setCellValueFactory(new PropertyValueFactory<>("f_weight"));

			TableColumn colForigin = new TableColumn("원산지");
			colForigin.setPrefWidth(100);
			colForigin.setStyle("-fx-alignment: CENTER");
			colForigin.setCellValueFactory(new PropertyValueFactory<>("f_origin"));

			TableColumn colFcname = new TableColumn("제조사");
			colFcname.setPrefWidth(100);
			colFcname.setStyle("-fx-alignment: CENTER");
			colFcname.setCellValueFactory(new PropertyValueFactory<>("f_cname"));

			TableColumn colFprice = new TableColumn("가격");
			colFprice.setPrefWidth(100);
			colFprice.setStyle("-fx-alignment: CENTER");
			colFprice.setCellValueFactory(new PropertyValueFactory<>("f_price"));

			TableColumn colFphone = new TableColumn("연락처");
			colFphone.setPrefWidth(100);
			colFphone.setStyle("-fx-alignment: CENTER");
			colFphone.setCellValueFactory(new PropertyValueFactory<>("f_phone"));

			TableColumn colFmaterial = new TableColumn("소재");
			colFmaterial.setPrefWidth(100);
			colFmaterial.setStyle("-fx-alignment: CENTER");
			colFmaterial.setCellValueFactory(new PropertyValueFactory<>("f_material"));

			TableColumn colFtrait = new TableColumn("특징");
			colFtrait.setPrefWidth(100);
			colFtrait.setStyle("-fx-alignment: CENTER");
			colFtrait.setCellValueFactory(new PropertyValueFactory<>("f_trait"));

			TableColumn colFremarks = new TableColumn("비고");
			colFremarks.setPrefWidth(100);
			colFremarks.setStyle("-fx-alignment: CENTER");
			colFremarks.setCellValueFactory(new PropertyValueFactory<>("f_remarks"));

			f_tableView.setItems(fabricDataList);

			f_tableView.getColumns().addAll(colFnumber, colFsort, colFname, colFcolor, colFsize, colFweight, colForigin,
					colFcname, colFprice, colFphone, colFmaterial, colFtrait, colFremarks);

			// 전체 목록
			fabricTotalList();

			f_btnRegist.setOnAction(event -> handlerBtnRegistAction(event));
			f_btnInit.setOnAction(event -> handlerBtnInitAction(event));
			f_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event));
			f_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event));
			f_btnExit.setOnAction(event -> handlerBtnExitAction(event));
			f_btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
			f_tableView.setOnMouseClicked(event -> handlerFabricTableViewAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerFabricTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectFabric = f_tableView.getSelectionModel().getSelectedItems();
				selectedFabricIndex = selectFabric.get(0).getF_number();
				String selectedf_sort = selectFabric.get(0).getF_sort();
				String selectedf_name = selectFabric.get(0).getF_name();
				String selectedf_color = selectFabric.get(0).getF_color();
				String selectedf_size = selectFabric.get(0).getF_size();
				String selectedf_weight = selectFabric.get(0).getF_weight();
				String selectedf_origin = selectFabric.get(0).getF_origin();
				String selectedf_cname = selectFabric.get(0).getF_cname();
				String selectedf_price = selectFabric.get(0).getF_price();
				String selectedf_phone = selectFabric.get(0).getF_phone();
				String selectedf_material = selectFabric.get(0).getF_material();
				String selectedf_trait = selectFabric.get(0).getF_trait();
				String selectedf_remarks = selectFabric.get(0).getF_remarks();

				f_txtNumber.setText(selectedFabricIndex);
				f_txtSort.setText(selectedf_sort);
				f_txtName.setText(selectedf_name);
				f_txtColor.setText(selectedf_color);
				f_txtSize.setText(selectedf_size);
				f_txtWeight.setText(selectedf_weight);
				f_txtOrigin.setText(selectedf_origin);
				f_txtCname.setText(selectedf_cname);
				f_txtPrice.setText(selectedf_price);
				f_txtPhone.setText(selectedf_phone);
				f_txtMaterial.setText(selectedf_material);
				f_txtTrait.setText(selectedf_trait);
				f_txtRemarks.setText(selectedf_remarks);

				f_txtCname.setEditable(false);

				f_btnUpdate.setDisable(false);
				f_btnDelete.setDisable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 등록버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {
		try {
			fabricDataList.removeAll(fabricDataList);

			FabricVO fvo = null;
			FabricDAO fdao = null;

			if (f_txtNumber.getLength() != 0 && f_txtSort.getLength() != 0 && f_txtName.getLength() != 0
					&& f_txtColor.getLength() != 0 && f_txtSize.getLength() != 0 && f_txtWeight.getLength() != 0
					&& f_txtOrigin.getLength() != 0 && f_txtCname.getLength() != 0 && f_txtPrice.getLength() != 0
					&& f_txtPhone.getLength() != 0 && f_txtMaterial.getLength() != 0) {

				fvo = new FabricVO(f_txtNumber.getText().trim(), f_txtSort.getText().trim(), f_txtName.getText().trim(),
						f_txtColor.getText().trim(), f_txtSize.getText().trim(), f_txtWeight.getText().trim(),
						f_txtOrigin.getText().trim(), f_txtCname.getText().trim(), f_txtPrice.getText().trim(),
						f_txtPhone.getText().trim(), f_txtMaterial.getText().trim(), f_txtTrait.getText().trim(),
						f_txtRemarks.getText().trim());
				fdao = new FabricDAO();
				fdao.getFabricRegist(fvo);

				fabricTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 정보 입력");
				alert.setHeaderText(f_txtCname.getText() + " 원단 정보가 성공적으로 추가되었습니다.");
				alert.setContentText("다음 원단 정보를 입력하세요.");
				alert.showAndWait();

				f_txtNumber.clear();
				f_txtSort.clear();
				f_txtName.clear();
				f_txtColor.clear();
				f_txtSize.clear();
				f_txtWeight.clear();
				f_txtOrigin.clear();
				f_txtCname.clear();
				f_txtPrice.clear();
				f_txtPhone.clear();
				f_txtMaterial.clear();
				f_txtTrait.clear();
				f_txtRemarks.clear();

				f_txtNumber.requestFocus();
			} else {
				fabricDataList.removeAll(fabricDataList);

				fabricTotalList();

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 미입력");
				alert.setHeaderText("원단 정보중에 미입력된 항목이 있습니다.");
				alert.setContentText("원단 정보를 정확히 입력하세요.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("원단 정보 입력");
			alert.setHeaderText("원단 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 전체 목록
	public void fabricTotalList() throws Exception {

		FabricDAO fDao = new FabricDAO();
		FabricVO fVo = null;
		ArrayList<String> title;
		ArrayList<FabricVO> list;

		title = fDao.getFabricColumnName();
		int columnCount = title.size();

		list = fDao.getFabricTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			fVo = list.get(index);
			fabricDataList.add(fVo);
		}
	}

	// 전체 목록
	public void handlerBtnStudentTotalListAction(ActionEvent event) {
		try {
			fabricDataList.removeAll(fabricDataList);
			fabricTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnInitAction(ActionEvent event) {
		try {
			fabricDataList.removeAll(fabricDataList);
			fabricTotalList();

			f_txtNumber.clear();
			f_txtSort.clear();
			f_txtName.clear();
			f_txtColor.clear();
			f_txtSize.clear();
			f_txtWeight.clear();
			f_txtOrigin.clear();
			f_txtCname.clear();
			f_txtPrice.clear();
			f_txtPhone.clear();
			f_txtMaterial.clear();
			f_txtTrait.clear();
			f_txtRemarks.clear();

			f_txtNumber.setEditable(true);
			f_txtName.setEditable(true);
			f_btnUpdate.setDisable(true);
			f_btnDelete.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			FabricDAO sdao = new FabricDAO();
			if (f_txtRemarks.getLength() != 0) {
				sucess = sdao.getFabricUpdate(f_txtNumber.getText().trim(), f_txtSort.getText().trim(),
						f_txtName.getText().trim(), f_txtColor.getText().trim(), f_txtSize.getText().trim(),
						f_txtWeight.getText().trim(), f_txtOrigin.getText().trim(), f_txtCname.getText().trim(),
						f_txtPrice.getText().trim(), f_txtPhone.getText().trim(), f_txtMaterial.getText().trim(),
						f_txtTrait.getText().trim(), f_txtRemarks.getText().trim());
			} else {
				sucess = sdao.getFabricUpdate(f_txtNumber.getText().trim(), f_txtSort.getText().trim(),
						f_txtName.getText().trim(), f_txtColor.getText().trim(), f_txtSize.getText().trim(),
						f_txtWeight.getText().trim(), f_txtOrigin.getText().trim(), f_txtCname.getText().trim(),
						f_txtPrice.getText().trim(), f_txtPhone.getText().trim(), f_txtMaterial.getText().trim(),
						f_txtTrait.getText().trim());
			}

			if (sucess) {
				fabricDataList.removeAll(fabricDataList);
				fabricTotalList();

				f_txtNumber.clear();
				f_txtSort.clear();
				f_txtName.clear();
				f_txtColor.clear();
				f_txtSize.clear();
				f_txtWeight.clear();
				f_txtOrigin.clear();
				f_txtCname.clear();
				f_txtPrice.clear();
				f_txtPhone.clear();
				f_txtMaterial.clear();
				f_txtTrait.clear();
				f_txtRemarks.clear();

				f_txtNumber.setEditable(true);
				f_txtName.setEditable(true);
				f_btnUpdate.setDisable(true);
				f_btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnDeleteAction(ActionEvent event) {
		try {
			boolean sucess;
			FabricDAO sDao = new FabricDAO();
			sucess = sDao.getFabricDelete(selectedFabricIndex);

			if (sucess) {

				fabricDataList.removeAll(fabricDataList);
				fabricTotalList();

				f_txtNumber.clear();
				f_txtSort.clear();
				f_txtName.clear();
				f_txtColor.clear();
				f_txtSize.clear();
				f_txtWeight.clear();
				f_txtOrigin.clear();
				f_txtCname.clear();
				f_txtPrice.clear();
				f_txtPhone.clear();
				f_txtMaterial.clear();
				f_txtTrait.clear();
				f_txtRemarks.clear();

				f_txtNumber.setEditable(true);
				f_txtName.setEditable(true);
				f_btnUpdate.setDisable(true);
				f_btnDelete.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<FabricVO> searchList = new ArrayList<FabricVO>();
		FabricVO fVo = null;
		FabricDAO fDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = f_txtSearch.getText().trim();
			fDao = new FabricDAO();
			searchList = fDao.getFabricCheck(searchName);

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 검색");
				alert.setHeaderText("원단명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
			}

			if (searchList != null) {
				int rowCount = searchList.size();

				f_txtSearch.clear();
				fabricDataList.removeAll(fabricDataList);
				for (int index = 0; index < rowCount; index++) {
					fVo = searchList.get(index);
					fabricDataList.add(fVo);
					searchResult = true;
				}
			}

			if (!searchResult) {
				f_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("원단 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				fabricTotalList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("원단 정보 검색 오류");
			alert.setHeaderText("원단 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

}
