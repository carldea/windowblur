package com.carlfx.sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.carlfx.windowblur.*;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // must use UNIFIED style for effect to work.
        stage.initStyle(StageStyle.UNIFIED);
        stage.setOnShown((windowEvent -> {
            System.out.println("Before invocation of blur effect");
            String test = TestUtil.test(getNativeHandleOfStage(stage), "NSAppearanceNameVibrantLight");
            System.out.println("output = " + test);
        }));

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        // Blur options
        List<BlurOption> blurOptionList = new ArrayList<>(List.of(
                new BlurOption("NSAppearanceNameAqua", "Aqua"),
                new BlurOption("NSAppearanceNameDarkAqua", "Dark Aqua"),
                new BlurOption("NSAppearanceNameVibrantLight", "Vibrant Light"),
                new BlurOption("NSAppearanceNameVibrantDark", "Vibrant Dark"),
                new BlurOption("NSAppearanceNameAccessibilityHighContrastAqua", "Accessibility High Contrast Aqua"),
                new BlurOption("NSAppearanceNameAccessibilityHighContrastDarkAqua", "Accessibility High Contrast Dark Aqua"),
                new BlurOption("NSAppearanceNameAccessibilityHighContrastVibrantLight", "Accessibility High Contrast Vibrant Light"),
                new BlurOption("NSAppearanceNameAccessibilityHighContrastVibrantDark", "Accessibility High Contrast Vibrant Dark")
        ));
        ComboBox<BlurOption> blurOptions = new ComboBox<>(FXCollections.observableList(blurOptionList));
        var blurOptionLabel = new Label("MacOS Background Blur options");
        blurOptions.setOnAction((actionEvent -> {
            TestUtil.test(getNativeHandleOfStage(stage), blurOptions.getSelectionModel().getSelectedItem().nativeName);
        }));


        VBox vBox = new VBox(label, new HBox(blurOptionLabel , blurOptions));
        var root = new StackPane(vBox);
        //root.setOpacity(0);

        var scene = new Scene(root, 640, 480);
        // Must use a transparent fill of the scene & root pane's background's alpha channel to work.
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        scene.getRoot().setStyle("-fx-background-color: rgba(255, 255, 255, 0.2);");

        //scene.getRoot().setOpacity(.50);
        //stage.setOpacity(.50);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
    public static long getNativeHandleOfStage(Window stage) {
        try {
            final Method getPeer = Window.class.getDeclaredMethod("getPeer");
            getPeer.setAccessible(true);
            final Object tkStage = getPeer.invoke(stage);
            final Method getPlatformWindow = tkStage.getClass().getMethod("getPlatformWindow");
            final Object jfxWindow = getPlatformWindow.invoke(tkStage);
            final Method getNativeWindow = jfxWindow.getClass().getMethod("getNativeWindow");
            getNativeWindow.setAccessible(true);
            Long ptr = (Long) getNativeWindow.invoke(jfxWindow);
            return ptr;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0l;
        }
    }

}
class BlurOption {
    public final String nativeName, optionName;
    public BlurOption(final String nativeName, final String optionName){
        this.nativeName = nativeName;
        this.optionName = optionName;
    }

    @Override
    public String toString() {
        return optionName;
    }
}