 module com.example.zavarovanje {
    requires javafx.controls;
    requires javafx.fxml;
     requires java.desktop;
     requires tornadofx.controls;
     requires se.alipsa.ymp;


     opens com.example.zavarovanje to javafx.fxml;
    exports com.example.zavarovanje;
}