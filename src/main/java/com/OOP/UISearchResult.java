package com.OOP;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;



public class UISearchResult extends UI{

    private static ListView<String> wagonChainView;
    static List<RailwayCar> wagonChain;


    public void searchWindow(){


        Stage primaryStage = new Stage();
        VBox rootPanel = new VBox(10);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().add(new Label("Search result:"));

        wagonChainView = createView(wagonChain);
        Label bottomPanel = new Label();
        rootPanel.getChildren().add(wagonChainView);
        rootPanel.getChildren().add(bottomPanel);
        primaryStage.setScene(new Scene(rootPanel, 300, 250));
        Image image = new Image(getClass().getResourceAsStream("/Search.png"));
        primaryStage.getIcons().add(image);
        primaryStage.setWidth(300);
        primaryStage.show();

    }


    private  ListView<String> createView(List<RailwayCar> wagonChain) {
        ListView<String> wagonsView = new ListView<String>( renderComponentsToList(wagonChain));
        wagonsView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() >= 2) {
                    wagonsView.setItems(renderComponentsToList(wagonChain));
                    updateView(wagonChain);
                }
            }
        });
        return wagonsView;
    }


}
