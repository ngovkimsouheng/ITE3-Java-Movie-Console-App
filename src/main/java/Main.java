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
            String opt = scanner.nextLine().toLowerCase();
            switch (opt) {
                case "1" -> {
                    System.out.println("============ Search By Title ===========");
                    System.out.print("Enter Title : ");
                    String title = scanner.nextLine();
                    Integer currentPage = 1;
                    boolean isSearching = true;
                    while (isSearching) {
                        try {
                            MovieListResponseDTO movieResponse = movieService.fetchMoviesByTitle(currentPage, title);
                            TableRenderer.displayMovies(movieResponse);
                            System.out.println("Page " + movieResponse.getCurrentPage() + " of " + movieResponse.getTotalPages() + " | Total Result : " + movieResponse.getTotalResults());
                            System.out.println("""
                                [n] Next Page\t\t[b] Back
                                [p] Previous Page\t[e] Exit
                                [g] Go to Page
                                [md] Movie Detail
                                """);
                            System.out.print("[-] Choose an Option : ");
                            String listOpt = scanner.nextLine().toLowerCase();
                            switch (listOpt) {

                                case "n" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage + 1);
                                }

                                case "p" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage - 1);
                                }

                                case "g" -> {
                                    try {
                                        System.out.print("[-] Enter Page Number : ");
                                        Integer goToPage = Integer.parseInt(scanner.nextLine());
                                        currentPage = updatePageNumber(movieResponse, currentPage, goToPage);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid number!");
                                    }
                                }

                                case "md" -> {
                                    try {
                                        System.out.print("[-] Enter Movie ID : ");
                                        String movieId = scanner.nextLine();

                                        TableRenderer.displayMovieDetails(
                                                movieService.fetchMovieDetails(movieId),
                                                movieService.fetchMovieCast(movieId)
                                        );
                                        System.out.println("""
                                    [b] Back
                                    [e] Exit
                                """);
                                        String detailOpt = scanner.nextLine().toLowerCase();
                                        if (detailOpt.equals("e")) {
                                            System.exit(0);
                                        }

                                    } catch (RuntimeException e) {
                                        System.out.println("Invalid Id! Exiting... Please Wait!");
                                    }
                                }

                                case "b" -> {
                                    isSearching = false;
                                    currentPage = 1;
                                }

                                case "e" -> {
                                    System.exit(0);
                                }
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
                case "2" -> {
                    System.out.println("============ Popular Movies ===========");
                    Integer currentPage = 1;
                    boolean isSearching = true;
                    while (isSearching) {
                        try {
                            MovieListResponseDTO movieResponse = movieService.fetchPopularMovies(currentPage);

                            TableRenderer.displayMovies(movieResponse);
                            System.out.println("Page " + movieResponse.getCurrentPage() + " ( Maximum At 500 Pages ) " + " of " + movieResponse.getTotalPages() + " | Total Result : " + movieResponse.getTotalResults());
                            System.out.println("""
                            [n] Next Page\t\t[b] Back
                            [p] Previous Page\t[e] Exit
                            [g] Go to Page
                            [md] Movie Detail
                            """);
                            System.out.print("[-] Choose an Option : ");
                            String listOpt = scanner.nextLine().toLowerCase();
                            switch (listOpt) {
                                case "n" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage + 1);
                                }
                                case "p" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage - 1);
                                }
                                case "g" -> {
                                    try {
                                        System.out.print("[-] Enter Page Number : ");
                                        Integer goToPage = Integer.parseInt(scanner.nextLine());
                                        currentPage = updatePageNumber(movieResponse, currentPage, goToPage);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid number!");
                                    }
                                }
                                case "md" -> {
                                    try {
                                        System.out.print("[-] Enter Movie ID : ");
                                        String movieId = scanner.nextLine();

                                        TableRenderer.displayMovieDetails(movieService.fetchMovieDetails(movieId), movieService.fetchMovieCast(movieId));
                                        System.out.println("""
                                    [b] Back
                                    [e] Exit
                                """);
                                        String detailOpt = scanner.nextLine().toLowerCase();
                                        if (detailOpt.equals("e")) {
                                            System.exit(0);
                                        }
                                    } catch (RuntimeException e) {
                                        System.out.println("Invalid Id! Exiting... Please Wait!");
                                    }
                                }
                                case "b" -> {
                                    isSearching = false;
                                    currentPage = 1;
                                }
                                case "e" -> {
                                    System.exit(0);
                                }
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
                case "3" -> {
                    System.out.println("============ Search By Genres ===========");
                    List<MovieGenre> genres = movieService.fetchAllGenres();
                    for (int i = 0; i < genres.size(); i++) {
                        System.out.println((i + 1) + ". " + genres.get(i).getGenreName());
                    }
                    System.out.print("Choose Genre : ");
                    int choice = -1;

                    while (true) {
                        System.out.print("Choose Genre : ");
                        String input = scanner.nextLine();
                        try {
                            choice = Integer.parseInt(input);
                            if (choice < 1 || choice > genres.size()) {
                                System.out.println("Invalid choice! Please select between 1 and " + genres.size());
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number!");
                        }
                    }

                    MovieGenre selected = genres.get(choice - 1);
                    String genreId = String.valueOf(selected.getGenreId());

                    Integer currentPage = 1;
                    boolean isSearching = true;

                    while (isSearching) {
                        try {
                            MovieListResponseDTO movieResponse = movieService.fetchMoviesByGenre(currentPage, genreId);
                            TableRenderer.displayMovies(movieResponse);
                            System.out.println("Page " + movieResponse.getCurrentPage() + " of " + movieResponse.getTotalPages() + " | Total Result : " + movieResponse.getTotalResults());
                            System.out.println("""
                                [n] Next Page\t\t[b] Back
                                [p] Previous Page\t[e] Exit
                                [g] Go to Page
                                [md] Movie Detail
                            """);
                            System.out.print("[-] Choose an Option : ");
                            String listOpt = scanner.nextLine().toLowerCase();
                            switch (listOpt) {
                                case "n" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage + 1);
                                }
                                case "p" -> {
                                    currentPage = updatePageNumber(movieResponse, currentPage, currentPage - 1);
                                }
                                case "g" -> {
                                    try {
                                        System.out.print("[-] Enter Page Number : ");
                                        Integer goToPage = Integer.parseInt(scanner.nextLine());
                                        currentPage = updatePageNumber(movieResponse, currentPage, goToPage);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid number!");
                                    }
                                }
                                case "md" -> {
                                    System.out.print("[-] Enter Movie ID : ");
                                    String movieId = scanner.nextLine();

                                    try {
                                        MovieDetailDTO detail = movieService.fetchMovieDetails(movieId);
                                        CastResponseDTO cast = movieService.fetchMovieCast(movieId);

                                        if (detail != null) {
                                            TableRenderer.displayMovieDetails(detail, cast);
                                        } else {
                                            System.out.println("No movie details found.");
                                        }

                                        System.out.println("""
            [b] Back
            [e] Exit
        """);

                                        String detailOpt = scanner.nextLine().toLowerCase();
                                        if (detailOpt.equals("e")) System.exit(0);

                                    } catch (Exception e) {
                                        System.out.println("❌ Failed to load movie details");
                                        System.out.println("Reason: " + e.getMessage());
                                    }
                                }
                                case "b" -> {
                                    isSearching = false;
                                    currentPage = 1;
                                }
                                case "e" -> System.exit(0);
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
                case "e" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Opt! Try Again.");
                }
            }
        }
    }

    private static Integer updatePageNumber(MovieListResponseDTO movieResponse, Integer currentPage, Integer pageNum) {

        if (pageNum < 1) {
            System.out.println("Already at first page!");
            return currentPage;
        }

        if (pageNum > movieResponse.getTotalPages()) {
            System.out.println("No more pages! Max page is " + movieResponse.getTotalPages());
            return currentPage;
        }

        if (pageNum > 500) {
            System.out.println("Page limit is 500!");
            return currentPage;
        }

        return pageNum;
    }
}
