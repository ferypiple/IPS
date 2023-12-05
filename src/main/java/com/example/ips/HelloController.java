package com.example.ips;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private AnchorPane scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private CheckBox checker;

    @FXML
    private TextField average;

    @FXML
    private Button breakButton;
    @FXML
    private Button breakButton1;

    @FXML
    private Button repairButton1;

    @FXML
    private Button repairButton2;

    @FXML
    private TableView<CostItem> tableView;

    @FXML
    private ScatterChart<String, Number> diagram;

    @FXML
    private TextField errors;

    @FXML
    private TextField events;
    @FXML
    private TextField allSpending;
    @FXML
    private TextField income;

    @FXML
    private TextField frequency;

    @FXML
    private TextField quantity;
    @FXML
    private TextField profit;

    @FXML
    private Button startButton;

    @FXML
    private TextField volume;
    @FXML
    private Label dolarIcon;

    @FXML
    private Label timeLabel;
    @FXML
    private TextField spending;

    private XYChart.Series<String, Number> series;

    private List<Double> bit = new ArrayList<>();

    private List<Thread> threads = new ArrayList<>();

    private Double volumeValue = 0.0;

    private Double volumeBitcoin = 0.0;

    private Integer quantityValue = 0;

    private Double averageValue = 0.0;

    private Double averageBitcoin = 0.0;

    private Double frequencyValue = 0.0;

    private Integer errorsValue = 0;

    private int i = 0;

    private int index = 0;

    private DiaramData diaramData = new DiaramData();

    private Boolean type = false;

    private String formattedNumber1;

    private String formattedNumber2;

    private String formattedNumber3;

    private String formattedNumber4;

    private int quantityRandom;
    private int spendingVal;
    CostItem item1 = new CostItem("МТС", 1000);
    CostItem item2 =  new CostItem("Мегафон", 2000);
    CostItem item3 =   new CostItem("Билайн", 1500);
    CostItem item4 = new CostItem("другое", 1500);
    TableColumn<CostItem, Double> expensesColumn;

    @FXML
    void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            String time = currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond();
            timeLabel.setText(time);

        }));
        series = new XYChart.Series<>();
        // Добавляем серию данных в график
        diagram.getData().add(series);
        diagram.setLegendVisible(false);
        startButton.setOnAction(actionEvent -> {
            threads.add(new Thread(diaramData));
            threads.get(index).start();
            events.setStyle("-fx-text-fill: green;");
            events.setText("Запуск прошел успешно!");

        });

        breakButton.setOnAction(actionEvent -> breaker());

        breakButton1.setOnAction(actionEvent -> toMuchMoney());

        repairButton1.setOnAction(actionEvent -> repair());

        repairButton2.setOnAction(actionEvent -> {
            spendingVal+=10000;
            repair();
        });

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();
        TableColumn<CostItem, String> companyNameColumn = new TableColumn<>("Название компании");
        expensesColumn = new TableColumn<>("Затраты в $");

        // Устанавливаем фабрику ячеек для столбцов
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<>("expenses"));

        // Добавляем столбцы к таблице
        tableView.getColumns().addAll(companyNameColumn, expensesColumn);

        // Заполняем таблицу данными
        ObservableList<CostItem> data = FXCollections.observableArrayList();
        data.add(item1);
        data.add(item2);
        data.add(item3);
        data.add(item4);
        tableView.setItems(data);
    }
    void breaker(){
        threads.get(index).interrupt();
        index++;
        type = false;
        events.setStyle("-fx-text-fill: red;");
        events.setText("Нет связи с сервером");


    }
    void repair(){
        if(type==false) {
            events.setText("Сервера успешно восстановлены");
        }else {
            quantity.setText(formattedNumber2);
            events.setText("Количество мошеннических операций уменьшенно");
        }
        threads.add(new Thread(diaramData));
        threads.get(index).start();
        events.setStyle("-fx-text-fill: green;");

    }
    void toMuchMoney(){
        threads.get(index).interrupt();
        index++;
        quantity.setText("1000000000000");
        events.setStyle("-fx-text-fill: red;");
        events.setText("Подозрительное количество транзакций- биржа времена приостановлена");
        type = true;
    }
    private String toRgbString(Color color) {
        return "rgb(" + to255Int(color.getRed()) + ", " + to255Int(color.getGreen()) + ", " + to255Int(color.getBlue()) + ")";
    }

    private int to255Int(double value) {
        return (int) (value * 255);
    }


    private class DiaramData implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                Random random = new Random();

                int value = random.nextInt(100000);
                item1.setExpenses(item1.getExpenses() + value);
                value = random.nextInt(100000);
                item2.setExpenses(item2.getExpenses() + value);
                value = random.nextInt(100000);
                item3.setExpenses(item3.getExpenses() + value);
                value = random.nextInt(50000);
                item4.setExpenses(item4.getExpenses() + value);
                tableView.refresh();
                Double volumeRandom = random.nextDouble(1000000);
                volumeValue = volumeValue + volumeRandom;
                volumeBitcoin = volumeValue / 40000.0;

                bit.add(volumeBitcoin);

                quantityRandom = random.nextInt(600);

                quantityValue = quantityValue + quantityRandom;

                averageValue = volumeRandom / quantityRandom;

                averageBitcoin = averageValue / 37392.3;

                Double frequencyRandom = random.nextDouble(1.9);

                frequencyValue = frequencyValue + frequencyRandom;

                errorsValue = random.nextInt(10);

                formattedNumber1 = String.format("%.2f", volumeValue);

                formattedNumber2 = String.valueOf(quantityValue);

                formattedNumber3 = String.format("%.2f", averageValue);

                formattedNumber4 = String.format("%.2f", frequencyValue);

                LocalDateTime current = LocalDateTime.now();

                String formattedTime = current.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                javafx.application.Platform.runLater(() -> {
                    if (checker.isSelected()) {
                        String formattedNumberbit1 = String.format("%.2f", volumeBitcoin);
                        String formattedNumberbit2 = String.format("%.2f", averageBitcoin);
                        double spend = volumeBitcoin/100;
                        double prof;
                        formattedNumber1 = String.format("%.2f", spend);
                        income.setText(formattedNumber1 +" ₿");
                        expensesColumn.setText("Затраты в ₿");
                        formattedNumber1 = String.format("%.2f", spend/50);
                        prof = spend - spend/50-spendingVal;
                        allSpending.setText(formattedNumber1 + " ₿");
                        formattedNumber1 = String.format("%.2f", prof);
                        profit.setText(formattedNumber1 + " ₿");
                        volume.setText(formattedNumberbit1 + " ₿");
                        average.setText(formattedNumberbit2 + " ₿");
                        spending.setText(spendingVal/40000 + " ₿");
                        dolarIcon.setText(" ₿");
                        item1.setExpenses(item1.getExpenses()/40000);
                        item2.setExpenses(item2.getExpenses()/40000);
                        item3.setExpenses(item3.getExpenses()/40000);
                        item4.setExpenses(item4.getExpenses()/40000);
                    } else {
                        volume.setText(formattedNumber1 + " $");
                        average.setText(formattedNumber3 + " $");
                        spending.setText(spendingVal + "$");
                        double spend = volumeValue/100;
                        double prof;
                        formattedNumber1 = String.format("%.2f", spend);
                        income.setText(formattedNumber1 +" $");
                        expensesColumn.setText("Затраты в $");
                        formattedNumber1 = String.format("%.2f", spend/50);
                        prof = spend - spend/50-spendingVal;

                        allSpending.setText(formattedNumber1 + " $");
                        formattedNumber1 = String.format("%.2f", prof);
                        profit.setText(formattedNumber1 + " $");
                        item1.setExpenses(item1.getExpenses());
                        item2.setExpenses(item2.getExpenses());
                        item3.setExpenses(item3.getExpenses());
                        item4.setExpenses(item4.getExpenses());
                        dolarIcon.setText(" $");
                    }
                    if(i %2 == 0){
                        errors.setText(String.valueOf(errorsValue));
                        events.setStyle("-fx-text-fill: orange;");
                        events.setText("Ошибочные операции: " + errorsValue);
                    }
                    series.getData().add(new ScatterChart.Data<>(formattedTime, averageValue));
                    quantity.setText(formattedNumber2);
                    frequency.setText(formattedNumber4 + " сек");

                });
                i++;
            }
        }
    }
}
