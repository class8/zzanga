package control;

import java.net.URL;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
	Button tr_btnCsearch;
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
	TextArea tr_txtRemarks;
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
		MessageFormat format = new MessageFormat("{0}");
		tr_c_txtNumber.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event;
			}
		}));
		tr_c_txtEmail.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 50) {
				return null;
			} else {
				return event;
			}
		}));
		tr_txtAddress.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 100) {
				return null;
			} else {
				return event;
			}
		}));
		tr_txtAmount.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event;
			}
		}));
		tr_txtRemarks.setTextFormatter(new TextFormatter<>(event -> {
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(event.getControlNewText(), parsePosition);
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 50) {
				return null;
			} else {
				return event;
			}
		}));
		// 고객번호입력에 숫자만 입력할수 있게해줌
		tr_c_txtNumber.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tr_c_txtNumber.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		// 수량입력에 숫자만 입력할수 있게해줌
		tr_txtAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tr_txtAmount.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

}
