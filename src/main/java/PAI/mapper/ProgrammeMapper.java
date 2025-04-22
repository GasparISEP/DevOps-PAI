package PAI.mapper;

import PAI.domain.programme.Programme;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.persistence.datamodel.*;
import PAI.VOs.*;

public class ProgrammeMapper implements IProgrammeMapper {

    private final ProgrammeIDMapper _progIDMapper;
    private final TeacherIDMapper _teacherIDMapper;
    private final DepartmentIDMapperImpl _departmentIDMapper;

    public ProgrammeMapper (ProgrammeIDMapper progIDMapper, TeacherIDMapper teacherIDMapper, DepartmentIDMapperImpl departmentIDMapper) {
        _progIDMapper = progIDMapper;
        _teacherIDMapper = teacherIDMapper;
        _departmentIDMapper = departmentIDMapper;
    }

    public ProgrammeDataModel toData(Programme programme) {

        ProgrammeIDDataModel progIDDM = _progIDMapper.toData(programme.getProgrammeID());
        TeacherIDDataModel teacherIDDM = _teacherIDMapper.toDataModel(programme.getProgrammeDirectorID());
        DepartmentIDDataModel departIDDM = _departmentIDMapper.toDataModel(programme.getDepartment());

        return new ProgrammeDataModel(progIDDM, programme.getProgrammeID().getName().getnameWithNumbersAndSpecialChars(), programme.getAcronym().getAcronym(), programme.getQuantSemesters().getQuantityOfSemesters(),
                programme.getQuantEcts().getQuantEcts(), programme.getDegreeTypeID().getDTID(), departIDDM, teacherIDDM);
    }

    public Programme toDomain(ProgrammeDataModel programmeDataModel){
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDataModel.getName());
        Acronym acronym = new Acronym(programmeDataModel.getAcronym());

        QuantSemesters quantSemesters = new QuantSemesters(programmeDataModel.getQuantSemesters());
        QuantEcts quantEcts = new QuantEcts(programmeDataModel.getQuantEcts());
        DegreeTypeID degreeTypeID = new DegreeTypeID(programmeDataModel.getDegreeTypeID());

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(programmeDataModel.getDepartmentID().getDepartmentID());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);

        TeacherAcronym directorAcronym = new TeacherAcronym(programmeDataModel.getProgrammeDirectorID().getTeacherAcronym());
        TeacherID programmeDirectorID = new TeacherID(directorAcronym);

        return new Programme(name, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }

}
