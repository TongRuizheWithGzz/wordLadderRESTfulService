package com.owe.wordladder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
public class WordLadderController {

    private static Logger LOGGER = LogManager.getLogger(WordLadderController.class);

    @RequestMapping("/wl")
    public Vector<String> greeting(@RequestParam(value = "begin", defaultValue = "hello") String begin,
                           @RequestParam(value = "end", defaultValue = "world") String end) {
        LOGGER.info("User use my application.");
        WordLadder wl = new WordLadder(begin, end);
        LOGGER.info("The start is "+begin+" , the end is "+end);
        return wl.BFS();

    }
}



