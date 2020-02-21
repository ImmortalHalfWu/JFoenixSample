package wu.ui;

import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyph;
import wu.ui.controllers.MyMasonryController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyMasonryPaneDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Flow flow = new Flow(MyMasonryController.class);

        DefaultFlowContainer container = new DefaultFlowContainer();
        ViewFlowContext flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        JFXDecorator decorator = new JFXDecorator(stage, container.getView(), false, false, true);

        decorator.setCustomMaximize(false);
        decorator.setMaximized(false);
        decorator.setGraphic(new SVGGlyph(""));

        stage.setTitle("我的Demo");
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
