package PAI.factory.DegreeTypeFactory;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeFactoryImpl implements IDegreeTypeFactory {

    @Override
    public DegreeType create(Name name, MaxEcts maxEcts) {
        DegreeTypeID id = new DegreeTypeID();
        return new DegreeType(id, name, maxEcts);
    }

    @Override
    public DegreeType recreate(DegreeTypeID id, Name name, MaxEcts maxEcts) {
        return new DegreeType(id, name, maxEcts);
    }
}