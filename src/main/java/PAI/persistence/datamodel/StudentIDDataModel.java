package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class StudentIDDataModel {

    private int uniqueNumber;
    private String NIF;
    private String country;

    public StudentIDDataModel() {}

    public StudentIDDataModel(int uniqueNumber, String NIF, String country) {
        this.uniqueNumber = uniqueNumber;
        this.NIF = NIF;
        this.country = country;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public String getNIF() {
        return NIF;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof StudentIDDataModel)) return false;

        StudentIDDataModel other = (StudentIDDataModel) obj;

        return this.uniqueNumber == other.uniqueNumber &&
                this.NIF.equals(other.NIF) &&
                this.country.equals(other.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueNumber, NIF, country);
    }
}
