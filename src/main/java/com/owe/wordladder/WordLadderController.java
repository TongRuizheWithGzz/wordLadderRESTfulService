package com.owe.wordladder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
public class WordLadderController {

    @RequestMapping("/wl")
    public Vector<String> greeting(@RequestParam(value = "begin", defaultValue = "hello") String begin,
                           @RequestParam(value = "end", defaultValue = "world") String end) {
        WordLadder wl = new WordLadder(begin, end);
        return wl.BFS();
    }
}



