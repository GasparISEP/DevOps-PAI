package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeacherIDDataModel {

    private String _teacherAcronym;
    private String _nif;
    private String _country;

    public TeacherIDDataModel() {}

    public TeacherIDDataModel(String teacherAcronym, String nif, String country) {
        _teacherAcronym = teacherAcronym;
        _nif = nif;
        _country = country;
    }

    public String getTeacherAcronym() { return _teacherAcronym; }

    public String getNIF() { return _nif; }

    public String getCountry() { return _country; }
}
