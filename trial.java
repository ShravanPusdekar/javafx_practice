import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class trial extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label label=new Label("Hello World");

        Button button=new Button("Dont Click me");
        button.setOnAction(e-> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Wtf"+(i+1));
            }
        });
        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,button);
        Scene scene =new Scene(layout,400,500);
        stage.setTitle("Shravan Pusdekar");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
