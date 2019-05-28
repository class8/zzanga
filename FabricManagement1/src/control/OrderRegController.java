package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	TextField or_c_txtNumber;
	@FXML
	TextField or_c_txtName;
	@FXML
	TextField or_c_txtPhone;
	@FXML
	TextField or_txtEmail;
	@FXML
	TextField or_txtAmount;
	@FXML
	TextField or_txtAddress;
	@FXML
	TextField or_a_txtNumber;
	@FXML
	TextField or_a_txtName;
	@FXML
	Button or_a_txtSearch;
	@FXML
	Button or_btnTotal;
	@FXML
	TextField or_txtTotal;
	@FXML
	TextArea or_txtRemarks;
	@FXML
	Button or_btnRegist;
	@FXML
	Button or_btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 거래처번호입력에 숫자만 입력할수 있게해줌
		or_a_txtNumber.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					or_a_txtNumber.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// 수량입력에 숫자만 입력할수 있게해줌
		or_txtAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					or_txtAmount.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
}
