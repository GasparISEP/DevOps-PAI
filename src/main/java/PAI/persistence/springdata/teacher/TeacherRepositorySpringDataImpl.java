package PAI.persistence.springdata.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.mapper.*;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.mapper.teacher.ITeacherIDMapper;
import PAI.mapper.teacher.ITeacherMapper;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TeacherRepositorySpringDataImpl implements ITeacherRepository {

    private ITeacherRepositorySpringData iTeacherRepositorySpringData;
    private ITeacherMapper teacherMapper;
    private ITeacherIDMapper teacherIDMapper;
    private INIFMapper nifMapper;
    private IDepartmentIDMapper departmentIDMapper;

    public TeacherRepositorySpringDataImpl(ITeacherRepositorySpringData teacherRepositorySpringData, ITeacherMapper teacherMapper,
                                           ITeacherIDMapper teacherIDMapper, INIFMapper nifMapper, IDepartmentIDMapper departmentIDMapper) {

        if (teacherRepositorySpringData == null) {
            throw new IllegalArgumentException("TeacherRepositorySpringData must not be null.");
        }
        if (teacherMapper == null) {
            throw new IllegalArgumentException("TeacherMapper must not be null.");
        }
        if (teacherIDMapper == null) {
            throw new IllegalArgumentException("TeacherIDMapper must not be null.");
        }
        if (nifMapper == null) {
            throw new IllegalArgumentException("NIFMapper must not be null.");
        }
        if (departmentIDMapper == null) {
            throw new IllegalArgumentException("DepartmentIdMapper must not be null.");
        }

        iTeacherRepositorySpringData = teacherRepositorySpringData;
        this.teacherMapper = teacherMapper;
        this.teacherIDMapper = teacherIDMapper;
        this.nifMapper = nifMapper;
        this.departmentIDMapper = departmentIDMapper;
    }

    public Teacher save(Teacher teacher) {

        TeacherDataModel teacherDataModel = teacherMapper.toDataModel(teacher);
        TeacherDataModel savedTeacher = iTeacherRepositorySpringData.save(teacherDataModel);

        return teacherMapper.toDomain(savedTeacher);
    }

    public List<Teacher> findAll() {

        return iTeacherRepositorySpringData.findAll().stream()
                .map(teacherMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Teacher> ofIdentity(TeacherID teacherID) {

        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);

        return iTeacherRepositorySpringData.findByTeacherId(teacherIDDataModel)
                .map(dataModel -> {
                    try {
                        return teacherMapper.toDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Could not convert Teacher Data Model to Teacher Domain Object.", e);
                    }
                })
                ;
    }

    @Override
    public boolean containsOfIdentity(TeacherID teacherID) {

        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);

        Optional<TeacherDataModel> result = iTeacherRepositorySpringData.findByTeacherId(teacherIDDataModel);

        return result.isPresent();
    }

    @Override
    public boolean existsByTeacherIdOrNif(TeacherID teacherID, NIF nif) {
        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);
        NIFDataModel nifDataModel = nifMapper.domainToDataModel(nif);

        if (iTeacherRepositorySpringData.existsByTeacherIdOrNif(teacherIDDataModel, nifDataModel))
            return true;

        return false;
    }

    @Override
    public Iterable<Teacher> findAllByDepartmentId(DepartmentID departmentID) {
        DepartmentIDDataModel departmentIDDataModel = departmentIDMapper.toDataModel(departmentID);
        Iterable<TeacherDataModel> teacherDataModels =
                iTeacherRepositorySpringData.findByDepartmentID(departmentIDDataModel);
        return teacherMapper.listToDomain(teacherDataModels);
    }

}
