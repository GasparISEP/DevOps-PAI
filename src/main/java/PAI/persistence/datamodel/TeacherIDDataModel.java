package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TeacherIDDataModel otherID)) return false;
        return _teacherAcronym.equals(otherID._teacherAcronym) &&
                _nif.equals(otherID._nif) && _country.equals(otherID._country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_teacherAcronym, _nif, _country);
    }
}

