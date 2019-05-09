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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.LessonVO;

public class LessonTabController implements Initializable {
	// ���� ��� ��
	@FXML
	private TextField txtLessonNum;
	@FXML
	private TextField txtLessonName;
	@FXML
	private TableView<LessonVO> lessonTableView = new TableView<>();
	@FXML
	private Button btnLessonInsert; // ���� ���
	@FXML
	private Button btnLessonUpdate; // ���� ����
	@FXML
	private Button btnLessonDelete; // ���� ����

	ObservableList<LessonVO> lessonDataList = FXCollections.observableArrayList();
	ObservableList<LessonVO> selectLesson = null; // ���̺��� ������ ���� ����
	int selectedLessonIndex; // ���̺��� ������ ���� ���� �ε��� ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// ������ �ʱ�ȭ
			btnLessonUpdate.setDisable(true);
			btnLessonDelete.setDisable(true);
			lessonTableView.setEditable(false);

			// ���� ���̺� �� �÷��̸� ����
			TableColumn colLessonNo = new TableColumn("NO.");
			colLessonNo.setPrefWidth(50);
			colLessonNo.setStyle("-fx-allignment: CENTER");
			colLessonNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			TableColumn colLessonNum = new TableColumn("�����ȣ");
			colLessonNum.setPrefWidth(90);
			colLessonNum.setStyle("-fx-allignment: CENTER");
			colLessonNum.setCellValueFactory(new PropertyValueFactory<>("l_num"));
			TableColumn colLessonName = new TableColumn("�����");
			colLessonName.setPrefWidth(160);
			colLessonName.setStyle("-fx-allignment: CENTER");
			colLessonName.setCellValueFactory(new PropertyValueFactory<>("l_name"));

			lessonTableView.setItems(lessonDataList);
			lessonTableView.getColumns().addAll(colLessonNo, colLessonNum, colLessonName);

			// ���� ��ü ���
			lessonTotalList();

			// ���� Ű �̺�Ʈ ���
			txtLessonNum.setOnKeyPressed(event -> handlerTxtLessonNumKeyPressed(event));
			// ���� ���, ����, ���� �̺�Ʈ ���
			btnLessonInsert.setOnAction(event -> handlerBtnLessonInsertActoion(event));
			btnLessonDelete.setOnAction(event -> handlerBtnLessonDeleteActoion(event));
			btnLessonUpdate.setOnAction(event -> handlerBtnLessonUpdateActoion(event));
			lessonTableView.setOnMouseClicked(event -> handlerLessonTableViewActoion(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ��� �ؽ�Ʈ �ʵ� Ű �̺�Ʈ �ڵ鷯
	public void handlerTxtLessonNumKeyPressed(KeyEvent event) {
		if (txtLessonNum.getText().length() >= 3) {
			txtLessonNum.clear();
		}
		if (event.getCode() == KeyCode.ENTER) {
			txtLessonName.requestFocus();
		}
	}

	// ���� ��� �̺�Ʈ �ڵ��
	public void handlerBtnLessonInsertActoion(ActionEvent event) {
		try {
			lessonDataList.removeAll(lessonDataList);

			LessonVO lvo = null;
			LessonDAO ldao = null;

			lvo = new LessonVO(txtLessonNum.getText().trim(), txtLessonName.getText().trim());
			ldao = new LessonDAO();
			ldao.getLessonRegiste(lvo);

			if (ldao != null) {

				lessonTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� �Է�");
				alert.setHeaderText("txtSubjectName.getText()" + " ������ ���������� �߰��Ǿ����ϴ�..");
				alert.setContentText("���� ���� �Է��ϼ���");
				alert.showAndWait();

				txtLessonNum.clear();
				txtLessonName.clear();
				txtLessonNum.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("���� ���� �Է�");
			alert.setHeaderText("���� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// ���� ���� �̺�Ʈ �ڵ��
	public void handlerBtnLessonDeleteActoion(ActionEvent event) {
		try {
			boolean sucess;

			LessonDAO ldao = new LessonDAO();
			sucess = ldao.getLessonDelete(selectedLessonIndex);
			if (sucess) {
				lessonDataList.removeAll(lessonDataList);
				lessonTotalList();

				txtLessonNum.clear();
				txtLessonName.clear();
				btnLessonInsert.setDisable(false);
				btnLessonUpdate.setDisable(true);
				btnLessonDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���� �̺�Ʈ �ڵ��
	public void handlerBtnLessonUpdateActoion(ActionEvent event) {
		try {
			boolean sucess;

			LessonDAO ldao = new LessonDAO();
			sucess = ldao.getLessonUpdate(selectedLessonIndex, txtLessonNum.getText().trim(),
					txtLessonName.getText().trim());
			if (sucess) {
				lessonDataList.removeAll(lessonDataList);
				lessonTotalList();

				txtLessonNum.clear();
				txtLessonName.clear();
				btnLessonInsert.setDisable(false);
				btnLessonUpdate.setDisable(true);
				btnLessonDelete.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���� ���̺�� ����Ŭ�� ���� �̺�Ʈ �ڵ鷯
	public void handlerLessonTableViewActoion(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectLesson = lessonTableView.getSelectionModel().getSelectedItems();
				selectedLessonIndex = selectLesson.get(0).getNo();
				String selectedL_num = selectLesson.get(0).getL_num();
				String selectedL_name = selectLesson.get(0).getL_name();

				txtLessonNum.setText(selectedL_num);
				txtLessonName.setText(selectedL_name);

				btnLessonUpdate.setDisable(false);
				btnLessonDelete.setDisable(false);
				btnLessonInsert.setDisable(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ���� ��ü ���
	public void lessonTotalList() throws Exception {
		lessonDataList.removeAll(lessonDataList);

		LessonDAO lDao = new LessonDAO();
		LessonVO lVo = null;
		ArrayList<String> title;
		ArrayList<LessonVO> list;

		title = lDao.getLessonColumnName();
		int columnCount = title.size();

		list = lDao.getLessonTotalList();
		int rowCount = list.size();
		
		for (int index = 0; index < rowCount; index++) {
			lVo = list.get(index);
			lessonDataList.add(lVo);
		}
	}

}
