package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountController implements Initializable {

	@FXML
	TextField a_txtNumber;
	@FXML
	TextField a_txtCname;
	@FXML
	TextField a_txtMname;
	@FXML
	TextField a_txtPhone;
	@FXML
	TextField a_txtEmail;

	
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 학생 등록 초기화
			a_btnUpdate.setDisable(true);
			btnStudentUpdate.setDisable(true);
			studentTableView.setEditable(false);

			// 학번 수정금지
			txtsd_num.setEditable(false);

			// 학생 테이블 뷰 컬럼이름 설정
			@SuppressWarnings("rawtypes")

			TableColumn colStudentNo = new TableColumn("NO.");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("-fx-alignment: CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colStudentNum = new TableColumn("학번");
			colStudentNum.setPrefWidth(70);
			colStudentNum.setStyle("-fx-alignment: CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

			TableColumn colStudentName = new TableColumn("이름");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-alignment: CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colStudentId = new TableColumn("아이디");
			colStudentId.setPrefWidth(80);
			colStudentId.setStyle("-fx-alignment: CENTER");
			colStudentId.setCellValueFactory(new PropertyValueFactory<>("sd_id"));

			TableColumn colStudentPassword = new TableColumn("비밀번호");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-alignment: CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));

			TableColumn colSubjectNum = new TableColumn("학과명");
			colSubjectNum.setPrefWidth(70);
			colSubjectNum.setStyle("-fx-alignment: CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn colStudentBirthday = new TableColumn("생년월일");
			colStudentBirthday.setPrefWidth(80);
			colStudentBirthday.setStyle("-fx-alignment: CENTER");
			colStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));

			TableColumn colStudentPhone = new TableColumn("연락처");
			colStudentPhone.setPrefWidth(80);
			colStudentPhone.setStyle("-fx-alignment: CENTER");
			colStudentPhone.setCellValueFactory(new PropertyValueFactory<>("sd_phone"));

			TableColumn colStudentAddress = new TableColumn("주 소");
			colStudentAddress.setPrefWidth(150);
			colStudentAddress.setStyle("-fx-alignment: CENTER");
			colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("sd_address"));

			TableColumn colStudentEmail = new TableColumn("이메일");
			colStudentEmail.setPrefWidth(80);
			colStudentEmail.setStyle("-fx-alignment: CENTER");
			colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("sd_email"));

			TableColumn colStudentDate = new TableColumn("등록일");
			colStudentDate.setPrefWidth(80);
			colStudentDate.setStyle("-fx-alignment: CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory<>("sd_date"));

			studentTableView.setItems(studentDataList);

			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentId,
					colStudentPassword, colSubjectNum, colStudentBirthday, colStudentPhone, colStudentAddress,
					colStudentEmail, colStudentDate);

			// 학생 전체 목록
			studentTotalList();

			// 추가된 학과명 호출
			// addSubjectName();

			btnStudentInsert.setOnAction(event -> handlerBtnStudentInsertAction(event));
			cbx_subjectName.setOnAction(event -> handlerCbx_subjectNameAction(event));
			btnIdCheck.setOnAction(event -> handlerBtnIdCheckAction(event));
			studentTableView.setOnMouseClicked(event -> handlerStudentTableViewAction(event));
			btnStudentUpdate.setOnAction(event -> handlerBtnStudentUpdateAction(event));
			btnStudentInit.setOnAction(event -> handlerBtnStudentInitAction(event));
			btnStudentTotalList.setOnAction(event -> handlerBtnStudentTotalListAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
