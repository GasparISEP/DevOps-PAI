package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.util.Objects;

@Embeddable
@Table(name = "NIF")
public class NIFDataModel {

    @Column(name = "nifNumber")
    private String nifNumber;

    @Column(name = "nifCountry")
    private String nifCountry;

    public NIFDataModel() {}

    public NIFDataModel(String nifNumber, String nifCountry) {
        this.nifNumber = nifNumber;
        this.nifCountry = nifCountry;
    }

    public String getNifNumber() {
        return nifNumber;
    }

    public String getNifCountry() {
        return nifCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NIFDataModel that = (NIFDataModel) o;
        return Objects.equals(nifNumber, that.nifNumber) && Objects.equals(nifCountry, that.nifCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nifNumber, nifCountry);
    }
}
