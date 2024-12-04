package PatientRelatedCodes;
public class PatientNotFoundException extends Exception {
    String prompt = "ERROR:Patient Not Found";
    public String toString(){
        return prompt;
    }
}
