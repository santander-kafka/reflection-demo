package com.mknieszner.reflectiondemo;

import com.mknieszner.reflectiondemo.input.ExtraProductDto;
import com.mknieszner.reflectiondemo.util.ByPath;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping(params = "path")
    public String byPath(@RequestParam List<String> path, @RequestBody ExtraProductDto o) {
        return ByPath.getString(o, path.toArray(new String[0]));
    }
}
