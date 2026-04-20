
package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.MovieGenre;
import model.Country;
import model.Studio;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailDTO {

    @JsonProperty("id")
    private Integer movieId;

    private String title;

    @JsonProperty("budget")
    private Integer budgetAmount;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("runtime")
    private Integer duration;

    @JsonProperty("vote_average")
    private Double rating;

    @JsonProperty("overview")
    private String summary;

    private List<MovieGenre> genres;

    @JsonProperty("production_countries")
    private List<Country> productionCountries;

    @JsonProperty("production_companies")
    private List<Studio> productionStudios;
}