package com.example.zavarovanje;

import javafx.application.Platform;
import java.awt.Desktop;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import tornadofx.control.DateTimePicker;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class HelloController implements Initializable {
    public TextField naslov;
    public TextField postnaCB;
    public int cena = 0;
    public Label MenjalnikLabel;
    public Spinner<Integer> Year;
    public Spinner<Integer> Month;
    public ImageView KarticaImage;
    public ImageView CVVImage;
    HashMap<String, Boolean> Validated = new HashMap<>();
    public ImageView image;
    public boolean DTP1 = false;
    public boolean DTP2 = false;
    public boolean dark = true;
    public HBox ImageHBox;
    public MenuButton Motor;
    public MenuButton Moznosti;
    public MenuButton KrajIzposoje;
    public ToggleGroup MenjalnikRB;
    public MenuButton Velikost;
    public DateTimePicker DateTimePicker1;
    public DateTimePicker DateTimePicker2;
    public TextField Mail;
    public TextField Telefon;
    public MenuButton nacinPlacila;
    public HBox HBoxKartica;
    public TextField stevKartice;
    public CheckBox Zavarovanje;
    public HBox HBoxCCV;
    public TextField CCV;
    public TextField imeCB;
    public TextField priimekCB;
    public TextField krajCB;
    public DatePicker datumCB;
    public Label status;
    public void izhodCB() {
        System.exit(0);
    }
    public void male() {Velikost.setText("Majhen");}
    public void sredji() {
        Velikost.setText("Srednji");
    }
    public void vejki() {
        Velikost.setText("Velik");
    }
    public void Dizel() {
        Motor.setText("Dizelski");
    }
    public void Benzin() {
        Motor.setText("Bencinski");
    }
    public void Elektrika() {
        Motor.setText("Električni");
    }
    public void Hibrid() {
        Motor.setText("Hibridni");
    }
    public void BancnaKartica() {
        nacinPlacila.setText("Bančna kartica");
    }
    public void Gotovina() {
        nacinPlacila.setText("Gotovina");
    }
    public void darilnaKartica() {
        nacinPlacila.setText("Darilna kartica");
    }
    public void LJ() {
        KrajIzposoje.setText("Ljubljana");
    }
    public void MB() {
        KrajIzposoje.setText("Maribor");
    }
    public void CE() {
        KrajIzposoje.setText("Celje");
    }
    public void KR() {
        KrajIzposoje.setText("Koper");
    }
    public void VE() {
        KrajIzposoje.setText("Velenje");
    }
    public void KO() {
        KrajIzposoje.setText("Koper");
    }
    public void letMB() {
        KrajIzposoje.setText("Letališče Maribor");
    }
    public void letBr() {
        KrajIzposoje.setText("Letališče Brnik");
    }
    public void NM() {
        KrajIzposoje.setText("Novo Mesto");
    }
    public void MS() {
        KrajIzposoje.setText("Murska Sobota");
    }
    public void J() {
        KrajIzposoje.setText("Jesenice");
    }
    public void P() {
        KrajIzposoje.setText("Portorož");
    }
    public void principi() {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("C:\\Users\\zigat\\OneDrive\\Namizje\\Konsistentnost in standardi.pdf");
                Desktop.getDesktop().open(myFile);
            }
            catch (IOException ignored) {
            }
        }
    }
    public void VoziloPobrisi() {
        KrajIzposoje.setText("Kraj izposoje");
        Motor.setText("Motor");
        Velikost.setText("Velikost");
        Moznosti.setText("Možni avtomobili");
        DateTimePicker1.setValue(null);
        DateTimePicker2.setValue(null);
        MenjalnikRB.selectToggle(null);
        MenjalnikLabel.setStyle("");
        Motor.getStylesheets().removeAll(correct, Incorrect);
        KrajIzposoje.getStylesheets().removeAll(correct, Incorrect);
        Velikost.getStylesheets().removeAll(correct, Incorrect);
        Moznosti.getStylesheets().removeAll(correct, Incorrect);
        DateTimePicker1.getStylesheets().removeAll(correct, Incorrect);
        DateTimePicker2.getStylesheets().removeAll(correct, Incorrect);
    }
    public void avtorCB() {
        status.setText("Avtor sem jaz");
    }
    public void IzbrisiCB() {
        pobrisiOseba();
        VoziloPobrisi();
        izbrisiPlacilo();
    }
    public void izbrisiPlacilo() {
        nacinPlacila.setText("Način plačila");
        Zavarovanje.setSelected(false);
        nacinPlacila.getStylesheets().removeAll(Incorrect, correct);
        Zavarovanje.getStylesheets().removeAll(Incorrect, correct);
        if (nacinPlacila.getText().equals("Način plačila")) {
            CCV.clear();
            stevKartice.clear();
            CCV.getStylesheets().removeAll(Incorrect, correct);
            stevKartice.getStylesheets().removeAll(Incorrect, correct);
        }
    }
    public void pobrisiOseba() {
        naslov.clear();
        postnaCB.clear();
        Telefon.clear();
        Mail.clear();
        krajCB.clear();
        priimekCB.clear();
        imeCB.clear();
        datumCB.getEditor().clear();
        Month.getStylesheets().removeAll(Incorrect, correct);
        Mail.getStylesheets().removeAll(Incorrect, correct);
        naslov.getStylesheets().removeAll(Incorrect, correct);
        postnaCB.getStylesheets().removeAll(Incorrect, correct);
        Telefon.getStylesheets().removeAll(Incorrect, correct);
        datumCB.getStylesheets().removeAll(Incorrect, correct);
        krajCB.getStylesheets().removeAll(Incorrect, correct);
        priimekCB.getStylesheets().removeAll(Incorrect, correct);
        imeCB.getStylesheets().removeAll(Incorrect, correct);
    }
    public void ShraniCB() {
        System.out.println(Validated.size());
        if (Validated.values().stream().anyMatch(n -> !n) || Validated.size() < 18) {
            if ((nacinPlacila.getText().equals("Bančna kartica") && Validated.size() == 18) || (!nacinPlacila.getText().equals("Bančna kartica") && Validated.size() == 16))
                status.setText("Status: Vsi elementi niso izpolnjeni.");
            else {
                status.setText("Status:");
                for (Map.Entry<String, Boolean> set : Validated.entrySet()) {
                    if (!set.getValue())
                        status.setText(status.getText() + String.format(" %s je napačno vnesen", set.getKey()));
                }
            }
            Delay();
            return;
        }
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            Delay();
            return;
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Podatki o vozilu:\n" +
                                "   Čas izposoje: " + DateTimePicker1.getValue() + "\n" +
                                "   Čas vrnitve: " + DateTimePicker2.getValue() + "\n" +
                                "   Velikost: " + Velikost.getText() + "\n" +
                                "   Kraj izposoje: " + KrajIzposoje.getText() + "\n" +
                                "   Motor: " + Motor.getText() + "\n" +
                                "   Izbran model: " + Moznosti.getText() + "\n");
            fileWriter.write("Podatki o Osebi:\n" +
                                "   Ime: " + imeCB.getText() + "\n" +
                                "   Priimek: " + priimekCB.getText() + "\n" +
                                "   Datum rojstva: " + datumCB.getValue() + "\n" +
                                "   Naslov: " + naslov.getText() + "\n" +
                                "   Poštna št.: " + postnaCB.getText() + "\n" +
                                "   Telefon.: " + postnaCB.getText() + "\n" +
                                "   E-Mail: " + postnaCB.getText() + "\n" +
                                "   Pridobitev izpita: " + postnaCB.getText() + "\n" +
                                "   Kraj: " + krajCB.getText() + "\n");
            fileWriter.write("Podatki o plačilu:\n" +
                                "   Način plačila: " + Zavarovanje.getText() + "\n" +
                                "   Dodatno zavarovanje: " + Zavarovanje.isSelected() + "\n" +
                                "   Bančna kartica: " + stevKartice.getText() + "\n" +
                                "   CVV: " + CCV.getText());
            fileWriter.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Delay() {
        status.getStylesheets().add(statCSS);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    status.getStylesheets().remove(statCSS);
                    status.setText("Status:");
                });
            }
        }, 2000, 1000);
    }
    public void setMaliElek() {
        MaliElek.add("Tesla Model 3");
        MaliElek.add("Peugeot e-208");
        MaliElek.add("Fiat 500");
        MaliElek.add("Vauxhall Corsa-e");
        MaliElek.add("Mazda MX-30");
        MaliElek.add("Renault Zoe");
        MaliElek.add("Mini Electric");
    }
    public void setMaliDizel() {
        MaliDizel.add("Renault Clio");
        MaliDizel.add("Mini Hatchback");
        MaliDizel.add("Ford Fiesta");
        MaliDizel.add("Peugeot 208");
        MaliDizel.add("Citroen C3 Aircross");
        MaliDizel.add("Audi Q2");
        MaliDizel.add("BMW 2 Series Active Tourer");
    }
    public void setMaliBencin() {
        MaliBencin.add("2023 Kia Rio");
        MaliBencin.add("2023 MINI Convertible");
        MaliBencin.add("2023 MINI Hardtop");
        MaliBencin.add("2024 MINI Clubman");
        MaliBencin.add("2022 Mercedes‑Benz A‑Class");
        MaliBencin.add("2023 Toyota GR Supra");
    }
    public void setMaliHybrid() {
        MaliHybrid.add("Volkswagen Golf R");
        MaliHybrid.add("Toyota Corolla 1.8");
        MaliHybrid.add("Cupra Leon V1 1.5 TSI");
        MaliHybrid.add("Honda Jazz SE 1.5 i-MMD Hybrid eCVT");
        MaliHybrid.add("Audi A3 Sportback S Line 30 TFSI");
        MaliHybrid.add("Toyota Yaris Hybrid 1.5 Hybrid CVT");
    }
    public void setSrednjiBencin() {
        SredjiBencin.add("2023 Toyota Camry");
        SredjiBencin.add("2023 Honda Accord");
        SredjiBencin.add("2023 Hyundai Sonata");
        SredjiBencin.add("2023 Lexus ES");
        SredjiBencin.add("2023 Nissan Altima");
        SredjiBencin.add("2023 Chevrolet Malibu");
    }
    public void setSrednjiElek() {
        SredjiElek.add("Kia Niro EV");
        SredjiElek.add("Renault Mégane E-Tech");
        SredjiElek.add("Volkswagen ID 3");
        SredjiElek.add("Peugeot e-2008");
        SredjiElek.add("Nissan Leaf");
        SredjiElek.add("Vauxhall Mokka Electric");
    }
    public void setSrednjiDizel() {
        SredjiDizel.add("BMW 3 Series saloon");
        SredjiDizel.add("Land Rover Defender SUV");
        SredjiDizel.add("Skoda Octavia Estate");
        SredjiDizel.add("BMW 5 Series saloon");
        SredjiDizel.add("Citroën C3 Aircross");
        SredjiDizel.add("Audi A3 Sportback hatchback");
    }
    public void setSrednjiHybrid() {
        SredjiHybrid.add("Toyota RAV4");
        SredjiHybrid.add("Subaru Forester");
        SredjiHybrid.add("Lexus IS300H");
        SredjiHybrid.add("Lexus NX300H");
        SredjiHybrid.add("Lexus IS");
        SredjiHybrid.add("Volvo S60");
    }
    public void setVejkiBencin() {
        VejkiBencin.add("2023 BMW 8 Series");
        VejkiBencin.add("2023 Mercedes‑Benz CLS");
        VejkiBencin.add("2023 Genesis G80");
        VejkiBencin.add("2023 BMW 7 Series");
        VejkiBencin.add("2023 Volvo S90 Recharge");
        VejkiBencin.add("2023 Toyota Crown");
    }
    public void setVejkiElek() {
        VejkiElek.add("Volvo EX90");
        VejkiElek.add("Tesla Model X");
        VejkiElek.add("Mercedes-Benz EQ-Class");
        VejkiElek.add("Porsche Taycan");
        VejkiElek.add("BMW IX");
        VejkiElek.add("Kia EV6");
    }
    public void setVejkiDizel() {
        VejkiDizel.add("Dacia Duster");
        VejkiDizel.add("Skoda Karoq");
        VejkiDizel.add("Audi Q7");
        VejkiDizel.add("Kia Sorento");
        VejkiDizel.add("Volkswagen T-Roc");
        VejkiDizel.add("Peugeot 5008");
    }
    public void setVejkiHybrid() {
        VejkiHybrid.add("Lexus ES");
        VejkiHybrid.add("Lexus ES300H");
        VejkiHybrid.add("Volvo XC90");
        VejkiHybrid.add("Hyundai Tucson SUV");
        VejkiHybrid.add("Volvo V60 Recharge");
        VejkiHybrid.add("Mercedes C-Class hybrid");
    }
    public void Switcheroo() {
        switch (Velikost.getText()) {
            case "Majhen" -> {
                switch (Motor.getText()) {
                    case "Dizelski" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < MaliDizel.size(); i++) {
                            String a = setElements(MaliDizel, i);
                            cena = 30 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Bencinski" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < MaliBencin.size(); i++) {
                            String a = setElements(MaliBencin, i);
                            cena = 31 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Električni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < MaliElek.size(); i++) {
                            String a = setElements(MaliElek, i);
                            cena = 28 + i ;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Hibridni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < MaliHybrid.size(); i++) {
                            String a = setElements(MaliHybrid, i);
                            cena = 34 + i ;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                }
            }
            case "Srednji" -> {
                switch (Motor.getText()) {
                    case "Dizelski" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < SredjiDizel.size(); i++) {
                            String a = setElements(SredjiDizel, i);
                            cena = 35 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Bencinski" -> {
                        RemoveMenuItems();
                        System.out.println(cena);
                        for (int i = 0; i < SredjiBencin.size(); i++) {
                            String a = setElements(SredjiBencin, i);
                            cena = 37 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Električni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < SredjiElek.size(); i++) {
                            String a = setElements(SredjiElek, i);
                            cena = 34 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Hibridni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < SredjiHybrid.size(); i++) {
                            String a = setElements(SredjiHybrid, i);
                            cena = 38 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                }
            }
            case "Velik" -> {
                switch (Motor.getText()) {
                    case "Bencinski" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < VejkiBencin.size(); i++) {
                            String a = setElements(VejkiBencin, i);
                            cena = 40 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Dizelski" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < VejkiDizel.size(); i++) {
                            String a = setElements(VejkiDizel, i);
                            cena = 39 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Električni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < VejkiElek.size(); i++) {
                            String a = setElements(VejkiElek, i);
                            cena = 38 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                    case "Hibridni" -> {
                        RemoveMenuItems();
                        for (int i = 0; i < VejkiHybrid.size(); i++) {
                            String a = setElements(VejkiHybrid, i);
                            cena = 44 + i;
                            Moznosti.getItems().get(i).setOnAction(e -> Moznosti.setText(a));
                        }
                    }
                }
            }
        }
    }
    public String setElements(ArrayList<String> type, int i) {
        String a = type.get(i);
        Moznosti.getItems().add(new MenuItem());
        Moznosti.getItems().get(i).setText(a);
        return a;
    }
    private void RemoveMenuItems() {
        while (!Moznosti.getItems().isEmpty())
            Moznosti.getItems().remove(0);
        if (Zavarovanje.isSelected() && DateTimePicker1.getValue() != null && DateTimePicker2.getValue() != null)
            cena = (int) (ChronoUnit.DAYS.between(DateTimePicker1.getValue(), DateTimePicker2.getValue()) * 2);
    }
    public void addCSSTextField(TextField elem, boolean cor) {
        elem.getStylesheets().removeAll(correct, Incorrect);
        elem.getStylesheets().add(style);
        if (cor) {
            elem.getStylesheets().add(correct);
            Validated.put(elem.toString(), true);
        }
        else {
            Validated.put(elem.toString(), false);
            elem.getStylesheets().add(Incorrect);
        }
    }
    public void addCSSDatePicker(DatePicker elem, boolean cor) {
        elem.getStylesheets().removeAll(correct, Incorrect);
        if (cor) {
            elem.getStylesheets().add(correct);
            Validated.put(elem.toString(), true);
        }
        else {
            Validated.put(elem.toString(), false);
            elem.getStylesheets().add(Incorrect);
        }
    }
    public void addCSSMenuBar(MenuButton elem, boolean cor) {
        elem.getStylesheets().removeAll(correct, Incorrect);
        if (cor) {
            elem.getStylesheets().add(correct);
            Validated.put(elem.toString().split("=")[1].split(",")[0], true);
        }
        else {
            Validated.put(elem.toString().split("=")[1].split(",")[0], false);
            elem.getStylesheets().add(Incorrect);
        }
    }
    public void addCSSLabel(Label elem) {
        elem.setStyle("-fx-text-fill: green");
        Validated.put(elem.toString(), true);
    }
    String style = Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm();
    String correct = Objects.requireNonNull(getClass().getResource("Correct.css")).toExternalForm();
    String Incorrect = Objects.requireNonNull(getClass().getResource("Incorrect.css")).toExternalForm();
    String statCSS = Objects.requireNonNull(getClass().getResource("status.css")).toExternalForm();
    String white = Objects.requireNonNull(getClass().getResource("WhiteTheme.css")).toExternalForm();
    ArrayList<String> MaliBencin = new ArrayList<>();
    ArrayList<String> MaliElek= new ArrayList<>();
    ArrayList<String> MaliHybrid = new ArrayList<>();
    ArrayList<String> MaliDizel = new ArrayList<>();
    ArrayList<String> SredjiElek = new ArrayList<>();
    ArrayList<String> SredjiBencin = new ArrayList<>();
    ArrayList<String> SredjiHybrid = new ArrayList<>();
    ArrayList<String> SredjiDizel = new ArrayList<>();
    ArrayList<String> VejkiElek= new ArrayList<>();
    ArrayList<String> VejkiBencin = new ArrayList<>();
    ArrayList<String> VejkiHybrid = new ArrayList<>();
    ArrayList<String> VejkiDizel = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setMaliElek();
        setMaliBencin();
        setMaliDizel();
        setMaliHybrid();
        setSrednjiBencin();
        setSrednjiHybrid();
        setSrednjiDizel();
        setSrednjiElek();
        setVejkiBencin();
        setVejkiDizel();
        setVejkiHybrid();
        setVejkiElek();
        KrajIzposoje.textProperty().addListener((observableValue, s, t1) -> addCSSMenuBar(KrajIzposoje, !KrajIzposoje.getText().equals("Kraj izposoje")));
        Motor.textProperty().addListener((observableValue, s, t1) -> {
            addCSSMenuBar(Motor, !Motor.getText().equals("Motor"));
            if (!Velikost.getText().equals("Velikost") && !Motor.getText().equals("Motor")) {
                Moznosti.setVisible(true);
                Moznosti.setDisable(false);
                Switcheroo();
                setCena();
            }
            else {
                Moznosti.setVisible(false);
                Moznosti.setDisable(true);
            }
        });
        MenjalnikRB.selectedToggleProperty().addListener(e -> addCSSLabel(MenjalnikLabel));
        DateTimePicker1.valueProperty().addListener(e -> {
            if (!DTP1 && DateTimePicker1.getValue() != null) {
                DateTimePicker1.setDateTimeValue(DateTimePicker1.getValue().atTime(0, 0));
                DTP1 = true;
            }
            if (DateTimePicker1.getValue() != null)
                addCSSDatePicker(DateTimePicker1, ChronoUnit.DAYS.between(LocalDate.now(), DateTimePicker1.valueProperty().getValue()) > 2 ||
                (ChronoUnit.DAYS.between(LocalDate.now(), DateTimePicker1.valueProperty().getValue()) == 2
                && LocalDateTime.now().getHour() < Integer.parseInt(DateTimePicker1.dateTimeValueProperty().getValue().toString().split("T")[1].split(":")[0])));
        });
        DateTimePicker2.valueProperty().addListener(e -> {
            if (!DTP2 && DateTimePicker2.getValue() != null) {
                DateTimePicker2.setDateTimeValue(DateTimePicker2.getValue().atTime(0, 0));
                DTP2 = true;
            }
            if (DateTimePicker1.getValue() != null && DateTimePicker2.getValue() != null) {
                addCSSDatePicker(DateTimePicker2, ChronoUnit.DAYS.between(DateTimePicker1.valueProperty().getValue(), DateTimePicker2.valueProperty().getValue()) > 1 ||
                        (ChronoUnit.DAYS.between(DateTimePicker1.valueProperty().getValue(), DateTimePicker2.valueProperty().getValue()) == 1 && Integer.parseInt(DateTimePicker2.dateTimeValueProperty().getValue().toString().split("T")[1].split(":")[0]) >=
                                Integer.parseInt(DateTimePicker1.dateTimeValueProperty().getValue().toString().split("T")[1].split(":")[0])));
                setCena();
            }
        });
        Velikost.textProperty().addListener((observableValue, s, t1) -> {
            addCSSMenuBar(Velikost, !Velikost.getText().equals("Velikost"));
            if (!Velikost.getText().equals("Velikost") && !Motor.getText().equals("Motor")) {
                Moznosti.setVisible(true);
                Moznosti.setDisable(false);
                Switcheroo();
                setCena();
            }
            else {
                Moznosti.setVisible(false);
                Moznosti.setDisable(true);
            }
        });
        naslov.setOnKeyTyped(e -> addCSSTextField(naslov, naslov.getText().matches("[A-ž]+ [0-9]{1,3}")));
        postnaCB.setOnKeyTyped(e -> addCSSTextField(postnaCB, postnaCB.getText().matches("[0-9]{4,5}")));
        imeCB.setOnKeyTyped(e -> addCSSTextField(imeCB, imeCB.getText().matches("[A-ž]+")));
        Telefon.setOnKeyTyped(e -> addCSSTextField(Telefon, Telefon.getText().matches("\\+[0-9]{1,3} [0-9]{2}[ -][0-9]{3}[ -][0-9]{3}") ||
                Telefon.getText().matches("[0-9]{3}[ -][0-9]{3}[ -][0-9]{3}")));
        Mail.setOnKeyTyped(e -> addCSSTextField(Mail, Mail.getText().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")));
        priimekCB.setOnKeyTyped(e -> addCSSTextField(priimekCB, priimekCB.getText().matches("[A-ž]+")));
        krajCB.setOnKeyTyped(e -> addCSSTextField(krajCB, krajCB.getText().matches("[A-ž]+")));
        datumCB.valueProperty().addListener(e -> addCSSDatePicker(datumCB, datumCB.getEditor().getText().matches("[0-9/]+") &&
                Math.abs(ChronoUnit.DAYS.between(datumCB.valueProperty().getValue(), LocalDate.now())) / 365.25 >= 18));
        datumCB.setOnKeyTyped(e -> addCSSDatePicker(datumCB, datumCB.getEditor().getText().matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}") &&
                Math.abs(ChronoUnit.DAYS.between(datumCB.valueProperty().getValue(), LocalDate.now())) / 365.25 >= 18));
        ImageHBox.setOnMouseMoved(e -> {
            if (dark)
                image.setImage(new Image(Objects.requireNonNull(getClass().getResource("white-theme.png")).toExternalForm()));
            else
                image.setImage(new Image(Objects.requireNonNull(getClass().getResource("dark-theme-icon.png")).toExternalForm()));
        });
        ImageHBox.setOnMouseExited(e -> {
            if (dark)
                image.setImage(new Image(Objects.requireNonNull(getClass().getResource("dark-theme-icon.png")).toExternalForm()));
        });
        image.setOnMousePressed(e -> {
            if (dark) {
                dark = false;
                datumCB.getScene().getStylesheets().remove(style);
                datumCB.getScene().getStylesheets().add(white);
            }
            else {
                dark = true;
                datumCB.getScene().getStylesheets().add(style);
                datumCB.getScene().getStylesheets().remove(white);
            }
        });
        Moznosti.textProperty().addListener(e -> setCena());
        Year.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1960, java.time.Year.now().getValue(), 2000));
        Month.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));
        CCV.textProperty().addListener(e -> addCSSTextField(CCV, CCV.getText().matches("[0-9]{3}")));
        stevKartice.textProperty().addListener(e -> {
            if (stevKartice.getText().length() % 5 == 4 && stevKartice.getText().length() < 19)
                stevKartice.setText(stevKartice.getText() + "-");
        });
        stevKartice.textProperty().addListener(e -> addCSSTextField(stevKartice, stevKartice.getText().matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}")));
        nacinPlacila.textProperty().addListener(e -> {
            addCSSMenuBar(nacinPlacila, !nacinPlacila.getText().equals("Način plačila"));
            if (nacinPlacila.getText().equals("Bančna kartica")) {
                HBoxCCV.setDisable(false);
                HBoxKartica.setDisable(false);
                HBoxCCV.setVisible(true);
                HBoxKartica.setVisible(true);
                HBoxCCV.setOnMouseMoved(a -> {
                    CVVImage.setDisable(false);
                    CVVImage.setVisible(true);
                });
                HBoxCCV.setOnMouseExited(a -> {
                    CVVImage.setDisable(true);
                    CVVImage.setVisible(false);
                });
                HBoxKartica.setOnMouseMoved(w -> {
                    KarticaImage.setDisable(false);
                    KarticaImage.setVisible(true);
                });
                HBoxKartica.setOnMouseExited(w -> {
                    KarticaImage.setDisable(true);
                    KarticaImage.setVisible(false);
                });
            }
            else {
                HBoxCCV.setDisable(true);
                HBoxKartica.setDisable(true);
                HBoxCCV.setVisible(false);
                HBoxKartica.setVisible(false);
            }
        });
        Zavarovanje.setOnMousePressed(e -> {
            if (!Zavarovanje.isSelected() && DateTimePicker1.getValue() != null && DateTimePicker2.getValue() != null) {
                cena += ChronoUnit.DAYS.between(DateTimePicker1.getValue(), DateTimePicker2.getValue()) * 2;
                status.setText("Status: Cena avtomobila je: " + cena + "€");
            }
            else {
                cena -= ChronoUnit.DAYS.between(DateTimePicker1.getValue(), DateTimePicker2.getValue()) * 2;
                status.setText("Status: Cena avtomobila je: " + cena + "€");
            }
        });
    }
    public void setCena() {
        if (DateTimePicker1.getValue() != null && DateTimePicker2.getValue() != null && !Moznosti.getText().equals("Možni avtomobili")) {
            addCSSMenuBar(Moznosti, !Moznosti.getText().equals("Možni avtomobili"));
            Switcheroo();
            int hours = Math.toIntExact(ChronoUnit.DAYS.between(DateTimePicker1.valueProperty().getValue(), DateTimePicker2.valueProperty().getValue()));
            cena += cena * ChronoUnit.DAYS.between(DateTimePicker1.valueProperty().getValue(), DateTimePicker2.valueProperty().getValue());
            cena += 0.035 * cena * hours;
            status.setText("Status: Cena avtomobila je: " + cena + "€");
            status.getStylesheets().add(statCSS);
        }
        else {
            status.setText("Status:");
            status.getStylesheets().removeAll(statCSS);
        }
    }
}