package itmo.devops.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppError {
    private int code;
    private String message;
}