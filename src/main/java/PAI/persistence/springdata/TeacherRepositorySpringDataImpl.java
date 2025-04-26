package PAI.persistence.springdata;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.mapper.*;
import PAI.persistence.datamodel.NIFDataModel;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.repository.ITeacherRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepositorySpringDataImpl implements ITeacherRepository {

    private ITeacherRepositorySpringData iTeacherRepositorySpringData;
    private ITeacherMapper teacherMapper;
    private ITeacherIDMapper teacherIDMapper;
    private INIFMapper nifMapper;

    public TeacherRepositorySpringDataImpl(ITeacherRepositorySpringData teacherRepositorySpringData, ITeacherMapper teacherMapper,
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

        iTeacherRepositorySpringData = teacherRepositorySpringData;
        this.teacherMapper = teacherMapper;
        this.teacherIDMapper = teacherIDMapper;
        this.nifMapper = nifMapper;
    }


    public Optional<Teacher> getTeacherByID(TeacherID teacherID) {
        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);
        return iTeacherRepositorySpringData.findById(teacherIDDataModel.toString())
                .map(dataModel -> {
                    try {
                        return teacherMapper.toDomain(dataModel);
                    } catch (Exception e) {
                        throw new RuntimeException("Mapping failed", e);
                    }
                });
    }

    public Teacher save(Teacher teacher) {

        TeacherDataModel teacherDataModel = teacherMapper.toDataModel(teacher);
        iTeacherRepositorySpringData.save(teacherDataModel);

        return teacher;
    }

    public Iterable<Teacher> findAll() {
        List<Teacher> teachersList = new ArrayList<>();
        List<TeacherDataModel> teacherDataModelsList = iTeacherRepositorySpringData.findAll();
        for(TeacherDataModel teacherDataModel : teacherDataModelsList) {
            Optional<Teacher> teacher;
            try {
                teacher = Optional.ofNullable(teacherMapper.toDomain(teacherDataModel));
            } catch (Exception e) {
                throw new RuntimeException("Could not convert Teacher Data Model to Teacher domain object.");
            }
            teacher.ifPresent(teachersList::add);
        }
        return teachersList;
    }

    public Optional<Teacher> ofIdentity(TeacherID teacherID) { return getTeacherByID(teacherID); }

    public boolean containsOfIdentity(TeacherID teacherID) {
        return iTeacherRepositorySpringData.existsById(teacherID.getTeacherAcronym().getAcronym());
    }

    @Override   // [Temporary] method added only because in order to implement ITeacherRepository this class needs this method
    public Optional<TeacherID> registerTeacher(TeacherAcronym acronym, Name name, Email email, NIF nif, PhoneNumber phoneNumber, AcademicBackground academicBackground, Street street, PostalCode postalCode, Location location, Country country, DepartmentID departmentID) {
        return Optional.empty();
    }

    @Override
    public boolean existsByIDorNIF(TeacherID teacherID, NIF nif) {
        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);
        NIFDataModel nifDataModel = nifMapper.domainToDataModel(nif);

        if (iTeacherRepositorySpringData.existsByIDorNIF(teacherIDDataModel, nifDataModel))
            return true;

        return false;
    }
}
