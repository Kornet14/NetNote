package pl.kornet.netnote_dwa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    TextArea textArea;
    @FXML
    ChoiceBox<String> cb_czcionka;
    @FXML
    ChoiceBox<String> cb_rozmiar;

    fileControl fileControlObj;
    Edit editObj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileControlObj = new fileControl();
        editObj = new Edit();
        //itemy choiceboxów
        cb_czcionka.getItems().addAll("Calibri", "Verdana", "Times New Roman", "Impact", "Comic Sans MS");
        cb_czcionka.getSelectionModel().select(0);

        cb_rozmiar.getItems().addAll("12", "14", "18", "22", "26", "32", "42", "48", "56", "64");
        cb_rozmiar.getSelectionModel().select(1);

        textArea.setFont(Font.font("Calibri", 14));

        cb_czcionka.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            String czcionkaRozmiar = cb_rozmiar.getSelectionModel().getSelectedItem();
            int cR = Integer.parseInt(czcionkaRozmiar);
            textArea.setFont(Font.font(cb_czcionka.getSelectionModel().getSelectedItem(), cR));
        }));

        cb_rozmiar.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            String czcionkaRozmiar = cb_rozmiar.getSelectionModel().getSelectedItem();
            int cR = Integer.parseInt(czcionkaRozmiar);
            textArea.setFont(Font.font(cb_czcionka.getSelectionModel().getSelectedItem(), cR));
        }));
    }
    public void newFileHandle(){
        fileControlObj.newFile(textArea);
    }
    public void openFileHandle() throws IOException {
        fileControlObj.openFile(textArea);
    }
    public void saveFileHandle(){
        fileControlObj.saveFile(textArea);
    }
    public void saveAsFileHandle(){
        fileControlObj.saveAsFile(textArea);
    }
    public void selectAllHandle(){
        editObj.selectAll(textArea);
    }
    public void copyHandle(){
        editObj.copy(textArea);
    }
    public void pasteHandle(){
        editObj.paste(textArea);
    }
    public void cutHandle(){
        editObj.cut(textArea);
    }
    public void undo(){
        textArea.undo();
    }
    public void redo(){
        textArea.redo();
    }
    public void aboutProgram() throws IOException{
        Stage aboutStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(mainApp.class.getResource("about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 366, 339);
        aboutStage.setTitle("O programie");
        aboutStage.setScene(scene);
        aboutStage.setResizable(false);
        aboutStage.show();
    }
}
