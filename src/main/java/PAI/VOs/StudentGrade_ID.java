package PAI.VOs;

import java.util.Objects;
import java.util.UUID;

public class StudentGrade_ID {
    private  String _id;

    public StudentGrade_ID () {
        this._id = UUID.randomUUID().toString();
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGrade_ID that)) return false;
        return Objects.equals(_id, that._id);
    }

}
