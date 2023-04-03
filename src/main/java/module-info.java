module com.example.javasae {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires java.logging;


    opens com.example.javasae to javafx.fxml;
    exports com.example.javasae;
}