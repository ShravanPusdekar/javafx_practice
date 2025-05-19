import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class volumeControl extends Application {
    public void start(Stage stage){

        Label value=new Label();
        Label warning=new Label();
        Slider slider=new Slider(0,100,0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(5);
        slider.setMaxWidth(200);
        slider.valueProperty().addListener((obs, oldVal, newVal) ->{
            value.setText("Value: "+newVal.intValue());
            if (newVal.intValue()>80){
                warning.setText("High Volume!");
                warning.setTextFill(Color.RED);
            }
            else {
                warning.setText("");
            }
        } );

        value.setText("Value: "+(int)slider.getValue());
        VBox box=new VBox(10);
        box.getChildren().addAll(slider,value,warning);
        box.setAlignment(Pos.CENTER);
        Scene scene=new Scene(box,500,500);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
