package com.app.comparetool.controller;

import com.app.comparetool.dto.DatabaseDetails;
import com.app.comparetool.service.CompareDataService;
import com.app.comparetool.service.DatabaseCheckerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private static final Logger log = LogManager.getLogger(MainController.class);
    @Autowired
    private DatabaseCheckerService databaseCheckerService;

    @Autowired
    private CompareDataService compareDataService;

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
            log.info("Fetching list of all databases...");
            List<String> listOfDatabases = databaseCheckerService.getAllDatabaseNames();
            log.info("Response received from service layer successfully - "+listOfDatabases);
            return ResponseEntity.ok().body(listOfDatabases);
        } catch (Exception e){
            log.info("Issue while fetching list of all databases...");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(
            summary = "Get a list of table names inside a database",
            description = "Get a list of table names inside a database",
            tags = {"tableNames"}
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
            value = "displayAllTables/{databaseName}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> getAllTableNames(@PathVariable String databaseName){

        try {
            log.info("Fetching list of all tables...");
            List<String> listOfTables = databaseCheckerService.getAllTableNames(databaseName);
            log.info("Response received from service layer successfully - "+listOfTables);
            return ResponseEntity.ok().body(listOfTables);
        } catch (Exception e){
            log.info("Issue while fetching list of all tables...");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(
            summary = "Get a list of table names inside a database",
            description = "Get a list of table names inside a database",
            tags = {"tableNames"}
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
            value = "displayAllTables/{databaseName}/{tableName}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> getAllColumnNames(@PathVariable String databaseName, @PathVariable String tableName){

        try {
            log.info("Fetching list of all columns...");
            List<String> listOfColumns = databaseCheckerService.getAllColumnNames(databaseName,tableName);
            log.info("Response received from service layer successfully - "+listOfColumns);
            return ResponseEntity.ok().body(listOfColumns);
        } catch (Exception e){
            log.info("Issue while fetching list of all columns...");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Operation(
            summary = "Get a list of table names inside a database",
            description = "Get a list of table names inside a database",
            tags = {"tableNames"}
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
            value = "compareDataForUserInput",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public void fetchDataForUserInput(@RequestBody DatabaseDetails databaseDetails){

        log.info("Data from user {}",databaseDetails);
        compareDataService.fetchDataAndCompareForUserInput(databaseDetails);

    }

}
