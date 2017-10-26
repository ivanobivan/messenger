package ru.messenger.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ResourceController {

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

   /* @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/login/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        return "hello " + id;
    }*/
}
