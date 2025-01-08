package PAI.domain;

public class Programme {

    private String _name;
    private String _acronym;
    private int _quantityOfEcts;
    private Semester _semester;
    private DegreeType _degreeType;


    public Programme(String name, String acronym, int quantityOfEcts, Semester semester, DegreeType degreeType) throws Exception {
        if (isNameInvalid(name)){ throw new IllegalArgumentException("Name must not be empty");}
        _name = name;

        if (isAcronymInvalid(acronym)){throw new IllegalArgumentException("Acronym must not be empty");}
        _acronym = acronym;

        if (isQuantityOfEctsInvalid(quantityOfEcts)){throw  new IllegalArgumentException("Insert a valid number of ECTS");}
        _quantityOfEcts = quantityOfEcts;

        if (semester==null){throw  new IllegalArgumentException("Insert a valid Semester");}
        _semester=semester;

        if (degreeType==null){throw  new IllegalArgumentException("Insert a valid DegreeType");}
        _degreeType = degreeType;

    }


    private boolean isNameInvalid (String name){
        return name == null || name.isBlank();
    }

    private boolean isAcronymInvalid (String acronym){
        return acronym == null || acronym.isBlank();
    }

    private boolean isQuantityOfEctsInvalid (int quantityOfEcts){
        return quantityOfEcts <= 0 || quantityOfEcts > 30;
    }

}
