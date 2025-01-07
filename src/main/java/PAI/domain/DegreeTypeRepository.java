package PAI.domain;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeRepository {

    private List<DegreeType> degreeTypeList = new ArrayList<>();

    // Constructor
    public DegreeTypeRepository() {
    }

    // Add Degree Type
    public void addDegreeType (DegreeType degreeType){
        degreeTypeList.add(degreeType);}


    protected List<DegreeType> getDegreeTypeList() {
        return degreeTypeList;
    }
}
