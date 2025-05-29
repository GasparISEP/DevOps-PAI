package PAI.dto.teacher;

import PAI.VOs.*;
import PAI.domain.teacher.Teacher;
import PAI.domain.teacherCareerProgression.TeacherCareerProgression;
import PAI.domain.teacherCategory.TeacherCategory;
import PAI.dto.teacher.TeacherWithRelevantDataAssembler;
import PAI.dto.teacher.TeacherWithRelevantDataDTO;
import net.bytebuddy.asm.Advice;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherWithRelevantDataAssemblerTest {
    @Test
    void testToDTOWithNameAndCategory() {
        //Arrange
        Teacher teacher = mock(Teacher.class);
        TeacherID teacherID = mock(TeacherID.class);
        Name teacherName = new Name("João Silva");

        TeacherCategory teacherCategory = mock(TeacherCategory.class);
        Name categoryName = new Name("Auxiliar");

        TeacherCareerProgression tcp = mock(TeacherCareerProgression.class);
        WorkingPercentage wp = new WorkingPercentage(100);

        when(teacher.getName()).thenReturn(teacherName);
        when(teacher.getTeacherID()).thenReturn(teacherID);
        when(teacherID.toString()).thenReturn("JS01");
        when(teacherCategory.getName()).thenReturn(categoryName);
        when(tcp.getWorkingPercentage()).thenReturn(wp);

        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();

        //Act
        TeacherWithRelevantDataDTO dto = assembler.toDTOWithNameAndCategory(teacher, teacherCategory, tcp);

        //Assert
        assertEquals("João Silva", dto.getName());
        assertEquals("JS01", dto.getAcronym());
        assertEquals("Auxiliar", dto.getCategory());
        assertEquals(100, dto.getWorkingPercentage());
    }

    @Test
    void shouldReturnTeacherID(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String id = "ABS";
        //Act
        TeacherID result = assembler.toTeacherID(id);
        //Assert
        assertEquals(result.getTeacherAcronym().toString(), id);
    }

    @Test
    void shouldReturnName(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String name = "Fulano Sousa";
        //Act
        Name result = assembler.toName(name);
        //Assert
        assertEquals(result.getName(), name);
    }

    @Test
    void shouldReturnEmail(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String email = "fulano@sousa.com";
        //Act
        Email result = assembler.toEmail(email);
        //Assert
        assertEquals(result.getEmail(), email);
    }

    @Test
    void shouldReturnNif(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String nif = "1234567";
        String country = "Portugal";
        //Act
        NIF result = assembler.toNif(nif, country);
        //Assert
        assertEquals(result.getNIF(), nif);
    }

    @Test
    void shouldReturnPhoneNumber(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String phoneNumber = "987987987";
        String country = "+351";
        //Act
        PhoneNumber result = assembler.toPhoneNumber(country, phoneNumber);
        //Assert
        assertEquals(result.getNumber(), phoneNumber);
    }

    @Test
    void shouldReturnAcademicBackground(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String academicBackground = "Professor Adjunto";
        //Act
        AcademicBackground result = assembler.toAcademicBackground(academicBackground);
        //Assert
        assertEquals(result.getAcademicBackground(), academicBackground);
    }

    @Test
    void shouldReturnStreet(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String street = "Rua da Avenida";
        //Act
        Street result = assembler.toStreet(street);
        //Assert
        assertEquals(result.getStreet(), street);
    }

    @Test
    void shouldReturnPostalCode(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String postalCode = "4400-000";
        //Act
        PostalCode result = assembler.toPostalCode(postalCode);
        //Assert
        assertEquals(result.getPostalCode(), postalCode);
    }

    @Test
    void shouldReturnLocation(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String location = "Vila Nova de Gaia";
        //Act
        Location result = assembler.toLocation(location);
        //Assert
        assertEquals(result.getLocation(), location);
    }

    @Test
    void shouldReturnCountry(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String country = "Portugal";
        //Act
        Country result = assembler.toCountry(country);
        //Assert
        assertEquals(result.getCountryName(), country);
    }

    @Test
    void shouldReturnDepartmentID(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String departmentID = "DEI";
        //Act
        DepartmentID result = assembler.toDepartmentID(departmentID);
        //Assert
        assertEquals(result.getAcronym().getAcronym(), departmentID);
    }

    @Test
    void shouldReturnDate(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String date = "11-10-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateToCompare = LocalDate.parse(date, formatter);
        //Act
        Date result = assembler.toDate(date);
        //Assert
        assertEquals(result.getLocalDate(), dateToCompare);
    }

    @Test
    void shouldReturnTeacherCategoryID(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String teacherCategoryID = UUID.randomUUID().toString();
        //Act
        TeacherCategoryID result = assembler.toTeacherCategoryID(teacherCategoryID);
        //Assert
        assertEquals(result.getValue().toString(), teacherCategoryID);
    }

    @Test
    void shouldReturnWorkingPercentage(){
        //Arrange
        TeacherWithRelevantDataAssembler assembler = new TeacherWithRelevantDataAssembler();
        String workingPercentage = "10";
        //Act
        WorkingPercentage result = assembler.toWorkingPercentage(workingPercentage);
        //Assert
        assertEquals(result.getValue(), Integer.parseInt(workingPercentage));
    }
}