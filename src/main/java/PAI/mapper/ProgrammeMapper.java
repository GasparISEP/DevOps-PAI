package PAI.mapper;

import PAI.domain.programme.Programme;
import PAI.persistence.datamodel.ProgrammeDataModel;
import PAI.VOs.*;
import org.springframework.stereotype.Service;

@Service
public class ProgrammeMapper implements IProgrammeMapper {

    public ProgrammeDataModel toData(Programme programme) {
        return new ProgrammeDataModel(programme);
    }

    public Programme toDomain(ProgrammeDataModel programmeDataModel) throws Exception {
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDataModel.getName());
        Acronym acronym = new Acronym(programmeDataModel.getAcronym());

        QuantSemesters quantSemesters = new QuantSemesters(programmeDataModel.getQuantSemesters());
        QuantEcts quantEcts = new QuantEcts(programmeDataModel.getQuantEcts());
        DegreeTypeID degreeTypeID = new DegreeTypeID(programmeDataModel.getDegreeTypeID());

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(programmeDataModel.getDepartmentID());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);

        TeacherAcronym directorAcronym = new TeacherAcronym(programmeDataModel.getProgrammeDirectorID());
        TeacherID programmeDirectorID = new TeacherID(directorAcronym);

        return new Programme(name, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }

}
