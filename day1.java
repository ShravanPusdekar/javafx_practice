import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class day1 extends Application {
    int count=0;

    public void start(Stage stage){

        Image image =new Image("luffy.jpeg");

        Button button1=new Button("Increaser");
        Button button2=new Button("Decreaser");

        Label label=new Label("Count is "+ count);
        Label error=new Label();
        Label message=new Label();

        TextField text =new TextField();
        TextArea area=new TextArea();

        message.setText("Enter a Number");
        text.setPromptText("Enter Number");
        button1.setOnAction(e -> {
            String inputText=text.getText();
            try {
                int integer=Integer.parseInt(inputText);
                count+=integer;
                label.setText("Count is "+count);
                error.setText("");
                text.clear();
            }
            catch (NumberFormatException exception){
                error.setText("Please Enter a Valid Number");
            }
        });
        button2.setOnAction(e -> {
            String inputText=text.getText();
            try {
                int integer=Integer.parseInt(inputText);
                count-=integer;

                label.setText("Count is "+count);
                error.setText("");
                text.clear();
            }
            catch (NumberFormatException exception){
                error.setText("Please Enter a Valid Number");
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(button1, button2);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(15);
        root.getChildren().addAll(message, text, error, label, buttonBox);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 500, 500);
        scene.setFill(Color.HOTPINK);
        scene.setFill(Color.BLACK);
        stage.setTitle("Increaser and Decreaser");
        stage.getIcons().addAll(image);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
