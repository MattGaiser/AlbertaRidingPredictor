/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albertaridingpredictor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Matthew
 */
public class AlbertaRidingPredictor extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView table = new TableView();
        ObservableList<Riding> data = getRidingData();
        tableSetup(table);
        table.setItems(data);

        Label ndpLabel = new Label("New Democrats");
        ndpLabel.setTextFill(Color.ORANGE);
        labelSetup(ndpLabel);

        Label ndpPercent = new Label();
        labelSetup(ndpPercent);

        Label wildroseLabel = new Label("Wildrose");
        wildroseLabel.setTextFill(Color.DARKGREEN);
        labelSetup(wildroseLabel);

        Label wildrosePercent = new Label();
        labelSetup(wildrosePercent);

        Label conservativeLabel = new Label("Conservatives");
        conservativeLabel.setTextFill(Color.BLUE);
        labelSetup(conservativeLabel);

        Label conPercent = new Label();
        labelSetup(conPercent);

        Label liberalLabel = new Label("Liberals");
        liberalLabel.setTextFill(Color.web("#C70D22"));
        labelSetup(liberalLabel);

        Label libPercent = new Label();
        labelSetup(libPercent);

        Label albertaLabel = new Label("Alberta Party");
        albertaLabel.setTextFill(Color.CYAN);
        labelSetup(albertaLabel);

        Label albertaPercent = new Label();
        labelSetup(albertaPercent);

        Label greenLabel = new Label("Green Party");
        greenLabel.setTextFill(Color.GREEN);
        labelSetup(greenLabel);

        Label greenPercent = new Label();
        labelSetup(greenPercent);

        Slider newDemocrats = new Slider();
        Slider wildRose = new Slider();
        Slider progressiveConservatives = new Slider();
        Slider liberal = new Slider();
        Slider alberta = new Slider();
        Slider green = new Slider();

        sliderSetup(newDemocrats, ndpPercent, 40.6);
        sliderSetup(wildRose, wildrosePercent, 24.2);
        sliderSetup(progressiveConservatives, conPercent, 27.8);
        sliderSetup(liberal, libPercent, 4.2);
        sliderSetup(alberta, albertaPercent, 2.2);
        sliderSetup(green, greenPercent, 0.5);

        HBox nSlide = new HBox(newDemocrats, ndpPercent);
        HBox wSlide = new HBox(wildRose, wildrosePercent);
        HBox cSlide = new HBox(progressiveConservatives, conPercent);
        HBox lSlide = new HBox(liberal, libPercent);
        HBox aSlide = new HBox(alberta, albertaPercent);
        HBox gSlide = new HBox(green, greenPercent);

        VBox sliders1 = new VBox(ndpLabel, nSlide, wildroseLabel, wSlide, conservativeLabel, cSlide);
        VBox sliders2 = new VBox(liberalLabel, lSlide, albertaLabel, aSlide, greenLabel, gSlide);

        HBox sliderLayout = new HBox(sliders1, sliders2);
        sliderLayout.setAlignment(Pos.CENTER);
        VBox root = new VBox(sliderLayout, table);
        Scene scene = new Scene(root);
        scene.getStylesheets().clear();

        URL url = this.getClass().getResource("style.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        getRidingData();
        primaryStage.setTitle("Alberta Vote Prediction");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void sliderSetup(Slider slider, Label label, double initialValue) {
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(initialValue);
        label.setText(initialValue + "%");
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setPrefWidth(250);

        slider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                label.setText(Double.toString(round(slider.getValue())) + "%");
            }
        });

    }

    public static void labelSetup(Label label) {
        label.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    }

    public static void tableSetup(TableView table) {
        TableColumn edNum = new TableColumn("Electoral District");
        TableColumn lib = new TableColumn("Liberal");
        lib.getStyleClass().add("lib");
        TableColumn ap = new TableColumn("Alberta");
        ap.getStyleClass().add("ap");
        TableColumn gpa = new TableColumn("Green");
        gpa.getStyleClass().add("gpa");
        TableColumn ndp = new TableColumn("New Democrats");
        ndp.getStyleClass().add("ndp");
        TableColumn pc = new TableColumn("Conservatives");
        pc.getStyleClass().add("pc");
        TableColumn wra = new TableColumn("Wildrose");
        wra.getStyleClass().add("wra");
        TableColumn turnout = new TableColumn("Turnout");
        TableColumn winner1 = new TableColumn("Winner");

        edNum.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        lib.setCellValueFactory(
                new PropertyValueFactory<>("lib")
        );
        ap.setCellValueFactory(
                new PropertyValueFactory<>("ap")
        );
        ndp.setCellValueFactory(
                new PropertyValueFactory<>("ndp")
        );
        gpa.setCellValueFactory(
                new PropertyValueFactory<>("gpa")
        );
        pc.setCellValueFactory(
                new PropertyValueFactory<>("pc")
        );
        wra.setCellValueFactory(
                new PropertyValueFactory<>("wra")
        );

        winner1.setCellValueFactory(
                new PropertyValueFactory<>("winnerOfRiding")
        );

        /* winner1.setCellFactory(column
                -> {
            return new TableCell<Riding, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (item == null) {
                        setText(null);
                        setStyle("");
                    } else if (item.equalsIgnoreCase("LIBERAL")) {
                        setTextFill(Color.RED);
                    } else if (item.equalsIgnoreCase("ALBERTA")) {
                        setTextFill(Color.RED);
                    } else if (item.equalsIgnoreCase("NEW DEMOCRATS")) {
                        setTextFill(Color.RED);
                    } else if (item.equalsIgnoreCase("GREEN")) {
                        setTextFill(Color.RED);
                    } else if (item.equalsIgnoreCase("CONSERVATIVES")) {
                        setTextFill(Color.RED);
                    } else if (item.equalsIgnoreCase("WILDROSE")) {
                        setTextFill(Color.RED);
                    }
                }
            };
        });
         */
        table.setPrefWidth(1000);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(edNum, lib, ap, gpa, ndp, pc, wra, winner1);
    }

    public static int proNum(String number) {
        number = number.replace(",", "");
        number = number.replace("\"", "");

        if (number.contentEquals("")) {
            return 0;
        }

        return Integer.parseInt(number);
    }

    public static double round(double num) {
        double var;
        var = (double) Math.round(num * 1000) / 1000;
        return var;
    }

    public static ObservableList getRidingData() {
        ObservableList<Riding> ridings = FXCollections.observableArrayList();
        BufferedReader reader = null;
        String line = "";
        String[] splitLine;
        String address = "C:\\Users\\Matthew\\Documents\\ORPROV2015.csv";
        Riding riding;

        try {
            reader = new BufferedReader(new FileReader(address));
            while ((line = reader.readLine()) != null) {
                splitLine = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if (splitLine.length > 13 && !splitLine[0].contains("ED") && !splitLine[0].contains("Official")) {
                    riding = new Riding(splitLine[1], proNum(splitLine[4]), proNum(splitLine[5]), proNum(splitLine[8]), proNum(splitLine[9]), proNum(splitLine[10]), proNum(splitLine[11]));
                    ridings.add(riding);

                }

                //System.out.println(line);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return ridings;
    }
}
