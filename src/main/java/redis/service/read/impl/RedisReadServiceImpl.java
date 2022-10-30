package redis.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.dao.repository.RedisRepository;
import redis.service.mapper.MapperMoviesAbstract;
import redis.service.read.RedisReadService;

import java.util.Map;

@Service
public class RedisReadServiceImpl implements RedisReadService {

    private RedisRepository redisRepository;

    private MapperMoviesAbstract mapper;


    @Autowired
    public RedisReadServiceImpl(RedisRepository redisRepository,
                                MapperMoviesAbstract mapper) {
        this.redisRepository = redisRepository;

        this.mapper = mapper;
    }


    @Override
    public Map<String, String> getKeys() {

        Map<Object, Object> allKeys = redisRepository.findAllMovies();

        Map<String, String> dtoKeys = mapper.toDto(allKeys);

        return dtoKeys;
    }

    /**
     * redisRepository.findAllMovies(); - получает коллекцию ключей записей.
     * @return коллекцию объектов, с разобранными ключами и преобразовывает все в
     */
    @Override
    public Map<String, String> getAll() {

        Map<Object, Object> allMovies = redisRepository.findAllMovies();

        Map<String, String> dto = mapper.toDto(allMovies);

        return dto;
    }

}
