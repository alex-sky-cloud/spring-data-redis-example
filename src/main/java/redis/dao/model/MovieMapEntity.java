package redis.dao.model;

import java.util.Map;
import java.util.Objects;

public class MovieMapEntity {

    Map<Object, Object> allMovies;

    public MovieMapEntity() {
    }

    public Map<Object, Object> getAllMovies() {
        return allMovies;
    }

    public void setAllMovies(Map<Object, Object> allMovies) {
        this.allMovies = allMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieMapEntity that = (MovieMapEntity) o;
        return Objects.equals(allMovies, that.allMovies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allMovies);
    }

    @Override
    public String toString() {
        return "MovieMapEntity{" +
                "allMovies=" + allMovies +
                '}';
    }
}
