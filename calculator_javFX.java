import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class calculator_javFX extends Application {
    public void start(Stage stage) {

        Label messageLabel = new Label("Enter Two Numbers");

        Image icon=new Image("calculator.png");

        TextField field1 = new TextField();
        field1.setPromptText("First Number");
        TextField field2 = new TextField();
        field2.setPromptText("Second Number");

        HBox inputBox = new HBox(10, field1, field2);
        inputBox.setAlignment(Pos.CENTER);


        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Label resultLabel = new Label("Result will be shown here");


        Button add = new Button("Add");
        Button sub = new Button("Subtract");
        Button mul = new Button("Multiply");
        Button div = new Button("Divide");

        HBox buttonBox = new HBox(10, add, sub, mul, div);
        buttonBox.setAlignment(Pos.CENTER);


        add.setOnAction(e -> calculate(field1, field2, resultLabel, errorLabel, "+"));
        sub.setOnAction(e -> calculate(field1, field2, resultLabel, errorLabel, "-"));
        mul.setOnAction(e -> calculate(field1, field2, resultLabel, errorLabel, "*"));
        div.setOnAction(e -> calculate(field1, field2, resultLabel, errorLabel, "/"));


        VBox root = new VBox(15, messageLabel, inputBox, errorLabel, buttonBox, resultLabel);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 450, 300);
        root.setStyle("-fx-background-color:#f0f0f0");
        stage.getIcons().addAll(icon);
        stage.setTitle("Clean Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void calculate(TextField f1, TextField f2, Label resultLabel, Label errorLabel, String op) {
        String num1 = f1.getText();
        String num2 = f2.getText();
        errorLabel.setText("");

        try {
            int a = Integer.parseInt(num1);
            int b = Integer.parseInt(num2);
            int result = 0;

            switch (op) {
                case "+": result = a + b; break;
                case "-": result = a - b; break;
                case "*": result = a * b; break;
                case "/":
                    if (b == 0) {
                        errorLabel.setText("Cannot divide by zero!");
                        return;
                    }
                    result = a / b;
                    break;
            }

            resultLabel.setText("Result is: " + result);
        } catch (NumberFormatException e) {
            errorLabel.setText("Please enter valid numbers!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
