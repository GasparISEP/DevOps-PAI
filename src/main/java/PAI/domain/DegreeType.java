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

    @Override
    public boolean equals(Object objectToCompare) {

        if (!(objectToCompare instanceof DegreeType)) {
            return false;
        }

        DegreeType degreeType = (DegreeType) objectToCompare;

        return _name.equals(degreeType._name) && _maxEcts == degreeType._maxEcts;
    }

    public String get_name (){
        return _name;
    }

    public int get_maxEcts (){
        return _maxEcts;
    }
}

