package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.factory.ITeacherFactory;
import PAI.factory.ITeacherListFactory;
import PAI.factory.TeacherFactoryImpl;
import PAI.factory.TeacherListFactoryImpl;
import PAI.repository.ITeacherRepository;
import PAI.repository.TeacherRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.programmeRepository.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.ProgrammeRepositoryImpl;
import PAI.repository.programmeRepository.ProgrammeRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        IProgrammeRepository programmeRepo = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeRepo, teacherRepo);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateAnExceptionWhenRepoIsNull(){
        //assert
        assertThrows(Exception.class, () -> new US12_iWantToChangeProgrammeDirectorOfProgrammeController(null, null));
    }

    @Test
    void shouldReturnTrueWhenDirectorIsChanged() throws Exception{
        //arrange
        IProgrammeRepository programmeList = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList, teacherRepo);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(programmeList.findProgrammeIdByProgramme(programme)).thenReturn(Optional.of(programmeID));
        when(teacherRepo.findTeacherIdByTeacher(teacher)).thenReturn(Optional.of(teacherID));
        when(programmeList.changeProgrammeDirector(programmeID,teacherID)).thenReturn(true);


        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDirectorIsChangedIntegrationTest() throws Exception{
        //arrange
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("ABC");
        Acronym acronym = new Acronym("ABC");
        QuantEcts quantEcts = new QuantEcts(12);
        QuantSemesters quantSemesters = new QuantSemesters(2);
        DegreeTypeID degreeTypeID = new DegreeTypeID("123456789");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("ALG");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("ALP");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("APP");
        TeacherID teacherID = new TeacherID(teacherAcronym);
        TeacherID teacherID2 = new TeacherID(teacherAcronym2);

        Name name = new Name("Joana Duarte");
        Email email = new Email("jam@isep.pt");
        Country country = new Country("Portugal");
        NIF nif = new NIF("123456789",country);
        PhoneNumber phoneNumber = new PhoneNumber("+351","924543321");
        AcademicBackground academicBackground = new AcademicBackground("MBA");
        Street street = new Street("Rua das Ruas");
        PostalCode postalCode = new PostalCode("4450-234");
        Location  location = new Location("Algures");
        Address address = new Address(street,postalCode,location,country);


        Teacher teacher2 = new Teacher(teacherAcronym2,name,email,nif,phoneNumber,academicBackground,address,departmentID);



        Programme programme = new Programme(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);
        IProgrammeFactory iProgrammeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        ITeacherFactory iTeacherFactory = new TeacherFactoryImpl();
        ITeacherListFactory iTeacherListFactory = new TeacherListFactoryImpl();

        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(iProgrammeFactory, iProgrammeRepositoryListFactory);
        ITeacherRepository iTeacherRepository = new TeacherRepository(iTeacherFactory,iTeacherListFactory);

        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(iProgrammeRepository,iTeacherRepository);
        iTeacherRepository.registerTeacher(teacherAcronym2,name,email,nif,phoneNumber,academicBackground,street,postalCode,location,country,departmentID);

        iProgrammeRepository.registerProgramme(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);


        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher2);
        //assert
        assertTrue(result);

    }
}