package ytpplusui;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.swing.DefaultListModel;
import zone.arctic.ytpplus.YTPGenerator;

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
    private ListView<String> listviewSourcesList;

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
    
    ObservableList<String> sourceList = FXCollections.observableArrayList();
    
    String TEMP = "temp/";
    
    @FXML
    void addSource(ActionEvent event) {
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileFilter);
        fileChooser.setTitle("Choose Source");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        File selected = fileChooser.showOpenDialog(null);
        if (selected==null) return;
        sourceList.add(selected.getAbsolutePath().replace('\\', '/'));
        listviewSourcesList.setItems(sourceList);
        
    }

    @FXML
    void goNow(ActionEvent event) {
        if (sourceList.isEmpty()) {
            alert("You need some sources...");
            return;
        }
        YTPGenerator generator = new YTPGenerator(TEMP + "tempoutput.mp4");
        generator.toolBox.FFMPEG = "";
    }

    @FXML
    void helpMeConfig(ActionEvent event) {
        alert("These boxes provide the paths to the executables "
                + "which will be run throughout the batch process.\n\n"
                + "If you're on linux or OSX, \"magick\" should be empty.\n\n"
                + "The only reason it is here is because on Windows, most "
                + "ImageMagick tools are called from commandline by using "
                + "\"magick convert...\", while on other operating systems, "
                + "magick commands can be called simply by saying \"convert...\".\n\n"
                + "If you have separate installations of these tools, feel "
                + "free to change what's in these boxes to suit your fancy.\n\n"
                + "If you have no idea what any of this shit means, leave it be."
                );

    }
    
    @FXML
    void helpMeEffect(ActionEvent event) {
        alert("Currently, these effects are based on a switch statement, and each effect has an equal chance of appearing, which means if you "
                + "turn one of them off, there will be more unedited clips. Additionally, there is a 1/2 chance of there even being an effect on a clip. "
                + "You do the math. There's 11 effects. 1/2 chance of each effect occuring. That means, regardless of being turned on or off, "
                + "each effect has a 1/22 chance of occuring. Pretty nasty, right? I'll add sliders for \"frequency\" in the future...\n\n"
                + "This might be beneficial to know also: There's a 1/15 chance of a \"transition\" clip being used in place of your sources, too. "
                + "So for every 15 clips you tell YTP+ to generate, one of them will be a transition clip from the folder you provide the program. "
                + "It's a big mess of numbers.\n\n"
                + "The reason there aren't frequency sliders now is because someone will probably break them somehow and I don't have the time to debug. "
                + "Give me a few weeks for an update..."
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
            case "btnBrowseSOUNDS":
                actuallyOpenDirBrowser(tfSOUNDS);
                break;
            case "btnBrowseMUSIC":
                actuallyOpenDirBrowser(tfMUSIC);
                break;
            case "btnBrowseRESOURCES":
                actuallyOpenDirBrowser(tfRESOURCES);
                break;
            case "btnBrowseSOURCES":
                actuallyOpenDirBrowser(tfSOURCES);
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
        toChange.setText(selected.getAbsolutePath().replace('\\', '/'));
        LAST_BROWSED = selected.getParentFile();
    }
    
    void actuallyOpenDirBrowser(TextField toChange) {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Choose File");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        File selected = fileChooser.showDialog(null);
        if (selected==null) return;
        toChange.setText(selected.getAbsolutePath().replace('\\', '/'));
        LAST_BROWSED = selected.getParentFile();
    }

    Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "outputfile.mp4");
    MediaPlayer player = new MediaPlayer(m);
    
    @FXML
    void pauseTheVideo(ActionEvent event) {
        player.pause();
    }

    @FXML
    void playTheVideo(ActionEvent event) {
        
        mediaviewVideoPlayer.setMediaPlayer(player);
        player.play();
    }

    @FXML
    void removeSource(ActionEvent event) {

    }

    @FXML
    void restartTheVideo(ActionEvent event) {
        player.stop();
    }

    @FXML
    void saveAsVideo(ActionEvent event) {

    }
    
    @FXML
    void openDiscord(ActionEvent event) {

    }
    
    void alert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        String[] titles = {"Yo", "Mmmmm!", "I'm the invisible man...", "Luigi, look!", "You want it?", "WTF Booooooooooom"};
        alert.setTitle(titles[randomInt(0,titles.length-1)]);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }
    
    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
