package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionEnrolmentRepositorySpringData extends JpaRepository<ProgrammeEditionEnrolmentDataModel, ProgrammeEditionEnrolmentIDDataModel> {
    List<ProgrammeEditionEnrolmentDataModel> findAllBy_id_ProgrammeEditionIdDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel);

    Optional<ProgrammeEditionEnrolmentDataModel> findByStudentIDAndProgrammeEditionID(StudentIDDataModel studentId, ProgrammeEditionIdDataModel programmeEditionId);
}
