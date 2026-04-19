//package model;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//public class MovieGenre {
//    @JsonProperty("id")
//    private Integer genreId;
//
//    @JsonProperty("name")
//    private String genreName;
//}

package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieGenre {

    @JsonProperty("id")
    private Integer genreId;

    @JsonProperty("name")
    private String genreName;
}