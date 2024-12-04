package DoctorRelatedCodes;
import AppointmentBookingCodes.Appointment;
import interfaces.iDoctor;

public class Doctor implements iDoctor{
    private String name;
    private String id;
    private int age;
    private String spcl;
    private String qual;
    private String DorN;
    private String[] opdD;
    private String doj;
    private double salary;
    private static int numDoctors = 0;
    private int numOfAppointments;
    private Appointment[] appointments;

    public Doctor(String name, String id, int age, String spcl, String qual, String DorN,
                  String[] opdD, String doj, double salary) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.spcl = spcl;
        this.qual = qual;
        this.DorN = DorN;
        this.opdD = opdD;
        this.doj = doj;
        this.salary = salary;

        this.numOfAppointments = 0;
        this.appointments = new Appointment[100]; 
        numDoctors++;
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

    public String getSpcl() {
        return spcl;
    }

    public String getQual() {
        return qual;
    }

    public String getDorN() {
        return DorN;
    }

    public String[] getOpdD() {
        return opdD;
    }

    public String getDoj() {
        return doj;
    }

    public double getSalary() {
        return salary;
    }

    public int getNumOfAppointments() {
        return numOfAppointments;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    public void setAppointment(Appointment appointment) {
        appointments[numOfAppointments] = appointment;
        numOfAppointments++;
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

    public void setSpcl(String spcl) {
        this.spcl = spcl;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public void setDorN(String DorN) {
        this.DorN = DorN;
    }

    public void setOpdD(String[] opdD) {
        this.opdD = opdD;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static int getNumDoctors() {
        return numDoctors;
    }
}
