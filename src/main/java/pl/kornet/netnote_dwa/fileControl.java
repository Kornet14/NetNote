package pl.kornet.netnote_dwa;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class fileControl {
    boolean openStatus;
    String path;
    boolean ifExt;

    public void newFile(TextArea textArea){
        textArea.clear();
        openStatus = false;
    }
    public void openFile(TextArea t) throws IOException{
        mainApp mainapp = new mainApp();

        t.clear();
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);

        path = file.getAbsolutePath();
        openStatus = true;

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {
            String str;
            while((str = reader.readLine()) != null){
                t.appendText(str);
                t.appendText("\n");
            }
        }
        catch(IOException e){
            System.out.println("Error!");
            Alert wrongFileAlert = new Alert(Alert.AlertType.ERROR);
            wrongFileAlert.setTitle("Błąd");
            wrongFileAlert.setHeaderText("Nieobsługiwany format pliku!");
            wrongFileAlert.showAndWait();
        }
    }
    public void saveFile(TextArea t){
        if (openStatus){
            try{
                FileWriter fw = new FileWriter(path);
                fw.write(t.getText());
                fw.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());

            }
        }
        else if (!openStatus){
            saveAsFile(t);
        }
    }
    public void saveAsFile(TextArea t){
        ifExt = true;
        FileChooser fc = new FileChooser();
        fc.setInitialFileName("nowy plik");
        fc.setTitle("Zapisz jako");
        File saveFile = fc.showSaveDialog(null);

        //zapisywanie i wyskakiwanie okna dialogowego
        if(!saveFile.getName().contains(".")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie zapisano pliku");
            alert.setContentText("Brak rozszerzenia. Dopisz rozszerzenie do nazwy pliku");
            alert.show();
            ifExt = false;
        }

        if(ifExt == true) {
            try {
                FileWriter fw = new FileWriter(saveFile);
                fw.write(t.getText());
                fw.close();
            } catch (Exception ex) {
                System.out.println("Error!");
            }
        }
    }
}
