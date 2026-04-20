
package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.MovieGenre;

import java.util.List;

@Setter
@Getter
public class GenreResponseDTO {

    @JsonProperty("genres")
    private List<MovieGenre> genreList;
}