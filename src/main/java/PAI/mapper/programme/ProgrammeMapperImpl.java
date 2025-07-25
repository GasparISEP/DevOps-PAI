package PAI.mapper.programme;

import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.mapper.degreeType.DegreeTypeIDMapper;
import PAI.mapper.teacher.TeacherIDMapperImpl;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.VOs.*;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeMapperImpl implements IProgrammeMapper {

    private final ProgrammeIDMapperImpl _progIDMapper;
    private final TeacherIDMapperImpl _teacherIDMapper;
    private final DepartmentIDMapperImpl _departmentIDMapper;
    private final DegreeTypeIDMapper _degreeTypeIDMapper;
    private final IProgrammeFactory _factory;

    public ProgrammeMapperImpl(ProgrammeIDMapperImpl progIDMapper, TeacherIDMapperImpl teacherIDMapper, DepartmentIDMapperImpl departmentIDMapper, DegreeTypeIDMapper degreeTypeIDMapper, IProgrammeFactory factory) {
        _progIDMapper = progIDMapper;
        _teacherIDMapper = teacherIDMapper;
        _departmentIDMapper = departmentIDMapper;
        _degreeTypeIDMapper = degreeTypeIDMapper;
        _factory = factory;
    }

    public ProgrammeDataModel toData(Programme programme) {

        ProgrammeIDDataModel progIDDM = _progIDMapper.toData(programme.getProgrammeID());
        TeacherIDDataModel teacherIDDM = _teacherIDMapper.toDataModel(programme.getProgrammeDirectorID());
        DepartmentIDDataModel departIDDM = _departmentIDMapper.toDataModel(programme.getDepartment());
        DegreeTypeIDDataModel degreeTIDDM = _degreeTypeIDMapper.toDataModel(programme.getDegreeTypeID());

        return new ProgrammeDataModel(progIDDM, programme.getProgrammeName().getNameWithNumbersAndSpecialChars(), programme.getAcronym().getAcronym(), programme.getQuantSemesters().getQuantityOfSemesters(),
                programme.getMaxEcts().getMaxEcts(), degreeTIDDM, departIDDM, teacherIDDM);
    }

    public Programme toDomain(ProgrammeDataModel programmeDataModel){
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDataModel.getName());
        Acronym acronym = new Acronym(programmeDataModel.getAcronym());
        QuantSemesters quantSemesters = new QuantSemesters(programmeDataModel.getQuantSemesters());
        MaxEcts maxEcts = new MaxEcts(programmeDataModel.getMaxEcts());

        DegreeTypeID degreeTypeID = _degreeTypeIDMapper.toDomain(programmeDataModel.getDegreeTypeID());

        DepartmentID departID = _departmentIDMapper.toDomainModel(programmeDataModel.getDepartmentID());

        TeacherID teacherID = _teacherIDMapper.toDomain(programmeDataModel.getProgrammeDirectorID());

        ProgrammeID programmeID = _progIDMapper.toDomain(programmeDataModel.getProgID());

        return _factory.reregisterProgramme(name,acronym,maxEcts,quantSemesters,degreeTypeID,departID,teacherID,programmeID);
    }

}
