package MedicalHistoryRelatedCodes;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;


public class MedHistory {
    private CheckBox hasDiabetes;
    private CheckBox hadHeartAttacks;
    private CheckBox hadStrokes;
    private CheckBox hadSeizures;
    private CheckBox hadSurgeries;
    private TextField[] surgeriesInfo;
    private CheckBox hadImplants;
    private TextField[] implantsInfo;
    private CheckBox hasOtherIssues;
    private TextField[] otherIssuesInfo;
    private CheckBox consumesMeds;
    private TextField[] medsInfo;
    private CheckBox hasAllergies;
    private TextField[] allergiesInfo;
    private CheckBox hasMedRestrictions;
    private TextField[] medRestrictionsInfo;
    private GridPane gridPane;

    public MedHistory(GridPane gridPane){
        this.gridPane = gridPane;
    }
    public MedHistory(VBox vbox) {
        hasDiabetes = new CheckBox("Diabetes");
        hadHeartAttacks = new CheckBox("Heart Attacks");
        hadStrokes = new CheckBox("Strokes");
        hadSeizures = new CheckBox("Seizures");
        hadSurgeries = new CheckBox("Surgeries");
        hadSurgeries.setOnAction(e -> {
            if (hadSurgeries.isSelected()) {
                Label label = new Label("Enter the number of surgeries the patient had");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    surgeriesInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label surgeryLabel = new Label("Enter the surgery");
                        TextField surgeryField = new TextField();
                        surgeriesInfo[i] = surgeryField;
                        vbox.getChildren().addAll(surgeryLabel, surgeryField);
                    }
                });
            } else {
                surgeriesInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });
        hadImplants = new CheckBox("Implants");
        hadImplants.setOnAction(e -> {
            if (hadImplants.isSelected()) {
                Label label = new Label("Enter the number of implants the patient had");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    implantsInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label implantLabel = new Label("Enter the implant");
                        TextField implantField = new TextField();
                        implantsInfo[i] = implantField;
                        vbox.getChildren().addAll(implantLabel, implantField);
                    }
                });
            } else {
                implantsInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });
        hasOtherIssues = new CheckBox("Other Issues");
        hasOtherIssues.setOnAction(e -> {
            if (hasOtherIssues.isSelected()) {
                Label label = new Label("Enter the number of issues the patient has");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    otherIssuesInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label issueLabel = new Label("Enter the issue");
                        TextField issueField = new TextField();
                        otherIssuesInfo[i] = issueField;
                        vbox.getChildren().addAll(issueLabel, issueField);
                    }
                });
            } else {
                otherIssuesInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });
        consumesMeds = new CheckBox("Consumes Medications");
        consumesMeds.setOnAction(e -> {
            if (consumesMeds.isSelected()) {
                Label label = new Label("Enter the number of medications the patient consumes");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    medsInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label medLabel = new Label("Enter the medication");
                        TextField medField = new TextField();
                        medsInfo[i] = medField;
                        vbox.getChildren().addAll(medLabel, medField);
                    }
                });
            } else {
                medsInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });
        hasAllergies = new CheckBox("Has Allergies");
        hasAllergies.setOnAction(e -> {
            if (hasAllergies.isSelected()) {
                Label label = new Label("Enter the number of allergies the patient has");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    allergiesInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label allergyLabel = new Label("Enter the allergy");
                        TextField allergyField = new TextField();
                        allergiesInfo[i] = allergyField;
                        vbox.getChildren().addAll(allergyLabel, allergyField);
                    }
                });
            } else {
                allergiesInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });
        hasMedRestrictions = new CheckBox("Medication Restrictions");
        hasMedRestrictions.setOnAction(e -> {
            if (hasMedRestrictions.isSelected()) {
                Label label = new Label("Enter the number of medication restrictions the patient has");
                TextField numField = new TextField();
                vbox.getChildren().addAll(label, numField);
                numField.setOnAction(event -> {
                    int num = Integer.parseInt(numField.getText());
                    medRestrictionsInfo = new TextField[num];
                    for (int i = 0; i < num; i++) {
                        Label restrictionLabel = new Label("Enter the medication restriction");
                        TextField restrictionField = new TextField();
                        medRestrictionsInfo[i] = restrictionField;
                        vbox.getChildren().addAll(restrictionLabel, restrictionField);
                    }
                });
            } else {
                medRestrictionsInfo = null;
                vbox.getChildren().removeIf(node -> node instanceof Label || node instanceof TextField);
            }
        });

        vbox.getChildren().addAll(
                hasDiabetes,
                hadHeartAttacks,
                hadStrokes,
                hadSeizures,
                hadSurgeries,
                hadImplants,
                hasOtherIssues,
                consumesMeds,
                hasAllergies,
                hasMedRestrictions
        );
    }

    public void dispMedHistory(GridPane gridPane) {
    VBox vbox = new VBox();
vbox.setSpacing(10);

vbox.getChildren().addAll(
    new Label("Diabetes: " + (hasDiabetes.isSelected() ? "Yes" : "No")),
    new Label("Heart Attacks: " + (hadHeartAttacks.isSelected() ? "Yes" : "No")),
    new Label("Strokes: " + (hadStrokes.isSelected() ? "Yes" : "No")),
    new Label("Seizures: " + (hadSeizures.isSelected() ? "Yes" : "No")),
    new Label("Surgeries: " + (hadSurgeries.isSelected() ? "Yes" : "No"))
);

if (hadSurgeries.isSelected()) {
    Label surgeriesInfoLabel = new Label("Surgeries Info:");
    vbox.getChildren().add(surgeriesInfoLabel);
    for (int i = 0; i < surgeriesInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + surgeriesInfo[i].getText()));
    }
}

