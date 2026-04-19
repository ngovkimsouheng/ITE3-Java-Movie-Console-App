//package service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dto.*;
//import model.Film;
//import model.MovieGenre;
//import model.VideoTrailer;
//import utils.API;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URLEncoder;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.charset.StandardCharsets;
//import java.time.Duration;
//import java.util.List;
//
//public class MovieServiceImpl implements MovieService {
//
//    private static final HttpClient httpClient = HttpClient.newBuilder()
//            .connectTimeout(Duration.ofSeconds(5))
//            .build();
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    // =========================
//    // SEARCH MOVIES
//    // =========================
//    @Override
//    public MovieListResponseDTO fetchMoviesByTitle(Integer page, String queryTitle) {
//
//        String encodedQuery = URLEncoder.encode(queryTitle, StandardCharsets.UTF_8);
//
//        String url = String.format(
//                "%s/search/movie?api_key=%s&query=%s&page=%d",
//                API.BASE_URL, API.API_KEY, encodedQuery, page
//        );
//
//        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);
//        enrichWithTrailer(response);
//
//        return response;
//    }
//
//    // =========================
//    // MOVIE DETAILS
//    // =========================
/// /    @Override
/// /    public MovieDetailDTO fetchMovieDetails(String movieId) {
/// /
/// /        String encodedId = URLEncoder.encode(movieId, StandardCharsets.UTF_8);
/// /
/// /        String url = String.format(
/// /                "%s/movie/%s?api_key=%s",
/// /                API.BASE_URL, encodedId, API.API_KEY
/// /        );
/// /
/// /        return request(url, MovieDetailDTO.class);
/// /    }
//    @Override
//    public MovieDetailDTO fetchMovieDetails(String movieId) {
//
//        movieId = movieId.trim(); // remove spaces
//
//        String url = String.format(
//                "%s/movie/%s?api_key=%s",
//                API.BASE_URL, movieId, API.API_KEY
//        );
//
//        return request(url, MovieDetailDTO.class);
//    }
//
//    // =========================
//    // POPULAR MOVIES
//    // =========================
//    @Override
//    public MovieListResponseDTO fetchPopularMovies(Integer page) {
//
//        if (page > 999) {
//            throw new IllegalArgumentException("Page limit exceeded (max 999)");
//        }
//
//        String url = String.format(
//                "%s/movie/popular?api_key=%s&language=en-US&page=%d",
//                API.BASE_URL, API.API_KEY, page
//        );
//
//        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);
//        enrichWithTrailer(response);
//
//        return response;
//    }
//
//    // =========================
//    // GENRES
//    // =========================
//    @Override
//    public List<MovieGenre> fetchAllGenres() {
//
//        String url = String.format(
//                "%s/genre/movie/list?api_key=%s",
//                API.BASE_URL, API.API_KEY
//        );
//
//        GenreResponseDTO response = request(url, GenreResponseDTO.class);
//        return response.getGenreList();
//    }
//
//    // =========================
//    // MOVIES BY GENRE
//    // =========================
//    @Override
//    public MovieListResponseDTO fetchMoviesByGenre(Integer page, String genreId) {
//
//        String encodedGenre = URLEncoder.encode(genreId, StandardCharsets.UTF_8);
//
//        String url = String.format(
//                "%s/discover/movie?api_key=%s&with_genres=%s&page=%d",
//                API.BASE_URL, API.API_KEY, encodedGenre, page
//        );
//
//        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);
//        enrichWithTrailer(response);
//
//        return response;
//    }
//
//    // =========================
//    // CAST
//    // =========================
//    @Override
//    public CastResponseDTO fetchMovieCast(String movieId) {
//
//        String encodedId = URLEncoder.encode(movieId, StandardCharsets.UTF_8);
//
//        String url = String.format(
//                "%s/movie/%s/credits?api_key=%s",
//                API.BASE_URL, encodedId, API.API_KEY
//        );
//
//        return request(url, CastResponseDTO.class);
//    }
//
//    // =========================
//    // GENERIC API CALL
//    // =========================
/// /    private <T> T request(String url, Class<T> responseType) {
/// /
/// /        HttpRequest request = HttpRequest.newBuilder()
/// /                .uri(URI.create(url))
/// /                .timeout(Duration.ofSeconds(10))
/// /                .header("Accept", "application/json")
/// /                .GET()
/// /                .build();
/// /
/// /        try {
/// /            HttpResponse<String> response = httpClient.send(
/// /                    request,
/// /                    HttpResponse.BodyHandlers.ofString()
/// /            );
/// /
//
//    /// /            System.out.println("URL: " + url);
//    /// /            System.out.println("STATUS: " + response.statusCode());
//    /// /            System.out.println("BODY: " + response.body());
////
////            if (response.statusCode() != 200) {
////                throw new RuntimeException("HTTP ERROR: " + response.statusCode());
////            }
////
////            return objectMapper.readValue(response.body(), responseType);
////
////        } catch (IOException | InterruptedException e) {
////            Thread.currentThread().interrupt();
////            throw new RuntimeException("Failed API request", e);
////        }
////    }
//    private <T> T request(String url, Class<T> responseType) {
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .timeout(Duration.ofSeconds(10))
//                .header("Accept", "application/json")
//                .GET()
//                .build();
//
//        try {
//            HttpResponse<String> response = httpClient.send(
//                    request,
//                    HttpResponse.BodyHandlers.ofString()
//            );
//
//            // 🔥 DEBUG (keep this while fixing issue)
////            System.out.println("REQUEST URL: " + url);
////            System.out.println("STATUS CODE: " + response.statusCode());
////            System.out.println("BODY: " + response.body());
//
//            if (response.statusCode() != 200) {
//                throw new RuntimeException("HTTP ERROR: " + response.statusCode() +
//                        " | RESPONSE: " + response.body());
//            }
//
//            return objectMapper.readValue(response.body(), responseType);
//
//        } catch (IOException | InterruptedException e) {
//            Thread.currentThread().interrupt();
//            throw new RuntimeException("API request failed: " + e.getMessage(), e);
//        }
//    }
//
//    // =========================
//    // ATTACH TRAILERS
//    // =========================
//    private void enrichWithTrailer(MovieListResponseDTO response) {
//
//        for (Film movie : response.getMovies()) {
//
//            String url = String.format(
//                    "%s/movie/%s/videos?api_key=%s",
//                    API.BASE_URL, movie.getMovieId(), API.API_KEY
//            );
//
//            TrailerResponseDTO trailerResponse = request(url, TrailerResponseDTO.class);
//
//            if (trailerResponse.getTrailers() == null) continue;
//
//            for (VideoTrailer trailer : trailerResponse.getTrailers()) {
//                if ("Trailer".equalsIgnoreCase(trailer.getType())) {
//                    movie.setTrailerInfo(trailer);
//                    break;
//                }
//            }
//        }
//    }
//}

