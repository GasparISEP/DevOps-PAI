package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.ITeacherFactory;
import PAI.persistence.datamodel.*;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements ITeacherMapper {

    private ITeacherFactory _teacherFactory;
    private ITeacherIDMapper _teacherIDMapper;
    private INIFMapper _nifMapper;
    private IPhoneNumberMapper _PhoneNumberMapper;
    private IAddressMapper _addressMapper;
    private ITeacherAcademicEmailMapper _teacherAcademicEmailMapper;


    public TeacherMapper(ITeacherFactory teacherFactory, ITeacherIDMapper teacherIDMapper, INIFMapper inifMapper, IPhoneNumberMapper phoneNumberMapper,
                         IAddressMapper addressMapper, ITeacherAcademicEmailMapper teacherAcademicEmailMapper) {

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
    }

    public TeacherDataModel toDataModel (Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        TeacherIDDataModel teacherIDDataModel = _teacherIDMapper.toDataModel(teacher.getTeacherID());

        String name = teacher.getName().toString();
        String email = teacher.getEmail().toString();

        NIFDataModel nifDatamodel = _nifMapper.domainToDataModel(teacher.getNIF());

        PhoneNumberDataModel phoneNumberDataModel = _PhoneNumberMapper.domainToDataModel(teacher.getPhoneNumber());

        String academicBackground = teacher.getAcademicBackground().toString();

        AddressDataModel addressDataModel = _addressMapper.toDataModel(teacher.getAddress());

        TeacherAcademicEmailDataModel teacherAcademicEmailDataModel = _teacherAcademicEmailMapper.toDataModel(teacher.getTeacherAcademicEmail());

        String departmentAcronym = teacher.getDepartmentID().toString();

        return new TeacherDataModel(teacherIDDataModel, name, email, nifDatamodel,
                phoneNumberDataModel, academicBackground, addressDataModel,
                teacherAcademicEmailDataModel, departmentAcronym);
    }

    public Teacher toDomain (TeacherDataModel teacherDataModel) throws Exception {

        if (teacherDataModel == null) {
            return null;
        }

        TeacherID teacherID = _teacherIDMapper.toDomain(teacherDataModel.getTeacherIDDataModel());
        TeacherAcronym teacherAcronym = teacherID.getTeacherAcronym();

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

        DepartmentAcronym departmentAcronym = new DepartmentAcronym(teacherDataModel.getDptAcronym());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);

        return _teacherFactory.createTeacher(teacherAcronym, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID);
    }
}