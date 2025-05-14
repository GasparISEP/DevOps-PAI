package PAI.mapper.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacher.ITeacherFactory;
import PAI.mapper.IAddressMapper;
import PAI.mapper.INIFMapper;
import PAI.mapper.IPhoneNumberMapper;
import PAI.mapper.ITeacherAcademicEmailMapper;
import PAI.mapper.department.IDepartmentIDMapper;
import PAI.persistence.datamodel.*;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.teacher.TeacherDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapperImpl implements ITeacherMapper {

    private ITeacherFactory _teacherFactory;
    private ITeacherIDMapper _teacherIDMapper;
    private INIFMapper _nifMapper;
    private IPhoneNumberMapper _PhoneNumberMapper;
    private IAddressMapper _addressMapper;
    private ITeacherAcademicEmailMapper _teacherAcademicEmailMapper;
    private IDepartmentIDMapper _departmentIDMapper;


    public TeacherMapperImpl(ITeacherFactory teacherFactory, ITeacherIDMapper teacherIDMapper, INIFMapper inifMapper, IPhoneNumberMapper phoneNumberMapper,
                         IAddressMapper addressMapper, ITeacherAcademicEmailMapper teacherAcademicEmailMapper, IDepartmentIDMapper departmentIDMapper) {

        if (teacherFactory == null) {
            throw new IllegalArgumentException("TeacherFactory Mapper cannot be null.");
        }
        _teacherFactory = teacherFactory;

        if (teacherIDMapper == null) {
            throw new IllegalArgumentException("TeacherID Mapper cannot be null.");
        }
        _teacherIDMapper = teacherIDMapper;

        if (inifMapper == null) {
            throw new IllegalArgumentException("NIF Mapper cannot be null.");
        }
        _nifMapper = inifMapper;

        if (phoneNumberMapper == null) {
            throw new IllegalArgumentException("PhoneNumber Mapper cannot be null.");
        }
        _PhoneNumberMapper = phoneNumberMapper;

        if (addressMapper == null) {
            throw new IllegalArgumentException("Address Mapper cannot be null.");
        }
        _addressMapper = addressMapper;

        if (teacherAcademicEmailMapper == null) {
            throw new IllegalArgumentException("TeacherAcademicEmail Mapper cannot be null.");
        }
        _teacherAcademicEmailMapper = teacherAcademicEmailMapper;

        if (departmentIDMapper == null)
            throw new IllegalArgumentException("DepartmentIDMapper cannot be null.");

        _departmentIDMapper = departmentIDMapper;
    }

    public TeacherDataModel toDataModel (Teacher teacher) {

        if(teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null.");
        }

        TeacherIDDataModel teacherIDDataModel = _teacherIDMapper.toDataModel(teacher.getTeacherID());

        String name = teacher.getName().getName();
        String email = teacher.getEmail().getEmail();

        NIFDataModel nifDatamodel = _nifMapper.domainToDataModel(teacher.getNIF());

        PhoneNumberDataModel phoneNumberDataModel = _PhoneNumberMapper.domainToDataModel(teacher.getPhoneNumber());

        String academicBackground = teacher.getAcademicBackground().getAcademicBackground();

        AddressDataModel addressDataModel = _addressMapper.toDataModel(teacher.getAddress());

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = _teacherAcademicEmailMapper.toDataModel(teacher.getTeacherAcademicEmail());

        DepartmentIDDataModel departmentIDDataModel = _departmentIDMapper.toDataModel(teacher.getDepartmentID());

        return new TeacherDataModel(teacherIDDataModel, name, email, nifDatamodel,
                phoneNumberDataModel, academicBackground, addressDataModel,
                teacherAcademicEmailDataModel, departmentIDDataModel);
    }

    public Teacher toDomain (TeacherDataModel teacherDataModel) {

        if (teacherDataModel == null) {
            throw new IllegalArgumentException("Teacher Data Model cannot be null.");
        }

        TeacherID teacherID = _teacherIDMapper.toDomain(teacherDataModel.getTeacherIDDataModel());

        Name name = new Name(teacherDataModel.getName());
        Email email = new Email(teacherDataModel.getEmail());

        NIF nif = _nifMapper.dataModelToDomain(teacherDataModel.getNif());

        PhoneNumber phoneNumber = _PhoneNumberMapper.dataModelToDomain(teacherDataModel.getPhoneNumber());

        String stringAcademicBackground = teacherDataModel.getAcademicBackground();
        AcademicBackground academicBackground = new AcademicBackground(stringAcademicBackground);

        Address address = _addressMapper.toDomain(teacherDataModel.getAddress());
        Street street = address.getStreet();
        PostalCode postalCode = address.getPostalCode();
        Location location = address.getLocation();
        Country country = address.getCountry();

        DepartmentID departmentID = _departmentIDMapper.toDomainModel(teacherDataModel.getDepartmentID());

        return _teacherFactory.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);
    }
}