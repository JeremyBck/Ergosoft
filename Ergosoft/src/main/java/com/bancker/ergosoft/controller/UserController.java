package com.bancker.ergosoft.controller;

import com.bancker.ergosoft.commands.UserToUpdateOrDelete;
import com.bancker.ergosoft.converters.UserConverter;
import com.bancker.ergosoft.model.v0.UserModel;
import com.bancker.ergosoft.persistence.entity.User;
import com.bancker.ergosoft.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/v0/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody UserModel userModel){
        User user=UserConverter.convertUserModelToUser(userModel);
        return new ResponseEntity(userService.addUser(user), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@RequestBody UserToUpdateOrDelete userToUpdateOrDelete){
        User user=userService.findByEmail(userToUpdateOrDelete.getEmail());
        userService.removeUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
