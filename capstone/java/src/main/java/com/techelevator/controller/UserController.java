package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;

    }
    @RequestMapping(path="/user/{name}", method= RequestMethod.GET)
    public int user (@PathVariable("name") String userName) {
        return userDao.findIdByUsername(userName);
    }
}
