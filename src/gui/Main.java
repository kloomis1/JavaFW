package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;


public class Main extends Application {

    Stage window;
    Scene scene;
    Button button;
    ComboBox<String> comboBox;
    TreeView<String> tree;

    private boolean checker = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        window.setTitle("Algora Finance 1.0");
        /*
        button = new Button();
        button.setText("Create Chart");
        button.setOnAction(e -> {
            if (checker == false) {
                System.out.println("Processing input...");
                System.out.println("|==========       |");
                checker = true;
            }
        });
        */
        TreeItem<String> root, forex, finance, technology;
        root = new TreeItem<>();
        root.setExpanded(false);

        // Forex
        forex = makeBranch("Forex", root);
        makeBranch("Charts", forex);
        makeBranch("Research", forex);

        // Financial Services
        finance = makeBranch("Financial Services", root);
        makeBranch("Charts", finance);
        makeBranch("Research", finance);

        // Technology
        technology = makeBranch("Technology", root);
        makeBranch("Charts", technology);
        makeBranch("Research", technology);

        // Create tree
        tree = new TreeView<>(root);
        tree.setShowRoot(false);


        StackPane layout = new StackPane();
        //layout.getChildren().add(button);

        Scene scene = new Scene(layout, 600, 450);
        window.setScene(scene);
        window.show();
    }

    // Creates branches on each root
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);

        parent.getChildren().add(item);
        return item;
    }

    public static void main(String[] args) {
        launch(args);
    }


}





/*
public class Main extends Application {

    Stage window;
    Scene scene;
    Button button;
    ComboBox<String> comboBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        window = primaryStage;
        window.setTitle("Algora Finance - Charting");

        button = new Button("Create Chart");
        comboBox.getItems().addAll(
                "NVDA",
                "MU",
                "AMD",
                "CENT"
        );

        comboBox.setPromptText("Select stock ticker");

        // button.setOnAction(e -> printStock());

        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(comboBox,button);

        scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }

    private void printStock() {
        System.out.println(comboBox.getValue());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/











