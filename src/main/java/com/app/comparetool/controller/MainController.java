package com.app.comparetool.controller;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.service.DatabaseCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private DatabaseCheckerService compareService;

    @GetMapping("compare")
    public ResponseEntity<?> getDbDetails(@RequestBody DatabaseDetails databaseDetails){
        return ResponseEntity.ok().body(compareService.checkIfDatabaseExists(databaseDetails));
    }
}
