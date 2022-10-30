package redis.service.mapper;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import redis.dao.model.Movie;
import redis.service.dto.MovieDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface MapperMovies  {

    Movie toEntity(MovieDto dto);

    /**
     * трансформация map-сущности полученной из базы Redis, в объект
     * типа dto
     * @param mapEntity - параметр
     * @return
     */
 /*
   Don't work
  @MapMapping(keyTargetType = Object.class, valueTargetType = Object.class)
    Map<String, String> toDto(Map<Object, Object> mapEntity);

    List<Map <String, String>> toListDto(Collection<Map<Object, Object>> listEntity);*/

    /**
     * трансформация объекта типа map-dto в map-сущность для работы с базой Redis
     * @param mapDto
     * @return
     */
    @MapMapping(keyTargetType = Object.class, valueTargetType = Object.class)
    Map<Object, Object> toEntity(Map<String, String> mapDto);

    List<Map<Object, Object> > toListEntity(Collection<Map<String, String>> listDto);


}

