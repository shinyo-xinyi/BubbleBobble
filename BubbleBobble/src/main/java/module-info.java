module com.ae2dms.bubblebobble {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;

    opens com.ae2dms.BubbleBobble to javafx.fxml;
    exports com.ae2dms.BubbleBobble;
    exports com.ae2dms.BubbleBobble.Controller;
    opens com.ae2dms.BubbleBobble.Controller to javafx.fxml;
    exports com.ae2dms.BubbleBobble.Database;
    opens com.ae2dms.BubbleBobble.Database to javafx.fxml;
    exports com.ae2dms.BubbleBobble.World;
    opens com.ae2dms.BubbleBobble.World to javafx.fxml;
    exports com.ae2dms.BubbleBobble.Factory;
    opens com.ae2dms.BubbleBobble.Factory to javafx.fxml;
}