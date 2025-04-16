package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class NIFDataModel {

    private String NIF;
    private String country;

    public NIFDataModel() {}

    public NIFDataModel(String NIF, String country) {
        this.NIF = NIF;
        this.country = country;
    }

    public String getNIF() {
        return NIF;
    }

    public String getCountry() {
        return country;
    }
}
