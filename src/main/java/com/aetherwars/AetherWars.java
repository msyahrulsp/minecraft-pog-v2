package com.aetherwars;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.aetherwars.model.Type;
import com.aetherwars.model.Character;
import com.aetherwars.util.CSVReader;

public class AetherWars extends Application {
//  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

// public void loadCards() throws IOException, URISyntaxException {
//   File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
//   CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
//   characterReader.setSkipHeader(true);
//   List<String[]> characterRows = characterReader.read();
////   for (String[] row : characterRows) {
//////      Character c = new Character(row[1], row[3], Type.valueOf(row[2]));
////     System.out.println(row);
////   }
// }

 @Override
 public void start(Stage stage) throws Exception {
   FXMLLoader gameLoader = new FXMLLoader(AetherWars.class.getResource("gui/BaseGame.fxml"));
   BorderPane baseGame = gameLoader.load();

   Scene scene = new Scene(baseGame, 1280, 720);

   stage.setTitle("Minecraft: Aether Wars");
   stage.setScene(scene);
   stage.show();

//   try {
//     this.loadCards();
//   } catch (Exception e) {
//       System.out.println(e);
//   }
 }

 public static void main(String[] args) {
   launch(args);
 }
}
