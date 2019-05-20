package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.AccountVO;
import model.FabricVO;

public class TradeRegController implements Initializable {

	@FXML
	TextField tr_f_txtNumber;
	@FXML
	TextField tr_f_txtSort;
	@FXML
	TextField tr_f_txtName;
	@FXML
	TextField tr_f_txtColor;
	@FXML
	TextField tr_f_txtSize;
	@FXML
	TextField tr_f_txtWeight;
	@FXML
	TextField tr_f_txtPrice;
	@FXML
	TextField tr_f_txtPhone;
	@FXML
	TextField tr_c_txtNumber;
	@FXML
	TextField tr_c_txtName;
	@FXML
	TextField tr_c_txtPhone;
	@FXML
	TextField tr_c_txtEmail;
	@FXML
	TextField tr_txtAddress;
	@FXML
	TextField tr_txtAmount;
	@FXML
	Button tr_btnTotal;
	@FXML
	TextField tr_txtTotal;
	@FXML
	Button tr_btnRegist;
	@FXML
	Button tr_btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
