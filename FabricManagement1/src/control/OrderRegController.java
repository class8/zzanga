package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderRegController implements Initializable {

	// 여기부터 주문 등록
	@FXML
	TextField or_f_txtNumber;
	@FXML
	TextField or_f_txtSort;
	@FXML
	TextField or_f_txtName;
	@FXML
	TextField or_f_txtColor;
	@FXML
	TextField or_f_txtSize;
	@FXML
	TextField or_f_txtWeight;
	@FXML
	TextField or_f_txtPrice;
	@FXML
	TextField or_f_txtPhone;
	@FXML
	TextField or_c_txtName;
	@FXML
	TextField or_o_txtAmount;
	@FXML
	TextField or_o_txtAddress;
	@FXML
	TextField or_o_txtCprice;
	@FXML
	Button or_o_btnTotal;
	@FXML
	TextField or_o_txtTotal;
	@FXML
	TextArea or_o_txtRemarks;
	@FXML
	Button or_o_btnRegist;
	@FXML
	Button or_o_btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		or_o_btnCancel.setOnAction(event -> handlerBtnCancelAction(event));
	}

	public void handlerBtnCancelAction(ActionEvent event) {

	}

}
