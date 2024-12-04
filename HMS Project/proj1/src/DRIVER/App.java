package DRIVER;
import java.util.Arrays;
import java.util.Scanner;

import AppointmentBookingCodes.Appointment;
import DoctorRelatedCodes.Doctor;
import DoctorRelatedCodes.DoctorNotFoundException;
import MedicalHistoryRelatedCodes.MedHistory;
import PatientRelatedCodes.NewPatient;
import PatientRelatedCodes.Patient;
import PatientRelatedCodes.PatientNotFoundException;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Optional;

public class App extends Application{
    private static Doctor[] doctorsArray = new Doctor[100];
    private static Patient[] patientsArray = new Patient[100];
    private static Appointment[] appointment = new Appointment[100];
    private static String[] medHistory = new String[100];
    private static int medHistoryIndex = 0;
    private static TextField genTextField = new TextField();
    private static VBox medHistoryVBox = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Healthcare Management System");

        Button addDoctorButton = new Button("Add Doctor");
        Button addPatientButton = new Button("Add Patient");
        Button bookAppointmentButton = new Button("Book Appointment");
        Button viewAppointmentsButton = new Button("View Appointments");
        Button searchDoctorButton = new Button("Search Doctor");
        Button searchPatientButton = new Button("Search Patient");

        GridPane   gridPane = new GridPane();
        addDoctorButton.setOnAction(e -> addDoctor(gridPane));
        addPatientButton.setOnAction(e -> addPatient(gridPane));
        bookAppointmentButton.setOnAction(e -> bookAppointment(gridPane));
        viewAppointmentsButton.setOnAction(e -> viewAppointments(gridPane));
        searchDoctorButton.setOnAction(e -> searchDoctor(gridPane,doctorsArray));
        searchPatientButton.setOnAction(e -> searchPatient(patientsArray));

