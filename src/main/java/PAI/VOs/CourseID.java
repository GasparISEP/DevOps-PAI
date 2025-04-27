package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;

public class CourseID implements DomainId {

    private final Acronym _acronym;
    private final Name _name;

    public CourseID(Acronym acronym, Name name) {
        if(acronym == null){
            throw new NullPointerException("Acronym cannot be null");
        }
        _acronym = acronym;

        if(name == null){
            throw new NullPointerException("Name cannot be null");
        }
        _name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CourseID))
            return false;
        CourseID courseIDTest = (CourseID) object;
        return Objects.equals(_acronym, courseIDTest._acronym) &&
                Objects.equals(_name, courseIDTest._name);
    }

    @Override
    public String toString() {
        return _acronym.toString() + "-" + _name.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_acronym, _name);
    }

    public Acronym getAcronym() {
        return _acronym;
    }

    public Name getName() {
        return _name;
    }
}