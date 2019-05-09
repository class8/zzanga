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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TraineeVO;

public class TraineeTotalTabController implements Initializable {

	@FXML
	ComboBox<String> cbx_searchList;
	@FXML
	TextField txtSearchWord;
	@FXML
	Button btnSearch;
	@FXML
	Label lblCount;
	@FXML
	TableView<TraineeVO> traineeTatolTableView = new TableView<>();

	ObservableList<TraineeVO> traineeDataList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			cbx_searchList.setItems(FXCollections.observableArrayList("�й�", "�����", "�л��̸�"));

			// ���� ���̺� �� �÷��̸� ����
			TableColumn colNo = new TableColumn("NO.");
			colNo.setPrefWidth(50);
			colNo.setStyle("-fx-allignment: CENTER");
			colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

			TableColumn colSdNum = new TableColumn("�й�");
			colSdNum.setPrefWidth(150);
			colSdNum.setStyle("-fx-allignment: CENTER");
			colSdNum.setCellValueFactory(new PropertyValueFactory<>("sd_num"));

			TableColumn colSdName = new TableColumn("�л� �̸�");
			colSdName.setPrefWidth(150);
			colSdName.setStyle("-fx-allignment: CENTER");
			colSdName.setCellValueFactory(new PropertyValueFactory<>("sd_name"));

			TableColumn colLNum = new TableColumn("�����");
			colLNum.setPrefWidth(150);
			colLNum.setStyle("-fx-allignment: CENTER");
			colLNum.setCellValueFactory(new PropertyValueFactory<>("l_num"));

			TableColumn colTSection = new TableColumn("���� ����");
			colTSection.setPrefWidth(150);
			colTSection.setStyle("-fx-allignment: CENTER");
			colTSection.setCellValueFactory(new PropertyValueFactory<>("t_section"));

			TableColumn colTDate = new TableColumn("��� ��¥");
			colTDate.setPrefWidth(250);
			colTDate.setStyle("-fx-allignment: CENTER");
			colTDate.setCellValueFactory(new PropertyValueFactory<>("t_date"));

			traineeTatolTableView.setItems(traineeDataList);
			traineeTatolTableView.getColumns().addAll(colNo, colSdNum, colSdName, colLNum, colTSection, colTDate);

			// ���� ��ü ���
			traineeTotalList();

			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void handlerBtnSearchAction(ActionEvent event) {
		String search = "";
		search = cbx_searchList.getSelectionModel().getSelectedItem();

		TraineeVO tVo = new TraineeVO();
		TraineeDAO tDao = new TraineeDAO();

		String searchName = "";
		boolean searchResult = false;

		searchName = txtSearchWord.getText().trim();

		try {

			if (searchName.equals("") || search.equals("")) {
				try {
					searchResult = true;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("���� ���� �˻�");
					alert.setHeaderText("���� �˻� ������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();

					traineeTotalList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<String> title;
				ArrayList<TraineeVO> list = null;

				title = tDao.getTraineeColumnName();
				int columnCount = title.size();

				if (search.equals("�й�")) {
					list = tDao.getTraineeStudentNumSearchList(searchName);
					
					if(list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("�л� �й� ���� �˻�");
						alert.setHeaderText(searchName + " �й��� ���� ����Ʈ�� �����ϴ�.");
						alert.setContentText("�ٽ� �˻��ϼ���.");
						alert.showAndWait();
						
						list = tDao.getTraineeTotalList();
					}
				}
				if (search.equals("�����")) {
					list = tDao.getTraineeSubjectSearchList(searchName);
					
					if(list.size() == 0) {
						list = tDao.getTraineeTotalList();
					}
				}
				if (search.equals("�л��̸�")) {
					list = tDao.getTraineeStudentNameSearchList(searchName);
					
					if(list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("�л� �̸� ���� �˻�");
						alert.setHeaderText(searchName + " �̸��� ���� ����Ʈ�� �����ϴ�.");
						alert.setContentText("�ٽ� �˻��ϼ���.");
						alert.showAndWait();
						
						list = tDao.getTraineeTotalList();
					}
				}
				
				txtSearchWord.clear();
				traineeDataList.removeAll(traineeDataList);

				int rowCount = list.size();
				lblCount.setText("�˻� : " + rowCount + " ��");
				for (int index = 0; index < rowCount; index++) {
					tVo = list.get(index);
					traineeDataList.add(tVo);
				}
				searchResult = true;
			}

			if (!searchResult) {
				txtSearchWord.clear();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�л� ���� �˻�");
				alert.setHeaderText(searchName + " �л��� ����Ʈ�� �����ϴ�.");
				alert.setContentText("�ٽ� �˻��ϼ���.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ���� ��ü ����Ʈ
	public void traineeTotalList() throws Exception {
		traineeDataList.removeAll(traineeDataList);

		TraineeDAO tDao = new TraineeDAO();
		TraineeVO tVo = null;
		ArrayList<String> title;
		ArrayList<TraineeVO> list;

		title = tDao.getTraineeColumnName();

		list = tDao.getTraineeTotalList();
		int rowCount = list.size();

		lblCount.setText("�ѿ� : " + rowCount + " ��");
		for (int index = 0; index < rowCount; index++) {
			tVo = list.get(index);
			traineeDataList.add(tVo);
		}
	}

}
