package PAI.service;

import PAI.VOs.*;
import PAI.factory.ITeacherFactory;
import PAI.persistence.springdata.TeacherRepositorySpringData;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherServiceTest {

    @Test
    void testConstructor () {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);
        TeacherRepositorySpringData teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);

        // Arrange + Act
        TeacherService teacherService = new TeacherService(teacherFactoryDouble, teacherRepositoryDouble);

        // Assert
        assertNotNull(teacherService);
    }

    @Test
    void testConstructorDoesNotBuildDueToNullFactory () {
        // Arrange
        TeacherRepositorySpringData teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);

        // Act
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            TeacherService teacherService = new TeacherService(null, teacherRepositoryDouble);
        });

        // Assert
        assertEquals("Teacher Factory must not be null.", expectedException.getMessage());
    }

    @Test
    void testConstructorDoesNotBuildDueToNullRepository () {
        // Arrange
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);

        // Act
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, () -> {
            TeacherService teacherService = new TeacherService(teacherFactoryDouble, null);
        });

        // Assert
        assertEquals("Teacher Repository must not be null.", expectedException.getMessage());
    }

    @Test
    void registerTeacherShouldRegisterTeacher() {
        // Arrange
        TeacherRepositorySpringData teacherRepositoryDouble = mock(TeacherRepositorySpringData.class);
        ITeacherFactory teacherFactoryDouble = mock(ITeacherFactory.class);

        TeacherService teacherService = new TeacherService(teacherFactoryDouble, teacherRepositoryDouble);

        TeacherAcronym acronymDouble = mock(TeacherAcronym.class);
        Name nameDouble = mock(Name.class);
        Email emailDouble = mock(Email.class);
        NIF nifDouble = mock(NIF.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        AcademicBackground academicBackgroundDouble = mock(AcademicBackground.class);
        Street streetDouble = mock(Street.class);
        PostalCode postalCodeDouble = mock(PostalCode.class);
        Location locationDouble = mock(Location.class);
        Country countryDouble = mock(Country.class);
        DepartmentID departmentIDDouble = mock(DepartmentID.class);

        // Act
        boolean result = teacherService.registerTeacher(
                acronymDouble, nameDouble, emailDouble, nifDouble, phoneNumberDouble, academicBackgroundDouble,
                streetDouble, postalCodeDouble, locationDouble, countryDouble, departmentIDDouble);

        // Assert
        assertTrue(result);
    }
}