package com.java360.pmanager;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/ok")
    public ResponseEntity<String> sayOk() {
        return ResponseEntity.ok("Everything OK!");
    }

    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody String value) {
        StringBuilder sb = new StringBuilder(value);
        return ResponseEntity.ok(sb.reverse().toString());
    }
}
