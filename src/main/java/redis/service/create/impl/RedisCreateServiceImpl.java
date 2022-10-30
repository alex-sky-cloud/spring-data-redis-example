package redis.service.create.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import redis.dao.model.Movie;
import redis.dao.repository.RedisRepository;
import redis.service.create.RedisCreateService;
import redis.service.dto.MovieDto;
import redis.service.mapper.MapperMovies;

@Service
public class RedisCreateServiceImpl implements RedisCreateService {


    private RedisRepository redisRepository;

    private MapperMovies mapper;

    @Autowired
    public RedisCreateServiceImpl(RedisRepository redisRepository, MapperMovies mapper) {
        this.redisRepository = redisRepository;
        this.mapper = mapper;
    }

    @Override
    public void add(String key,  String value) {

        MovieDto movieDto = new MovieDto();
        movieDto.setId(key);
        movieDto.setName(value);

        Movie entity = mapper.toEntity(movieDto);

        redisRepository.add(entity);
    }
}
