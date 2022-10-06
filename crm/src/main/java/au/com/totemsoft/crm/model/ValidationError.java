package au.com.totemsoft.crm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {

    @JsonProperty("field")
    private String field;

    @JsonProperty("description")
    private String description;

}
