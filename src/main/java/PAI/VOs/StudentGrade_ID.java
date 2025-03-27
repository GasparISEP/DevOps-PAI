package PAI.VOs;

import java.util.UUID;

public class StudentGrade_ID {
    private  String _id;

    public StudentGrade_ID () {
        this._id = UUID.randomUUID().toString();
    }
}
