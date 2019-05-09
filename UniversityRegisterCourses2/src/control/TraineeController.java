package control;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.StudentVO;
import model.TraineeVO;

public class TraineeController implements Initializable {

	// �޴�
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;

	// ���� ��û ��
	@FXML
	private TextField txtStudentName;
	@FXML
	private TextField txtStudentNum;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private RadioButton rbMajor;
	@FXML
	private RadioButton rbMinor;
	@FXML
	private RadioButton rbCulture;
	@FXML
	private ComboBox<String> cbx_subjectName;
	@FXML
	private TextField txtSectionName;
	@FXML
	private Button btnTraineeInsert;
	@FXML
	private Button btnTraineeCancel;
	@FXML
	private Button btnTraineeExit;
	@FXML
	private TableView<TraineeVO> traineeTableView = new TableView<>();

	ObservableList<TraineeVO> traineeDataList = FXCollections.observableArrayList();
	ObservableList<TraineeVO> selectTrainee = null; // ���̺��� ������ ���� ����
	int selectedTraineeIndex; // ���̺��� ������ ���� ��û �ε��� ����

	String studentLoginId; // �α��� ���̵�
	String l_num; // ���� ��ȣ
	String t_section; // ���� ����
	String sd_num; // �α����� �л��� �й�

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			studentLoginId = LoginController.studentId;

