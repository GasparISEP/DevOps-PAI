package PAI.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse<T> {


    private T object;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> messages = new ArrayList<>();

    public static <T> ServiceResponse<T> success(T object) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.object = object;
        return response;
    }

    public static <T> ServiceResponse<T> failure(String message) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.messages.add(message);
        return response;
    }

    // Getters
    public T getObject() {
        return object;
    }

    public List<String> getMessages() {
        return messages;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return object != null;
    }

}

