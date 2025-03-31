package PAI.VOs;

import PAI.ddd.ValueObject;

public class CourseName implements ValueObject {

    private final String _name;

    public CourseName(String name) throws Exception{

        if(areParameterInvalid(name)){
            throw new IllegalArgumentException("Course Name cannot be empty");
        }
        this._name = name;
    }

    public boolean areParameterInvalid(String parameter){
        return parameter == null || parameter.isBlank();
    }
}
