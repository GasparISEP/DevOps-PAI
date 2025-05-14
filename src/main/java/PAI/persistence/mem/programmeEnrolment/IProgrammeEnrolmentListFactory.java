package PAI.persistence.mem.programmeEnrolment;

import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.ArrayList;

public interface IProgrammeEnrolmentListFactory {

    ArrayList<ProgrammeEnrolment> newArrayList();
}
