module com.example.ips {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ips to javafx.fxml;
    exports com.example.ips;
}