			if (!studentLoginId.equals("")) {
				StudentVO sVo = new StudentVO();
				TraineeDAO tDao = new TraineeDAO();
				sVo = tDao.getStudentSubjectName(studentLoginId);

				txtStudentNum.setText(sVo.getSd_num());
				txtStudentName.setText(sVo.getSd_name());
				txtSubjectName.setText(sVo.getS_num());

				sd_num = txtStudentNum.getText().trim();

				btnTraineeCancel.setDisable(true);
				traineeTableView.setEditable(false);

				// �л� ���� ���� ����
				txtStudentName.setEditable(false);
				txtStudentNum.setEditable(false);
				txtSubjectName.setEditable(false);
				txtSectionName.setEditable(false);

				// ���� ���̺� �� �÷��̸� ����
				TableColumn colNo = new TableColumn("NO.");
				colNo.setPrefWidth(50);
				colNo.setStyle("-fx-allignment: CENTER");
				colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

				TableColumn colSdNum = new TableColumn("�й�");
				colSdNum.setPrefWidth(90);
				colSdNum.setStyle("-fx-allignment: CENTER");
				colSdNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

				TableColumn colLNum = new TableColumn("�����");
				colLNum.setPrefWidth(100);
				colLNum.setStyle("-fx-allignment: CENTER");
				colLNum.setCellValueFactory(new PropertyValueFactory<>("l_num"));

				TableColumn colTSection = new TableColumn("���� ����");
				colTSection.setPrefWidth(100);
				colTSection.setStyle("-fx-allignment: CENTER");
				colTSection.setCellValueFactory(new PropertyValueFactory<>("t_section"));

				TableColumn colTDate = new TableColumn("��� ��¥");
				colTDate.setPrefWidth(160);
				colTDate.setStyle("-fx-allignment: CENTER");
				colTDate.setCellValueFactory(new PropertyValueFactory<>("t_date"));

				traineeTableView.setItems(traineeDataList);
				traineeTableView.getColumns().addAll(colNo, colSdNum, colLNum, colTSection, colTDate);

				// ���� ��ü ���
				traineeTotalList();

				// �޴� �̺�Ʈ ���
				menuExit.setOnAction(event -> handlerMenuExitAction(event));
				menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
				menuInfo.setOnAction(event -> handlerMenuInfoAction(event));

				// ���� ���� ���� �̺�Ʈ
				rbMajor.setOnAction(event -> handlerRbMajorAction(event));
				rbMinor.setOnAction(event -> handlerRbMinorAction(event));
				rbCulture.setOnAction(event -> handlerRbCultureAction(event));
				cbx_subjectName.setOnAction(event -> handlerCbx_subjectNameAction(event));

				// ���� ���, ���� �̺�Ʈ ���
				btnTraineeInsert.setOnAction(event -> handlerBtnTraineeInsertActoion(event));
				btnTraineeCancel.setOnAction(event -> handlerBtnTraineeCancelActoion(event));

				btnTraineeExit.setOnAction(event -> handlerBtnTraineeExitActoion(event));
				traineeTableView.setOnMouseClicked(event -> handlerTraineeTableViewActoion(event));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���� ���� �̺�Ʈ �ڵ鷯
	public void handlerRbCultureAction(ActionEvent event) {
		cbx_subjectName.setItems(FXCollections.observableArrayList("����", "����", "����", "����"));
		t_section = rbCulture.getText();
	}

	public void handlerRbMinorAction(ActionEvent event) {
		cbx_subjectName.setItems(FXCollections.observableArrayList("�������̷�"));
		t_section = rbMinor.getText();
	}

	public void handlerRbMajorAction(ActionEvent event) {
		cbx_subjectName.setItems(FXCollections.observableArrayList("���α׷���", "�����ͺ��̽�"));
		t_section = rbMajor.getText();
	}

	public void handlerCbx_subjectNameAction(ActionEvent event) {
		txtSectionName.setText(cbx_subjectName.getSelectionModel().getSelectedItem());
		selectLessonNameToLessonNum();
	}

	// ���� ��û�� ������� ���� ��ȣ
	public void selectLessonNameToLessonNum() {
		try {
			TraineeDAO tDao = new TraineeDAO();
			if (txtSectionName.getText() != null) {
				l_num = tDao.getLessonNum(txtSectionName.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnTraineeInsertActoion(ActionEvent event) {
		try {
			traineeDataList.removeAll(traineeDataList);

			TraineeVO tvo = null;
			TraineeDAO tdao = null;

			tvo = new TraineeVO(txtStudentNum.getText().trim(), l_num, t_section);
			tdao = new TraineeDAO();
			tdao.getTraineeRegiste(tvo);

			if (tdao != null) {

				traineeTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ��û");
				alert.setHeaderText(txtStudentName.getText() + " ���� ��û�� �Ǿ����ϴ�..");
				alert.setContentText("�ٸ� ���� ���� ��û�� �ϼ���");
				alert.showAndWait();

				txtSectionName.clear();
				l_num = "";
				t_section = "";
				rbCulture.setSelected(false);
				rbMajor.setSelected(false);
				rbMinor.setSelected(false);
				cbx_subjectName.setItems(FXCollections.observableArrayList("����"));
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�а� ���� �Է�");
			alert.setHeaderText("�а� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// ���� ��û ���
	public void handlerBtnTraineeCancelActoion(ActionEvent event) {
		try {
			boolean sucess;

			TraineeDAO tdao = new TraineeDAO();
			sucess = tdao.getTraineeDelete(selectedTraineeIndex);
			if (sucess) {
				traineeDataList.removeAll(traineeDataList);
				traineeTotalList();

				btnTraineeCancel.setDisable(true);
				btnTraineeInsert.setDisable(false);

				txtSectionName.clear();
				l_num = "";
				t_section = "";
				rbCulture.setSelected(false);
				rbMajor.setSelected(false);
				rbMinor.setSelected(false);
				cbx_subjectName.setItems(FXCollections.observableArrayList("����"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �޴� �̺�Ʈ �ڵ鷯
	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("�̷� ���б� �л����");
			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTraineeExit.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�̷� ���б�");
		alert.setHeaderText("�̷� ���б� ������û ���α׷�");
		alert.setContentText("Future Universit Register Courses Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		Platform.exit();
	}

	public void handlerBtnTraineeExitActoion(ActionEvent event) {
		Platform.exit();
	}

	// ���� ���̺�� ����Ŭ�� ���� �̺�Ʈ �ڵ鷯
	public void handlerTraineeTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectTrainee = traineeTableView.getSelectionModel().getSelectedItems();
				selectedTraineeIndex = selectTrainee.get(0).getNo();
				String selectedL_num = selectTrainee.get(0).getL_num();

				txtSectionName.setText(selectedL_num);

				btnTraineeCancel.setDisable(false);
				btnTraineeInsert.setDisable(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ���� ��ü ����Ʈ
	public void traineeTotalList() throws Exception {

		TraineeDAO tDao = new TraineeDAO();
		TraineeVO tVo = null;
		ArrayList<String> title;
		ArrayList<TraineeVO> list;

		title = tDao.getTraineeColumnName();
		int columnCount = title.size();

		list = tDao.getTraineeTotalList(sd_num);
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			tVo = list.get(index);
			traineeDataList.add(tVo);
		}
	}
}
