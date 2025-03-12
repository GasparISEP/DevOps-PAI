package PAI.factory;

import PAI.domain.DegreeType;

import java.util.ArrayList;
import java.util.List;

public class DegreeTypeListFactoryImpl implements DegreeTypeListFactoryInterface {
    public List<DegreeType> createDegreeTypeList(){
        return new ArrayList<>();
    }
}
