//package dto;
//
//import lombok.Getter;
//import lombok.Setter;
//import model.Film;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import model.MovieGenre;
//
//@Setter
//@Getter
//public class MovieListResponseDTO {
//
//    @JsonProperty("page")
//    private Integer currentPage;
//
//    @JsonProperty("total_results")
//    private Integer totalResults;
//
//    @JsonProperty("results")
//    private List<Film> movies;
//
//    @JsonProperty("total_pages")
//    private Integer totalPages;
//}

package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import model.Film;

import java.util.List;

@Getter
@Setter
public class MovieListResponseDTO {

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_results")
    private Integer totalResults;

    @JsonProperty("results")
    private List<Film> movies;

    @JsonProperty("total_pages")
    private Integer totalPages;
}