package com.carlfx.sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.carlfx.windowblur.*;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.lang.reflect.Method;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        stage.initStyle(StageStyle.UNIFIED);
        stage.setOnShown((windowEvent -> {
            System.out.println("Before invocation of blur effect");
            String test = TestUtil.test(getNativeHandleOfStage(stage));
            System.out.println("output = " + test);
        }));

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var root = new StackPane(label);
        //root.setOpacity(0);
        var scene = new Scene(root, 640, 480);

        //scene.getRoot().setOpacity(.50);
//        stage.setOpacity(.50);
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