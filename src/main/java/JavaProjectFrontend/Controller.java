package JavaProjectFrontend;

import JavaProjectFrontend.CustomControls.CodeTab;
import JavaProjectFrontend.Models.Code;
import JavaProjectFrontend.Models.CodesGroup;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
    private ObservableList<CodesGroup> _codeGroups;

    public TextArea helloArea;

    public Button copyToClipboard;
    public Button saveAsFile;
    public Button closeTab;
    public HBox tabButtonsBox;

    public Accordion labsAccordian;
    public TabPane codesTabPane;

    public Controller() {
        _codeGroups = FXCollections.observableArrayList();

        for (int i = 0; i < 20; i++) {
            _codeGroups.add(new CodesGroup(
                    i + ". Pierwsze laby",
                    FXCollections.observableArrayList
                            (
                                    new Code(i + ".1 Przykład pierwszy", "someCOde!dadw awdawwadaw"),
                                    new Code(i + ".2 Przykład drugi", "someCOde!dadw awdawwadaw"),
                                    new Code(i + ".3Przykład trzeci", "someCOde!dadw awdawwadaw")
                            )
            ));
        }
    }

    @FXML
    private void initialize() {
        handleTabButtons();
        generateCodeList();
    }

    private void handleTabButtons() {
        closeTab.setOnMouseClicked(mouseEvent -> {
            Tab selectedTab = codesTabPane.getSelectionModel().getSelectedItem();
            codesTabPane.getTabs().remove(selectedTab);
        });
        copyToClipboard.setOnMouseClicked(mouseEvent -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(((CodeTab) codesTabPane.getSelectionModel().getSelectedItem()).getCode());
            clipboard.setContent(content);
        });
        saveAsFile.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java", "*.java"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FXML", "*.fxml"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Inny", "*.*"));

            fileChooser.setTitle("Zapisz kod");
            File file = fileChooser.showSaveDialog(labsAccordian.getScene().getWindow());
            if (file != null) {
                try {
                    Path filePath = Paths.get(file.getAbsolutePath());
                    Files.write(filePath,
                            ((CodeTab) codesTabPane.getSelectionModel().getSelectedItem())
                                    .getCode()
                                    .getBytes(Charset.forName("UTF-8"))
                    );
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });


        codesTabPane.getTabs().addListener(new ListChangeListener<Tab>() {
            @Override
            public void onChanged(Change<? extends Tab> c) {
                if (codesTabPane.getTabs().size() <= 0) {
                    tabButtonsBox.setVisible(false);
                    helloArea.setVisible(true);
                } else {
                    tabButtonsBox.setVisible(true);
                    helloArea.setVisible(false);
                }
            }
        });
    }

    private void generateCodeList() {
        for (int i = 0; i < _codeGroups.size(); i++) {
            VBox codesList = new VBox();
            codesList.setSpacing(8);

            for (int j = 0; j < _codeGroups.get(i).codes.size(); j++) {
                Button codeButton = new Button(_codeGroups.get(i).codes.get(j).codeName.getValue());
                codeButton.setId(i + ":" + j);

                int finalI = i;
                int finalJ = j;
                codeButton.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        if (mouseEvent.getClickCount() == 1) {
                            CodeTab tab = new CodeTab();

                            String[] id = ((Button) mouseEvent.getSource()).getId().split(":");
                            String codeName = _codeGroups.get(Integer.parseInt(id[0])
                            ).codes.get(Integer.parseInt(id[1])
                            ).codeName.getValue();
                            String code = _codeGroups.get(Integer.parseInt(id[0])
                            ).codes.get(Integer.parseInt(id[1])
                            ).code.getValue();

                            for (Tab tempTab : codesTabPane.getTabs()) {
                                if (tempTab.getText() == codeName)
                                    return;
                            }

                            tab.setText(codeName);
                            tab.setCode(code);

                            codesTabPane.getTabs().add(tab);
                            codesTabPane.getSelectionModel().select(tab);
                        }
                    }
                });

                codeButton.setGraphic(new Rectangle(5, 5, Color.GRAY));

                codeButton.setMaxWidth(Double.MAX_VALUE);
                codeButton.setAlignment(Pos.BASELINE_LEFT);
                codesList.getChildren().add(codeButton);
                VBox.setVgrow(codeButton, Priority.ALWAYS);
            }

            TitledPane titledPane = new TitledPane(_codeGroups.get(i).groupName.getValue(), codesList);
            labsAccordian.getPanes().add(titledPane);

            if (i == 0)
                labsAccordian.setExpandedPane(titledPane);
        }
    }
}
