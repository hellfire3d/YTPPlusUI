package ytpplusui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class FXMLController {
// <editor-fold defaultstate="collapsed" desc="idk how to neaten this so heres a fold to make it neater">
    @FXML
    private MediaView mediaviewVideoPlayer;

    @FXML
    private CheckBox cbEffect1;

    @FXML
    private CheckBox cbEffect2;

    @FXML
    private CheckBox cbEffect3;

    @FXML
    private CheckBox cbEffect4;

    @FXML
    private CheckBox cbEffect5;

    @FXML
    private CheckBox cbEffect6;

    @FXML
    private CheckBox cbEffect7;

    @FXML
    private CheckBox cbEffect8;

    @FXML
    private CheckBox cbEffect9;

    @FXML
    private CheckBox cbEffect10;

    @FXML
    private CheckBox cbEffect11;

    @FXML
    private Button btnCreate;

    @FXML
    private TextField tfClipCount;

    @FXML
    private TextField tfMaxStream;

    @FXML
    private TextField tfMinStream;

    @FXML
    private ProgressBar barProgress;

    @FXML
    private Button btnPlayVideo;

    @FXML
    private Button btnPauseVideo;

    @FXML
    private Button restartVideo;

    @FXML
    private TextField tfFFMPEG;

    @FXML
    private TextField tfFFPROBE;

    @FXML
    private TextField tfMAGICK;

    @FXML
    private TextField tfTEMP;

    @FXML
    private TextField tfSOUNDS;

    @FXML
    private TextField tfMUSIC;

    @FXML
    private TextField tfRESOURCES;

    @FXML
    private Button btnBrowseFFMPEG;

    @FXML
    private Button btnBrowseFFPROBE;

    @FXML
    private Button btnBrowseMAGICK;

    @FXML
    private Button btnBrowseTEMP;

    @FXML
    private Button btnBrowseSOUNDS;

    @FXML
    private Button btnBrowseMUSIC;

    @FXML
    private Button btnBrowseRESOURCES;

    @FXML
    private Button btnHelpMeConfig;

    @FXML
    private ListView<?> listviewSourcesList;

    @FXML
    private TextField tfSOURCES;

    @FXML
    private Button btnBrowseSOURCES;

    @FXML
    private CheckBox cbUseTransitions;

    @FXML
    private Button btnSaveAs;

    @FXML
    private Button btnAddSource;

    @FXML
    private Button btnRemoveSource;
// </editor-fold>
    
    //javafx sucks. It's got a lot under the hood but it sucks.
    //This is incredibly messy. And I can't fix it because javafx sucks.
    //Moral of this story is don't use javafx. Swing is your friend.
    
    @FXML
    void addSource(ActionEvent event) {

    }

    @FXML
    void goNow(ActionEvent event) {

    }

    @FXML
    void helpMeConfig(ActionEvent event) {
        alert("These boxes provide the paths to the executables\n"
                + "which will be run throughout the batch process.\n\n"
                + "If you're on linux or OSX, \"magick\" should be empty.\n\n"
                + "The only reason it is here is because on Windows, most\n"
                + "ImageMagick tools are called from commandline by using\n"
                + "\"magick convert...\", while on other operating systems,\n"
                + "magick commands can be called simply by saying \"convert...\".\n\n"
                + "If you have separate installations of these tools, feel\n"
                + "free to change what's in these boxes to suit your fancy.\n\n"
                + "If you have no idea what any of this shit means, leave it be."
                );

    }

    @FXML
    void openBrowser(ActionEvent event) {
        switch (((Control)event.getSource()).getId()) {
            case "btnBrowseFFMPEG":
                actuallyOpenBrowser(tfFFMPEG);
                break;
            case "btnBrowseFFPROBE":
                actuallyOpenBrowser(tfFFPROBE);
                break;
            case "btnBrowseMAGICK":
                actuallyOpenBrowser(tfMAGICK);
                break;
            case "btnBrowseTEMP":
                actuallyOpenDirBrowser(tfTEMP);
                break;
                
        }
    }
    
    public File LAST_BROWSED;
    
    void actuallyOpenBrowser(TextField toChange) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        File selected = fileChooser.showOpenDialog(null);
        if (selected==null) return;
        toChange.setText(selected.getAbsolutePath());
        LAST_BROWSED = selected.getParentFile();
    }
    
    void actuallyOpenDirBrowser(TextField toChange) {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        File selected = fileChooser.showDialog(null);
        if (selected==null) return;
        toChange.setText(selected.getAbsolutePath());
        LAST_BROWSED = selected.getParentFile();
    }

    @FXML
    void pauseTheVideo(ActionEvent event) {

    }

    @FXML
    void playTheVideo(ActionEvent event) {

    }

    @FXML
    void removeSource(ActionEvent event) {

    }

    @FXML
    void restartTheVideo(ActionEvent event) {

    }

    @FXML
    void saveAsVideo(ActionEvent event) {

    }
    
    void alert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Yo");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }

}
