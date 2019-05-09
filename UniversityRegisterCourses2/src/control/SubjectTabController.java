package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.SubjectVO;

public class SubjectTabController implements Initializable {

	// ������ ��
	@FXML
	private Label lblManagerName;
	// �а� ��� ��
	@FXML
	private TextField txtSubjectNum;
	@FXML
	private TextField txtSubjectName;
	@FXML
	private TableView<SubjectVO> subjectTableView = new TableView<>();
	@FXML
	private Button btnInsert; // �а� ���
	@FXML
	private Button btnUpdate; // �а� ����
	@FXML
	private Button btnDelete; // �а� ����
	@FXML
	private Button btnRead;

	public static ObservableList<SubjectVO> subjectDataList = FXCollections.observableArrayList();
	ObservableList<SubjectVO> selectSubject = null; // ���̺��� ������ ���� ����
	int selectedIndex; // ���̺��� ������ �а� ���� �ε��� ����

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			lblManagerName.setText(LoginController.managerName);

			// �а���� �ʱ�ȭ
			btnUpdate.setDisable(true);
			btnDelete.setDisable(true);
			subjectTableView.setEditable(false);

			// �а� ���̺� �� �÷��̸� ����
			TableColumn colNo = new TableColumn("NO.");
			colNo.setPrefWidth(50);
			colNo.setStyle("-fx-allignment: CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colSNum = new TableColumn("�а� ��ȣ");
			colSNum.setPrefWidth(90);
			colSNum.setStyle("-fx-allignment: CENTER");
			colSNum.setCellValueFactory(new PropertyValueFactory<>("s_num"));

			TableColumn colSName = new TableColumn("�� �� ��");
			colSName.setPrefWidth(160);
			colSName.setStyle("-fx-allignment: CENTER");
			colSName.setCellValueFactory(new PropertyValueFactory<>("s_name"));

			subjectTableView.setItems(subjectDataList);
			subjectTableView.getColumns().addAll(colNo, colSNum, colSName);

			// �а� ��ü ���
			subjectTotalList();

			// �а� Ű �̺�Ʈ ���
			txtSubjectNum.setOnKeyPressed(event -> handlerTxtSubjectNumKeyPressed(event));

			// �а� ���, ����, ���� �̺�Ʈ ���
			btnInsert.setOnAction(event -> handlerBtnInsertActoion(event));
			btnDelete.setOnAction(event -> handlerBtnDeleteActoion(event));
			btnUpdate.setOnAction(event -> handlerBtnUpdateActoion(event));
			subjectTableView.setOnMouseClicked(event -> handlerSubjectTableViewActoion(event));
			btnRead.setOnAction(event -> handlerBtnReadAction(event));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �а� ���̺�� ����Ŭ�� ���� �̺�Ʈ �ڵ鷯
	public void handlerSubjectTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectSubject = subjectTableView.getSelectionModel().getSelectedItems();
				selectedIndex = selectSubject.get(0).getNo();
				String selectedS_num = selectSubject.get(0).getS_num();
				String selectedS_name = selectSubject.get(0).getS_name();

				txtSubjectNum.setText(selectedS_num);
				txtSubjectName.setText(selectedS_name);

				btnUpdate.setDisable(false);
				btnDelete.setDisable(false);
				btnInsert.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// �а� ��� �ؽ�Ʈ �ʵ� Ű �̺�Ʈ �ڵ鷯
	public void handlerTxtSubjectNumKeyPressed(KeyEvent event) {

		if ((txtSubjectNum.getText().length() >= 3)) {
			txtSubjectNum.clear();
		}
		if (event.getCode() == KeyCode.ENTER) {
			txtSubjectName.requestFocus();
		}
	}

	// �а� ��ü ����Ʈ
	public void subjectTotalList() throws Exception {
		subjectDataList.removeAll(subjectDataList);

		SubjectDAO sDao = new SubjectDAO();
		SubjectVO sVo = null;
		ArrayList<String> title;
		ArrayList<SubjectVO> list;

		title = sDao.getSubjectColumnName();
		int columnCount = title.size();

		list = sDao.getSubjectTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			subjectDataList.add(sVo);
		}
	}

	// �а� ��� �̺�Ʈ �ڵ鷯
	public void handlerBtnInsertActoion(ActionEvent event) {
		try {
			subjectDataList.removeAll(subjectDataList);

			SubjectVO svo = null;
			SubjectDAO sdao = null;

			svo = new SubjectVO(txtSubjectNum.getText().trim(), txtSubjectName.getText().trim());
			sdao = new SubjectDAO();
			sdao.getSubjectRegiste(svo);

			if (sdao != null) {
				
				subjectTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�а� �Է�");
				alert.setHeaderText(txtSubjectName.getText() + " �а��� ���������� �߰��Ǿ����ϴ�..");
				alert.setContentText("���� �а��� �Է��ϼ���");
				alert.showAndWait();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				txtSubjectNum.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("�а� ���� �Է�");
			alert.setHeaderText("�а� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// �а� ���� �̺�Ʈ �ڵ鷯
	public void handlerBtnUpdateActoion(ActionEvent event) {
		try {
			boolean sucess;

			SubjectDAO sdao = new SubjectDAO();
			sucess = sdao.getSubjectUpdate(selectedIndex, txtSubjectNum.getText().trim(),
					txtSubjectName.getText().trim());
			if (sucess) {
				subjectDataList.removeAll(subjectDataList);
				subjectTotalList();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				btnInsert.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �а� ���� �̺�Ʈ �ڵ鷯
	public void handlerBtnDeleteActoion(ActionEvent event) {
		try {
			boolean sucess;

			SubjectDAO sdao = new SubjectDAO();
			sucess = sdao.getSubjectDelete(selectedIndex);
			if (sucess) {
				subjectDataList.removeAll(subjectDataList);
				subjectTotalList();

				txtSubjectNum.clear();
				txtSubjectName.clear();
				btnInsert.setDisable(false);
				btnUpdate.setDisable(true);
				btnDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���̺� �� �б� �׽�Ʈ
	public void handlerBtnReadAction(ActionEvent event) {
		try {
			int count = subjectTableView.getItems().size();
			System.out.println("count : " + count);
			for (int i = 0; i < count; i++) {
				selectSubject = subjectTableView.getItems();
				int index = selectSubject.get(i).getNo();
				String selectedS_num = selectSubject.get(i).getS_num();
				String selectedS_name = selectSubject.get(i).getS_name();

				System.out.print(index + " ");
				System.out.print(selectedS_num + " ");
				System.out.println(selectedS_name + " ");
				/*
				 * SubjectVO svo = null; SubjectDAO sdao = null;
				 * 
				 * svo = new SubjectVO(index, selectedS_num, selectedS_name); sdao = new
				 * SubjectDAO();
				 * 
				 * sdao.getSubjectRegiste(svo);
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
