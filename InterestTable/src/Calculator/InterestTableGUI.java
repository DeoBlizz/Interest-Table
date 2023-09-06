package Calculator;

import java.text.NumberFormat;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.geometry.*;

public class InterestTableGUI extends Application{
	
	protected double principal, rate;
	private TextArea displayArea;
	protected int years;
	protected TextField principalField, rateField;
	
	public void start(Stage primaryStage) throws Exception{
		principal = 0;
		rate = 0;
		years = 0;
		
		Label principal = new Label("Principal:");
		principalField = new TextField();
		
		Label rate = new Label("Rate(Percentage):");
		rateField = new TextField();
		
		Button button1 = new Button("SimpleInterest");
		button1.setOnAction(new SimpleButtonHandler());
        
		Button button2 = new Button("CompoundInterest");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent b) {
				try {
	    			displayArea.clear();
	    			double principal = Double.parseDouble(principalField.getText());
	    			double rate = Double.parseDouble(rateField.getText());
	    			displayArea.appendText("Principal: " + principal + " Rate: " + rate + "\n" + "Year, Compound Interest Amount\n");
	    			for (int i = 1; i <= years; i++) {
	    				String value = NumberFormat.getCurrencyInstance().format(Interest.compoundInterest(principal, rate, i));
	    				displayArea.appendText(i + "-->" + value + "\n");
	    			}
	    		}catch(NumberFormatException e) {
	    			displayArea.setText("Enter only numbers");
	    		}
			}
		});
		
		Button button3 = new Button("BothInterests");
        button3.setOnAction(c -> {
        	try {
    			displayArea.clear();
    			this.principal = Double.parseDouble(principalField.getText());
    			this.rate = Double.parseDouble(rateField.getText());
    			displayArea.appendText("Principal: " + this.principal + " Rate: " + this.rate + "\n" + "Year, Simple Interest Amount, Compound Interest Amount\n");
    			for (int i = 1; i <= years; i++) {
    				String value1 = NumberFormat.getCurrencyInstance().format(Interest.simpleInterest(this.principal, this.rate, i));
    				String value2 = NumberFormat.getCurrencyInstance().format(Interest.compoundInterest(this.principal, this.rate, i));
    				displayArea.appendText(i + "-->" + value1 + "-->" + value2 + "\n");
    			}
    		}catch(NumberFormatException e) {
    			displayArea.setText("Enter only numbers");
    		}
        });
        
        displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(displayArea);

		Label year = new Label("Number of Years:");
		Slider yearSlider = new Slider();
		yearSlider.setMin(1);
		yearSlider.setMax(25);
		yearSlider.setValue(1);
		yearSlider.setMajorTickUnit(1);
		yearSlider.setShowTickMarks(true);
		yearSlider.setShowTickLabels(true);
		yearSlider.setBlockIncrement(1);
		yearSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldV, Number newV) {
                years = newV.intValue();
            }
        });
        
		BorderPane border = new BorderPane();
		border.setTop(scrollPane);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.BASELINE_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, principal, principalField, rate, rateField);
        grid.addRow(1, year, yearSlider);
        grid.addRow(2, button1, button2, button3);
        
        VBox v = new VBox();
        v.setSpacing(8);
        v.getChildren().addAll(border, grid);
		
        Scene scene = new Scene(v, 500, 400);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private void simpleInterest() {
		try {
			displayArea.clear();
			this.principal = Double.parseDouble(principalField.getText());
			this.rate = Double.parseDouble(rateField.getText());
			displayArea.appendText("Principal: " + this.principal + " Rate: " + this.rate + "\n" + "Year, Simple Interest Amount\n");
			for (int i = 1; i <= years; i++) {
				String value = NumberFormat.getCurrencyInstance().format(Interest.simpleInterest(principal, rate, i));
				displayArea.appendText(i + "-->" + value + "\n");
			}
		}catch(NumberFormatException e) {
			displayArea.setText("Enter only numbers");
		}
	}
	private class SimpleButtonHandler implements EventHandler<ActionEvent> {
	    @Override
	    public void handle(ActionEvent a) {
	        simpleInterest();
	    }
	}
}
