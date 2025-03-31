package PAI.VOs;

import PAI.ddd.ValueObject;

public class CourseName implements ValueObject {

    private final String _name;

    public CourseName(String name) throws Exception{

        if(isNameInvalid(name)){
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }
        this._name = name;
    }

    public boolean isNameInvalid(String parameter){
        return parameter == null || parameter.isBlank();
    }
}
