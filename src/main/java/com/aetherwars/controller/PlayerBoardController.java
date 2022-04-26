package com.aetherwars.controller;

import java.net.URL;
        import java.util.ResourceBundle;

import com.aetherwars.AetherWars;
import com.aetherwars.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
        import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
        import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PlayerBoardController extends StackPane {

    private BaseGameController baseGameController;
    private Player player;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ProgressBar playerHealth;

    @FXML
    private Label playerName;

    @FXML
    private GridPane playerBoard;

    @FXML
    private ImageView playerImage;

    @FXML
    void initialize() {
        assert playerHealth != null : "fx:id=\"playerHealth\" was not injected: check your FXML file 'PlayerBoard.fxml'.";
        assert playerName != null : "fx:id=\"playerName\" was not injected: check your FXML file 'PlayerBoard.fxml'.";
        assert playerBoard != null : "fx:id=\"playerBoard\" was not injected: check your FXML file 'PlayerBoard.fxml'.";
        assert playerImage != null : "fx:id=\"playerImage\" was not injected: check your FXML file 'PlayerBoard.fxml'.";

    }

    public PlayerBoardController(BaseGameController baseGameController, Player player) {
        FXMLLoader boardLoader = new FXMLLoader(AetherWars.class.getResource("gui/PlayerBoard.fxml"));
        boardLoader.setRoot(this);
        boardLoader.setController(this);
        this.player = player;
        this.baseGameController = baseGameController;
        try {
            boardLoader.load();

            this.initPlayerBoard();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void initPlayerBoard() {
        Image playerImg;
        this.playerHealth.setProgress(Double.valueOf(this.player.getHealth())/80);

        if (this.player.getName().equals("Player One")) {
            playerImg = new Image(AetherWars.class.getResource("card/image/playerImage1.png").toString());
        }
        else {
            playerImg = new Image(AetherWars.class.getResource("card/image/playerImage2.png").toString());
        }

        this.playerImage.setImage(playerImg);
        this.playerName.setText(this.player.getName());
        System.out.println(this.player.toString());
    }
}
