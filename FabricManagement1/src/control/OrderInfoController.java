package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import model.OrderVO;

public class OrderInfoController implements Initializable {

	ObservableList<OrderVO> orderDataList = FXCollections.observableArrayList();
	ObservableList<OrderVO> selectOrder = null;
	int selectedOrderIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void orderTotalList() throws Exception {
		orderDataList.removeAll(orderDataList);
		OrderDAO oDao = new OrderDAO();
		OrderVO oVo = null;
		ArrayList<String> title;
		ArrayList<OrderVO> list;

		title = oDao.getOrderColumnName();
		int columnCount = title.size();

		list = oDao.getOrderTotalList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			oVo = list.get(index);
			orderDataList.add(oVo);
		}
	}
}
