package PAI.factory.DegreeTypeFactory;

import PAI.domain.degreeType.DegreeType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DegreeTypeListFactoryImpl implements IDegreeTypeListFactory {

    @Override
    public List<DegreeType> createEmptyList() {
        return new ArrayList<>();
    }

    @Override
    public List<DegreeType> createFromExisting(List<DegreeType> existing) {
        return new ArrayList<>(existing); // cópia mutável
    }
}