package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private TabPane mainPane;
	@FXML
	private Tab customer;
	@FXML
	private CustomerInfoController customerInfoController;
	@FXML
	private Tab account;
	@FXML
	private AccountInfoController accountInfoController;
	@FXML
	private Tab fabric;
	@FXML
	private FabricInfoController fabricInfoController;
	@FXML
	private Tab trade;
	@FXML
	private TradeInfoController tradeInfoController;
	@FXML
	private Tab order;
	@FXML
	private OrderInfoController orderInfoController;

	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuInfo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {

			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if (newValue == customer) {
						try {
							customerInfoController.customerTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == account) {
						try {
							accountInfoController.accountTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == fabric) {
						try {
							fabricInfoController.fabricTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == trade) {
						try {
							tradeInfoController.tradeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == order) {
						try {
							orderInfoController.orderTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});

			// 메뉴 이벤트 등록
			menuExit.setOnAction(event -> handlerMenuExitAction(event));
			menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlerMenuInfoAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 메뉴 이벤트 핸들러
	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scene = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("원단 관리 프로그램 v0.01");
			mainMtage.setResizable(false);
			mainMtage.setScene(scene);
			Stage oldStage = (Stage) mainPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("원단 관리");
		alert.setHeaderText("원단 관리 프로그램 프로그램");
		alert.setContentText("Fabric Management Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("원단 관리");
		alert.setHeaderText("원단 관리 프로그램 종료");
		alert.setContentText("확인 버튼을 클릭하시면 원단관리 프로그램이 종료됩니다.");
		alert.setResizable(false);
		alert.showAndWait();

		Platform.exit();
	}

}
