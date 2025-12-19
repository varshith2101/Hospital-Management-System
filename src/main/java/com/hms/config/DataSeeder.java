package com.hms.config;

import com.hms.domain.*;
import com.hms.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final AppointmentRepository appointmentRepository;
    private final BedAvailabilityRepository bedAvailabilityRepository;
    private final Random random = new Random();

    public DataSeeder(DoctorRepository doctorRepository,
                      PatientRepository patientRepository,
                      MedicalHistoryRepository medicalHistoryRepository,
                      AppointmentRepository appointmentRepository,
                      BedAvailabilityRepository bedAvailabilityRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.appointmentRepository = appointmentRepository;
        this.bedAvailabilityRepository = bedAvailabilityRepository;
    }

    @Override
    public void run(String... args) {
        // Only seed if database is empty
        if (doctorRepository.count() == 0) {
            System.out.println("Seeding database with sample data...");
            seedDoctors();
            seedPatients();
            seedBeds();
            seedAppointments();
            System.out.println("Database seeding completed!");
        } else {
            System.out.println("Database already contains data. Skipping seeding.");
        }
    }

    private void seedDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        String[][] doctorData = {
            {"Dr. Sarah Johnson", "45", "Cardiology", "MD, FACC", "Morning", "Mon,Wed,Fri", "2015-03-15", "180000"},
            {"Dr. Michael Chen", "52", "Neurology", "MD, PhD", "Evening", "Tue,Thu,Sat", "2012-06-20", "195000"},
            {"Dr. Emily Rodriguez", "38", "Pediatrics", "MD, FAAP", "Morning", "Mon,Tue,Wed,Thu,Fri", "2018-01-10", "165000"},
            {"Dr. James Wilson", "48", "Orthopedics", "MD, FAAOS", "Morning", "Mon,Wed,Fri", "2014-09-05", "175000"},
            {"Dr. Priya Sharma", "42", "Dermatology", "MD, FAAD", "Evening", "Tue,Wed,Thu", "2016-11-12", "155000"},
            {"Dr. Robert Brown", "55", "General Surgery", "MD, FACS", "Morning", "Mon,Tue,Wed,Thu,Fri", "2010-04-18", "205000"},
            {"Dr. Lisa Martinez", "40", "Gynecology", "MD, FACOG", "Morning", "Mon,Wed,Fri,Sat", "2017-02-28", "170000"},
            {"Dr. David Kim", "36", "Emergency Medicine", "MD, FACEP", "Night", "All Days", "2019-07-01", "185000"},
            {"Dr. Amanda Taylor", "44", "Psychiatry", "MD, MRCPsych", "Morning", "Mon,Tue,Wed,Thu", "2015-08-22", "160000"},
            {"Dr. Christopher Lee", "50", "Oncology", "MD, PhD, FASCO", "Morning", "Mon,Wed,Fri", "2013-05-15", "200000"},
            {"Dr. Maria Garcia", "39", "Ophthalmology", "MD, FACS", "Evening", "Tue,Thu,Sat", "2018-10-03", "168000"},
            {"Dr. Kevin Patel", "46", "Radiology", "MD, FACR", "Morning", "Mon,Tue,Wed,Thu,Fri", "2014-12-08", "178000"},
            {"Dr. Jennifer Wu", "41", "Endocrinology", "MD, FACE", "Morning", "Mon,Wed,Fri", "2016-03-25", "162000"},
            {"Dr. Thomas Anderson", "54", "Pulmonology", "MD, FCCP", "Morning", "Tue,Thu,Sat", "2011-09-14", "188000"},
            {"Dr. Rachel Green", "37", "Internal Medicine", "MD, FACP", "Evening", "Mon,Tue,Wed,Thu,Fri", "2019-01-20", "158000"}
        };

        for (String[] data : doctorData) {
            Doctor doctor = new Doctor();
            doctor.setName(data[0]);
            doctor.setAge(Integer.parseInt(data[1]));
            doctor.setSpecialization(data[2]);
            doctor.setQualification(data[3]);
            doctor.setShiftType(data[4]);
            doctor.setOpdDays(data[5]);
            doctor.setDateOfJoining(data[6]);
            doctor.setSalary(Double.parseDouble(data[7]));
            doctor.setIsAvailable(random.nextBoolean() || random.nextBoolean()); // 75% available
            doctors.add(doctor);
        }

        doctorRepository.saveAll(doctors);
        System.out.println("Seeded " + doctors.size() + " doctors");
    }

    private void seedPatients() {
        List<Patient> patients = new ArrayList<>();
        List<MedicalHistory> histories = new ArrayList<>();

        String[][] patientData = {
            {"John Smith", "65", "175", "78", "Male", "+1-555-0101"},
            {"Emma Johnson", "32", "162", "58", "Female", "+1-555-0102"},
            {"Michael Williams", "45", "180", "85", "Male", "+1-555-0103"},
            {"Sophia Brown", "28", "168", "62", "Female", "+1-555-0104"},
            {"William Jones", "58", "172", "92", "Male", "+1-555-0105"},
            {"Olivia Davis", "41", "165", "68", "Female", "+1-555-0106"},
            {"James Miller", "52", "178", "88", "Male", "+1-555-0107"},
            {"Ava Wilson", "36", "160", "55", "Female", "+1-555-0108"},
            {"Robert Moore", "71", "170", "75", "Male", "+1-555-0109"},
            {"Isabella Taylor", "29", "166", "60", "Female", "+1-555-0110"},
            {"David Anderson", "48", "182", "95", "Male", "+1-555-0111"},
            {"Mia Thomas", "55", "158", "70", "Female", "+1-555-0112"},
            {"Joseph Jackson", "63", "176", "82", "Male", "+1-555-0113"},
            {"Charlotte White", "34", "164", "59", "Female", "+1-555-0114"},
            {"Daniel Harris", "42", "179", "87", "Male", "+1-555-0115"},
            {"Amelia Martin", "50", "161", "65", "Female", "+1-555-0116"},
            {"Matthew Thompson", "67", "174", "80", "Male", "+1-555-0117"},
            {"Harper Garcia", "31", "169", "63", "Female", "+1-555-0118"},
            {"Christopher Martinez", "39", "177", "84", "Male", "+1-555-0119"},
            {"Evelyn Robinson", "56", "163", "72", "Female", "+1-555-0120"},
            {"Andrew Clark", "44", "181", "90", "Male", "+1-555-0121"},
            {"Abigail Rodriguez", "26", "157", "52", "Female", "+1-555-0122"},
            {"Joshua Lewis", "60", "173", "78", "Male", "+1-555-0123"},
            {"Emily Lee", "33", "167", "61", "Female", "+1-555-0124"},
            {"Ryan Walker", "49", "175", "86", "Male", "+1-555-0125"}
        };

        for (int i = 0; i < patientData.length; i++) {
            String[] data = patientData[i];

            // Create medical history
            MedicalHistory history = new MedicalHistory();
            history.setHasDiabetes(random.nextDouble() < 0.2); // 20% have diabetes
            history.setHadHeartAttacks(random.nextDouble() < 0.1); // 10% had heart attacks
            history.setHadStrokes(random.nextDouble() < 0.08); // 8% had strokes
            history.setHadSeizures(random.nextDouble() < 0.05); // 5% had seizures

            if (random.nextBoolean()) {
                history.setAllergies(getRandomAllergies());
            }
            if (random.nextBoolean()) {
                history.setMedications(getRandomMedications());
            }
            if (random.nextDouble() < 0.15) {
                history.setSurgeriesInfo(getRandomSurgery());
            }
            if (random.nextDouble() < 0.1) {
                history.setImplantsInfo(getRandomImplant());
            }

            histories.add(history);

            // Create patient
            Patient patient = new Patient();
            patient.setName(data[0]);
            patient.setAge(Integer.parseInt(data[1]));
            patient.setHeight(Integer.parseInt(data[2]));
            patient.setWeight(Integer.parseInt(data[3]));
            patient.setGender(data[4]);
            patient.setPhoneNumber(data[5]);
            patient.setIsAdmitted(i < 5); // First 5 patients are admitted

            if (patient.getIsAdmitted()) {
                patient.setBedNumber("B" + (100 + i));
            }

            patient.setMedicalHistory(history);
            patients.add(patient);
        }

        patientRepository.saveAll(patients);
        System.out.println("Seeded " + patients.size() + " patients with medical histories");
    }

    private void seedBeds() {
        List<BedAvailability> beds = new ArrayList<>();
        List<Patient> admittedPatients = patientRepository.findAll().stream()
            .filter(Patient::getIsAdmitted)
            .limit(5)
            .toList();

        // ICU Beds
        for (int i = 1; i <= 10; i++) {
            BedAvailability bed = new BedAvailability();
            bed.setBedNumber("ICU-" + i);
            bed.setWardName("ICU");
            bed.setBedType("ICU");
            bed.setIsAvailable(i > 3); // First 3 occupied
            if (i <= 3 && i <= admittedPatients.size()) {
                bed.setPatient(admittedPatients.get(i - 1));
            }
            beds.add(bed);
        }

        // General Ward Beds
        for (int i = 1; i <= 30; i++) {
            BedAvailability bed = new BedAvailability();
            bed.setBedNumber("GEN-" + i);
            bed.setWardName("General");
            bed.setBedType("General");
            bed.setIsAvailable(i > 2); // First 2 occupied
            if (i <= 2 && (i + 2) < admittedPatients.size()) {
                bed.setPatient(admittedPatients.get(i + 2));
            }
            beds.add(bed);
        }

        // VIP Beds
        for (int i = 1; i <= 5; i++) {
            BedAvailability bed = new BedAvailability();
            bed.setBedNumber("VIP-" + i);
            bed.setWardName("VIP");
            bed.setBedType("VIP");
            bed.setIsAvailable(true);
            beds.add(bed);
        }

        // Emergency Beds
        for (int i = 1; i <= 8; i++) {
            BedAvailability bed = new BedAvailability();
            bed.setBedNumber("ER-" + i);
            bed.setWardName("Emergency");
            bed.setBedType("Emergency");
            bed.setIsAvailable(i > 2); // First 2 occupied
            beds.add(bed);
        }

        bedAvailabilityRepository.saveAll(beds);
        System.out.println("Seeded " + beds.size() + " beds");
    }

    private void seedAppointments() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        List<Appointment> appointments = new ArrayList<>();

        String[] reasons = {
            "Annual checkup",
            "Follow-up visit",
            "Chest pain",
            "Headache and dizziness",
            "Skin rash",
            "Back pain",
            "Fever and cough",
            "Routine blood test",
            "Vaccination",
            "Post-surgery checkup",
            "Abdominal pain",
            "Joint pain",
            "Vision problems",
            "Breathing difficulty",
            "Anxiety and stress"
        };

        Appointment.AppointmentStatus[] statuses = Appointment.AppointmentStatus.values();

        // Create 50 appointments
        for (int i = 0; i < 50; i++) {
            Appointment appointment = new Appointment();
            appointment.setDoctor(doctors.get(random.nextInt(doctors.size())));
            appointment.setPatient(patients.get(random.nextInt(patients.size())));

            // Mix of past, current, and future appointments
            int daysOffset = random.nextInt(60) - 30; // -30 to +30 days
            int hour = 9 + random.nextInt(9); // 9 AM to 5 PM
            appointment.setAppointmentDate(
                LocalDateTime.now()
                    .plusDays(daysOffset)
                    .withHour(hour)
                    .withMinute(random.nextBoolean() ? 0 : 30)
                    .withSecond(0)
                    .withNano(0)
            );

            appointment.setReason(reasons[random.nextInt(reasons.length)]);

            // Past appointments are mostly completed, future are scheduled
            if (daysOffset < 0) {
                appointment.setStatus(random.nextDouble() < 0.8 ?
                    Appointment.AppointmentStatus.COMPLETED :
                    Appointment.AppointmentStatus.CANCELLED);
            } else {
                appointment.setStatus(random.nextDouble() < 0.9 ?
                    Appointment.AppointmentStatus.SCHEDULED :
                    Appointment.AppointmentStatus.CANCELLED);
            }

            appointments.add(appointment);
        }

        appointmentRepository.saveAll(appointments);
        System.out.println("Seeded " + appointments.size() + " appointments");
    }

    private String getRandomAllergies() {
        String[] allergies = {
            "Penicillin",
            "Peanuts",
            "Dust mites",
            "Pollen",
            "Latex",
            "Aspirin",
            "Shellfish",
            "Sulfa drugs"
        };
        int count = 1 + random.nextInt(2);
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selected.add(allergies[random.nextInt(allergies.length)]);
        }
        return String.join(", ", selected);
    }

    private String getRandomMedications() {
        String[] medications = {
            "Lisinopril 10mg daily",
            "Metformin 500mg twice daily",
            "Levothyroxine 50mcg daily",
            "Atorvastatin 20mg daily",
            "Omeprazole 20mg daily",
            "Metoprolol 25mg twice daily",
            "Amlodipine 5mg daily"
        };
        int count = 1 + random.nextInt(3);
        List<String> selected = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selected.add(medications[random.nextInt(medications.length)]);
        }
        return String.join("; ", selected);
    }

    private String getRandomSurgery() {
        String[] surgeries = {
            "Appendectomy (2018)",
            "Cholecystectomy (2015)",
            "Knee arthroscopy (2020)",
            "Cesarean section (2017)",
            "Hernia repair (2019)",
            "Cataract surgery (2021)"
        };
        return surgeries[random.nextInt(surgeries.length)];
    }

    private String getRandomImplant() {
        String[] implants = {
            "Hip replacement (left, 2019)",
            "Dental implant (2020)",
            "Pacemaker (2018)",
            "Knee replacement (right, 2017)",
            "Cochlear implant (2021)"
        };
        return implants[random.nextInt(implants.length)];
    }
}
