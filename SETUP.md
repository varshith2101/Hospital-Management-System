# Healthcare Management System - Setup Guide

## Prerequisites

The following software has been installed and configured:
- ✅ Java JDK 25.0.1 (Temurin)
- ✅ Apache Maven 3.9.12
- ✅ JavaFX 21.0.1 (managed by Maven)

## Project Structure

```
hms/
├── HMS Project/
│   └── proj1/
│       └── src/
│           ├── DRIVER/
│           │   └── App.java (Main application class)
│           ├── DoctorRelatedCodes/
│           ├── PatientRelatedCodes/
│           ├── AppointmentBookingCodes/
│           ├── MedicalHistoryRelatedCodes/
│           └── interfaces/
├── pom.xml (Maven configuration)
└── SETUP.md (this file)
```

## How to Run the Application

### Option 1: Using Maven (Recommended)

Run the application using the JavaFX Maven plugin:

```bash
mvn clean javafx:run
```

This command will:
1. Clean any previous builds
2. Compile the source code
3. Launch the JavaFX application with all necessary modules

### Option 2: Compile and Run Separately

1. Compile the project:
```bash
mvn clean compile
```

2. Run the application:
```bash
mvn javafx:run
```

### Option 3: Create an Executable JAR

Build a standalone JAR file:

```bash
mvn clean package
```

This creates a JAR file in the `target/` directory. However, note that JavaFX applications require special handling for module paths, so Option 1 is recommended for development.

## Application Features

The Healthcare Management System provides the following functionality:

1. **Add Doctor** - Register new doctors with their details (name, ID, age, specialization, qualification, schedule, etc.)
2. **Add Patient** - Register new patients with their details (name, age, height, weight, gender, medical history)
3. **Book Appointment** - Schedule appointments between doctors and patients
4. **View Appointments** - View all appointments for a specific patient
5. **Search Doctor** - Search for doctors by ID or name
6. **Search Patient** - Search for patients by ID or name

## Troubleshooting

### If you encounter module errors:
Make sure you're using the `mvn javafx:run` command, which automatically configures the JavaFX module path.

### If the GUI doesn't appear:
Ensure you're running from the project root directory (`/Users/varshith/sandbox/hms/`) where the `pom.xml` file is located.

### If compilation fails:
1. Clean the project: `mvn clean`
2. Update dependencies: `mvn dependency:resolve`
3. Try compiling again: `mvn compile`

## Development

To modify the source code:
1. Edit files in `HMS Project/proj1/src/`
2. Recompile with `mvn compile`
3. Run with `mvn javafx:run`

## Notes

- The project uses JavaFX for the GUI
- Patient IDs and Doctor IDs are automatically generated
- Data is stored in memory (arrays) and not persisted to disk
- Maximum capacity: 100 doctors, 100 patients, 100 appointments
