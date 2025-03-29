package PAI.VOs;

public class Description {
    private String description;

    public Description(String description) {
        if (!isDescriptionValid(description)) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    private boolean isDescriptionValid(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public String getDescription() {
        return description;
    }
}
