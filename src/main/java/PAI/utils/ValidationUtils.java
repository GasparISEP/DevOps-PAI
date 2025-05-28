package PAI.utils;

public class ValidationUtils {

        public static <T> T validateNotNull(T value, String name) {
            if (value == null) {
                throw new IllegalArgumentException(name + " cannot be null.");
            }
            return value;
        }

    public static void validateNotBlank(String str, String name) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be null or blank.");
        }
    }

}
