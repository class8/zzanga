package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
import model.CustomerVO;
import model.FabricVO;
import model.TradeVO;

public class FabricInfoController implements Initializable {

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
	TableView<CustomerVO> c_tableView = new TableView<>();

	ObservableList<CustomerVO> customerDataList = FXCollections.observableArrayList();
	ObservableList<CustomerVO> selectCustomer = null; // 학생등록 테이블에서 선택한 정보 저장
	int selectedCustomerIndex;

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
	File selectedFile;

	// 이미지 처리
	// 이미지 저장할 폴더를 매개변수로 파일 객체 생성
	private File dirSave = new File("C:/images");

	// 거래등록필드

	@FXML
	TextField tr_f_txtNumber; // 제품코드
	@FXML
	TextField tr_f_txtSort; // 종류
	@FXML
	TextField tr_f_txtName; // 제품명
	@FXML
	TextField tr_f_txtColor; // 색상
	@FXML
	TextField tr_f_txtSize; // 사이즈
	@FXML
	TextField tr_f_txtWeight; // 중량
	@FXML
	TextField tr_f_txtPrice; // 가격
	@FXML
	TextField tr_f_txtPhone; // 담당자연락처
	@FXML
	TextField tr_c_txtNumber; // 고객 번호
	@FXML
	TextField tr_c_txtName; // 고객명
	@FXML
	TextField tr_c_txtPhone; // 연락처
	@FXML
	TextField tr_c_txtEmail; // 이메일
	@FXML
	TextField tr_txtAddress; // 배달주소
	@FXML
	TextField tr_Amount; // 수량
	@FXML
	TextField tr_txtTotal; // 총액
	@FXML
	Button tr_btnRegist; // 등록
	@FXML
	Button tr_btnCancel; // 취소
	@FXML
	Button tr_btnTotal; // 총액버튼
	@FXML
	Button tr_btnCsearch; // 고객정보불러오기 버
	@FXML
	TextArea tr_txtRemarks; // 비고

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 수량입력에 숫자만 입력할수 있게해줌
		f_txtSize.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					f_txtSize.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// 중량 입력에 숫자만 입력할수 있게해줌
		f_txtWeight.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					f_txtWeight.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// 가격 입력에 숫자만 입력할수 있게해줌
		f_txtPrice.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					f_txtPrice.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		try {

			f_btnTrade.setDisable(true);

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

			TableColumn colFregistdate = new TableColumn("등록일");
			colFregistdate.setPrefWidth(80);
			colFregistdate.setStyle("-fx-alignment: CENTER");
			colFregistdate.setCellValueFactory(new PropertyValueFactory<>("f_registdate"));

			TableColumn colImageFileName = new TableColumn("이미지");
			colImageFileName.setMinWidth(260);
			colImageFileName.setStyle("-fx-alignment: CENTER");
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

			// fabricTotalList();

			// 기본 이미지
			localUrl = "/image/default.png";
			localImage = new Image(localUrl, false);
			imageView.setImage(localImage);
			imageView.setFitHeight(200);
			imageView.setFitWidth(180);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 거래등록버튼 이벤트
	public void handlerBtnTradeAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tradeReg.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainStage = new Stage();
			mainStage.setTitle("거래 등록");
			mainStage.setResizable(false);
			mainStage.setScene(scane);
			mainStage.show();

			FabricVO fabricReg = f_tableView.getSelectionModel().getSelectedItem();
			selectedFabricIndex = String.valueOf(f_tableView.getSelectionModel().getSelectedIndex());

			selectFabric = f_tableView.getSelectionModel().getSelectedItems();

			selectedFabricIndex = selectFabric.get(0).getF_number();

			// 원단정보 불러오기!

			TextField f_txtNumber = (TextField) mainView.lookup("#tr_f_txtNumber");
			TextField f_txtSort = (TextField) mainView.lookup("#tr_f_txtSort");
			TextField f_txtName = (TextField) mainView.lookup("#tr_f_txtName");
			TextField f_txtColor = (TextField) mainView.lookup("#tr_f_txtColor");
			TextField f_txtSize = (TextField) mainView.lookup("#tr_f_txtSize");
			TextField f_txtWeight = (TextField) mainView.lookup("#tr_f_txtWeight");
			TextField f_txtPrice = (TextField) mainView.lookup("#tr_f_txtPrice");
			TextField f_txtPhone = (TextField) mainView.lookup("#tr_f_txtPhone");
			TextField c_txtNumber = (TextField) mainView.lookup("#tr_c_txtNumber");
			TextField c_txtName = (TextField) mainView.lookup("#tr_c_txtName");
			TextField c_txtPhone = (TextField) mainView.lookup("#tr_c_txtPhone");
			TextField c_txtEmail = (TextField) mainView.lookup("#tr_c_txtEmail");
			TextField t_txtAddress = (TextField) mainView.lookup("#tr_txtAddress");
			TextField t_txtAmount = (TextField) mainView.lookup("#tr_txtAmount");
			TextField t_txtTotal = (TextField) mainView.lookup("#tr_txtTotal");
			TextArea t_txtRemarks = (TextArea) mainView.lookup("#tr_txtRemarks");

			f_txtNumber.setText(selectFabric.get(0).getF_number());
			f_txtSort.setText(selectFabric.get(0).getF_sort());
			f_txtName.setText(selectFabric.get(0).getF_sort());
			f_txtColor.setText(selectFabric.get(0).getF_color());
			f_txtSize.setText(selectFabric.get(0).getF_size());
			f_txtPhone.setText(selectFabric.get(0).getF_phone());
			f_txtWeight.setText(selectFabric.get(0).getF_weight());
			f_txtPrice.setText(selectFabric.get(0).getF_price());

			// 자동입력란은 수정불가
			f_txtNumber.setDisable(true);
			f_txtSort.setDisable(true);
			f_txtName.setDisable(true);
			f_txtColor.setDisable(true);
			f_txtSize.setDisable(true);
			f_txtPhone.setDisable(true);
			f_txtWeight.setDisable(true);
			f_txtPrice.setDisable(true);
			c_txtName.setDisable(true);
			c_txtPhone.setDisable(true);
			t_txtTotal.setDisable(true);

			// f_txtNumber.setText(fabricReg.getF_number() + "");
			// f_txtSort.setText(fabricReg.getF_sort() + "");
			// f_txtName.setText(fabricReg.getF_name() + "");
			// f_txtColor.setText(fabricReg.getF_color() + "");
			// f_txtSize.setText(fabricReg.getF_size() + "");
			// f_txtPhone.setText(fabricReg.getF_phone() + "");
			// f_txtWeight.setText(fabricReg.getF_weight() + "");
			// f_txtPrice.setText(fabricReg.getF_price() + "");

			Button tr_btnCancel = (Button) mainView.lookup("#tr_btnCancel");// 취소버튼 이벤트
			Button tr_btnCsearch = (Button) mainView.lookup("#tr_btnCsearch"); // 고객검색 버튼
			Button tr_btnTotal = (Button) mainView.lookup("#tr_btnTotal");// 총액
			Button tr_btnRegist = (Button) mainView.lookup("#tr_btnRegist");// 등록

			// 고객정보불러오기 버튼 이벤트
			tr_btnCsearch.setOnAction(e -> {
				c_txtName.clear();
				c_txtPhone.clear();
				c_txtEmail.clear();
				t_txtAddress.clear();

				FabricDAO fDao = new FabricDAO();
				String search = c_txtNumber.getText();
				ArrayList<String> list = null;
				try {

					list = fDao.getSearchNumber(search);

					if (list != null) {

						c_txtName.setText(list.get(0).toString());
						c_txtPhone.setText(list.get(1).toString());

						if (!list.get(3).toString().equals("null") && !list.get(2).toString().equals("null")) {

							c_txtEmail.setText(list.get(3).toString());

							t_txtAddress.setText(list.get(2).toString());
						}

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("검색 성공");
						alert.setHeaderText(search + "번 고객정보를 찾았습니다.");
						alert.setContentText(search + "번 고객정보를 불러 옵니다.");
						alert.showAndWait();

					} else {

						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("고객정보");
						alert.setHeaderText("고객정보가 리스트에 없습니다.");
						alert.setContentText("다시 시도해주세요.");
						alert.showAndWait();

					}

				} catch (Exception e1) {

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("고객정보");
					alert.setHeaderText("고객정보가 리스트에 없습니다.");
					alert.setContentText("다시 시도해주세요.");
					alert.showAndWait();

				}

			});

			// 총액버튼 이벤트
			tr_btnTotal.setOnAction(e -> {

				if (!(t_txtAmount.getText().equals(""))) {

					int price = Integer.parseInt(f_txtPrice.getText());
					int amount = Integer.parseInt(t_txtAmount.getText());

					t_txtTotal.setText(price * amount + "");

				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("정보 미입력");
					alert.setHeaderText("수량이 입력되지 않았습니다.");
					alert.setContentText("수량을 정확히 입력하세요.");
					alert.showAndWait();
				}
			});

			// 취소버튼 이벤트
			tr_btnCancel.setOnAction(e -> {

				mainStage.close();
				handlerBtnInitAction(event);

			});
			// 거래등록 버튼 이벤트
			tr_btnRegist.setOnAction(e -> {

				try {

					TradeVO tvo = null;
					TradeDAO tdao = null;

					if (t_txtAddress.getLength() != 0 && t_txtAmount.getLength() != 0 && t_txtTotal.getLength() != 0
							&& c_txtNumber.getLength() != 0 && c_txtName.getLength() != 0
							&& c_txtPhone.getLength() != 0) {

						tvo = new TradeVO(f_txtNumber.getText().trim(), Integer.parseInt(c_txtNumber.getText().trim()),
								c_txtName.getText().trim(), c_txtPhone.getText().trim(), c_txtEmail.getText().trim(),
								Integer.parseInt(t_txtAmount.getText().trim()),
								Integer.parseInt(t_txtTotal.getText().trim()), t_txtAddress.getText().trim(),
								t_txtRemarks.getText().trim(), f_txtSort.getText().trim(), f_txtName.getText().trim(),
								f_txtColor.getText().trim(), f_txtSize.getText().trim(), f_txtWeight.getText().trim(),
								f_txtPrice.getText().trim(), c_txtPhone.getText().trim());

						tdao = new TradeDAO();
						tdao.getTradeRegist(tvo);

						// tradeTotalList();

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("거래 입력");
						alert.setHeaderText("거래가 성공적으로 추가되었습니다.");
						alert.setContentText("다음 거래를 입력하세요.");
						alert.showAndWait();

						mainStage.close();

					} else {

						// tradeTotalList();

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("거래 정보 미입력");
						alert.setHeaderText("거래 정보중에 미입력된 항목이 있습니다.");
						alert.setContentText("거래 정보를 정확히 입력하세요.");
						alert.showAndWait();

					}
				} catch (Exception ex) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("거래 정보 입력");
					alert.setHeaderText("거래 정보입력 중 오류가 발생했습니다.");
					alert.setContentText("다음에는 주의하세요.");
					alert.showAndWait();
				}

			});

		} catch (

		IOException e1) {
			System.out.println(e1.toString());
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

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단 정보 검색");
				alert.setHeaderText("원단명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
				fabricDataList.removeAll(fabricDataList);
				fabricTotalList();
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
				alert.setHeaderText(searchName + " 원단이 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				fabricTotalList();
			}
		} catch (SQLException sqle) {
			sqle.toString();
		} catch (Exception e) {
			e.toString();
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
			imageSave(selectedFile);

			if (f_txtColor.getLength() != 0) {

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("원단정보 수정");
				alert.setHeaderText("색상은 필수 입력사항입니다.");
				alert.setContentText("색상을 입력하신 후 수정버튼을 눌러주세요!");
				alert.showAndWait();
			}

			fvo = new FabricVO(selectedFabricIndex, f_txtColor.getText().trim(), f_txtSize.getText().trim(),
					f_txtMaterial.getText().trim(), f_txtOrigin.getText().trim(), f_txtCname.getText().trim(),
					f_txtPhone.getText().trim(), f_txtWeight.getText().trim(), f_txtTrait.getText(),
					f_txtPrice.getText().trim(), f_txtRemarks.getText(), selectFileName);

			sucess = fdao.getfabricUpdate(fvo);

			if (sucess) {

				fabricTotalList();

				f_txtNumber.clear();
				f_txtName.clear();
				f_txtSort.clear();
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
				f_txtNumber.requestFocus();

				f_txtNumber.setDisable(false);
				f_txtSort.setDisable(false);

				f_btnDelete.setDisable(true);
				f_btnUpdate.setDisable(true);
				f_btnRegist.setDisable(false);
				// btnImageFile.setDisable(false);
				// 기본 이미지
				localUrl = "/image/default.png";
				localImage = new Image(localUrl, false);
				imageView.setImage(localImage);
				imageView.setFitHeight(200);
				imageView.setFitWidth(180);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// 등록 버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {

		try {

			selectFabric = f_tableView.getSelectionModel().getSelectedItems();

			// selectedFabricIndex = selectFabric.get(0).getF_number();

			fabricDataList.removeAll(fabricDataList);

			FabricVO fvo = null;
			FabricDAO fdao = new FabricDAO();

			File dirMake = new File(dirSave.getAbsolutePath());

			// 이미지 저장 폴더 생성
			if (!dirMake.exists()) {
				dirMake.mkdir();
			}

			// 이미지 파일 저장
			imageSave(selectedFile);

			if (f_txtNumber.getLength() != 0 && f_txtSort.getLength() != 0 && f_txtName.getLength() != 0
					&& f_txtColor.getLength() != 0 && f_txtSize.getLength() != 0 && f_txtMaterial.getLength() != 0
					&& f_txtOrigin.getLength() != 0 && f_txtCname.getLength() != 0 && f_txtPhone.getLength() != 0
					&& f_txtWeight.getLength() != 0 && f_txtPrice.getLength() != 0 && (fileName != null)) {

				fvo = new FabricVO(f_txtNumber.getText().trim(), f_txtSort.getText().trim(), f_txtName.getText().trim(),
						f_txtColor.getText().trim(), f_txtSize.getText().trim(), f_txtMaterial.getText().trim(),
						f_txtOrigin.getText().trim(), f_txtCname.getText().trim(), f_txtPhone.getText().trim(),
						f_txtWeight.getText().trim(), f_txtTrait.getText().trim(), f_txtPrice.getText().trim(),
						f_txtRemarks.getText().trim(), selectFileName);

				fdao = new FabricDAO();
				fdao.getFabricRegist(fvo);

				if (fdao != null) {

					fabricTotalList();

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("원단정보 입력");
					alert.setHeaderText(f_txtName.getText() + " 원단이 성공적으로 추가되었습니다..");
					alert.setContentText("다음 원단정보를 입력하세요");
					alert.showAndWait();

					// 기본 이미지
					localUrl = "/image/default.png";
					localImage = new Image(localUrl, false);
					imageView.setImage(localImage);
					imageView.setFitHeight(200);
					imageView.setFitWidth(180);

					btnImageFile.setDisable(true);
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
					fabricTotalList();
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
			e.printStackTrace();
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
				String selectedF_filename = selectFabric.get(0).getFilename();

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
				selectFileName = selectedF_filename;

				// 이미지 가져오기
				selectFileName = selectFabric.get(0).getFilename();

				localUrl = "file:/C:/images/" + selectFileName;
				localImage = new Image(localUrl, false);

				imageView.setImage(localImage);

				f_txtNumber.setDisable(true);
				f_txtSort.setDisable(true);

				f_btnRegist.setDisable(true);
				f_btnUpdate.setDisable(false);
				f_btnInit.setDisable(false);
				f_btnExit.setDisable(false);
				f_btnDelete.setDisable(false);
				f_btnTrade.setDisable(false);

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
		f_btnUpdate.setDisable(false);
		f_btnDelete.setDisable(true);

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
				f_txtName.clear();
				f_txtSort.clear();
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
				f_txtNumber.requestFocus();

				f_txtNumber.setDisable(false);
				f_txtSort.setDisable(false);

				f_btnUpdate.setDisable(true);
				f_btnDelete.setDisable(true);
				f_btnRegist.setDisable(false);
				handlerBtnInitAction(event);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 초기화 이벤트 핸들러
	public void handlerBtnInitAction(ActionEvent event) {

		f_txtNumber.clear();
		f_txtName.clear();
		f_txtSort.clear();
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
		fileName = null;
		f_txtNumber.requestFocus();

		f_btnUpdate.setDisable(true);
		f_btnDelete.setDisable(true);
		f_btnRegist.setDisable(false);
		btnImageFile.setDisable(false);
		f_btnTrade.setDisable(true);

		f_txtNumber.setDisable(false);
		f_txtSort.setDisable(false);

		// 기본 이미지
		localUrl = "/image/default.png";
		localImage = new Image(localUrl, false);
		imageView.setImage(localImage);
		imageView.setFitHeight(200);
		imageView.setFitWidth(180);

		try {
			fabricTotalList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 원단 전체 리스트
	public void fabricTotalList() throws Exception {
		f_btnUpdate.setDisable(true);
		f_btnDelete.setDisable(true);
		f_btnRegist.setDisable(false);
		btnImageFile.setDisable(false);

		f_txtNumber.setDisable(false);
		f_txtSort.setDisable(false);

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
