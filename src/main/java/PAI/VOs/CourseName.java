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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseName))
            return false;
        CourseName courseNameTest = (CourseName) object;
        return _name.equals(courseNameTest._name);
    }
}
