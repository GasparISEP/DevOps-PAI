package PAI.persistence.springdata.student;


import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.student.StudentDataModel;

import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IStudentRepositorySpringData extends JpaRepository<StudentDataModel, StudentIDDataModel> {

    boolean existsByStudentIDOrNIF(StudentIDDataModel studentIDDataModel, NIFDataModel nifDataModel);
    boolean existsByStudentID(StudentIDDataModel studentIDDataModel);
    boolean existsByNIF_NifNumberAndNIF_NifCountry(String nifNumber, String nifCountry);
    @Query("SELECT MAX(s.studentID.uniqueNumber) FROM StudentDataModel s") Integer lastStudentID();
}

