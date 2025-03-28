package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.UUID;

public class TeacherCareerProgressionID implements DomainId {

    private final String _ID;

    public TeacherCareerProgressionID() {

        _ID = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;

        if(!(obj instanceof TeacherCareerProgressionID)) return false;

        TeacherCareerProgressionID other = (TeacherCareerProgressionID) obj;
        return _ID.equals(other._ID);
    }
}
