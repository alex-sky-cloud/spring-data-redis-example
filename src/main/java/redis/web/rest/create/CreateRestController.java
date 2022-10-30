package redis.web.rest.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.service.create.RedisCreateService;

@RestController
@RequestMapping("api")
public class CreateRestController {

    private RedisCreateService service;

    @Autowired
    public CreateRestController(RedisCreateService service) {
        this.service = service;
    }

    @PostMapping("keys")
    public ResponseEntity<String> add( @RequestParam String key, @RequestParam String value){

        service.add(key, value);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
