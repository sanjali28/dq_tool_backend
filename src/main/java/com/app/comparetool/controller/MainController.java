package com.app.comparetool.controller;

import com.app.comparetool.service.DatabaseCheckerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private DatabaseCheckerService compareService;

    @Operation(
            summary = "Get a list of databases",
            description = "Get a list of databases",
            tags = {"databaseNames"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND: Entity could not be found"),
            @ApiResponse(responseCode = "500", description = "Internal server error, please try again or contact the administrator")}
    )
    @RequestMapping(
            value = "displayAllDbs",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> getAllDatabaseNames(){

        try {
            return ResponseEntity.ok().body(compareService.getAllDatabaseNames());
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
