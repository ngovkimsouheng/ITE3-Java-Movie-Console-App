//package service;
//
//import dto.CastResponseDTO;
//import dto.MovieDetailDTO;
//import dto.MovieListResponseDTO;
//import model.MovieGenre;
//
//import java.util.List;
//
//public interface MovieService {
//
//    MovieListResponseDTO fetchMoviesByTitle(Integer page, String queryTitle);
//
//    MovieDetailDTO fetchMovieDetails(String movieId);
//
//    MovieListResponseDTO fetchPopularMovies(Integer page);
//
//    List<MovieGenre> fetchAllGenres();
//
//    MovieListResponseDTO fetchMoviesByGenre(Integer page, String genreId);
//
//    CastResponseDTO fetchMovieCast(String movieId);
//}
package service;

import dto.CastResponseDTO;
import dto.MovieDetailDTO;
import dto.MovieListResponseDTO;
import model.MovieGenre;

import java.util.List;

public interface MovieService {

    MovieListResponseDTO fetchMoviesByTitle(Integer page, String queryTitle);

    MovieDetailDTO fetchMovieDetails(String movieId);

    MovieListResponseDTO fetchPopularMovies(Integer page);

    List<MovieGenre> fetchAllGenres();

    MovieListResponseDTO fetchMoviesByGenre(Integer page, String genreId);

    CastResponseDTO fetchMovieCast(String movieId);

    MovieListResponseDTO fetchTopRatedMovies(Integer page);
}