        GridPane layout = new GridPane(10,10); 
        layout.add(addDoctorButton, 0, 0);
        layout.add(addPatientButton, 1, 0);
        layout.add(bookAppointmentButton, 0, 1);
        layout.add(viewAppointmentsButton, 1, 1);
        layout.add(searchDoctorButton, 0, 2);
        layout.add(searchPatientButton, 1, 2);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

public void addDoctor(GridPane gridPane) {
    TextField nameTextField = new TextField();
    Label nameLabel = new Label("Name:");
    TextField idTextField = new TextField();
    Label idLabel = new Label("ID:");
    TextField ageTextField = new TextField();
    Label ageLabel = new Label("Age:");
    TextField spclTextField = new TextField();
    Label spclLabel = new Label("Specialization:");
    TextField qualTextField = new TextField();
    Label qualLabel = new Label("Qualification:");
    TextField dorNTextField = new TextField();
    Label dorNLabel = new Label("Day or Night:");
    TextField opdDTextField = new TextField();
    Label opdDLabel = new Label("OPD Days:");
    TextField dojTextField = new TextField();
    Label dojLabel = new Label("Date of Joining:");
    TextField salaryTextField = new TextField();
    Label salaryLabel = new Label("Salary:");

    HBox nameBox = new HBox(nameLabel, nameTextField);
    HBox idBox = new HBox(idLabel, idTextField);
    HBox ageBox = new HBox(ageLabel, ageTextField);
    HBox spclBox = new HBox(spclLabel, spclTextField);
    HBox qualBox = new HBox(qualLabel, qualTextField);
    HBox dorNBox = new HBox(dorNLabel, dorNTextField);
    HBox opdDBox = new HBox(opdDLabel, opdDTextField);
    HBox dojBox = new HBox(dojLabel, dojTextField);
    HBox salaryBox = new HBox(salaryLabel, salaryTextField);

    VBox vbox = new VBox(nameBox, idBox, ageBox, spclBox, qualBox, dorNBox, opdDBox, dojBox, salaryBox);
    vbox.setSpacing(10);

    Button addButton = new Button("Add");
    addButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String name = nameTextField.getText();
            String id = idTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String spcl = spclTextField.getText();
            String qual = qualTextField.getText();
            String dorN = dorNTextField.getText();
            String[] opdD = opdDTextField.getText().split(" ");
            String doj = dojTextField.getText();
            double salary = Double.parseDouble(salaryTextField.getText());

            doctorsArray[Doctor.getNumDoctors()] = new Doctor(name, id, age, spcl, qual, dorN, opdD, doj, salary);
            Label message = new Label("Doctor has been added to DATABASE");
            message.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
            gridPane.add(message, 1, 9);

            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        }
    });

    Button cancelButton = new Button("Cancel");
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    });

    HBox buttonBox = new HBox(addButton, cancelButton);
    buttonBox.setSpacing(10);

    VBox root = new VBox(vbox, buttonBox);
    root.setSpacing(10);
    root.setPadding(new Insets(10));

    Scene scene = new Scene(root, 300, 400);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Add Doctor");
    stage.show();
}

    public void addPatient(GridPane gridPane) {
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageTextField = new TextField();
        Label htLabel = new Label("Height:");
        TextField htTextField = new TextField();
        Label wtLabel = new Label("Weight:");
        TextField wtTextField = new TextField();
        Label genLabel = new Label("Gender");
        TextField genTextField = new TextField();


        HBox nameBox = new HBox(nameLabel, nameTextField);
        HBox ageBox = new HBox(ageLabel, ageTextField);
        HBox htBox = new HBox(htLabel, htTextField);
        HBox wtBox = new HBox(wtLabel, wtTextField);
        HBox genBox = new HBox(genLabel, genTextField);

        VBox vbox = new VBox(nameBox, ageBox, htBox, wtBox,genBox);
        vbox.setSpacing(10);
       
        MedHistory medHistory = new MedHistory(medHistoryVBox);

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                int ht = Integer.parseInt(htTextField.getText());
                int wt = Integer.parseInt(wtTextField.getText());
                String gender = genTextField.getText();
                medHistory.dispMedHistory(gridPane);

                patientsArray[Patient.getNumPatients()] = new NewPatient(name, age, ht, wt, gender, medHistory);
                                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                        successAlert.setTitle("Add Patient");
                                        successAlert.setHeaderText(null);
                                        String id = patientsArray[Patient.getNumPatients() - 1].getId();
                                        successAlert.setContentText("Patient has been added to DATABASE\nPatient ID: " + id);                                       
                                        successAlert.showAndWait();

                                        Stage stage = (Stage) addButton.getScene().getWindow();
                                        stage.close();                                        
                                    }

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
                public void handle(ActionEvent event) {
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
                }
        });

        HBox buttonBox = new HBox(addButton, cancelButton);
        buttonBox.setSpacing(10);

        VBox root = new VBox(vbox, medHistoryVBox, buttonBox);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 300, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add Patient");
        stage.show();
        }

        public static void bookAppointment(GridPane gridPane) {
            TextField doctorIdTextField = new TextField();
            Label doctorIdLabel = new Label("Doctor ID:");
            TextField patientIdTextField = new TextField();
            Label patientIdLabel = new Label("Patient ID:");
            TextField dateTextField = new TextField();
            Label dateLabel = new Label("Date:");
            TextField timeTextField = new TextField();
            Label timeLabel = new Label("Time:");
            TextField reasonTextField = new TextField();
            Label reasonLabel = new Label("Reason:");

            HBox doctorIdBox = new HBox(doctorIdLabel, doctorIdTextField);
            HBox patientIdBox = new HBox(patientIdLabel, patientIdTextField);
            HBox dateBox = new HBox(dateLabel, dateTextField);
            HBox timeBox = new HBox(timeLabel, timeTextField);
            HBox reasonBox = new HBox(reasonLabel, reasonTextField);

            VBox vbox = new VBox(doctorIdBox, patientIdBox, dateBox, timeBox, reasonBox);
            vbox.setSpacing(10);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Book Appointment");
            alert.setHeaderText("Enter details for new appointment:");
            alert.getDialogPane().setContent(vbox);

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    String doctorId = doctorIdTextField.getText();
                    String patientId = patientIdTextField.getText();
                    String date = dateTextField.getText();
                    String time = timeTextField.getText();
                    String reason = reasonTextField.getText();

                   try{
                       appointment[Appointment.getNumAppointments()] = new Appointment(getDoctorById(doctorId, doctorsArray), getPatientById(patientId, patientsArray), date, time, reason);
                   }
                   catch(DoctorNotFoundException e){
                       Label excL = new Label(e.toString());
                       VBox vboxDocExc = new VBox(excL);
                       Scene scene = new Scene(vboxDocExc, 300, 400);
                       Stage stage = new Stage();
                       stage.setScene(scene);
                       stage.setTitle(" ");
                       stage.show();
                   }

                   catch(PatientNotFoundException e){
                       Label excL = new Label(e.toString());
                       VBox vboxPatExc = new VBox(excL);
                       Scene scene = new Scene(vboxPatExc, 300, 400);
                       Stage stage = new Stage();
                       stage.setScene(scene);
                       stage.setTitle(" ");
                       stage.show();
                   }
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Book Appointment");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Appointment has been booked successfully");
                    successAlert.showAndWait();
                }
            });
        }

        public static void viewAppointments(GridPane gridPane) {
            TextField patientIdTextField = new TextField();
            Label patientIdLabel = new Label("Patient ID:");

            HBox patientIdBox = new HBox(patientIdLabel, patientIdTextField);

            VBox vbox = new VBox(patientIdBox);
            vbox.setSpacing(10);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("View Appointments");
            alert.setHeaderText("Enter Patient ID to view appointments:");
            alert.getDialogPane().setContent(vbox);

            alert.showAndWait().ifPresent(response -> {
                try{
                    if (response == ButtonType.OK) {
                        String enteredPatientId = patientIdTextField.getText();
                        Patient patientToViewAppointments = getPatientById(enteredPatientId, patientsArray);


                        if (patientToViewAppointments != null) {
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("View Appointments");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Appointments for Patient " + patientToViewAppointments.getName() + ":");

                            Appointment[] patientAppointments = patientToViewAppointments.getAppointments();

                            for (Appointment patientAppointment : patientAppointments) {
                                if (patientAppointment != null) {
                                    Doctor doctorOfAppointment = patientAppointment.getDoctor();
                                    successAlert.setContentText(successAlert.getContentText() + "\nDoctor Name: " + doctorOfAppointment.getName());
                                    successAlert.setContentText(successAlert.getContentText() + "\nDate: " + patientAppointment.getDate());
                                    successAlert.setContentText(successAlert.getContentText() + "\nTime: " + patientAppointment.getTime());
                                    successAlert.setContentText(successAlert.getContentText() + "\n---------------------");
                                }
                            }
                            successAlert.showAndWait();
                        } else {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("View Appointments");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Patient not found!");
                            errorAlert.showAndWait();
                        }
                    }
                }


                catch(PatientNotFoundException e){
                    Label excL = new Label(e.toString());
                    VBox vboxPatExc = new VBox(excL);
                    Scene scene = new Scene(vboxPatExc, 300, 400);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle(" ");
                    stage.show();
                }
            });           
        }

        public static Doctor getDoctorById(String doctorId, Doctor[] doctorsArray) throws DoctorNotFoundException{
            for (Doctor doctor : doctorsArray) {
                if (doctor != null && doctor.getId().equals(doctorId)) {
                    return doctor;
                }
            }
            throw new DoctorNotFoundException();
        }

        public static Patient getPatientById(String patientId, Patient[] patients) throws PatientNotFoundException{
            for (Patient patient : patients) {
                if (patient != null && patient.getId().equals(patientId)) {
                    return patient;
                }
            }
            throw new PatientNotFoundException();
        }

        public static Doctor getDoctorByName(String doctorName, Doctor[] doctorsArray) throws DoctorNotFoundException{
            for (Doctor doctor : doctorsArray) {
                if (doctor != null && doctor.getName().equalsIgnoreCase(doctorName)) {
                    return doctor;
                }
            }
            throw new DoctorNotFoundException();
        }


        public static void searchDoctor(GridPane gridPane, Doctor[] doctorsArray) {
            Label choiceLabel = new Label("1.Search by Doctor ID\n2.Search by Doctor Name\nEnter your choice:");
            TextField choiceTextField = new TextField();

            HBox choiceHBox = new HBox(choiceLabel, choiceTextField);
            
            Button searchButton = new Button("Search");
            searchButton.setOnAction(event -> {
                int choice = Integer.parseInt(choiceTextField.getText());
                switch (choice) {
                    case 1:
                        Label idLabel = new Label("Enter Doctor ID:");
                        TextField idTextField = new TextField();
                        HBox idHBox = new HBox(idLabel, idTextField);
                        Button searchButton1 = new Button("Search");
                        searchButton1.setOnAction(event1 -> {
                        String doctorIdToSearch = idTextField.getText();
                            Doctor doctorById = null;
                            try {
                                doctorById = getDoctorById(doctorIdToSearch, doctorsArray);
                            } catch (DoctorNotFoundException e) {
                                Label excL = new Label(e.toString());
                                VBox vboxDocExc = new VBox(excL);
                                Scene scene = new Scene(vboxDocExc, 300, 400);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle(" ");
                                stage.show();
                            }
                            if (doctorById != null) {
                            displayDoctorDetails(gridPane, doctorById);
                        } else {
                            Label notFoundLabel = new Label("Doctor not found!");
                            gridPane.add(notFoundLabel, 0, 6);
                        }
                    });
                        HBox buttonBox1 = new HBox(searchButton1);
                        buttonBox1.setSpacing(10);
                        VBox root1 = new VBox(idHBox, buttonBox1);
                        root1.setSpacing(10);
                        root1.setPadding(new Insets(10));
                        Scene scene = new Scene(root1, 300, 400);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Search Doctor");
                        stage.show();
                        break;

                    case 2:

                        Label nameLabel = new Label("Enter Doctor Name:");
                        TextField nameTextField = new TextField();
                        HBox nameHBox = new HBox(nameLabel, nameTextField);
                        Button searchButton2 = new Button("Search");
                        searchButton2.setOnAction(event2 -> {
                        String doctorNameToSearch = nameTextField.getText();
                            Doctor doctorByName = null;
                            try {
                                doctorByName = getDoctorByName(doctorNameToSearch, doctorsArray);
                            } catch (DoctorNotFoundException e) {
                                Label excL = new Label(e.toString());
                                VBox vboxDocExc = new VBox(excL);
                                Scene scene1 = new Scene(vboxDocExc, 300, 400);
                                Stage stage1 = new Stage();
                                stage1.setScene(scene1);
                                stage1.setTitle(" ");
                                stage1.show();
                            }
                            if (doctorByName != null) {
                            displayDoctorDetails(gridPane, doctorByName);
                        } else {
                            Label notFoundLabel = new Label("Doctor not found!");
                            gridPane.add(notFoundLabel, 0, 6);
                        }
                    });
                        HBox buttonBox2 = new HBox(searchButton2);
                        buttonBox2.setSpacing(10);
                        VBox root2 = new VBox(nameHBox, buttonBox2);
                        root2.setSpacing(10);
                        root2.setPadding(new Insets(10));
                        Scene scene1 = new Scene(root2, 300, 400);
                        Stage stage1 = new Stage();
                        stage1.setScene(scene1);
                        stage1.setTitle("Search Doctor");
                        stage1.show();
                        break;

                    default:
                       System.out.println("INVALID OPTION");
                }
            });

            HBox buttonBox = new HBox(searchButton);
            buttonBox.setSpacing(10);    
             
    VBox root2 = new VBox(choiceHBox,buttonBox);
    root2.setSpacing(10);
    root2.setPadding(new Insets(10));

    Scene scene = new Scene(root2, 300, 400);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Search Doctor");
    stage.show();
        }

    public static void searchPatient(Patient[] patientsArray) {
        Label choiceLabel = new Label("Search Patient:\n1. Search by ID\n2. Search by Name\nEnter your choice:");
        TextField choiceTextField = new TextField();

        HBox choiceHBox = new HBox(choiceLabel, choiceTextField);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int choice = Integer.parseInt(choiceTextField.getText());
            switch (choice) {
                case 1:
                    Label idLabel = new Label("Enter Patient ID:");
                    TextField idTextField = new TextField();
                    HBox idHBox = new HBox(idLabel, idTextField);
                    Button searchButton1 = new Button("Search");
                    searchButton1.setOnAction(event1 -> {
                        String patientIdToSearch = idTextField.getText();
                        Patient patientById = null;
                        try {
                            patientById = getPatientById(patientIdToSearch, patientsArray);
                        } catch (PatientNotFoundException e) {
                            Label excL = new Label(e.toString());
                            VBox vboxPatientExc = new VBox(excL);
                            Scene scene = new Scene(vboxPatientExc, 300, 400);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setTitle(" ");
                            stage.show();
                        }
                        if (patientById != null) {
                            displayPatientDetails(patientById);
                        }
                    });
                    HBox buttonBox1 = new HBox(searchButton1);
                    buttonBox1.setSpacing(10);
                    VBox root1 = new VBox(idHBox, buttonBox1);
                    root1.setSpacing(10);
                    root1.setPadding(new Insets(10));
                    Scene scene = new Scene(root1, 300, 400);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Search Patient");
                    stage.show();
                    break;

                case 2:
                    Label nameLabel = new Label("Enter Patient Name:");
                    TextField nameTextField = new TextField();
                    HBox nameHBox = new HBox(nameLabel, nameTextField);
                    Button searchButton2 = new Button("Search");
                    searchButton2.setOnAction(event2 -> {
                        String patientNameToSearch = nameTextField.getText();
                        Patient patientByName = null;
                        try {
                            patientByName = getPatientByName(patientNameToSearch, patientsArray);
                        } catch (PatientNotFoundException e) {
                            Label excL = new Label(e.toString());
                            VBox vboxPatientExc = new VBox(excL);
                            Scene scene1 = new Scene(vboxPatientExc, 300, 400);
                            Stage stage1 = new Stage();
                            stage1.setScene(scene1);
                            stage1.setTitle(" ");
                            stage1.show();
                        }
                        if (patientByName != null) {
                            displayPatientDetails(patientByName);
                        }
                    });
                    HBox buttonBox2 = new HBox(searchButton2);
                    buttonBox2.setSpacing(10);
                    VBox root2 = new VBox(nameHBox, buttonBox2);
                    root2.setSpacing(10);
                    root2.setPadding(new Insets(10));
                    Scene scene1 = new Scene(root2, 300, 400);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene1);
                    stage1.setTitle("Search Patient");
                    stage1.show();
                    break;

                default:
                    System.out.println("INVALID OPTION");
            }
        });

        HBox buttonBox = new HBox(searchButton);
        buttonBox.setSpacing(10);

        VBox root2 = new VBox(choiceHBox, buttonBox);
        root2.setSpacing(10);
        root2.setPadding(new Insets(10));

        Scene scene = new Scene(root2, 300, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Search Patient");
        stage.show();
    }

    public static Patient getPatientByName(String patientName, Patient[] patientsArray) throws PatientNotFoundException{
                                for (Patient patient : patientsArray) {
                                    if (patient != null && patient.getName().equalsIgnoreCase(patientName)) {
                                        return patient;
                                    }
                                }
                            throw new PatientNotFoundException();
    }

    public static void displayPatientDetails( Patient patient) {
        VBox detailsVBox = new VBox(10); 

        detailsVBox.getChildren().addAll(
                new Label("Patient Details:"),
                new Label("Name: " + patient.getName()),
                new Label("ID: " + patient.getId()),
                new Label("Age: " + patient.getAge()),
                new Label("Height: " + patient.getHt()),
                new Label("Weight: " + patient.getWt()),
                new Label("Gender: " + patient.getGender())
        );
        Scene scene = new Scene(detailsVBox, 300, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Patient Details");
        stage.show();
}
                            
public static void displayDoctorDetails(GridPane gridPane, Doctor doctor) {
    
    VBox vboxDocDetails = new VBox();
    vboxDocDetails.setSpacing(10);
    
    vboxDocDetails.getChildren().addAll(
        new Label("Doctor Details:"),
        new Label("Name: " + doctor.getName()),
        new Label("ID: " + doctor.getId()),
        new Label("Age: " + doctor.getAge()),
        new Label("Specialization: " + doctor.getSpcl()),
        new Label("Qualification: " + doctor.getQual()),
        new Label("Day or Night: " + doctor.getDorN()),
        new Label("OPD Days: " + Arrays.toString(doctor.getOpdD())),
        new Label("Date of Joining: " + doctor.getDoj()),
        new Label("Salary: " + doctor.getSalary())
    );
    
    Scene scene = new Scene(vboxDocDetails, 300, 400);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.setTitle("Doctor Details");
    stage.show();
    

}
}