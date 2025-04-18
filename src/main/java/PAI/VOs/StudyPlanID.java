package PAI.VOs;

import PAI.ddd.DomainId;
import java.time.LocalDate;
import java.util.Objects;

public class StudyPlanID implements DomainId {

    private final ProgrammeID _programmeID;
    private final Date _implementationDate;

    public StudyPlanID(ProgrammeID programmeID, Date implementationDate) {

        this._programmeID = programmeID;
        this._implementationDate = implementationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyPlanID that)) return false;
        return Objects.equals(_programmeID, that._programmeID) && Objects.equals(_implementationDate, that._implementationDate);
    }

    @Override
    public String toString() {
        return "StudyPlanID{" +
                "_programmeID=" + _programmeID +
                ", _implementationDate=" + _implementationDate +
                '}';
    }

    public LocalDate getLocalDate() {
        return _implementationDate.getLocalDate();
    }

    public ProgrammeID getProgrammeID() {
        return _programmeID;
    }
}