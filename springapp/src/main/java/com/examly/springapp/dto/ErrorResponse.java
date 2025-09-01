package com.examly.springapp.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// @Builder
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class ErrorResponse {
//     private int status;
//     private String message;
//     private LocalDateTime timestamp;

//     // Convenience method for test compatibility
//     public List<String> getErrors() {
//         return Arrays.asList(message);
//     }

//     public static ErrorResponse of(int status, String message) {
//         return new ErrorResponse(status, message, LocalDateTime.now());
//     }
// }

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private List<String> errors;
    private LocalDateTime timestamp;
}
