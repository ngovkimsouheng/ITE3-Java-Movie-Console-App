package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Actor {
    @JsonProperty("name")
    private String fullName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("character")
    private String roleName;
}