package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.*;
import model.Film;
import model.MovieGenre;
import model.VideoTrailer;
import utils.API;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // =========================
    // REQUEST HELPER
    // =========================
    private <T> T request(String url, Class<T> responseType) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new RuntimeException("HTTP ERROR: " + response.statusCode());
            }

            return objectMapper.readValue(response.body(), responseType);

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("API request failed: " + e.getMessage(), e);
        }
    }

    // =========================
    // SEARCH MOVIES
    // =========================
    @Override
    public MovieListResponseDTO fetchMoviesByTitle(Integer page, String queryTitle) {

        String encodedQuery = URLEncoder.encode(queryTitle, StandardCharsets.UTF_8);

        String url = String.format(
                "%s/search/movie?api_key=%s&query=%s&page=%d",
                API.BASE_URL, API.API_KEY, encodedQuery, page
        );

        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);

        enrichWithTrailer(response);

        return response;
    }

    // =========================
    // MOVIE DETAILS
    // =========================
    @Override
    public MovieDetailDTO fetchMovieDetails(String movieId) {

        movieId = movieId.trim();

        String url = String.format(
                "%s/movie/%s?api_key=%s",
                API.BASE_URL, movieId, API.API_KEY
        );

        return request(url, MovieDetailDTO.class);
    }

    // =========================
    // POPULAR MOVIES
    // =========================
    @Override
    public MovieListResponseDTO fetchPopularMovies(Integer page) {

        if (page > 500) {
            throw new IllegalArgumentException("Page limit is 500");
        }

        String url = String.format(
                "%s/movie/popular?api_key=%s&page=%d",
                API.BASE_URL, API.API_KEY, page
        );

        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);

        enrichWithTrailer(response);

        return response;
    }

    // =========================
    // GENRES
    // =========================
    @Override
    public List<MovieGenre> fetchAllGenres() {

        String url = String.format(
                "%s/genre/movie/list?api_key=%s",
                API.BASE_URL, API.API_KEY
        );

        GenreResponseDTO response = request(url, GenreResponseDTO.class);

        return response.getGenreList();
    }

    // =========================
    // MOVIES BY GENRE
    // =========================
    @Override
    public MovieListResponseDTO fetchMoviesByGenre(Integer page, String genreId) {

        String url = String.format(
                "%s/discover/movie?api_key=%s&with_genres=%s&page=%d",
                API.BASE_URL, API.API_KEY, genreId, page
        );

        MovieListResponseDTO response = request(url, MovieListResponseDTO.class);

        enrichWithTrailer(response);

        return response;
    }

    @Override
    public MovieListResponseDTO fetchTopRatedMovies(Integer page) {

        String url = String.format(
                "%s/movie/top_rated?api_key=%s&page=%d",
                API.BASE_URL, API.API_KEY, page
        );

        MovieListResponseDTO response =
                request(url, MovieListResponseDTO.class);

        enrichWithTrailer(response);

        return response;
    }

    // =========================
    // CAST
    // =========================
    @Override
    public CastResponseDTO fetchMovieCast(String movieId) {

        String url = String.format(
                "%s/movie/%s/credits?api_key=%s",
                API.BASE_URL, movieId, API.API_KEY
        );

        return request(url, CastResponseDTO.class);
    }

    // =========================
    // TRAILER ENRICHMENT
    // =========================
    private void enrichWithTrailer(MovieListResponseDTO response) {

        if (response == null || response.getMovies() == null) return;

        for (Film movie : response.getMovies()) {

            String url = String.format(
                    "%s/movie/%s/videos?api_key=%s",
                    API.BASE_URL, movie.getMovieId(), API.API_KEY
            );

            TrailerResponseDTO trailerResponse = request(url, TrailerResponseDTO.class);

            if (trailerResponse == null || trailerResponse.getTrailers() == null) continue;

            for (VideoTrailer trailer : trailerResponse.getTrailers()) {
                if ("Trailer".equalsIgnoreCase(trailer.getType())) {
                    movie.setTrailerInfo(trailer);
                    break;
                }
            }
        }
    }
}