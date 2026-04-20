package utils;

import dto.CastResponseDTO;
import dto.MovieDetailDTO;
import dto.MovieListResponseDTO;
import model.*;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.List;

public class TableRenderer {

    // =========================
    // MOVIES LIST TABLE
    // =========================
    public static void displayMovies(MovieListResponseDTO response) {

        Table table = new Table(5, BorderStyle.CLASSIC, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.CENTER);

        String[] headers = {"ID", "TITLE", "RELEASE DATE", "RATING", "TRAILER"};

        for (String header : headers) {
            table.addCell(header, center);
        }

        for (Film movie : response.getMovies()) {

            table.addCell(safe(movie.getMovieId().toString()));
            table.addCell(safe(movie.getMovieTitle()));
            table.addCell(safe(movie.getReleaseDate()));
            table.addCell(movie.getAverageRating() != null ? movie.getAverageRating().toString() : "N/A");

            String trailerUrl = movie.getTrailerInfo() != null
                    ? "https://www.youtube.com/watch?v=" + movie.getTrailerInfo().getKey()
                    : "N/A";

            table.addCell(trailerUrl);
        }

        System.out.println(table.render());
    }

    // =========================
    // MOVIE DETAILS TABLE
    // =========================
    public static void displayMovieDetails(MovieDetailDTO movie, CastResponseDTO castResponse) {

        if (movie == null) {
            System.out.println("Movie not found");
            return;
        }

        try {
            Table table = new Table(2, BorderStyle.CLASSIC, ShownBorders.ALL);
            CellStyle center = new CellStyle(CellStyle.HorizontalAlign.CENTER);

            addRow(table, center, "ID", safe(movie.getMovieId().toString()));
            addRow(table, center, "TITLE", safe(movie.getTitle()));
            addRow(table, center, "BUDGET", String.valueOf(movie.getBudgetAmount()));
            addRow(table, center, "RELEASE DATE", safe(movie.getReleaseDate()));
            addRow(table, center, "RUNTIME", String.valueOf(movie.getDuration()));
            addRow(table, center, "RATING", String.valueOf(movie.getRating()));
            addRow(table, center, "OVERVIEW", safe(movie.getSummary()));
//
//            addRow(table, center, "GENRES", joinGenres(movie.getGenres()));
//            addRow(table, center, "COUNTRY", extractCountries(movie.getProductionCountries()));
//            addRow(table, center, "COMPANIES", extractStudios(movie.getProductionStudios()));

            System.out.println(table.render());
//
//            if (castResponse != null) {
//                displayCastTable(castResponse);
//            }

        } catch (Exception e) {
            System.out.println("⚠️ Error rendering table: " + e.getMessage());
        }
    }

    // =========================
    // HELPERS
    // =========================
    private static void addRow(Table table, CellStyle style, String key, String value) {
        table.addCell(key, style);
        table.addCell(value != null ? value : "N/A");
    }

    private static String safe(String value) {
        return value != null ? value : "N/A";
    }

    private static String joinGenres(List<MovieGenre> genres) {
        if (genres == null || genres.isEmpty()) return "N/A";

        List<String> names = new ArrayList<>();
        for (MovieGenre g : genres) {
            if (g.getGenreName() != null) {
                names.add(g.getGenreName());
            }

        }
        return String.join(", ", names);
    }

    private static String extractStudios(List<Studio> studios) {
        if (studios == null || studios.isEmpty()) return "N/A";

        List<String> names = new ArrayList<>();
        for (Studio s : studios) {
            if (s.getStudioName() != null) {
                names.add(s.getStudioName());
            }
        }
        return String.join(", ", names);
    }

    private static String extractCountries(List<Country> countries) {
        if (countries == null || countries.isEmpty()) return "N/A";

        List<String> names = new ArrayList<>();
        for (Country c : countries) {
            if (c.getName() != null) {
                names.add(c.getName());
            }
        }
        return String.join(", ", names);
    }
}