package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Studio {
    @JsonProperty("id")
    private Integer studioId;

    @JsonProperty("name")
    private String studioName;

    @JsonProperty("origin_country")
    private String countryOrigin;
}
