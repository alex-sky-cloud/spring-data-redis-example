package redis.web.rest.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.service.delete.RedisDeleteService;

@RestController
@RequestMapping("api")
public class DeleteRestController {

    private RedisDeleteService service;

    @Autowired
    public DeleteRestController(RedisDeleteService service) {
        this.service = service;
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestParam String key) {

        service.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
