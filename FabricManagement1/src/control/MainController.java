package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {

	@FXML
	private TabPane mainPane;
	@FXML
	private Tab cutomer;
	@FXML
	private CustomerInfoController customerController;
	@FXML
	private Tab account;
	@FXML
	private AccountInfoController accountController;
	@FXML
	private Tab fabric;
	@FXML
	private FabricInfoController fabricController;
	@FXML
	private Tab Trade;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
