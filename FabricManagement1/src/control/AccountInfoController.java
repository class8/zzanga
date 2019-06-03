package control;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.AccountVO;

public class AccountInfoController implements Initializable {

	@FXML
	TextField a_txtCname; // 거래처명
	@FXML
	TextField a_txtMname; // 거래처 담당자명
	@FXML
	TextField a_txtPhone; // 거래처 연락처
	@FXML
	TextField a_txtEmail; // 거래처 이메일
	@FXML
	TextField a_txtAddress; // 거래처 주소
	@FXML
	TextField a_txtBnumber; // 거래처 사업자번호
	@FXML
	TextField a_txtMsubject; // 거래처 주종목
	@FXML
	TextArea a_txtRemarks; // 비고
	@FXML
	Button a_btnRegist; // 등록 버튼
	@FXML
	Button a_btnInit; // 초기화 버튼
	@FXML
	Button a_btnUpdate; // 수정 버튼
	@FXML
	Button a_btnDelete; // 삭제 버튼
	@FXML
	Button a_btnExit; // 종료 버튼
	@FXML
	TextField a_txtSearch; // 검색어 텍스트필드
	@FXML
	Button a_btnSearch; // 검색 버튼
	@FXML
	private TableView<AccountVO> a_tableView = new TableView<>();

	// 거래처 정보 전달에 쓰일 거래처VO 형태의 리스트
	ObservableList<AccountVO> accountDataList = FXCollections.observableArrayList();

	// 선택한 거래처 정보를 담을 거래처VO형태의 리스트
	ObservableList<AccountVO> selectAccount = null;

