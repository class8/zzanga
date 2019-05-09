package control;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.StudentVO;
import model.SubjectVO;

public class StudentTabController implements Initializable {
	// �л� ��� ��
	@FXML
	private ComboBox<SubjectVO> cbx_subjectName;
	@FXML
	private TextField txtsd_num;
	@FXML
	private TextField txtsd_name;
	@FXML
	private TextField txtsd_id;
	@FXML
	private PasswordField txtsd_passwd;
	@FXML
	private TextField txtsd_birthday;
	@FXML
	private TextField txtsd_phone;
	@FXML
	private TextField txtsd_address;
	@FXML
	private TextField txtsd_email;
	@FXML
	private Button btnIdCheck; // ���̵� üũ
	@FXML
	private Button btnStudentInsert; // �л� ���
	@FXML
	private Button btnStudentUpdate; // �л� ����
	@FXML
	private Button btnStudentInit; // �л� �ʱ�ȭ
	@FXML
	private Button btnStudentTatolList; // �л� ��ü ���
	@FXML
	private TableView<StudentVO> studentTableView = new TableView<>();

	ObservableList<StudentVO> studentDataList = FXCollections.observableArrayList();
	ObservableList<StudentVO> selectStudent = null; // �л���� ���̺��� ������ ���� ����
	int selectedStudentIndex; // �л���� �ǿ��� ������ �а� ���� �ε��� ����
	String studentNumber = "";
	private String selectSubjectNum; // ������ �а����� �а��ڵ�

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// �л���� �ʱ�ȭ
			btnStudentInsert.setDisable(true);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			studentTableView.setEditable(false);

			// �й� ���� ����
			txtsd_num.setEditable(false);

