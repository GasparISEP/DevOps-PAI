package PAI.persistence.springdata.student;


import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.student.StudentDataModel;

import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepositorySpringData extends JpaRepository<StudentDataModel, StudentIDDataModel> {

    boolean existsByStudentIDOrNIF(StudentIDDataModel studentIDDataModel, NIFDataModel nifDataModel);
}
