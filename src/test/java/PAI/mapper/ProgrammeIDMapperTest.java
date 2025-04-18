package PAI.mapper;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.persistence.datamodel.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeIDMapperTest {

    @Test
    void shouldConvertToDomain() {
        //arrange
        ProgrammeIDMapper mapper = new ProgrammeIDMapper();

        ProgrammeIDDataModel dataModel = mock(ProgrammeIDDataModel.class);

        when(dataModel.getName()).thenReturn("A");
        when(dataModel.getAcronym()).thenReturn("OLA");

        //act
        ProgrammeID res = mapper.toDomain(dataModel);

        //assert
        assertEquals("A", res.getName().getnameWithNumbersAndSpecialChars());
        assertEquals("OLA", res.getAcronym().getAcronym());
    }

    @Test
    void shouldCovertToData() {
        //arrange
        ProgrammeIDMapper mapper = new ProgrammeIDMapper();

        Acronym acronym = mock(Acronym.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        when(acronym.getAcronym()).thenReturn("OLA");
        when(name.getnameWithNumbersAndSpecialChars()).thenReturn("name");

        ProgrammeID progID = mock(ProgrammeID.class);

        when(progID.getAcronym()).thenReturn(acronym);
        when(progID.getName()).thenReturn(name);

        //act
        ProgrammeIDDataModel res = mapper.toData(progID);

        //assert
        assertEquals(acronym.getAcronym(), res.getAcronym());
        assertEquals(name.getnameWithNumbersAndSpecialChars(), res.getName());
    }
}