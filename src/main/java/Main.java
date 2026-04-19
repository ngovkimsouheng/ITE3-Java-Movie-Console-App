import dto.CastResponseDTO;
import dto.MovieDetailDTO;
import dto.MovieListResponseDTO;
import model.MovieGenre;
import service.MovieService;
import service.MovieServiceImpl;
import utils.TableRenderer;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MovieService movieService = new MovieServiceImpl();

        while (true) {

            System.out.println("============ ICinema ===========");
            System.out.println("[ 1 ] Search Movies By Title");
            System.out.println("[ 2 ] Popular Movies");
            System.out.println("[ 3 ] Search By Genres");
            System.out.println("[ E ] Exit ");
            System.out.print("Enter Option : ");

            String opt = scanner.nextLine().trim().toLowerCase();

            switch (opt) {

                // =========================
                // SEARCH BY TITLE
                // =========================
                case "1" -> {

                    System.out.println("============ Search By Title ===========");
                    System.out.print("Enter Title : ");
                    String title = scanner.nextLine();

                    int currentPage = 1;
                    boolean running = true;

                    while (running) {

                        MovieListResponseDTO response =
                                movieService.fetchMoviesByTitle(currentPage, title);

                        TableRenderer.displayMovies(response);

                        System.out.println("Page " + response.getPage()
                                + " of " + response.getTotalPages()
                                + " | Total Result : " + response.getTotalResults());

                        System.out.println("""
                            [n] Next Page      [p] Previous Page
                            [g] Go to Page     [md] Movie Detail
                            [b] Back           [e] Exit
                        """);

                        System.out.print("Choose: ");
                        String choice = scanner.nextLine().trim().toLowerCase();

                        switch (choice) {

                            case "n" -> currentPage = updatePage(response, currentPage, currentPage + 1);
                            case "p" -> currentPage = updatePage(response, currentPage, currentPage - 1);

                            case "g" -> {
                                try {
                                    System.out.print("Enter Page: ");
                                    int page = Integer.parseInt(scanner.nextLine());
                                    currentPage = updatePage(response, currentPage, page);
                                } catch (Exception e) {
                                    System.out.println("Invalid number!");
                                }
                            }

                            case "md" -> {
                                System.out.print("Enter Movie ID: ");
                                String id = scanner.nextLine();
                                showMovieDetail(movieService, id);
                            }

                            case "b" -> running = false;
                            case "e" -> System.exit(0);
                        }
                    }
                }

                // =========================
                // POPULAR MOVIES
                // =========================
                case "2" -> {

                    int currentPage = 1;
                    boolean running = true;

                    while (running) {

                        MovieListResponseDTO response =
                                movieService.fetchPopularMovies(currentPage);

                        TableRenderer.displayMovies(response);

                        System.out.println("Page " + response.getPage()
                                + " of " + response.getTotalPages()
                                + " | Total Result : " + response.getTotalResults());

                        System.out.println("""
                            [n] Next Page      [p] Previous Page
                            [g] Go to Page     [md] Movie Detail
                            [b] Back           [e] Exit
                        """);

                        System.out.print("Choose: ");
                        String choice = scanner.nextLine().trim().toLowerCase();

                        switch (choice) {

                            case "n" -> currentPage = updatePage(response, currentPage, currentPage + 1);
                            case "p" -> currentPage = updatePage(response, currentPage, currentPage - 1);

                            case "g" -> {
                                try {
                                    System.out.print("Enter Page: ");
                                    int page = Integer.parseInt(scanner.nextLine());
                                    currentPage = updatePage(response, currentPage, page);
                                } catch (Exception e) {
                                    System.out.println("Invalid number!");
                                }
                            }

                            case "md" -> {
                                System.out.print("Enter Movie ID: ");
                                String id = scanner.nextLine();
                                showMovieDetail(movieService, id);
                            }

                            case "b" -> running = false;
                            case "e" -> System.exit(0);
                        }
                    }
                }

                // =========================
                // GENRES
                // =========================
                case "3" -> {

                    List<MovieGenre> genres = movieService.fetchAllGenres();

                    System.out.println("============ Genres ===========");

                    for (int i = 0; i < genres.size(); i++) {
                        System.out.println((i + 1) + ". " +
                                (genres.get(i).getGenreName() != null
                                        ? genres.get(i).getGenreName()
                                        : "N/A"));
                    }

                    int choice;

                    while (true) {
                        try {
                            System.out.print("Choose Genre: ");
                            choice = Integer.parseInt(scanner.nextLine());

                            if (choice < 1 || choice > genres.size()) {
                                System.out.println("Invalid choice!");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter valid number!");
                        }
                    }

                    MovieGenre selected = genres.get(choice - 1);
                    String genreId = String.valueOf(selected.getGenreId());

                    int currentPage = 1;
                    boolean running = true;

                    while (running) {

                        MovieListResponseDTO response =
                                movieService.fetchMoviesByGenre(currentPage, genreId);

                        TableRenderer.displayMovies(response);

                        System.out.println("Page " + response.getPage()
                                + " of " + response.getTotalPages()
                                + " | Total Result : " + response.getTotalResults());

                        System.out.println("""
                            [n] Next Page      [p] Previous Page
                            [g] Go to Page     [md] Movie Detail
                            [b] Back           [e] Exit
                        """);

                        System.out.print("Choose: ");
                        String choiceOpt = scanner.nextLine().trim().toLowerCase();

                        switch (choiceOpt) {

                            case "n" -> currentPage = updatePage(response, currentPage, currentPage + 1);
                            case "p" -> currentPage = updatePage(response, currentPage, currentPage - 1);

                            case "g" -> {
                                try {
                                    System.out.print("Enter Page: ");
                                    int page = Integer.parseInt(scanner.nextLine());
                                    currentPage = updatePage(response, currentPage, page);
                                } catch (Exception e) {
                                    System.out.println("Invalid number!");
                                }
                            }

                            case "md" -> {
                                System.out.print("Enter Movie ID: ");
                                String id = scanner.nextLine();
                                showMovieDetail(movieService, id);
                            }

                            case "b" -> running = false;
                            case "e" -> System.exit(0);
                        }
                    }
                }

                case "e" -> System.exit(0);

                default -> System.out.println("Invalid Option!");
            }
        }
    }

    // =========================
    // HELPERS
    // =========================

    private static void showMovieDetail(MovieService service, String movieId) {
        MovieDetailDTO detail = service.fetchMovieDetails(movieId);
        CastResponseDTO cast = service.fetchMovieCast(movieId);

        if (detail != null) {
            TableRenderer.displayMovieDetails(detail, cast);
        } else {
            System.out.println("Movie not found.");
        }
    }

    private static int updatePage(MovieListResponseDTO response, int current, int next) {

        if (next < 1) {
            System.out.println("Already first page!");
            return current;
        }

        if (next > response.getTotalPages()) {
            System.out.println("Max page reached!");
            return current;
        }

        return next;
    }
}