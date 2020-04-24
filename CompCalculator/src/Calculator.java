import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Calculator extends Application implements Initializable {
	@FXML
	Button b1;
	@FXML
	Button b2;
	@FXML
	Button b3;
	@FXML
	Button b4;
	@FXML
	Button b5;
	@FXML
	Button b6;
	@FXML
	Button b7;
	@FXML
	Button b8;
	@FXML
	Button b9;
	@FXML
	Button b0;
	@FXML
	Button bequals;
	@FXML
	Button bdec;
	@FXML
	Button bder;
	@FXML
	Button bint;
	@FXML
	Button bplus;
	@FXML
	Button bsub;
	@FXML
	Button btimes;
	@FXML
	Button bdiv;
	@FXML
	Button bans;
	@FXML
	Button bfun;
	@FXML
	Button bpow;
	@FXML
	Button banti;
	@FXML
	Button bclear;
	@FXML
	Button bnext;
	@FXML
	TextField numberId;
	
	private String text=new String();
	private ArrayList<String>numbers = new ArrayList<String>();
	private String operations = "";
	private String currentNumber = "";
	private boolean der = false;
	private boolean anti = false;
	private boolean integral = false;
	private boolean limits = false;
	private String[] coefficients = new String[6];
	private String[] endPoints = new String[2];
	private int counter = 0;
	private int endCounter = 0;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/Calculator.fxml"));
		final Pane p = loader.load();

		primaryStage.setScene(new Scene(p));
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("1");
			}
		});
		b2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("2");
			}
		});
		b3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("3");
			}
		});
		b4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("4");
			}
		});
		b5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("5");
			}
		});
		b6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("6");
			}
		});
		b7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("7");
			}
		});
		b8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("8");
			}
		});
		b9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("9");
			}
		});
		b0.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("0");
			}
		});
		bdec.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput(".");
			}
		});
		bplus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("+");
			}
		});
		bsub.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("-");
			}
		});
		btimes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("*");
			}
		});
		bdiv.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("/");
			}
		});
		bpow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("^");
			}
		});
		bder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				der = true;
				numberId.setText(String.format("%sx^5 + %sx^4 + %sx^3 + %sx^2 + %sx + %s", coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4], coefficients[5], coefficients[6]));
			}
		});
		bequals.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleInput("=");
			}
		});
		bnext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				if (integral && counter == 5) {
					limits = true;
					integral = false;
				} else if ((integral || anti || der) && counter != 5) {
					counter += 1;
				} else if (limits && endCounter != 1) {
					endCounter += 1;
				}
				
			}
		});
		bclear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				text=new String();
				numbers = new ArrayList<String>();
				operations = "";
				currentNumber = "";
				der = false;
				anti = false;
				integral = false;
				limits = false;
				coefficients = new String[6];
				endPoints = new String[2];
				counter = 0;
				endCounter = 0;
			}
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void handleInput(String c) {
		
		if (text.length() <= 20) {
			if (der || anti || integral) {
				if (!(c.equals("+") || c.equals("*") || c.equals("/") || c.equals("^"))) {
					coefficients[counter] += c;
				}
			} else if (limits) {
				if (!(c.equals("+") || c.equals("*") || c.equals("/") || c.equals("^") || c.equals("-")) || (endPoints[endCounter].length() == 0 && c.equals("-"))) {
					endPoints[endCounter] += c;
				}
			} else if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^")) {
				text += c;
				operations += c;
				numbers.add(currentNumber);
				currentNumber = "";
			} else if (c.equals("=")) {
				if (der) {
					text = Operations.derivative(coefficients);
				} else if (limits) {
					text = Operations.integral(coefficients, endPoints);
				} else if (anti) {
					text = Operations.antiderivative(coefficients);
				} else {
					text = Operations.solve(numbers, operations);
				}
			} else {
				currentNumber += c;
				text += c;
			}
			
			refreshScreen();
		}
		
	}
	
	public void refreshScreen() {
		
		if (der || integral || anti) {
			text = String.format("%sx^5 + %sx^4 + %sx^3 + %sx^2 + %sx + %s", coefficients[0], coefficients[1], coefficients[2], coefficients[3], coefficients[4], coefficients[5], coefficients[6]);
		} else if (limits) {
			text = String.format("Start point: %s   End Point: %s", endPoints[0], endPoints[1]);
		}
		numberId.setText(text);
		
	}
}