package PAI.persistence.springdata.Student;


import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.Student.StudentDataModel;

import PAI.persistence.datamodel.Student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepositorySpringData extends JpaRepository<StudentDataModel, StudentIDDataModel> {

    boolean existsByStudentIDOrNIF(StudentIDDataModel studentIDDataModel, NIFDataModel nifDataModel);
}
