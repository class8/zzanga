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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	// String selectedFabricIndex = "";
	String selectedFabricIndex;

	// 이미지 추가
	private Stage primaryStage;
	String selectFileName = ""; // 이미지 파일명
	String localUrl = ""; // 이미지 파일 경로
	Image localImage;

	int no; // 삭제시 테이블에서 선택한 학생의 번호 저장
	File selectedFile = null;

	// 이미지 처리
	// 이미지 저장할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("C:/images");

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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

			f_tableView.setItems(fabricDataList);

			f_tableView.getColumns().addAll(colFnumber, colFsort, colFname, colFcolor, colFsize, colFweight, colForigin,
					colFcname, colFprice, colFphone, colFmaterial, colFtrait, colFremarks, colFregistdate,
					colImageFileName);

			f_btnRegist.setOnAction(event -> handlerBtnRegistAction(event)); // 등록버튼 이벤트
			f_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화버튼 이벤트
			f_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			f_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제버튼 이벤트
			f_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			f_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			f_tableView.setOnMouseClicked(event -> handlerFabricTableViewAction(event)); // 마우스 더블클릭 이벤트
			btnImageFile.setOnAction(event -> handlerBtnImageFileAction(event)); // 이미지파일
			f_btnTrade.setOnAction(event -> handlerBtnTradeAction(event)); // 거래등록버튼 이벤트

			fabricTotalList();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 거래등록버튼 이벤트
	public void handlerBtnTradeAction(ActionEvent event) {

		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(f_btnRegist.getScene().getWindow());
			dialog.setTitle("거래 등록");
			Parent parent = FXMLLoader.load(getClass().getResource("/view/tradeReg.fxml"));

			// Button btnClose = (Button) parent.lookup("#btnClose");
			// btnClose.setOnAction(e -> dialog.close());
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			searchList = fDao.getFabricCheck(searchName);

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
				alert.setHeaderText(searchName + " 원단이 리스트에 없습니다.");
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

	// 수정버튼 이벤트
	public void handlerBtnUpdateAction(ActionEvent event) {

		try {

			boolean sucess;

			FabricVO fvo = null;
			FabricDAO fdao = new FabricDAO();

			// 이미지 파일 저장
			String fileName = imageSave(selectedFile);

			fvo = new FabricVO(selectedFabricIndex, f_txtColor.getText().trim(), f_txtSize.getText().trim(),
					f_txtOrigin.getText().trim(), f_txtCname.getText().trim(), f_txtPhone.getText().trim(),
					f_txtWeight.getText().trim(), f_txtPrice.getText().trim(), f_txtMaterial.getText().trim(),
					f_txtTrait.getText().trim(), f_txtRemarks.getText(), fileName);

			sucess = fdao.getfabricUpdate(fvo);

			if (sucess) {

				fabricDataList.removeAll(fabricDataList);

				fabricTotalList();

				f_txtNumber.clear();
				f_txtSort.clear();
				f_txtName.clear();
				f_txtColor.clear();
				f_txtSize.clear();
				f_txtMaterial.clear();
				f_txtOrigin.clear();
				f_txtCname.clear();
				f_txtPhone.clear();
				f_txtWeight.clear();
				f_txtTrait.clear();
				f_txtPrice.clear();
				f_txtRemarks.clear();
				// fileName.clear();

				// f_txtName.requestFocus();
				f_txtNumber.setEditable(true);
				f_txtName.setEditable(true);

				handlerBtnInitAction(event);
				f_btnDelete.setDisable(true);
				f_btnInit.setDisable(false);
				// btnImageFile.setDisable(false);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 등록 버튼 이벤트
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

			if (f_txtNumber.getLength() != 0 && f_txtSort.getLength() != 0 && f_txtName.getLength() != 0
					&& f_txtColor.getLength() != 0 && f_txtSize.getLength() != 0 && f_txtMaterial.getLength() != 0
					&& f_txtOrigin.getLength() != 0 && f_txtCname.getLength() != 0 && f_txtPhone.getLength() != 0
					&& f_txtWeight.getLength() != 0 && f_txtTrait.getLength() != 0 && f_txtPrice.getLength() != 0
					&& f_txtRemarks.getLength() != 0 && fileName.length() != 0) {

				fvo = new FabricVO(f_txtNumber.getText().trim(), f_txtSort.getText().trim(), f_txtName.getText().trim(),
						f_txtColor.getText().trim(), f_txtSize.getText().trim(), f_txtMaterial.getText().trim(),
						f_txtOrigin.getText().trim(), f_txtCname.getText().trim(), f_txtPhone.getText().trim(),
						f_txtWeight.getText().trim(), f_txtTrait.getText().trim(), f_txtPrice.getText().trim(),
						f_txtRemarks.getText().trim(), fileName);

				fdao = new FabricDAO();
				fdao.getFabricRegist(fvo);

				if (fdao != null) {

					fabricTotalList();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("원단정보 입력");
					alert.setHeaderText(f_txtName.getText() + " 원단이 성공적으로 추가되었습니다..");
					alert.setContentText("다음 원단정보를 입력하세요");
					alert.showAndWait();

					btnImageFile.setDisable(true);

					// 기본 이미지
					localUrl = "/image/default.png";
					localImage = new Image(localUrl, false);
					imageView.setImage(localImage);

					f_txtNumber.setEditable(true);
					f_txtSort.setEditable(true);
					f_txtName.setEditable(true);
					f_txtColor.setEditable(true);
					f_txtSize.setEditable(true);
					f_txtMaterial.setEditable(true);
					f_txtOrigin.setEditable(true);
					f_txtCname.setEditable(true);
					f_txtPhone.setEditable(true);
					f_txtWeight.setEditable(true);
					f_txtTrait.setEditable(true);
					f_txtPrice.setEditable(true);
					f_txtRemarks.setEditable(true);

					handlerBtnInitAction(event);
					// fabricTotalList();
					f_btnDelete.setDisable(true);
					f_btnUpdate.setDisable(true);
				}

			} else {

				fabricDataList.removeAll(fabricDataList);

				fabricTotalList();

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 미입력");
				alert.setHeaderText("원단정보 중에 미입력된 항목이 있습니다.");
				alert.setContentText("원단 정보를 정확히 입력하세요.");
				alert.showAndWait();
			}

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.WARNING);

			alert.setTitle("원단정보 입력");
			alert.setHeaderText("원단정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();

		}

	}

	// 마우스클릭 이벤트
	public void handlerFabricTableViewAction(MouseEvent event) {

		if (event.getClickCount() == 2) {

			try {

				selectFabric = f_tableView.getSelectionModel().getSelectedItems();

				selectedFabricIndex = selectFabric.get(0).getF_number();

				String selectedF_number = selectFabric.get(0).getF_number();
				String selectedF_sort = selectFabric.get(0).getF_sort();
				String selectedF_name = selectFabric.get(0).getF_name();
				String selectedF_color = selectFabric.get(0).getF_color();
				String selectedF_size = selectFabric.get(0).getF_size();
				String selectedF_material = selectFabric.get(0).getF_material();
				String selectedF_origin = selectFabric.get(0).getF_origin();
				String selectedF_cname = selectFabric.get(0).getF_cname();
				String selectedF_phone = selectFabric.get(0).getF_phone();
				String selectedF_weight = selectFabric.get(0).getF_weight();
				String selectedF_trait = selectFabric.get(0).getF_trait();
				String selectedF_price = selectFabric.get(0).getF_price();
				String selectedF_remarks = selectFabric.get(0).getF_remarks();
				// String selectedFileName = selectFabric.get(0).getFilename();

				f_txtNumber.setText(selectedF_number);
				f_txtSort.setText(selectedF_sort);
				f_txtName.setText(selectedF_name);
				f_txtColor.setText(selectedF_color);
				f_txtSize.setText(selectedF_size);
				f_txtMaterial.setText(selectedF_material);
				f_txtOrigin.setText(selectedF_origin);
				f_txtCname.setText(selectedF_cname);
				f_txtPhone.setText(selectedF_phone);
				f_txtWeight.setText(selectedF_weight);
				f_txtTrait.setText(selectedF_trait);
				f_txtPrice.setText(selectedF_price);
				f_txtRemarks.setText(selectedF_remarks);
				// fileName.setText(selectedFileName);

				selectFabric = f_tableView.getSelectionModel().getSelectedItems();
				// f_txtNumber = selectFabric.get(0).getF_number();
				selectFileName = selectFabric.get(0).getFilename();
				localUrl = "file:/C:/images/" + selectFileName;
				localImage = new Image(localUrl, false);

				imageView.setImage(localImage);
				imageView.setFitHeight(200);
				imageView.setFitWidth(180);

				f_btnRegist.setDisable(true);
				f_btnUpdate.setDisable(false);
				f_btnInit.setDisable(false);
				f_btnExit.setDisable(false);
				f_btnDelete.setDisable(false);

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}

	// 종료버튼 이벤트
	public void handlerBtnExitAction(ActionEvent event) {

		Platform.exit();
	}

	// 이미지저장 폴더
	public String imageSave(File file) {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		int fabricDataList = -1;

		String fileName = null;

		try {

			// 이미지 파일명 생성
			fileName = "fabric" + System.currentTimeMillis() + "_" + file.getName(); // 이게 무슨 네임이 오는 걸까?

			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(new FileOutputStream(dirSave.getAbsolutePath() + "\\" + fileName));

			// 선택한 이미지 파일 InputStream의 마지막에 이르렀을 경우는 -1
			while ((fabricDataList = bis.read()) != -1) {
				bos.write(fabricDataList);
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
		imageView.setFitHeight(200);
		imageView.setFitWidth(180);

		f_btnRegist.setDisable(false);

		if (selectedFile != null) {
			selectFileName = selectedFile.getName();
		}
	}

	// 원단정보삭제
	public void handlerBtnDeleteAction(ActionEvent event) {

		try {
			boolean sucess;
			FabricDAO fDao = new FabricDAO();
			sucess = fDao.getFabricDelete(selectedFabricIndex);

			if (sucess) {

				fabricDataList.removeAll(fabricDataList);
				fabricTotalList();

				f_txtNumber.clear();
				f_txtSort.clear();
				f_txtName.clear();
				f_txtColor.clear();
				f_txtSize.clear();
				f_txtMaterial.clear();
				f_txtOrigin.clear();
				f_txtCname.clear();
				f_txtPhone.clear();
				f_txtWeight.clear();
				f_txtTrait.clear();
				f_txtPrice.clear();
				f_txtRemarks.clear();

				f_btnUpdate.setDisable(true);
				f_btnDelete.setDisable(true);
				f_btnRegist.setDisable(false);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 초기화 이벤트 핸들러
	public void handlerBtnInitAction(ActionEvent event) {

		f_txtNumber.clear();
		f_txtSort.clear();
		f_txtName.clear();
		f_txtColor.clear();
		f_txtSize.clear();
		f_txtMaterial.clear();
		f_txtOrigin.clear();
		f_txtCname.clear();
		f_txtPhone.clear();
		f_txtWeight.clear();
		f_txtTrait.clear();
		f_txtPrice.clear();
		f_txtRemarks.clear();

		f_btnUpdate.setDisable(true);
		f_btnDelete.setDisable(true);
		f_btnRegist.setDisable(false);
		btnImageFile.setDisable(false);

		// 기본 이미지
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);

	}

	// 원단 전체 리스트
	public void fabricTotalList() throws Exception {

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
