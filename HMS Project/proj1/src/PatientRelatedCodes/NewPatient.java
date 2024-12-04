package PatientRelatedCodes;
import MedicalHistoryRelatedCodes.MedHistory;

public class NewPatient extends Patient {
  
    private int visits;
    private String id=generateID();
    

    public NewPatient(String name, int age, int ht, int wt, String gender, MedHistory medHistory) {
        super(name, age, ht, wt, gender,medHistory); 
        this.visits = 0;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public String getId() {
        return id;
    }

    private String generateID() {
        String generatedID = String.valueOf((int) (Math.random() * 900000) + 100000);
        return generatedID;
    }


}