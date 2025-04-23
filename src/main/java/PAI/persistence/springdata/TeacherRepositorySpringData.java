package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.mapper.*;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepositorySpringData {

    private ITeacherRepositorySpringData iteacherRepositorySpringData;
    private ITeacherMapper iteacherMapper;
    private ITeacherIDMapper iteacherIDMapper;
    private INIFMapper inifMapper;

    public TeacherRepositorySpringData(ITeacherRepositorySpringData teacherRepositorySpringData, ITeacherMapper teacherMapper,
                                       ITeacherIDMapper teacherIDMapper, INIFMapper nifMapper) {

        if (teacherRepositorySpringData == null) {
            throw new IllegalArgumentException("teacherRepositorySpringData must not be null");
        }
        if (teacherMapper == null) {
            throw new IllegalArgumentException("teacherMapper must not be null");
        }
        if (teacherIDMapper == null) {
            throw new IllegalArgumentException("teacherIDMapper must not be null");
        }
        if (nifMapper == null) {
            throw new IllegalArgumentException("nifMapper must not be null");
        }

        iteacherRepositorySpringData = teacherRepositorySpringData;
        iteacherMapper = teacherMapper;
        iteacherIDMapper = teacherIDMapper;
        inifMapper = nifMapper;
    }


    public Optional<Teacher> getTeacherByID(TeacherID teacherID) {
        TeacherIDDataModel teacherIDDataModel = iteacherIDMapper.toDataModel(teacherID);
        return iteacherRepositorySpringData.findById(teacherIDDataModel.toString())
                .map(dataModel -> {
                    try {
                        return iteacherMapper.toDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Mapping failed", e);
                    }
                });
    }

    public Teacher save(Teacher teacher) {

        TeacherDataModel teacherDataModel = iteacherMapper.toDataModel(teacher);
        iteacherRepositorySpringData.save(teacherDataModel);

        return teacher;
    }

    public Iterable<Teacher> findAll() {
        List<Teacher> teachersList = new ArrayList<>();
        List<TeacherDataModel> teacherDataModelsList = iteacherRepositorySpringData.findAll();
        for(TeacherDataModel teacherDataModel : teacherDataModelsList) {
            Optional<Teacher> teacher;
            try {
                teacher = Optional.ofNullable(iteacherMapper.toDomain(teacherDataModel));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            teacher.ifPresent(teachersList::add);
        }
        return teachersList;
    }

    public Optional<Teacher> ofIdentity(TeacherID teacherID) { return getTeacherByID(teacherID); }

    public boolean containsOfIdentity(TeacherID teacherID) {
        return iteacherRepositorySpringData.existsById(teacherID.getTeacherAcronym().getAcronym());
    }
}
