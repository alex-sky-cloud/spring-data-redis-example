package redis.service.delete.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.dao.repository.RedisRepository;
import redis.service.delete.RedisDeleteService;

@Service
public class RedisDeleteServiceImpl implements RedisDeleteService {

    private RedisRepository redisRepository;

    @Autowired
    public RedisDeleteServiceImpl(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void delete(String key) {

        redisRepository.delete(key);
    }
}
