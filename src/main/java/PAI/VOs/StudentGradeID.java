package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class StudentGradeID {
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
