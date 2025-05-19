import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class tempConverter extends Application{
    public void start(Stage stage){

        Label text=new Label("Enter the temperature in Celsius");
        Label ans=new Label();
        Label warning=new Label();
        TextField field=new TextField();
        field.setPromptText("Enter");
        field.setMaxWidth(200);
        Button b1=new Button("Enter");

        b1.setOnAction(event -> {
            String n1=field.getText();
            try{
                double a=Double.parseDouble(n1);
                double c = 0.0;

                c = (a - 32) / 1.8;
                ans.setText("Temperature is "+c+" F");

            }
            catch (NumberFormatException e){
                warning.setText("Enter valid Input");
                warning.setTextFill(Color.RED);
            }
        });
        VBox box=new VBox(10);
        box.getChildren().addAll(text,field,b1,ans);
        box.setAlignment(Pos.CENTER);
        Scene scene=new Scene(box,500,500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
