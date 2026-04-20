
package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.Actor;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CastResponseDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("cast")
    private List<Actor> castList;
}