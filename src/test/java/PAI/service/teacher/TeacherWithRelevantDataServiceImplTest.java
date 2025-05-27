package PAI.service.teacher;

import PAI.VOs.*;
import PAI.domain.department.Department;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.repositoryInterfaces.teacherCareerProgression.ITeacherCareerProgressionRepository;
import PAI.domain.repositoryInterfaces.teacherCategory.ITeacherCategoryRepository;
import PAI.domain.teacher.ITeacherFactory;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacher.TeacherFactoryImpl;
import PAI.domain.teacherCareerProgression.ITeacherCareerProgressionFactory;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCareerProgression.TeacherCareerProgressionFactoryImpl;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacher.TeacherWithRelevantDataAssembler;
import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import PAI.exception.BusinessRuleViolationException;
import PAI.persistence.datamodel.teacherCategory.TeacherCategoryDataModel;
import PAI.persistence.mem.teacher.TeacherRepositoryImpl;
import PAI.persistence.mem.teacherCareerProgression.TeacherCareerProgressionRepositoryImpl;
import PAI.persistence.mem.teacherCategory.TeacherCategoryRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherWithRelevantDataServiceImplTest {

    @Test
    void getTeacherCategory_shouldReturnCategory_whenFound() {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        TeacherCategoryID categoryId = mock(TeacherCategoryID.class);
        TeacherCategory category = mock(TeacherCategory.class);

        when(tcp.getTeacherCategoryID()).thenReturn(categoryId);
        when(teacherCategoryRepository.ofIdentity(categoryId)).thenReturn(Optional.of(category));

        //Act
        Optional<TeacherCategory> result = teacherWithRelevantDataService.getTeacherCategory(tcp);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

    @Test
    void shouldNotCreateTeacherCareerProgressionWithNullArguments() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);
        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            teacherWithRelevantDataService.createTeacherCareerProgression(null, mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            teacherWithRelevantDataService.createTeacherCareerProgression(mock(Date.class), null, mock(WorkingPercentage.class), mock(TeacherID.class));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            teacherWithRelevantDataService.createTeacherCareerProgression(mock(Date.class), mock(TeacherCategoryID.class), null, mock(TeacherID.class));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            teacherWithRelevantDataService.createTeacherCareerProgression(mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), null);
        });
    }

    @Test
    void shouldCreateTeacherCareerProgressionSuccessfully() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);

        when(teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgressionRepository.containsOfIdentity(teacherCareerProgression.getID())).thenReturn(false);
        when(teacherCareerProgressionRepository.save(teacherCareerProgression)).thenReturn(teacherCareerProgression);
        //Act
        Optional<TeacherCareerProgression> result = teacherWithRelevantDataService.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID);
        //Assert
        assertEquals(result, Optional.of(teacherCareerProgression));
    }

    @Test
    void shouldReturnBusinessExceptionWhenTeacherCareerProgressionAlreadyExists() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        TeacherID teacherID = mock(TeacherID.class);
        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);

        when(teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgressionRepository.containsOfIdentity(teacherCareerProgression.getID())).thenReturn(true);
        //Act + Assert
        assertThrows(BusinessRuleViolationException.class, () ->
                teacherWithRelevantDataService.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID));
    }

    @Test
    void shouldRegisterTeacherSuccessfully() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        Teacher teacher = mock(Teacher.class);

        when(teacherFactory.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID)).thenReturn(teacher);
        when(teacher.identity()).thenReturn(teacherID);
        when(teacher.getNIF()).thenReturn(nif);

        when(teacherRepository.existsByTeacherIdOrNif(teacher.getTeacherID(), teacher.getNIF())).thenReturn(false);

        when(teacherRepository.save(teacher)).thenReturn(teacher);
        //Act
        Optional<Teacher> result = teacherWithRelevantDataService.registerTeacher(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID);
        //Assert
        assertEquals(result, Optional.of(teacher));
    }

    @Test
    void shouldReturnBusinessExceptionWhenTeacherAlreadyExists() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);

        Teacher teacher = mock(Teacher.class);

        when(teacherFactory.createTeacher(teacherID, name, email, nif, phoneNumber, academicBackground, street, postalCode, location, country, departmentID)).thenReturn(teacher);
        when(teacher.identity()).thenReturn(teacherID);
        when(teacher.getTeacherID()).thenReturn(teacherID);
        when(teacher.getNIF()).thenReturn(nif);

        when(teacherRepository.existsByTeacherIdOrNif(teacher.getTeacherID(), teacher.getNIF())).thenReturn(true);
        //Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> {
            teacherWithRelevantDataService.registerTeacher(
                    teacherID, name, email, nif, phoneNumber, academicBackground,
                    street, postalCode, location, country, departmentID
            );
        });
    }

    @Test
    void shouldFullyRegisterTeacherWithTeacherCareerProgressionSuccessfully() throws Exception {
        //Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl teacherWithRelevantDataService = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);
        Teacher teacher = mock(Teacher.class);

        when(teacherFactory.createTeacher(
                teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID))
                .thenReturn(teacher);
        when(teacher.identity()).thenReturn(teacherID);
        when(teacher.getNIF()).thenReturn(nif);
        when(teacherRepository.existsByTeacherIdOrNif(teacherID, nif)).thenReturn(false);

        TeacherCareerProgression teacherCareerProgression = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionID tcpID = mock(TeacherCareerProgressionID.class);
        when(teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID)).thenReturn(teacherCareerProgression);
        when(teacherCareerProgression.getID()).thenReturn(tcpID);
        when(teacherCareerProgressionRepository.containsOfIdentity(tcpID)).thenReturn(false);

        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        when(teacherCategoryRepository.ofIdentity(teacherCategoryID)).thenReturn(Optional.of(teacherCategory));
        when(teacherCareerProgression.getTeacherCategoryID()).thenReturn(teacherCategoryID);

        TeacherWithRelevantDataDTO expectedDto = mock(TeacherWithRelevantDataDTO.class);
        when(teacherWithRelevantDataAssembler.toDTOWithNameAndCategory(teacher, teacherCategory, teacherCareerProgression))
                .thenReturn(expectedDto);

        //Act
        TeacherWithRelevantDataDTO result = teacherWithRelevantDataService.registerTeacherWithRelevantData(
                teacherID, name, email, nif, phoneNumber, academicBackground,
                street, postalCode, location, country, departmentID,
                date, teacherCategoryID, wp
        );

        //Assert
        assertEquals(expectedDto, result);
    }

    @Test
    void shouldThrowExceptionWhenTeacherCareerProgressionIsEmpty() throws Exception {
        // Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl service = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);

        Teacher teacher = mock(Teacher.class);
        when(teacherFactory.createTeacher(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(teacher);
        when(teacher.identity()).thenReturn(teacherID);
        when(teacher.getNIF()).thenReturn(nif);
        when(teacherRepository.existsByTeacherIdOrNif(teacherID, nif)).thenReturn(false);

        when(teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID))
                .thenReturn(mock(TeacherCareerProgression.class));
        when(teacherCareerProgressionRepository.containsOfIdentity(any())).thenReturn(true);

        // Act + Assert
        assertThrows(BusinessRuleViolationException.class, () -> {
            service.registerTeacherWithRelevantData(
                    teacherID, name, email, nif, phoneNumber, academicBackground,
                    street, postalCode, location, country, departmentID,
                    date, teacherCategoryID, wp
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenTeacherCategoryIsEmpty() throws Exception {
        // Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl service = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherID teacherID = mock(TeacherID.class);
        Name name = mock(Name.class);
        Email email = mock(Email.class);
        NIF nif = mock(NIF.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);
        AcademicBackground academicBackground = mock(AcademicBackground.class);
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        Date date = mock(Date.class);
        TeacherCategoryID teacherCategoryID = mock(TeacherCategoryID.class);
        WorkingPercentage wp = mock(WorkingPercentage.class);

        Teacher teacher = mock(Teacher.class);
        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        TeacherCareerProgressionID tcpID = mock(TeacherCareerProgressionID.class);

        when(teacherFactory.createTeacher(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(teacher);
        when(teacher.identity()).thenReturn(teacherID);
        when(teacher.getNIF()).thenReturn(nif);
        when(teacherRepository.existsByTeacherIdOrNif(teacherID, nif)).thenReturn(false);

        when(teacherCareerProgressionFactory.createTeacherCareerProgression(date, teacherCategoryID, wp, teacherID))
                .thenReturn(tcp);
        when(tcp.getID()).thenReturn(tcpID);
        when(teacherCareerProgressionRepository.containsOfIdentity(tcpID)).thenReturn(false);
        when(tcp.getTeacherCategoryID()).thenReturn(teacherCategoryID);

        when(teacherCategoryRepository.ofIdentity(teacherCategoryID)).thenReturn(Optional.empty());
        //Act + Assert
        assertThrows(Exception.class, () -> {
            service.registerTeacherWithRelevantData(
                    teacherID, name, email, nif, phoneNumber, academicBackground,
                    street, postalCode, location, country, departmentID,
                    date, teacherCategoryID, wp
            );
        });
    }

    @Test
    void shouldReturnDepartmentIterableWhenFindAll() {
        // Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl service = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        // Act
        Iterable<Department> departmentIterable = service.findAll();

        //Assert
        assertNotNull(departmentIterable);
    }

    @Test
    void getTeacherCategoryList_shouldReturnListOfTeacherCategories() {

        // Arrange
        ITeacherCategoryRepository teacherCategoryRepository = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepository = mock(TeacherCareerProgressionRepositoryImpl.class);
        ITeacherRepository teacherRepository = mock(TeacherRepositoryImpl.class);
        ITeacherFactory teacherFactory = mock(TeacherFactoryImpl.class);
        ITeacherCareerProgressionFactory teacherCareerProgressionFactory = mock(TeacherCareerProgressionFactoryImpl.class);
        TeacherWithRelevantDataAssembler teacherWithRelevantDataAssembler = mock(TeacherWithRelevantDataAssembler.class);
        IDepartmentRepository departmentRepository = mock(IDepartmentRepository.class);

        TeacherWithRelevantDataServiceImpl service = new TeacherWithRelevantDataServiceImpl(teacherCategoryRepository, teacherCareerProgressionRepository, teacherRepository, teacherFactory, teacherCareerProgressionFactory, teacherWithRelevantDataAssembler, departmentRepository);

        TeacherCategory cat1 = mock(TeacherCategory.class);
        TeacherCategory cat2 = mock(TeacherCategory.class);
        List<TeacherCategory> categories = List.of(cat1, cat2);

        when(teacherCategoryRepository.findAll()).thenReturn(categories);

        Iterable<TeacherCategory> result = service.getAllTeacherCategories();

        assertEquals(2, ((List<TeacherCategory>) result).size());
    }
}