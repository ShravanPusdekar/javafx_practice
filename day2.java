import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class day2 extends Application {
    public void start(Stage stage) {
        ProgressBar progressBar = new ProgressBar(0);  // start at 0%
        Button button = new Button("Increase Progress");

        button.setOnAction(e -> {
            double progress = progressBar.getProgress();
            if (progress < 1.0) {
                progressBar.setProgress(progress + 0.1);
            }
        });

        VBox root = new VBox(10, progressBar, button);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 100);
        stage.setScene(scene);
        stage.setTitle("ProgressBar Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
