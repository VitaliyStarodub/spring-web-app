package com.starodub.controller.rest;

import com.starodub.controller.external.model.UserDto;
import com.starodub.model.User;
import com.starodub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping (value = "/user", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
        return userService.getUserByMail(email)
                .map(UserDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
