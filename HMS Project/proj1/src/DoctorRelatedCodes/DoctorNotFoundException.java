package DoctorRelatedCodes;
public class DoctorNotFoundException extends Exception{
    String prompt = "ERROR:Doctor Not Found";
    public String toString(){
        return prompt;
    }
}
