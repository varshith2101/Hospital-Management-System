package AppointmentBookingCodes;
import DoctorRelatedCodes.Doctor;
import PatientRelatedCodes.Patient;

public class Appointment {
    private String date;
    private String time;
    private String reason;
    private int appointmentIndex;
    private Doctor doctor; 
    private Patient patient;
    private static int NumAppointments;

    
    public Appointment(Doctor doctor, Patient patient, String date, String time, String reason) {
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.appointmentIndex = doctor.getNumOfAppointments() + 1;
        this.doctor = doctor; 
        this.patient = patient;
        doctor.setAppointment(this);
        patient.setPatientAppointment(this);
        NumAppointments++;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public int getAppointmentIndex() {
        return appointmentIndex;
    }

    public static int getNumAppointments() {
        return NumAppointments;
    }

    public void showAppointments(String docID, String date, String time) {
        if (this.doctor.getId().equals(docID) && this.date.equals(date) && this.time.equals(time)) {
            System.out.println("Doctor ID: " + this.doctor.getId());
            System.out.println("Patient ID: " + this.patient.getId());
            System.out.println("Date: " + this.date);
            System.out.println("Time: " + this.time);
            System.out.println("Reason: " + this.reason);
            System.out.println("Appointment Index: " + this.appointmentIndex);
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAppointmentIndex(int appointmentIndex) {
        this.appointmentIndex = appointmentIndex;
    }
}
