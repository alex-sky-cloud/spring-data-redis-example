package redis.service.read;

import java.util.Map;

public interface RedisReadService {

    /**
     * получаем коллекцию  пар ключ:значение
     * @return
     */
    Map<String, String> getAll();

    /**
     * получаем коллекцию только ключей
     */
    Map<String, String> getKeys();

}
