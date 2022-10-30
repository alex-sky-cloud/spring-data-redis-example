package redis.service.dto;

import java.util.Map;
import java.util.Objects;

public class MovieMapDto {

    Map<String, String> allMovies;

    public MovieMapDto() {
    }

    public Map<String, String> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(Map<String, String> allMovies) {
        this.allMovies = allMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieMapDto that = (MovieMapDto) o;
        return Objects.equals(allMovies, that.allMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allMovies);
    }

    @Override
    public String toString() {
        return "MovieMapDto{" +
                "allMovies=" + allMovies +
                '}';
    }
}
