package it.univaq.disim.numismatic.stubservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface StubController {

    @GetMapping("stub")
    ResponseEntity<StubResource> stub();

}
