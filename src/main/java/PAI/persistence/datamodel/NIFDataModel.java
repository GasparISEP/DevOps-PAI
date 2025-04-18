package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NIFDataModel that = (NIFDataModel) o;
        return Objects.equals(NIF, that.NIF) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF, country);
    }
}
