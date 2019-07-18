package ytpplusui;

import java.awt.Desktop;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
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
    //Media m;
    
    @FXML
    void addSource(ActionEvent event) {
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileFilter);
        fileChooser.setTitle("Choose Source");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        List<File> selected = fileChooser.showOpenMultipleDialog(null);
        if (selected==null) return;
        for (File file : selected) {
            sourceList.add(file.getAbsolutePath().replace('\\', '/'));
        }
        listviewSourcesList.setItems(sourceList);
        LAST_BROWSED = selected.get(0).getParentFile();
    }

    @FXML
    void goNow(ActionEvent event) throws Exception {
        if (sourceList.isEmpty()) {
            alert("You need some sources...");
            return;
        }
        
        Thread vidThread = new Thread() {
            public void run() {
                try {
                btnCreate.setDisable(true);
                System.out.println("poop");
                YTPGenerator generator = new YTPGenerator(TEMP + "tempoutput.mp4");
                System.out.println("poop2");
                generator.toolBox.FFMPEG = tfFFMPEG.getText();
                generator.toolBox.FFPROBE = tfFFPROBE.getText();
                generator.toolBox.MAGICK = tfMAGICK.getText();
                System.out.println("poop3");
                String jobDir = tfTEMP.getText() + "job_" + System.currentTimeMillis() + "/";
                generator.toolBox.TEMP = jobDir;
                new File(jobDir).mkdir();
                new File(generator.toolBox.TEMP).mkdir();
                generator.toolBox.SOUNDS = tfSOUNDS.getText();
                generator.toolBox.MUSIC = tfMUSIC.getText();
                generator.toolBox.RESOURCES = tfRESOURCES.getText();
                generator.toolBox.SOURCES = tfSOURCES.getText();
                System.out.println("poop4");
                generator.effect1 = cbEffect1.isSelected();
                generator.effect2 = cbEffect2.isSelected();
                generator.effect3 = cbEffect3.isSelected();
                generator.effect4 = cbEffect4.isSelected();
                generator.effect5 = cbEffect5.isSelected();
                generator.effect6 = cbEffect6.isSelected();
                generator.effect7 = cbEffect7.isSelected();
                generator.effect8 = cbEffect8.isSelected();
                generator.effect9 = cbEffect9.isSelected();
                generator.effect10 = cbEffect10.isSelected();
                generator.effect11 = cbEffect11.isSelected();
                generator.insertTransitionClips = cbUseTransitions.isSelected();
                
                System.out.println("poop5");
                for (String source : sourceList) {
                    generator.addSource(source);
                }
                System.out.println("poop6");
                int maxclips = Integer.parseInt(tfClipCount.getText());
                generator.setMaxClips(Integer.parseInt(tfClipCount.getText()));
                generator.setMaxDuration(Double.parseDouble(tfMaxStream.getText()));
                generator.setMinDuration(Double.parseDouble(tfMinStream.getText()));
                System.out.println("poop7");
                
                double timeStarted = System.nanoTime();
                double elapsedTime = System.nanoTime() - timeStarted;

                
                generator.go();
                System.out.println("poop8");
                while (!generator.done) {
                    barProgress.setProgress(generator.doneCount);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        // Keep going
                    }
                //    System.out.println((elapsedTime * generator.doneCount ) - elapsedTime);
                }
                barProgress.setProgress(1);
                try {
                mediaviewVideoPlayer.getMediaPlayer().stop();
                } catch (Exception ex) {} //if there's no video then don't bother doing anything
                
                Thread.sleep(1000);
                File media = new File(tfTEMP.getText() + "tempoutput.mp4");
                //System.out.println("AAAAAAAAAAAAAAA" + media.toURL().toString());
                Media m = new Media(media.toURI().toString());
                MediaPlayer mm = new MediaPlayer(m);
                mediaviewVideoPlayer.setMediaPlayer(mm);
                btnCreate.setDisable(false);
                } catch (Exception ex) {
                   
                   btnCreate.setDisable(false);
                   ex.printStackTrace();
                }
            }
        };
        vidThread.start();
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
        toChange.setText(selected.getAbsolutePath().replace('\\', '/') + "/");
        LAST_BROWSED = selected.getParentFile();
    }

    
    //MediaPlayer player = new MediaPlayer(m);
    
    @FXML
    void pauseTheVideo(ActionEvent event) {
        try {
        mediaviewVideoPlayer.getMediaPlayer().pause();
        } catch (Exception ex) {} //if there's no video then don't bother doing anything
    }

    @FXML
    void playTheVideo(ActionEvent event) {
        try {
        mediaviewVideoPlayer.getMediaPlayer().play();
        } catch (Exception ex) {} //if there's no video then don't bother doing anything
    }

    @FXML
    void removeSource(ActionEvent event) {
        sourceList.remove(listviewSourcesList.getSelectionModel().getSelectedItems().get(0));
    }

    @FXML
    void restartTheVideo(ActionEvent event) {
        try {
        mediaviewVideoPlayer.getMediaPlayer().stop();
        } catch (Exception ex) {} //if there's no video then don't bother doing anything
    }

    @FXML
    void saveAsVideo(ActionEvent event) {
        if (!new File(tfTEMP.getText() + "tempoutput.mp4").exists()) return;
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Video files (*.mp4)", "*.mp4");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileFilter);
        fileChooser.setTitle("Choose Source");
        fileChooser.setInitialDirectory(LAST_BROWSED);
        File selected = fileChooser.showSaveDialog(null);
        if (selected==null) return;
        Path temp = Paths.get(tfTEMP.getText() + "tempoutput.mp4");
        try {
        Files.copy(temp, selected.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            alert("Had a problem copying the file.");
        }
    }
    
    @FXML
    void openDiscord(ActionEvent event) {
        try {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://discord.gg/mAwQQt7"));
        }
        } catch (Exception ex) {} //how does that even happen
    }

    @FXML
    void openArcticZone() {
        try {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://arctic.zone/"));
        }
        } catch (Exception ex) {} //how does that even happen
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
