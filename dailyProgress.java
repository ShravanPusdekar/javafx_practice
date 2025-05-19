import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class dailyProgress extends Application {
    double progress=0;
    public void start(Stage stage){

        ProgressBar bar=new ProgressBar(0);
        Button doTask =new Button("Do Task");

        doTask.setOnAction(event -> {
            if (progress<1.0){
                progress+=0.1;
                bar.setProgress(progress);
            }
            if (progress>=1.0){
                doTask.setText("All Tasks Done!");
                doTask.setTextFill(Color.RED);
                doTask.setDisable(true);
            }
        });


        VBox box=new VBox();
        box.getChildren().addAll(bar,doTask);
        box.setAlignment(Pos.CENTER);
        Scene scene=new Scene(box,500,500);

        stage.setScene(scene);
        stage.show();
    }
}
