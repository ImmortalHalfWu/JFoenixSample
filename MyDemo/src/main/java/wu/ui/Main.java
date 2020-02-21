package wu.ui;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import immortal.half.wu.FileUtils;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wu.ui.controllers.MainController;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        FileUtils.init();
        launch(args);
    }

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void start(Stage stage) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(Main.class.getResourceAsStream("/fonts/icomoon.svg"),
                    "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        });
        thread.start();
        thread.join();

        Flow flow = new Flow(MainController.class);

        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView(), false, false, true);

        decorator.setCustomMaximize(false);
        decorator.setMaximized(false);

        stage.setTitle("智淘交易助手");
        stage.setResizable(false);

        Scene scene = new Scene(decorator, 1024, 760);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
            JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
            Main.class.getResource("/css/jfoenix-main-demo.css").toExternalForm(),
            Main.class.getResource("/css/jfoenix-components.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();

    }
}
