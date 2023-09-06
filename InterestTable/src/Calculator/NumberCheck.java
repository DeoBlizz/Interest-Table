package Calculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NumberCheck implements EventHandler<ActionEvent>{
	
	private Label myLabel;
	private TextField myTextField;
	int field;

	@Override
	public void handle(ActionEvent a) {
		try {
			field = Integer.parseInt(myTextField.getText());
		}catch(NumberFormatException e) {
			myLabel.setText("Enter only numbers");
		}catch(Exception e) {
			myLabel.setText("ERROR");
		}
	}
}
