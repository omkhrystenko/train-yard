package com.OOP;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UI extends Application {

    private WagonTrain wagonTrain;
    private ListView<String> wagonChainView;
    private Label totals;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Let's assemble a train!");
        VBox rootPanel = new VBox(10);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().add(new Label("Select some railway car or locomotive:"));

        RailwayCarsDepot railwayCarsDepot = new RailwayCarsDepot();
        LocomotiveDepot locomotiveDepot = new LocomotiveDepot(railwayCarsDepot);
        wagonTrain = locomotiveDepot.get("Locomotive");
        wagonChainView = createView(wagonTrain);
        totals = new Label();

        rootPanel.getChildren().add(createAddPanel(locomotiveDepot, railwayCarsDepot));
        rootPanel.getChildren().add(wagonChainView);
        rootPanel.getChildren().add(totals);
        rootPanel.getChildren().add(createSortPanel());

        primaryStage.setScene(new Scene(rootPanel, 300, 500));
        Image image = new Image(getClass().getResourceAsStream("/Train.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setWidth(650);
        primaryStage.show();
        updateView(wagonTrain);
    }


    private Button createAddRailWayCarButton(String label, String id, RailwayCarsDepot railwayCarsDepot) {
        Button addButton = new Button();
        addButton.setText(label);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wagonTrain.add(railwayCarsDepot.get(id));
                updateView(wagonTrain);
            }
        });
        return addButton;
    }

   private Button createSetWagonTrainButton(String label, String id, LocomotiveDepot locomotiveDepot) {
        Button addButton = new Button();
        addButton.setText(label);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wagonTrain = locomotiveDepot.get(id);
                updateView(wagonTrain);
            }
        });
        return addButton;
    }


    private Button createSortButton (String label, String id) {
        Button sortButton = new Button();
        sortButton.setText(label);
        sortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sort(id);
            }
        });
        return sortButton;
    }

    private Button createSearchButton (String label) {
        Button searchButton = new Button();
        searchButton.setText(label);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                search();
                new UISearchResult().searchWindow();
            }

        });
        return searchButton;
    }

    private Pane createSortPanel() {
        HBox sortPanel = new HBox(10);
        sortPanel.getChildren().add(createSortButton("Sort by comfort","class of comfort"));
        sortPanel.getChildren().add(createSortButton("Sort by seats","seats number"));
        sortPanel.getChildren().add(createSearchButton("Search by seats"));
        return sortPanel;
    }

    private Pane createAddPanel(LocomotiveDepot locomotiveDepot, RailwayCarsDepot railwayCarsDepot) {
        HBox addPanel = new HBox(10);
        addPanel.getChildren().add(createSetWagonTrainButton("Assemble train", "Locomotive", locomotiveDepot));
        addPanel.getChildren().add(createAddRailWayCarButton("Attach Locomotive", "Locomotive", railwayCarsDepot));
        addPanel.getChildren().add(createAddRailWayCarButton("Attach Luxury", "Luxury", railwayCarsDepot));
        addPanel.getChildren().add(createAddRailWayCarButton("Attach Coupe", "Coupe", railwayCarsDepot));
        addPanel.getChildren().add(createAddRailWayCarButton("Attach Platskart", "Platskart", railwayCarsDepot));
        addPanel.getChildren().add(createAddRailWayCarButton("Attach Seated", "Seated", railwayCarsDepot));

        return addPanel;
    }

    private ListView<String> createView(WagonTrain wagonTrain) {
        ListView<String> wagonsView = new ListView<String>(renderWagonTrainToList(wagonTrain));
        wagonsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() >= 2) {
                    wagonsView.setItems(renderWagonTrainToList(wagonTrain));
                    updateView(wagonTrain);
                }
            }
        });
        return wagonsView;
    }

    private void updateView(WagonTrain wagonTrain) {
        wagonChainView.setItems(renderWagonTrainToList(wagonTrain));
        totals.setText(renderTotals(wagonTrain));
    }

    protected void updateView(List<RailwayCar> wagonChain) {
        wagonChainView.setItems(renderComponentsToList(wagonChain));
    }

    private String renderTotals(WagonTrain wagonTrain) {
        return "Total: " + wagonTrain.getSeatsNumber() + " seats; Luggage places:" + wagonTrain.getLuggagePlaces();
    }

    private ObservableList<String> renderWagonTrainToList(WagonTrain wagonTrain) {
        return renderComponentsToList(wagonTrain.getWagonsChain());
    }

    protected ObservableList<String> renderComponentsToList (List<RailwayCar> wagonChain) {
        ObservableList<String> wagonsList = FXCollections.observableArrayList();
        for (RailwayCar x: wagonChain) {
            String s = x.getWagonName() ;
            int s1 = x.getSeatsNumber() ;
            int s2 = x.getClassOfComfort() ;
            wagonsList.add(x.getWagonName() + " (" + x.getSeatsNumber() + " seats) - " +x.getClassOfComfort() + " class.");
        }
        return wagonsList;
    }


    private void sort (String id) {
        List<RailwayCar> sortedWagonTrain = wagonTrain.getWagonsChain();
        Collections.sort(sortedWagonTrain, WagonComparatorFactory.get(id));
        updateView(sortedWagonTrain);
    }

    private void search () {
        TextInputDialog td1 = new TextInputDialog("enter range number from");
        TextInputDialog td2 = new TextInputDialog("enter range number to");
        td1.setHeaderText("Search by range of seats");
        td1.showAndWait();
        td2.setHeaderText("Search by range of seats");
        td2.showAndWait();

        List<RailwayCar> wagonChain = wagonTrain.getWagonsChain();
        UISearchResult.wagonChain = sortDataInRange(Integer.valueOf(td1.getEditor().getText()), Integer.valueOf(td2.getEditor().getText()), wagonChain );
    }

    private List<RailwayCar> sortDataInRange (int from, int to, List<RailwayCar> wagonChain){
        List<RailwayCar> list = new ArrayList<>();
            for(RailwayCar railwayCar : wagonChain){
                if(railwayCar.getSeatsNumber() >= from && railwayCar.getSeatsNumber() <= to){
                    list.add(railwayCar);
                }
            }
        Collections.sort(list, WagonComparatorFactory.get("seats number"));

        return list;
    }


}
