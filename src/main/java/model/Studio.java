package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Studio {

    @JsonProperty("id")
    private Integer studioId;

    @JsonProperty("name")
    private String studioName;

    @JsonProperty("origin_country")
    private String countryOrigin;
}