package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class StudentIDDataModel {

    private int uniqueNumber;

    public StudentIDDataModel() {}

    public StudentIDDataModel(int uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof StudentIDDataModel)) return false;

        StudentIDDataModel other = (StudentIDDataModel) obj;

        return this.uniqueNumber == other.uniqueNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueNumber);
    }
}
