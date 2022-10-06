package au.com.totemsoft.crm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {

    @JsonProperty("errors")
    private List<ValidationError> errors;

}
