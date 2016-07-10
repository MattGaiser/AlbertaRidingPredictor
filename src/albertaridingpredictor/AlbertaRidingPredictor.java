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
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Matthew
 */
public class AlbertaRidingPredictor extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView table = new TableView();
        tableSetup(table);
        ObservableList<Riding> data = getRidingData();
        table.setItems(data);

        Slider newDemocrats = new Slider();
        Slider wildRose = new Slider();
        Slider progressiveConservatives = new Slider();
        Slider liberal = new Slider();
        Slider alberta = new Slider();
        Slider green = new Slider();

        sliderSetup(newDemocrats);
        sliderSetup(wildRose);
        sliderSetup(progressiveConservatives);
        sliderSetup(liberal);
        sliderSetup(alberta);
        sliderSetup(green);

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        VBox sliders1 = new VBox();
        sliders1.getChildren().addAll(newDemocrats, wildRose, progressiveConservatives);
        VBox sliders2 = new VBox();
        sliders2.getChildren().addAll(liberal, alberta, green);

        HBox middle = new HBox();
        middle.getChildren().addAll(sliders1, sliders2);

        VBox root = new VBox();
        root.getChildren().addAll(middle, table);
        Scene scene = new Scene(root);

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

    public static void sliderSetup(Slider slider) {
        slider.setMin(0);
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }

    public static void tableSetup(TableView table) {
        TableColumn edNum = new TableColumn("Electoral District");
        TableColumn lib = new TableColumn("Liberal");
        TableColumn ap = new TableColumn("Alberta");
        TableColumn gpa = new TableColumn("Green");
        TableColumn ndp = new TableColumn("New Democrats");
        TableColumn pc = new TableColumn("Conservatives");
        TableColumn wra = new TableColumn("Wildrose");
        TableColumn turnout = new TableColumn("Turnout");
        TableColumn winner = new TableColumn("Winner");
        
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
        


        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(edNum, lib, ap, gpa, ndp, pc, wra, winner);
    }
    
    public static int proNum(String number)
    {
        number = number.replace(",", "");
        number = number.replace("\"", "");
        
        if (number.contentEquals(""))
        {
            return 0;
        }
        
        return Integer.parseInt(number);
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
                
                if (splitLine.length > 13&&!splitLine[0].contains("ED")&&!splitLine[0].contains("Official"))
                {
                    riding = new Riding(splitLine[1], proNum(splitLine[4]), proNum(splitLine[5]), proNum(splitLine[8]),proNum(splitLine[9])
                            ,proNum(splitLine[10]),proNum(splitLine[11]));
                    System.out.println(riding.getWra());
                    ridings.add(riding);

                }
                
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    
        return ridings;
    }
}
