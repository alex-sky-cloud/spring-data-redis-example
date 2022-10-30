package redis.web.rest.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.service.read.RedisReadService;

import java.util.Map;

@RestController
@RequestMapping("api")
public class ReadRestController {

    private RedisReadService service;

    @Autowired
    public ReadRestController(RedisReadService service) {
        this.service = service;
    }

    @GetMapping("values")
    public @ResponseBody Map<String, String> findAll(){

        Map<String, String> all = service.getAll();

        return all;
    }


    @RequestMapping("keys/all")
    public @ResponseBody Map<String, String> keys() {

        Map<String, String> keys = service.getKeys();

        return keys;
    }

}
