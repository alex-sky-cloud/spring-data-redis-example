package redis.service.mapper;

import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring")
public abstract class MapperMoviesAbstract {

    /**
     * Map.Entry<Object, Object> entry - временная переменная для
     * текущей итерации
     * mapEntity.entrySet() - получаем набор пар ключ:значения из объекта
     * mapEntity
     * entry.getKey() - получаем ключ, текущей обрабатываемой записи
     * Затем используя полученный ключ, можно из предоставленного объекта
     * (без необходимости делать перебор в цикле for()). получить значение
     * по данному ключу.
     *  stringMap.put(key, mapEntity.get(key).toString()); - выполняем преобразование
     * @param mapEntity
     * @return возвращает преобразованный объект
     */
    public Map<String, String> toDto(Map<Object, Object> mapEntity) {

        if(mapEntity == null) return null;

        Map<String, String> stringMap = new HashMap<>();

        for(Map.Entry<Object, Object> entry : mapEntity.entrySet()){

            String key = (String) entry.getKey();

            stringMap.put(key, mapEntity.get(key).toString());
        }

        return stringMap;

    }


    public abstract List< Map<String, String>> toListDto(Collection<Map<Object, Object>> listEntity);

}
