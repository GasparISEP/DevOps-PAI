package PAI.mapper;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.mapper.programme.ProgrammeIDMapperImpl;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProgrammeIDMapperImplTest {

    @Test
    void shouldConvertToDomain() {
        //arrange
        ProgrammeIDMapperImpl mapper = new ProgrammeIDMapperImpl();

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
        ProgrammeIDMapperImpl mapper = new ProgrammeIDMapperImpl();

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