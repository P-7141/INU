package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 400);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        Label label = new Label("Hello world");
        //root.setCenter(label);
        label.setLayoutX(0);
        label.setLayoutY(120);

        Label label1 = new Label("nr 1");
        Label label2 = new Label("nr 2");

        VBox leftVbox = new VBox(10);
        leftVbox.getChildren().add(label1);
        leftVbox.getChildren().add(label2);
        root.setLeft(leftVbox);

        //======================
        TextField text1 = new TextField();
        text1.getStyleClass().add("my-field");
        TextField text2 = new TextField();
        text2.setLayoutX(10);
        text2.setLayoutY(60);
        Group centerGroup = new Group();


        Image testImage = new Image("http://galera.ii.pw.edu.pl/~zsz/javafx/img/lenna256px.png");
        ImageView testImageView = new ImageView(testImage);
        centerGroup.getChildren().add(testImageView);
        centerGroup.getChildren().add(label);
        centerGroup.getChildren().addAll(text1, text2);

        root.setCenter(centerGroup);
        //===================================
        RadioButton radioButton1 = new RadioButton("1");
        radioButton1.setUserData("przycisk 1");
        RadioButton radioButton2 = new RadioButton("2");
        radioButton2.setUserData("przycisk 2");
        RadioButton radioButton3 = new RadioButton("3");
        radioButton3.setUserData("przycisk 3");

        HBox bottomHBox=new HBox(10);
        bottomHBox.getChildren().addAll(radioButton1,radioButton2, radioButton3);
        root.setBottom(bottomHBox);

        ToggleGroup tg = new ToggleGroup();
        radioButton1.setToggleGroup(tg);
        radioButton2.setToggleGroup(tg);
        radioButton3.setToggleGroup(tg);

        //===================================
        Button button1 = new Button("Kliknij!");
        Button button2 = new Button("nr 2");
        Button button3 = new Button("nr 3");
        HBox topHBox = new HBox(10);
        topHBox.getChildren().addAll(button1, button2, button3);
        root.setTop(topHBox);

        button1.setOnAction(actionEvent -> {
            System.out.println("Wcisnieto przycisk nr1!");
            Stage stage_dialog = new Stage();
            stage_dialog.setTitle("Okno dialogowe");
            BorderPane rootDialog = new BorderPane();
            stage_dialog.setScene(new Scene(rootDialog, 300, 100));
            Label msg = new Label("To jest okno dialogowe");
            rootDialog.setCenter(msg);
            stage_dialog.initOwner(primaryStage);
            stage_dialog.initModality(Modality.WINDOW_MODAL);
            stage_dialog.show();

        });

        button2.setOnAction(
                actionEvent -> {
                    System.out.println("Przycisk 2.");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Wcisnieto przycisk nr 2.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() &&
                            result.get()==ButtonType.OK)
                        System.out.println("OK");
                    //alert.show();
                }
        );

        button3.setOnAction(
                actionEvent -> {
                    if (text1.getText().matches("(JA|BD|DS [0-9]{2}[ZLzl][0-9]{2}[a-z])"))
                        System.out.println("OK");

                    if (radioButton1.isSelected())
                        System.out.println("Radio1 wybrany");

                    if (tg.getSelectedToggle()==radioButton2)
                        System.out.println("Radio2 wybrany");

                    if (tg.getSelectedToggle()!=null) {
                        System.out.println(
                                tg.getSelectedToggle().getUserData()
                        );
                    }
                });


        //primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
