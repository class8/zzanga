package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {

	@FXML
	private TabPane mainPane;
	@FXML
	private Tab subject;
	@FXML
	private SubjectTabController subjectTabController;
	// 참조변수명 부여 방법: include시 명시한 id+"Controller"
	@FXML
	private Tab student;
	@FXML
	private StudentTabController studentTabController;
	@FXML
	private Tab lesson;
	@FXML
	private LessonTabController lessonTabController;
	@FXML
	private Tab traineeTotal;
	@FXML
	private TraineeTotalTabController traineeTotalTabController;

	// 메뉴
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					
					if(newValue == subject) {
						
						System.out.println("학과");
						
						try { 
							
							subjectTabController.subjectTotalList(); 
							
						} catch (Exception e) {
							
							e.printStackTrace();
							
						}
					
					} else if(newValue == student) {
						
						try {
							
							studentTabController.studentTotalList();
						
						} catch (Exception e) {
							
							e.printStackTrace();
							
						}
						
					} else if(newValue == lesson) {
						
						try {
							
							lessonTabController.lessonTotalList();
							
						} catch (Exception e) {
							
							e.printStackTrace();
							
						}
						
					}else if(newValue == traineeTotal) {
							
							try {
								
								traineeTotalTabController.traineeTotalList();  
							
							} catch (Exception e) {
								
								e.printStackTrace();
								
							}
						}
				}
				
			});
			
			// 메뉴 이벤트 등록 
			menuExit.setOnAction(event -> handlerMenuExitAction(event));
		
	
}
}
