package PAI.mapper;

import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.TeacherDataModel;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TeacherIDMapperTest {

    private TeacherIDMapper teacherIDMapper;

    @BeforeEach
    void setUp() {
        // Arrange
        teacherIDMapper = new TeacherIDMapper();
    }

    @Test
    void toDomainShouldReturnNullWhenTeacherIDDataModelIsNull () {
        // Act
        TeacherID result = teacherIDMapper.toDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDomainShouldReturnTeacherID () {
        // Arrange
        String acronym = "MSA";
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        // Act
        when(teacherIDDataModel.getTeacherAcronym()).thenReturn(acronym);

        TeacherID teacherID = teacherIDMapper.toDomain(teacherIDDataModel);

        // Assert
        assertEquals(acronym, teacherID.getTeacherAcronym().getAcronym());
    }

    @Test
    void toDataModelShouldReturnNullWhenTeacherIDIsNull () {
        // Act
        TeacherIDDataModel result = teacherIDMapper.toDataModel(null);
        // Assert
        assertNull(result);
    }

    @Test
    void toDataModelShouldReturnTeacherIDDataModel () {
        // Arrange
        String acronym = "MSA";
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherID = mock(TeacherID.class);

        // Act
        when(teacherID.getTeacherAcronym()).thenReturn(teacherAcronym);
        when(teacherAcronym.getAcronym()).thenReturn(acronym);

        TeacherIDDataModel teacherIDDataModel = teacherIDMapper.toDataModel(teacherID);

        // Assert
        assertEquals(acronym, teacherIDDataModel.getTeacherAcronym());
    }
}