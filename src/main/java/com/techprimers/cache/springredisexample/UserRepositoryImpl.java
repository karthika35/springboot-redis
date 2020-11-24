package com.techprimers.cache.springredisexample;

import com.techprimers.cache.springredisexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRepositoryImpl {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;
    private final String Hkey = "testSuc-01";

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@RequestBody User user){

        redisTemplate.opsForHash().put(Hkey,user.getId(),user);
        return "product created succesfully";

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(){

       return new ResponseEntity<>( redisTemplate.opsForHash().entries(Hkey), HttpStatus.OK);


    }

}
