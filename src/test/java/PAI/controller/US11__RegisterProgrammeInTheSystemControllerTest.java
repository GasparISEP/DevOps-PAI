package PAI.controller;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.assembler.programme.ProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.DegreeTypeFactoryImpl;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.department.Department;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.persistence.mem.degreeType.DegreeTypeListFactoryImpl;
import PAI.persistence.mem.degreeType.DegreeTypeRepositoryImpl;
import PAI.persistence.mem.degreeType.IDegreeTypeListFactory;
import PAI.persistence.mem.department.DepartmentListFactoryImpl;
import PAI.persistence.mem.department.DepartmentRepositoryImpl;
import PAI.persistence.mem.department.IDepartmentListFactory;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.persistence.mem.teacher.ITeacherListFactory;
import PAI.persistence.mem.teacher.TeacherListFactoryImpl;
import PAI.persistence.mem.teacher.TeacherRepositoryImpl;
import PAI.service.degreeType.DegreeTypeRegistrationServiceImpl;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class US11__RegisterProgrammeInTheSystemControllerTest {

    @Test
    void registerProgrammeInTheSystemControllerFailureWithNullProgrammeService() throws Exception {
        // arrange
        IProgrammeService programmeService = null;

        // act + assert
        Exception exception = assertThrows(Exception.class, () ->
                new US11_RegisterProgrammeInTheSystemController(programmeService));

        assertEquals("Programme Service cannot be null.", exception.getMessage());
    }

    @Test
    void registerProgrammeInTheSystemSuccessIntegrationTest() throws Exception {
        // arrange
        String name = "ABC";
        String acronym = "ABC";
        int maxOfEcts = 180;
        int quantityOfSemesters = 6;
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentID departmentID = new DepartmentID(new DepartmentAcronym("ALG"));
        TeacherID teacherID = new TeacherID(new TeacherAcronym("ALP"));

        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IDegreeTypeListFactory degreeTypeListFactory = new DegreeTypeListFactoryImpl();
        IDegreeTypeRepository degreeTypeRepository = new DegreeTypeRepositoryImpl(degreeTypeListFactory);
        IDepartmentListFactory departmentListFactory = new DepartmentListFactoryImpl();
        IDepartmentRepository departmentRepository = new DepartmentRepositoryImpl(departmentListFactory);
        ITeacherListFactory teacherListFactory = new TeacherListFactoryImpl();
        ITeacherRepository teacherRepository = new TeacherRepositoryImpl(teacherListFactory);
        IProgrammeAssembler programmeAssembler = new ProgrammeAssembler();

        IDegreeTypeFactory degreeTypeFactory = new DegreeTypeFactoryImpl();
        IDegreeTypeRegistrationService degreeTypeRegistrationService = new DegreeTypeRegistrationServiceImpl(degreeTypeFactory, degreeTypeRepository);

        ProgrammeServiceImpl programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository, programmeAssembler, degreeTypeRegistrationService, departmentRepository, teacherRepository);
        
        MaxEcts maxEcts = new MaxEcts(180);
        Name dtname = new Name("Master");
        DegreeType degreeType = new DegreeType(degreeTypeID, dtname, maxEcts);
        DepartmentAcronym dAcronym = new DepartmentAcronym("ALG");
        Name departmentName = new Name("Astronomy");
        Department department = new Department(dAcronym, departmentName);
        Name teacherName = new Name("AAA");
        Email email = new Email ("aaa@gmail.com");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789", country);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Mestrado ISEP 2024");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4450-789");
        Location location = new Location("Coimbra");
        Address address = new Address(street, postalCode, location, country);
        Teacher teacher = new Teacher(teacherID, teacherName, email, nif, phoneNumber, academicBackground, address, departmentID);

        degreeTypeRepository.save(degreeType);
        departmentRepository.save(department);
        teacherRepository.save(teacher);

        US11_RegisterProgrammeInTheSystemController controller = new US11_RegisterProgrammeInTheSystemController(programmeService);

        // act
        boolean result = controller.registerProgramme(name, acronym, maxOfEcts, quantityOfSemesters,
                degreeTypeID, departmentID, teacherID);

        // assert
        assertTrue(result);
    }
}