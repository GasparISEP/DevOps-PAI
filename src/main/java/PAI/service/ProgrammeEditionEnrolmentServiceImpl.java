package PAI.service;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.IProgrammeEditionEnrolmentRepository;
import PAI.repository.IProgrammeEnrolmentRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeEditionEnrolmentServiceImpl implements IProgrammeEditionEnrolmentService {

    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository;
    private final ICourseEditionRepository courseEditionRepository;
    private final ISchoolYearRepository schoolYearRepository;
    private final IProgrammeEnrolmentRepository programmeEnrolmentRepository;
    private final IProgrammeRepository programmeRepository;

    @Autowired
    public ProgrammeEditionEnrolmentServiceImpl(
            IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
            IProgrammeEditionRepository programmeEditionRepository,
            ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository,
            ICourseEditionRepository courseEditionRepository,
            ISchoolYearRepository schoolYearRepository,
            IProgrammeEnrolmentRepository programmeEnrolmentRepository,
            IProgrammeRepository programmeRepository) {

        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;
        this.programmeEditionRepository = programmeEditionRepository;
        this.courseEditionEnrolmentRepository = courseEditionEnrolmentRepository;
        this.courseEditionRepository = courseEditionRepository;
        this.schoolYearRepository = schoolYearRepository;
        this.programmeEnrolmentRepository = programmeEnrolmentRepository;
        this.programmeRepository = programmeRepository;
    }


    @Override
    public boolean enrolStudentInProgrammeEditionAndSetOfCoursesEditions(StudentID studentID, ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (!programmeEnrolmentRepository.isStudentEnrolled(studentID, programmeID)) {
            return false;
        }

        Optional<ProgrammeEditionID> optionalProgrammeEdition =
                programmeEditionRepository.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID, schoolYearID);

        if (optionalProgrammeEdition.isEmpty()) {
            return false;
        }

        ProgrammeEditionID programmeEditionId = optionalProgrammeEdition.get();

        if (programmeEditionEnrolmentRepository.isStudentEnrolledInThisProgrammeEdition(studentID, programmeEditionId)) {
            return false;
        }

        programmeEditionEnrolmentRepository.enrolStudentInProgrammeEdition(studentID, programmeEditionId);

        List<CourseEditionID> courseEditions =
                courseEditionRepository.findCourseEditionsByProgrammeEditionID(programmeEditionId);

        courseEditionEnrolmentRepository.enrolStudentInProgrammeCourseEditions(studentID, courseEditions);

        return true;
    }

    @Override
    public List<ProgrammeID> getAllProgrammesIDs() {
        return programmeRepository.getAllProgrammesIDs();
    }

    @Override
    public List<SchoolYearID> getAllSchoolYearIDs() {
        return schoolYearRepository.getAllSchoolYearsIDs();
    }
}
