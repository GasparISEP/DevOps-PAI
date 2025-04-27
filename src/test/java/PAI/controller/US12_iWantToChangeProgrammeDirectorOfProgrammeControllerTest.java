package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.factory.ITeacherFactory;
import PAI.factory.ITeacherListFactory;
import PAI.factory.TeacherFactoryImpl;
import PAI.factory.TeacherListFactoryImpl;
import PAI.persistence.springdata.TeacherRepositorySpringDataImpl;
import PAI.repository.ITeacherRepository;
import PAI.repository.TeacherRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.persistence.mem.programmeEdition.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryListFactoryImpl;
import PAI.service.IProgrammeService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeService);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateAnExceptionWhenRepoIsNull(){
        //assert
        assertThrows(Exception.class, () -> new US12_iWantToChangeProgrammeDirectorOfProgrammeController(null));
    }

    @Test
    void shouldReturnTrueWhenDirectorIsChanged() throws Exception {
        // arrange
        IProgrammeService programmeService = mock(IProgrammeService.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeService);

        Programme mockProgramme = mock(Programme.class);
        Teacher mockTeacher = mock(Teacher.class);
        ProgrammeID mockProgrammeID = mock(ProgrammeID.class);
        TeacherID mockTeacherID = mock(TeacherID.class);

        when(mockProgramme.identity()).thenReturn(mockProgrammeID);
        when(mockTeacher.identity()).thenReturn(mockTeacherID);
        when(programmeService.changeProgrammeDirector(mockProgrammeID, mockTeacherID)).thenReturn(true);

        // act
        boolean result = controller.changeProgrammeDirector(mockProgramme, mockTeacher);

        // assert
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
        IProgrammeService programmeService = mock(IProgrammeService.class);


        when(programmeService.changeProgrammeDirector(programme.identity(), teacher2.identity())).thenReturn(true);

        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeService);

        // act
        boolean result = controller.changeProgrammeDirector(programme, teacher2);

        // assert
        assertTrue(result);

    }
}