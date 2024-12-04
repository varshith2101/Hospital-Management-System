package PatientRelatedCodes;
import AppointmentBookingCodes.Appointment;
import MedicalHistoryRelatedCodes.MedHistory;
import interfaces.iPatient;

public class Patient implements iPatient{
    private String name;
    private String id;
    private int age;
    private int ht;
    private int wt; 
    private String gender;
    private int NUMofAppointments;
    private Appointment[] appointments;
    private static int numPatients = 0;

    private MedHistory medHistory;

    public Patient(String name, int age, int ht, int wt, String gender, MedHistory medHistory) {
        this.name = name;
        this.age = age;
        this.ht = ht;
        this.wt = wt;
        this.gender = gender;
        this.NUMofAppointments = 0;
        this.appointments = new Appointment[100]; 
        this.medHistory = medHistory;
        numPatients++;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getHt() {
        return ht;
    }

    public int getWt() {
        return wt;
    }

    public String getGender() {
        return gender;
    }

    public int getNUMofAppointments() {
        return NUMofAppointments;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public static int getNumPatients() {
        return numPatients;
    }

    public MedHistory getMedHistory(){
        return medHistory;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHt(int ht) {
        this.ht = ht;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPatientAppointment(Appointment appointment) {
        appointments[NUMofAppointments] = appointment;
        NUMofAppointments++;
    }

   
}
