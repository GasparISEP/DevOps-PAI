package PAI.VOs;

public class Location {

    private final String _locationName;

    public Location(String locationName) throws IllegalArgumentException {
        if (locationName == null || locationName.isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty");
        }
        if (!locationName.matches("^[A-Za-zÀ-ÖØ-öø-ÿ' -]+$") || locationName.startsWith(" ") || locationName.endsWith(" ")) {
            throw new IllegalArgumentException("Write a valid location!");
        }
        _locationName = locationName;
    }

    public String getLocation() {return _locationName;}
}