vbox.getChildren().addAll(
    new Label("Implants: " + (hadImplants.isSelected() ? "Yes" : "No"))
);

if (hadImplants.isSelected()) {
    Label implantsInfoLabel = new Label("Implants Info:");
    vbox.getChildren().add(implantsInfoLabel);
    for (int i = 0; i < implantsInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + implantsInfo[i].getText()));
    }
}

vbox.getChildren().addAll(
    new Label("Other Issues: " + (hasOtherIssues.isSelected() ? "Yes" : "No"))
);

if (hasOtherIssues.isSelected()) {
    Label otherIssuesInfoLabel = new Label("Other Issues Info:");
    vbox.getChildren().add(otherIssuesInfoLabel);
    for (int i = 0; i < otherIssuesInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + otherIssuesInfo[i].getText()));
    }
}

vbox.getChildren().addAll(
    new Label("Consumes Medications: " + (consumesMeds.isSelected() ? "Yes" : "No"))
);

if (consumesMeds.isSelected()) {
    Label medsInfoLabel = new Label("Medications Info:");
    vbox.getChildren().add(medsInfoLabel);
    for (int i = 0; i < medsInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + medsInfo[i].getText()));
    }
}

vbox.getChildren().addAll(
    new Label("Has Allergies: " + (hasAllergies.isSelected() ? "Yes" : "No"))
);

if (hasAllergies.isSelected()) {
    Label allergiesInfoLabel = new Label("Allergies Info:");
    vbox.getChildren().add(allergiesInfoLabel);
    for (int i = 0; i < allergiesInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + allergiesInfo[i].getText()));
    }
}

vbox.getChildren().addAll(
    new Label("Medication Restrictions: " + (hasMedRestrictions.isSelected() ? "Yes" : "No"))
);

if (hasMedRestrictions.isSelected()) {
    Label medRestrictionsInfoLabel = new Label("Medication Restrictions Info:");
    vbox.getChildren().add(medRestrictionsInfoLabel);
    for (int i = 0; i < medRestrictionsInfo.length; i++) {
        vbox.getChildren().add(new Label("- " + medRestrictionsInfo[i].getText()));
    }
}
    Scene scene = new Scene(vbox, 300, 400);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Medical History Details");
    stage.show();
    }  
}