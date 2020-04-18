package it.univaq.disim.numismatic.stubservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StubControllerImpl implements StubController {

    @Override
    public ResponseEntity<StubResource> stub() {
        StubResource stubResource = new StubResource();
        stubResource.setStub("stub");
        return ResponseEntity.ok(stubResource);
    }

}
