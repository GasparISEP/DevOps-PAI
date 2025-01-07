package PAI.domain;

import java.util.List;

public class DegreeType {

    private String _name;
    private int _maxEcts;

    //private List<Programme> _programme;

    // Constructor
    public DegreeType(String name, int maxEcts) throws Exception {
        if (nameIsInvalid(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        _name=name;
        if(maxEctsIsInvalid(maxEcts)) {
            throw new IllegalArgumentException("Insert a valid number of ECTS");
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
        if(maxEcts<0 || maxEcts >30){
            return true;
        }
        return false;
    }
}
