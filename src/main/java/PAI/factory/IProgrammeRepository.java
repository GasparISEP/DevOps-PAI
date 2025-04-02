package PAI.factory;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;

import java.util.List;
import java.util.Optional;

public interface IProgrammeRepository {
    boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, ICourseFactory ICourseFactory) throws Exception;
    boolean changeProgrammeDirector(Programme programme, Teacher newDirector) throws Exception;
    List<Programme> getAllProgrammes();
    List<Course> getCourseList(Programme programme);
    Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name);
    Programme getProgrammeByAcronym(Acronym acronym);
    List<NameWithNumbersAndSpecialChars> getAllProgrammeNames();

}
