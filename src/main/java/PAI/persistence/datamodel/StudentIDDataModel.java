package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

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
}