			// �л� ���̺� �� �÷��̸� ����
			@SuppressWarnings("rawtypes")
			TableColumn colStudentNo = new TableColumn("NO.");
			colStudentNo.setPrefWidth(30);
			colStudentNo.setStyle("-fx-allignment: CENTER");
			colStudentNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			TableColumn colStudentNum = new TableColumn("�й�");
			colStudentNum.setPrefWidth(70);
			colStudentNum.setStyle("-fx-allignment: CENTER");
			colStudentNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));
			TableColumn colStudentName = new TableColumn("�̸�");
			colStudentName.setPrefWidth(80);
			colStudentName.setStyle("-fx-allignment: CENTER");
			colStudentName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));
			TableColumn colStudentId = new TableColumn("���̵�");
			colStudentId.setPrefWidth(80);
			colStudentId.setStyle("-fx-allignment: CENTER");
			colStudentId.setCellValueFactory(new PropertyValueFactory<>("sd_id"));
			TableColumn colStudentPassword = new TableColumn("��й�ȣ");
			colStudentPassword.setPrefWidth(80);
			colStudentPassword.setStyle("-fx-allignment: CENTER");
			colStudentPassword.setCellValueFactory(new PropertyValueFactory<>("sd_passwd"));
			TableColumn colSubjectNum = new TableColumn("�а���");
			colSubjectNum.setPrefWidth(70);
			colSubjectNum.setStyle("-fx-allignment: CENTER");
			colSubjectNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));
			TableColumn colStudentBirthday = new TableColumn("�������");
			colStudentBirthday.setPrefWidth(80);
			colStudentBirthday.setStyle("-fx-allignment: CENTER");
			colStudentBirthday.setCellValueFactory(new PropertyValueFactory<>("sd_birthday"));
			TableColumn colStudentPhone = new TableColumn("����ó");
			colStudentPhone.setPrefWidth(80);
			colStudentPhone.setStyle("-fx-allignment: CENTER");
			colStudentPhone.setCellValueFactory(new PropertyValueFactory<>("sd_phone"));
			TableColumn colStudentAddress = new TableColumn("�� ��");
			colStudentAddress.setPrefWidth(150);
			colStudentAddress.setStyle("-fx-allignment: CENTER");
			colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("sd_address"));
			TableColumn colStudentEmail = new TableColumn("�̸���");
			colStudentEmail.setPrefWidth(80);
			colStudentEmail.setStyle("-fx-allignment: CENTER");
			colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("sd_email"));
			TableColumn colStudentDate = new TableColumn("�����");
			colStudentDate.setPrefWidth(80);
			colStudentDate.setStyle("-fx-allignment: CENTER");
			colStudentDate.setCellValueFactory(new PropertyValueFactory<>("sd_date"));

			studentTableView.setItems(studentDataList);
			studentTableView.getColumns().addAll(colStudentNo, colStudentNum, colStudentName, colStudentId,
					colStudentPassword, colSubjectNum, colStudentBirthday, colStudentPhone, colStudentAddress,
					colStudentEmail, colStudentDate);

			// �л� ��ü ���
			studentTotalList();

			// �߰��� �а��� ȣ��
			// addSubjectName();

			btnStudentInsert.setOnAction(event -> handlerBtnStudentInsertAction(event));
			cbx_subjectName.setOnAction(event -> handlerCbx_subjectNameActoion(event));
			btnIdCheck.setOnAction(event -> handlerBtnIdCheckAction(event));
			studentTableView.setOnMouseClicked(event -> handlerStudentTableViewActoion(event));
			btnStudentUpdate.setOnAction(event -> handlerBtnStudentUpdateAction(event));
			btnStudentInit.setOnAction(event -> handlerBtnStudentInitAction(event));
			btnStudentTatolList.setOnAction(event -> handlerBtnStudentTatolListAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addSubjectName() throws Exception {
		StudentDAO sDao = new StudentDAO();
		ArrayList subjectNameList = new ArrayList<>();
		subjectNameList = sDao.subjectTotalList();
		// �л� ��� �� �а� ��ȣ �޺� �� ����
		cbx_subjectName.setItems(FXCollections.observableArrayList(subjectNameList));

	}

	// �л� ��� �̺�Ʈ �ڵ鷯
	public void handlerBtnStudentInsertAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);

			StudentVO svo = null;
			StudentDAO sdao = null;

			svo = new StudentVO(txtsd_num.getText().trim(), txtsd_name.getText().trim(), txtsd_id.getText().trim(),
					txtsd_passwd.getText().trim(), selectSubjectNum, txtsd_birthday.getText().trim(),
					txtsd_phone.getText().trim(), txtsd_address.getText().trim(), txtsd_email.getText().trim());
			sdao = new StudentDAO();
			sdao.getStudentRegiste(svo);

			if (sdao != null) {

				studentTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� �Է�");
				alert.setHeaderText(txtsd_name.getText() + " �л��� ���������� �߰��Ǿ����ϴ�..");
				alert.setContentText("���� �л��� �Է��ϼ���");
				alert.showAndWait();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				selectSubjectNum = "";
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();
				txtsd_name.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�а� ���� �Է�");
			alert.setHeaderText("�а� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// �л� ���̵� �ߺ� üũ
	public void handlerBtnIdCheckAction(ActionEvent event) {
		btnStudentInsert.setDisable(false);
		btnIdCheck.setDisable(true);

		StudentDAO sDao = null;

		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtsd_id.getText().trim();
			sDao = new StudentDAO();
			searchResult = (boolean) sDao.getStudentIdOverlap(searchId);

			if (!searchResult && !searchId.equals("")) {
				txtsd_id.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText(searchId + "�� ����� �� �ֽ��ϴ�.");
				alert.setContentText("�н���带 �Է��ϼ���.");
				alert.showAndWait();

				btnStudentInsert.setDisable(false);
				btnIdCheck.setDisable(true);

			} else if (searchId.equals("")) {
				btnStudentInsert.setDisable(true);
				btnIdCheck.setDisable(false);
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText("���̵� �Է��Ͻÿ�.");
				alert.setContentText("����� ���̵� �Է��ϼ���!");
				alert.showAndWait();
			} else {
				btnStudentInsert.setDisable(true);
				btnIdCheck.setDisable(false);
				txtsd_id.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���̵� �ߺ� �˻�");
				alert.setHeaderText(searchId + "�� ����� �� �����ϴ�.");
				alert.setContentText("���̵� �ٸ������� �Է��ϼ���.");
				alert.showAndWait();

				txtsd_id.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���̵� �ߺ� �˻� ����");
			alert.setHeaderText("���̵� �ߺ� �˻翡 ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("�ٽ� �ϼ���.");
			alert.showAndWait();
		}
	}

	// �л� ��� ���� �а� ���� �̺�Ʈ �ڵ鷯
	public void handlerCbx_subjectNameActoion(ActionEvent event) {

		SubjectDAO sudao = new SubjectDAO();
		StudentDAO sdao = new StudentDAO();
		String serialNumber = "";// �Ϸù�ȣ
		String sdYear = "";

		try {
			selectSubjectNum = sudao.getSubjectNum(cbx_subjectName.getSelectionModel().getSelectedItem() + "");

			// �й��� 8�ڸ��� �����Ѵ�. (����2�ڸ�+�а�2�ڸ�+�Ϸù�ȣ4�ڸ� - ����06010001)
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			sdYear = sdf.format(new Date());

			serialNumber = sdao.getStudentCount(selectSubjectNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		studentNumber = sdYear + selectSubjectNum + serialNumber;

		txtsd_num.setText(studentNumber);
	}

	// �л� ��ü ���
	public void studentTotalList() throws Exception {
		studentDataList.removeAll(studentDataList);

		StudentDAO sDao = new StudentDAO();
		StudentVO sVo = null;
		ArrayList<String> title;
		ArrayList<StudentVO> list;

		title = sDao.getStudnetColumnName();
		int columnCount = title.size();

		list = sDao.getStudentTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			studentDataList.add(sVo);
		}

		// �߰��� �а��� ȣ��
		addSubjectName();
	}

	// �л� ���̺� �� ���� Ŭ�� �̺�Ʈ �ڵ鷯
	public void handlerStudentTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectStudent = studentTableView.getSelectionModel().getSelectedItems();
				selectedStudentIndex = selectStudent.get(0).getNo();
				String selectedSd_num = selectStudent.get(0).getSd_num();
				String selectedSd_name = selectStudent.get(0).getSd_name();
				String selectedSd_id = selectStudent.get(0).getSd_id();
				String selectedSd_passwd = selectStudent.get(0).getSd_passwd();
				String selectedSd_birthday = selectStudent.get(0).getSd_birthday();
				String selectedSd_phone = selectStudent.get(0).getSd_phone();
				String selectedSd_address = selectStudent.get(0).getSd_address();
				String selectedSd_email = selectStudent.get(0).getSd_email();

				txtsd_num.setText(selectedSd_num);
				txtsd_name.setText(selectedSd_name);
				txtsd_id.setText(selectedSd_id);
				txtsd_passwd.setText(selectedSd_passwd);
				txtsd_birthday.setText(selectedSd_birthday);
				txtsd_phone.setText(selectedSd_phone);
				txtsd_address.setText(selectedSd_address);
				txtsd_email.setText(selectedSd_email);

				txtsd_num.setEditable(false);
				txtsd_name.setEditable(false);
				txtsd_id.setEditable(false);

				btnIdCheck.setDisable(true);
				cbx_subjectName.setDisable(true);
				btnStudentUpdate.setDisable(false);
				btnStudentInit.setDisable(false);
				btnStudentInsert.setDisable(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// �л� ��ü ���
	public void handlerBtnStudentTatolListAction(ActionEvent event) {

		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �л� �ʱ�ȭ
	public void handlerBtnStudentInitAction(ActionEvent event) {
		try {
			studentDataList.removeAll(studentDataList);
			studentTotalList();

			txtsd_num.clear();
			txtsd_name.clear();
			txtsd_id.clear();
			txtsd_passwd.clear();
			txtsd_birthday.clear();
			txtsd_phone.clear();
			txtsd_address.clear();
			txtsd_email.clear();

			txtsd_num.setEditable(true);
			txtsd_name.setEditable(true);
			txtsd_id.setEditable(true);

			btnIdCheck.setDisable(false);
			cbx_subjectName.setDisable(false);
			btnStudentUpdate.setDisable(true);
			btnStudentInit.setDisable(true);
			btnStudentInsert.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �л� ���� ����
	public void handlerBtnStudentUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			StudentDAO sdao = new StudentDAO();
			sucess = sdao.getStudentUpdate(selectedStudentIndex, txtsd_passwd.getText().trim(),
					txtsd_birthday.getText().trim(), txtsd_phone.getText().trim(), txtsd_address.getText().trim(),
					txtsd_email.getText().trim());
			if (sucess) {
				studentDataList.removeAll(studentDataList);
				studentTotalList();

				txtsd_num.clear();
				txtsd_name.clear();
				txtsd_id.clear();
				txtsd_passwd.clear();
				txtsd_birthday.clear();
				txtsd_phone.clear();
				txtsd_address.clear();
				txtsd_email.clear();

				txtsd_num.setEditable(true);
				txtsd_name.setEditable(true);
				txtsd_id.setEditable(true);

				btnIdCheck.setDisable(false);
				cbx_subjectName.setDisable(false);
				btnStudentUpdate.setDisable(true);
				btnStudentInit.setDisable(true);
				btnStudentInsert.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
