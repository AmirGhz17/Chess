module com.example.chess2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires java.desktop;
    requires jlayer;

    opens com.example.chess2 to javafx.graphics,javafx.controls,javafx.media,com.google.gson;
//    opens com.example.chess2 to javafx.fxml;
}