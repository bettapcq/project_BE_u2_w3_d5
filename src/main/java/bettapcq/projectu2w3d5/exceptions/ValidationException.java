package bettapcq.projectu2w3d5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private List<String> errorsMsg;

    public ValidationException(List<String> errorsMsg) {

        super("There are problems in payload");
        this.errorsMsg = errorsMsg;
    }
}