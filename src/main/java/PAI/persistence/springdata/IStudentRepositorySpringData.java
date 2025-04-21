package PAI.persistence.springdata;


import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.StudentDataModel;

import PAI.persistence.datamodel.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepositorySpringData extends JpaRepository<StudentDataModel, Integer> {

    boolean existsByStudentIDOrNIF(StudentIDDataModel studentIDDataModel, NIFDataModel nifDataModel);
}
