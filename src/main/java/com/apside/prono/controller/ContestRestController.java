package com.apside.prono.controller;

import com.apside.prono.errors.InvalidContestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apside.prono.model.Contest;
import com.apside.prono.model.Player;
import com.apside.prono.service.ContestService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
public class ContestRestController {

    @Autowired
    private ContestService contestService;

    @GetMapping(produces = "application/json", path = "/contest")
    public Iterable<Contest> getAllContest() {

        return contestService.getAllContest();
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/contest")
    public ResponseEntity<Contest> create(@RequestBody Contest contest, UriComponentsBuilder uriBuilder) {
        if (contest == null) {
            throw new InvalidContestDataException();
        } else {
            contestService.createContest(contest);
            URI location = uriBuilder.path("/contest/{id}").buildAndExpand(contest.getId()).toUri();
            return ResponseEntity.created(location).body(contest);
        }
    }

    @GetMapping(produces = "application/json", path = "/contest/{id}")
    public Contest getContestById(@PathVariable Long id) {

        return contestService.getContestById(id);
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/contest")
    public Contest contestPlayer(@RequestBody Contest contest) {

        if (contest == null) {
            throw new InvalidContestDataException();
        } else {
            return contestService.modifyContest(contest);
        }
    }

    @DeleteMapping(path = "/contest")
    public void deleteContest(@RequestBody Long id) {
        contestService.deleteContest(id);
    }
}






