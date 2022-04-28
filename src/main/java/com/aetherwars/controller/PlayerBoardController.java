package com.aetherwars.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import com.aetherwars.AetherWars;
import com.aetherwars.model.Character;
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
    private Boolean[] isFilledSlot;
    private Character clickedCard;
    private BoardCardController clickedCardController;

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

    public PlayerBoardController(BaseGameController baseGameController, Player player) {
        FXMLLoader boardLoader = new FXMLLoader(AetherWars.class.getResource("gui/PlayerBoard.fxml"));
        boardLoader.setRoot(this);
        boardLoader.setController(this);
        this.player = player;
        this.baseGameController = baseGameController;
        this.isFilledSlot = new Boolean[]{false,false,false,false,false};
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

    public GridPane getPlayerBoard() {
        return this.playerBoard;
    }

    public Boolean[] isFilledSlot() {
        return this.isFilledSlot;
    }

    public void setClickedCard(Character card) {
        this.clickedCard = card;
    }

    public Character getClickedCard() {
        return this.clickedCard;
    }

    public void setClickedCardController(BoardCardController boardCardController) {
        this.clickedCardController = boardCardController;
    }

    public BoardCardController getClickedCardController() {
        return this.clickedCardController;
    }
}
