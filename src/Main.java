import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {

    private TextField sideAtxt;
    private TextField sideBtxt;
    private TextField answer;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Right Triangle Calculator");
        GridPane grid = new GridPane();
        Label label1 = new Label("Enter side A: ");
        sideAtxt = new TextField();
        Label label2 = new Label("Enter side B: ");
        sideBtxt = new TextField();
        Label label3 = new Label("The hypotenuse is: ");
        answer = new TextField();
        answer.setEditable(false); // Make the answer field read-only
        Button calculate = new Button("Calculate");
        Button exit = new Button("Exit");
        grid.add(label1, 0, 0);
        grid.add(sideAtxt, 1, 0);
        grid.add(label2, 0, 1);
        grid.add(sideBtxt, 1, 1);
        grid.add(label3, 0, 3);
        grid.add(answer, 1, 3);
        calculate.setOnAction(event -> calculateClicked());
        exit.setOnAction(event -> exitClicked());
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().add(calculate);
        buttonBox.getChildren().add(exit);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(buttonBox, 1, 4, 2, 1);
        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Complete the calculation process
    private void calculateClicked() {
        Validation v = new Validation();
        String errorMsg = "";
        errorMsg += v.isDouble(sideAtxt.getText(), "Side A");
        errorMsg += v.isDouble(sideBtxt.getText(), "Side B");

        if (errorMsg.isEmpty()) {
            try {
                double sideA = Double.parseDouble(sideAtxt.getText());
                double sideB = Double.parseDouble(sideBtxt.getText());
                double hypotenuse = Math.hypot(sideA, sideB);
                String formattedHypot = String.format("%.3f", hypotenuse);
                answer.setText(formattedHypot);
            } catch (NumberFormatException e) {
                showAlert("Calculation Error", "An error occurred during the calculation. Please check your input.");
            }
        } else {
            showAlert("Invalid Entry", errorMsg);
        }
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Exit button
    private void exitClicked() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
