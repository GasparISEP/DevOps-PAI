package PAI.factory.DegreeTypeFactory;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeFactoryImpl implements IDegreeTypeFactory {

    @Override
    public DegreeType create(Name name, MaxEcts maxEcts) {
        return DegreeType.create(name, maxEcts);  // gera automaticamente o ID
    }
}