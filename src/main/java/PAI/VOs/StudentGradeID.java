package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;
import java.util.UUID;

public class StudentGradeID implements DomainId {
    private  final UUID _id;

    public StudentGradeID() {
        this._id = UUID.randomUUID();
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGradeID that)) return false;
        return Objects.equals(_id, that._id);
    }

}
