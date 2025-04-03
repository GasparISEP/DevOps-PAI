package PAI.controller;

import PAI.VOs.*;
import PAI.domain.DegreeType;
import PAI.domain.Programme;
import PAI.domain.Teacher;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.factory.ITeacherFactory;
import PAI.factory.ITeacherListFactory;
import PAI.factory.TeacherFactoryImpl;
import PAI.factory.TeacherListFactoryImpl;
import PAI.repository.ITeacherRepository;
import PAI.repository.TeacherRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionDDDListFactory;
import PAI.repository.programmeEditionRepository.ProgrammeEditionDDDListFactoryImpl;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        IProgrammeDDDRepository programmeRepo = mock(IProgrammeDDDRepository.class);
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
        IProgrammeDDDRepository programmeList = mock(IProgrammeDDDRepository.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList, teacherRepo);
        Teacher teacher = mock(Teacher.class);
        ProgrammeDDD programme = mock(ProgrammeDDD.class);
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



        ProgrammeDDD programmeDDD = new ProgrammeDDD(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);
        IProgrammeDDDFactory iProgrammeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        ITeacherFactory iTeacherFactory = new TeacherFactoryImpl();
        ITeacherListFactory iTeacherListFactory = new TeacherListFactoryImpl();

        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(iProgrammeDDDFactory,iProgrammeDDDRepositoryListFactory);
        ITeacherRepository iTeacherRepository = new TeacherRepository(iTeacherFactory,iTeacherListFactory);

        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(iProgrammeDDDRepository,iTeacherRepository);
        iTeacherRepository.registerTeacher(teacherAcronym2,name,email,nif,phoneNumber,academicBackground,street,postalCode,location,country,departmentID);

        iProgrammeDDDRepository.registerProgramme(nameWithNumbersAndSpecialChars,acronym,quantEcts,quantSemesters,degreeTypeID,departmentID,teacherID);


        //act
        boolean result = controller.changeProgrammeDirector(programmeDDD,teacher2);
        //assert
        assertTrue(result);

    }
}