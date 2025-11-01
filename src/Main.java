//Joey Barton
//Holds the JavaFX logic
//This program uses an ArrayStack to represent each hiking group and a
//LinkedListQueue to manage the groups waiting for each trail.
//ArrayStack was chosen for the hiking groups because there is a known maximum capacity for each group
//LinkedListQueue was chosen for each trail entrance because it is unknown how many groups will go to each trail.
//The LinkedList approach is more efficient with this unknown quantity.


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/PurpleMtn1.1.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("COULD NOT LOAD FXML: " + e.getMessage());
        }
    }
}
