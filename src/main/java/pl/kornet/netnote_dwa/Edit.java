package pl.kornet.netnote_dwa;

import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class Edit {
    Clipboard clp;
    ClipboardContent cnt;

    public void selectAll(TextArea t){
        t.selectAll();
    }
    public void copy(TextArea t){
        clp = Clipboard.getSystemClipboard();
        cnt = new ClipboardContent();
        cnt.putString(t.getSelectedText());
        clp.setContent(cnt);
    }
    public void paste(TextArea t){
        t.deselect();
        t.paste();
    }
    public void cut(TextArea t){
        cnt = new ClipboardContent();
        cnt.putString(t.getSelectedText());
        clp.setContent(cnt);
        t.replaceText(t.getSelection(), "");
    }
}