	// 선택한 거래처정보의 인덱스 값을 저장할 변수
	int selectedAccountIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			// 수정과 삭제 버튼의 기본값을 사용불가로
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);

			TableColumn colAnumber = new TableColumn("번호");
			colAnumber.setPrefWidth(40);
			colAnumber.setStyle("-fx-alignment: CENTER");
			colAnumber.setCellValueFactory(new PropertyValueFactory<>("a_number"));

			TableColumn colAcname = new TableColumn("거래처명");
			colAcname.setPrefWidth(100);
			colAcname.setStyle("-fx-alignment: CENTER");
			colAcname.setCellValueFactory(new PropertyValueFactory<>("a_cname"));

			TableColumn colAmname = new TableColumn("담당자명");
			colAmname.setPrefWidth(100);
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
			colAregistdate.setPrefWidth(100);
			colAregistdate.setStyle("-fx-alignment: CENTER");
			colAregistdate.setCellValueFactory(new PropertyValueFactory<>("a_registdate"));

			a_tableView.setItems(accountDataList);

			a_tableView.getColumns().addAll(colAnumber, colAcname, colAmname, colAphone, colAemail, colAbnumber,
					colAmsubject, colAaddress, colAremarks, colAregistdate);

			// 전체 목록
			accountTotalList();

			a_btnRegist.setOnAction(event -> handlerBtnRegistAction(event)); // 등록버튼 이벤트
			a_btnInit.setOnAction(event -> handlerBtnInitAction(event)); // 초기화 버튼 이벤트
			a_btnUpdate.setOnAction(event -> handlerBtnUpdateAction(event)); // 수정버튼 이벤트
			a_btnDelete.setOnAction(event -> handlerBtnDeleteAction(event)); // 삭제 버튼 이벤트
			a_btnExit.setOnAction(event -> handlerBtnExitAction(event)); // 종료버튼 이벤트
			a_btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색버튼 이벤트
			a_tableView.setOnMouseClicked(event -> handlerAccountTableViewAction(event)); // 테이블뷰 더블클릭 이벤트

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 테이블 뷰 클릭 이벤트
	public void handlerAccountTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) { // 더블클릭시

			try {
				// 선택한 거래처 정보 가져오기
				selectAccount = a_tableView.getSelectionModel().getSelectedItems();
				selectedAccountIndex = selectAccount.get(0).getA_number();
				String selectedA_cname = selectAccount.get(0).getA_cname();
				String selectedA_mname = selectAccount.get(0).getA_mname();
				String selectedA_phone = selectAccount.get(0).getA_phone();
				String selectedA_email = selectAccount.get(0).getA_email();
				String selectedA_address = selectAccount.get(0).getA_address();
				String selectedA_bnumber = selectAccount.get(0).getA_bnumber();
				String selectedA_msubject = selectAccount.get(0).getA_msubject();
				String selectedA_remarks = selectAccount.get(0).getA_remarks();

				// 좌측의 텍스트 필드에 선택한 거래처 정보 설정
				a_txtCname.setText(selectedA_cname);
				a_txtMname.setText(selectedA_mname);
				a_txtPhone.setText(selectedA_phone);
				a_txtEmail.setText(selectedA_email);
				a_txtAddress.setText(selectedA_address);
				a_txtBnumber.setText(selectedA_bnumber);
				a_txtMsubject.setText(selectedA_msubject);
				a_txtRemarks.setText(selectedA_remarks);

				// 수정과 삭제 버튼을 사용가능으로
				a_btnRegist.setDisable(true);
				a_btnUpdate.setDisable(false);
				a_btnDelete.setDisable(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 등록버튼 이벤트
	public void handlerBtnRegistAction(ActionEvent event) {
		boolean sucess;
		try {
			// 다른 메소드에서 쓰이고 남은 값이 있을수 있으니 전부 삭제
			accountDataList.removeAll(accountDataList);

			// 거래처 정보를 담을 VO
			AccountVO avo = null;
			AccountDAO adao = null;

			// 거래처명,담당자명,연락처,이메일,주소,사업자번호,주종목 텍스트 필드가 입력되었을 경우
			if (a_txtCname.getLength() != 0 && a_txtMname.getLength() != 0 && a_txtPhone.getLength() != 0
					&& a_txtEmail.getLength() != 0 && a_txtAddress.getLength() != 0 && a_txtBnumber.getLength() != 0
					&& a_txtMsubject.getLength() != 0) {
				// 거래처명,담당자명,연락처,이메일,주소,사업자번호,주종목,비고를 읽어와서 VO를 생성한다.
				avo = new AccountVO(a_txtCname.getText().trim(), a_txtMname.getText().trim(),
						a_txtPhone.getText().trim(), a_txtEmail.getText().trim(), a_txtAddress.getText().trim(),
						a_txtBnumber.getText().trim(), a_txtMsubject.getText().trim(), a_txtRemarks.getText().trim());
				adao = new AccountDAO();
				sucess = adao.getAccountRegist(avo); // 거래처 정보를 가지고있는 VO 정보를 DB에 등록한다.

				if (sucess) {
					accountDataList.removeAll(accountDataList);
					accountTotalList();
					// 거래처 정보 텍스트 필드 비우기
					a_txtCname.clear();
					a_txtMname.clear();
					a_txtPhone.clear();
					a_txtEmail.clear();
					a_txtAddress.clear();
					a_txtBnumber.clear();
					a_txtMsubject.clear();
					a_txtRemarks.clear();
					// 거래처명 텍스트필드에 포커스 두기
					a_txtCname.requestFocus();
				} else {
					accountDataList.removeAll(accountDataList);
					accountTotalList();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("거래처 정보 등록 실패");
					alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
					alert.setContentText("거래처 정보 등록에 실패하였습니다.");
					alert.showAndWait();
				}
			} else {
				// 다른 메소드에서 쓰이고 남은 값이 있을수 있으니 전부 삭제
				accountDataList.removeAll(accountDataList);
				accountTotalList();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 정보 등록 실패");
				alert.setHeaderText("미입력된 항목이 있어서 거래처 등록에 실패하였습니다.");
				alert.setContentText("다시 한번 확인후 시도해주세요.");
				alert.showAndWait();
			}
		} catch (SQLException sqe) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 입력");
			alert.setHeaderText("거래처 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 입력");
			alert.setHeaderText("거래처 정보를 정확히 입력하세요.");
			alert.setContentText("다음에는 주의하세요.");
			alert.showAndWait();
		}
	}

	// 거래처 전체 목록
	public void accountTotalList() throws Exception {
		accountDataList.removeAll(accountDataList);

		AccountDAO aDao = new AccountDAO();
		AccountVO aVo = null;
		ArrayList<AccountVO> list;

		// 데이터 베이스에서 거래처 정보 가져오기
		list = aDao.getAccountTotalList();

		// 가져온 정보 만큼의 크기 변수
		int rowCount = list.size();

		// 준비해둔 리스트에 거래처 정보 채우기
		for (int index = 0; index < rowCount; index++) {
			aVo = list.get(index);
			accountDataList.add(aVo);
		}
	}

	// 전체 목록
	public void handlerBtnStudentTotalListAction(ActionEvent event) {
		try {
			accountDataList.removeAll(accountDataList);
			accountTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 초기화 버튼 메소드
	public void handlerBtnInitAction(ActionEvent event) {
		try {
			// 다른 메소드에서 쓰이고 남은 값이 있을수 있으니 전부 삭제
			accountDataList.removeAll(accountDataList);
			// 거래처 정보 새로 불러오기
			accountTotalList();

			// 거래처 정보 텍스트필드 비우기
			a_txtCname.clear();
			a_txtMname.clear();
			a_txtPhone.clear();
			a_txtEmail.clear();
			a_txtAddress.clear();
			a_txtBnumber.clear();
			a_txtMsubject.clear();
			a_txtRemarks.clear();
			a_txtCname.requestFocus();

			// 수정과 삭제버튼을 사용 불가로
			a_btnUpdate.setDisable(true);
			a_btnDelete.setDisable(true);

			a_btnRegist.setDisable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 수정 버튼 메소드
	public void handlerBtnUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			AccountDAO adao = new AccountDAO();
			if (a_txtCname.getLength() != 0 && a_txtMname.getLength() != 0 && a_txtPhone.getLength() != 0
					&& a_txtEmail.getLength() != 0 && a_txtAddress.getLength() != 0 && a_txtBnumber.getLength() != 0
					&& a_txtMsubject.getLength() != 0) {
				// 텍스트필드 정보로 수정 메소드 호출
				sucess = adao.getAccountUpdate(selectedAccountIndex, a_txtCname.getText().trim(),
						a_txtMname.getText().trim(), a_txtPhone.getText().trim(), a_txtEmail.getText().trim(),
						a_txtAddress.getText().trim(), a_txtBnumber.getText().trim(), a_txtMsubject.getText().trim(),
						a_txtRemarks.getText());
				// 수정에 성공한 경우
				if (sucess) {
					accountDataList.removeAll(accountDataList);
					accountTotalList();

					a_txtCname.clear();
					a_txtMname.clear();
					a_txtPhone.clear();
					a_txtEmail.clear();
					a_txtAddress.clear();
					a_txtBnumber.clear();
					a_txtMsubject.clear();
					a_txtRemarks.clear();
					a_txtCname.requestFocus();

					a_btnRegist.setDisable(false);
					a_btnUpdate.setDisable(true);
					a_btnDelete.setDisable(true);
				} else {
					accountDataList.removeAll(accountDataList);
					accountTotalList();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("거래처 정보 등록 실패");
					alert.setHeaderText("입력된 값이 범위를 초과했습니다.");
					alert.setContentText("거래처 정보 등록에 실패하였습니다.");
					alert.showAndWait();
				}
			} else {
				// 다른 메소드에서 쓰이고 남은 값이 있을수 있으니 전부 삭제
				accountDataList.removeAll(accountDataList);
				accountTotalList();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("거래처 정보 수정 실패");
				alert.setHeaderText("미입력된 항목이 있어서 거래처 수정에 실패하였습니다.");
				alert.setContentText("다시 한번 확인후 시도해주세요.");
				alert.showAndWait();
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("거래처 정보 수정 실패");
			alert.setHeaderText("거래처 정보 수정에 실패하였습니다.");
			alert.setContentText("다시 한번 확인후 시도하세요.");
			alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제 버튼 메소드
	public void handlerBtnDeleteAction(ActionEvent event) {
		try {
			boolean sucess;
			AccountDAO sDao = new AccountDAO();
			// 테이블 뷰에서 선택한 정보의 인덱스값으로 삭제 메소드 호출
			sucess = sDao.getAccountDelete(selectedAccountIndex);

			// 삭제에 성공한 경우
			if (sucess) {
				accountDataList.removeAll(accountDataList);
				accountTotalList();

				a_txtCname.clear();
				a_txtMname.clear();
				a_txtPhone.clear();
				a_txtEmail.clear();
				a_txtAddress.clear();
				a_txtBnumber.clear();
				a_txtMsubject.clear();
				a_txtRemarks.clear();
				a_txtCname.requestFocus();

				a_btnRegist.setDisable(false);
				a_btnUpdate.setDisable(true);
				a_btnDelete.setDisable(true);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 종료 버튼 이벤트
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
	}

	// 검색 버튼 이벤트
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<AccountVO> searchList = new ArrayList<AccountVO>();
		AccountVO aVo = null;
		AccountDAO aDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			// 검색 텍스트 필드값을 가진 문자열
			searchName = a_txtSearch.getText().trim();
			aDao = new AccountDAO();
			searchList = aDao.getAccountCheck(searchName);

			// 검색 텍스트 필드값이 없을경우
			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("거래처 정보 검색");
				alert.setHeaderText("거래처명을 입력하세요.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();
			}

			// 결과가 null이 아닌경우
			if (searchList != null) {
				int rowCount = searchList.size();

				// 검색어 텍스트필드 비우기
				a_txtSearch.clear();
				accountDataList.removeAll(accountDataList);

				// 검색된 정보 불러오기
				for (int index = 0; index < rowCount; index++) {
					aVo = searchList.get(index);
					accountDataList.add(aVo);
					searchResult = true;
				}
			}

			// 검색 결과가 false 인경우 출력
			if (!searchResult) {
				a_txtSearch.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("거래처 정보 검색");
				alert.setHeaderText(searchName + "이(가) 리스트에 없습니다.");
				alert.setContentText("다시 시도해주세요.");
				alert.showAndWait();

				accountTotalList();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("거래처 정보 검색 오류");
			alert.setHeaderText("거래처 정보 검색에 오류가 발생하였습니다.");
			alert.setContentText("다시 시도해주세요.");
		}
	}

}
