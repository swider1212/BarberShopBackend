package pl.ibd.cloud.itscloudy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ibd.cloud.itscloudy.Consts;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/check-health")
public final class CheckHealthController {

    @GetMapping(
            produces = Consts.APPLICATION_JSON
    )
    public ResponseEntity<String> CheckHealth(){
        return ResponseEntity
                .status(OK)
                .body("Nie ma sprawy szefie, kogo zabić");
    }
}
