//package model;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class Film {
//
//    @JsonProperty("id")
//    private String movieId;
//
//    @JsonProperty("title")
//    private String movieTitle;
//
//    @JsonProperty("popularity")
//    private Double popularityScore;
//
//    @JsonProperty("release_date")
//    private String releaseDate;
//
//    @JsonProperty("vote_average")
//    private Double averageRating;
//
//    private VideoTrailer trailerInfo;
//}

package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

    @JsonProperty("id")
    private Integer movieId;

    @JsonProperty("title")
    private String movieTitle;

    @JsonProperty("popularity")
    private Double popularityScore;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double averageRating;

    private VideoTrailer trailerInfo;
}