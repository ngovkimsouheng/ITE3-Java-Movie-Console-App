package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.Actor;

import java.util.List;

@Setter
@Getter
public class CastResponseDTO {
    @JsonProperty("cast")
    private List<Actor> castList;
}
