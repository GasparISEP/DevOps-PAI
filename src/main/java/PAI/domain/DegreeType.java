package PAI.domain;

public class DegreeType {

    private String _name;
    private int _maxEcts;


    // Constructor
    public DegreeType(String name, int maxEcts) throws Exception {
        if (nameIsInvalid(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        _name=name;
        if(maxEctsIsInvalid(maxEcts)) {
            throw new IllegalArgumentException("The number os ECTS cannot be 0 or below");
        }
        _maxEcts =maxEcts;


    }

    private boolean nameIsInvalid (String name){
        if(name==null || name.isBlank()){
            return true;
        }
        return false;
    }

    private boolean maxEctsIsInvalid (int maxEcts){
        if(maxEcts<=0){
            return true;
        }
        return false;
    }
}
