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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
        tableSetup(table);
        ObservableList<Riding> data = getRidingData();
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

        sliderSetup(newDemocrats, ndpPercent);
        sliderSetup(wildRose, wildrosePercent);
        sliderSetup(progressiveConservatives, conPercent);
        sliderSetup(liberal, libPercent);
        sliderSetup(alberta, albertaPercent);
        sliderSetup(green, greenPercent);


        HBox nSlide = new HBox(newDemocrats,ndpPercent);
        HBox wSlide = new HBox(wildRose, wildrosePercent);
        HBox cSlide = new HBox(progressiveConservatives, conPercent);
        HBox lSlide = new HBox(liberal, libPercent);
        HBox aSlide = new HBox(alberta, albertaPercent);
        HBox gSlide = new HBox(green, greenPercent);
        
        VBox sliders1 = new VBox(ndpLabel, nSlide,wildroseLabel, wSlide, conservativeLabel, cSlide);
        VBox sliders2 = new VBox(liberalLabel, lSlide, albertaLabel, aSlide, greenLabel, gSlide);


        HBox sliderLayout = new HBox(sliders1, sliders2);
        VBox root = new VBox(sliderLayout, table);
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

    public static void sliderSetup(Slider slider, Label label) {
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(40);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setPrefWidth(250);
        
        slider.valueProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed (ObservableValue arg0, Object arg1, Object arg2)
            {
                label.setText(Double.toString(round(slider.getValue())) + "%");
            }
        });
        
        
    }
    public static void labelSetup(Label label)
    {
           label.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
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
    
    public static double round(double num)
    {
        double var; 
        var =  (double) Math.round(num*1000)/1000;
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
