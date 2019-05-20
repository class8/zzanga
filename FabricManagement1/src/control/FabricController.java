package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.CustomerVO;
import model.FabricVO;
import model.TradeVO;

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
	TextField f_registdate; // 등록일
	@FXML
	TextField fileName;

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
	TableView<FabricVO> f_tableView = new TableView<>();

	ObservableList<FabricVO> fabricDataList = FXCollections.observableArrayList();
	ObservableList<FabricVO> selectFabric = null;
	String selectedFabricIndex = "";

	// 추가
	private Stage primaryStage;
	String selectFileName = ""; // 이미지 파일명
	String localUrl = ""; // 이미지 파일 경로
	Image localImage;

	File selectedFile = null;

	// 이미지 처리
	// 이미지 저장할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("C:/images");
	// 이미지 불러올 파일을 저장할 파일 객체 선언
	private File file = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			FabricDAO fdao = new FabricDAO();
			f_btnUpdate.setDisable(true);
			f_btnDelete.setDisable(true);

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

			f_tableView.setItems(fabricDataList);

			f_tableView.getColumns().addAll(colFnumber, colFsort, colFname, colFcolor, colFsize, colFweight, colForigin,
					colFcname, colFprice, colFphone, colFmaterial, colFtrait, colFremarks, colFregistdate,
					colImageFileName);

			// 전체 목록
			fabricTotalList();

			f_btnRegist.setOnAction(event -> handlerBtnRegistAction(event)); // 등록버튼 이벤트
			f_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화버튼 이벤트
			f_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			f_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제버튼 이벤트
			f_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			f_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			f_tableView.setOnMouseClicked(event -> handlerFabricTableViewAction(event)); // 마우스 더블클릭 이벤트
			btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event));
			f_btnTrade.setOnAction(event -> handlerBtnTradeAction(event)); // 거래등록버튼 이벤트

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 거래등록버튼 이벤트
	public void handlerBtnTradeAction(ActionEvent event) {

		try {
			
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			
			//dialog.initOwner(f_btnTrade.getScene().getWindow());

			Parent parent = FXMLLoader.load(getClass().getResource("/view/tradeReg.fxml"));

			//TradeVO tradeReg = (TradeVO) parent.lookup("#tradeReg");


			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 등록버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {

		try {

			fabricDataList.removeAll(fabricDataList);

			FabricVO fvo = null;
			FabricDAO fdao = new FabricDAO();

			File dirMake = new File(dirSave.getAbsolutePath());

			// 이미지 저장 폴더 생성
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}

			// 이미지 파일 저장
			String fileName = imageSave(selectedFile);

			fabricDataList.removeAll(fabricDataList);

			if (f_txtSort.getLength() != 0 && f_txtName.getLength() != 0 && f_txtColor.getLength() != 0
					&& f_txtSize.getLength() != 0 && f_txtMaterial.getLength() != 0 && f_txtWeight.getLength() != 0
					&& f_txtOrigin.getLength() != 0 && f_txtCname.getLength() != 0 && f_txtPrice.getLength() != 0
					&& f_txtPhone.getLength() != 0) {

				fvo = new FabricVO(f_txtSort.getText().trim(), f_txtName.getText().trim(), f_txtColor.getText().trim(),
						f_txtSize.getText().trim(), f_txtWeight.getText().trim(), f_txtOrigin.getText().trim(),
						f_txtCname.getText().trim(), f_txtPrice.getText().trim(), f_txtPhone.getText().trim(),
						f_txtMaterial.getText().trim(), f_txtTrait.getText(), f_txtRemarks.getText(), fileName);

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

	// 테이블뷰 마우스클릭 이벤트
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

	private String imageSave(File selectedFile) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int data = -1;
		String fileName = null;
		try {
			// 이미지 파일명 생성
			fileName = "fabric" + System.currentTimeMillis() + "_" + file.getName();

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return fileName;
	}

	// 이미지 파일 선택 창
	public void handlerBtnImageFileAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));

		try {
			selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				// 이미지 파일 경로
				localUrl = selectedFile.toURI().toURL().toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		localImage = new Image(localUrl, false);

		imageView.setImage(localImage);
		imageView.setFitHeight(250);
		imageView.setFitWidth(230);

		f_btnRegist.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
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

	// 종료버튼 이벤트
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

	// 수정 버튼 이벤트
	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			FabricDAO sdao = new FabricDAO();
			sucess = sdao.getFabricUpdate(f_txtNumber.getText().trim(), f_txtSort.getText().trim(),
					f_txtName.getText().trim(), f_txtColor.getText().trim(), f_txtSize.getText().trim(),
					f_txtWeight.getText().trim(), f_txtOrigin.getText().trim(), f_txtCname.getText().trim(),
					f_txtPrice.getText().trim(), f_txtPhone.getText().trim(), f_txtMaterial.getText().trim(),
					f_txtTrait.getText().trim(), f_txtRemarks.getText().trim());

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

	// 원단정보 삭제버튼 이벤트
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

	// 종료버튼 이벤트
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 검색버튼 이벤트
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
