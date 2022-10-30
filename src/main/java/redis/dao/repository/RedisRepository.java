package redis.dao.repository;

import java.util.Map;

import redis.dao.model.Movie;

public interface RedisRepository {

    /** Return all movies*/
    Map<Object, Object> findAllMovies();

    /** Add key-value pair to Redis (Key:value). */
    void add(Movie movie);

    /**Delete a key-value pair in Redis (key:value).*/
    void delete(String id);

    /**find a movie*/
    Movie findMovie(String id);

}
