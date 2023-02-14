package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbapi.config.ProblemDetail;
import com.github.kishorp.ibdb.ibdbdomain.dto.PublisherDto;
import com.github.kishorp.ibdb.ibdbservice.error.IbdbServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <li> API that defines all operations for <b>publihsers</b> collection </li>
 * <li> Operates at endpoint: <code>[BASE_URL]/publishers</code> </li>
 * <li> Read more about each operation at swagger page </li>
 */
@RequestMapping("/publishers")
@CrossOrigin("http://localhost:4200")
@Tag(name = "Publisher API",description = "Operations related Book Publishers")
public interface PublisherApi {

    @Operation(
            summary = "Lists all available Publishers",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "List of Publishers",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PublisherDto.class)))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<PublisherDto>> getAllPublishers( @Parameter(required = false) @RequestParam(value="name", required = false) String name,
                                                         @Parameter(required = false) @RequestParam(value="email", required = false) String email);



    @Operation(
            summary = "Fetches a Published based on ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Publisher based on id in path param",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublisherDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> getPublisherById(@PathVariable("id") String id) throws IbdbServiceException;

    @Operation(
            summary = "Adds a new Publisher",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created Publisher",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublisherDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> postNewPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException ;

    @Operation(
            summary = "Updates an existing Publisher",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "updated Publisher",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublisherDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PublisherDto> putUpdatedPublisher(@RequestBody PublisherDto publisherDto) throws IbdbServiceException ;

    @Operation(
            summary = "Deletes an existing Publisher",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete successful"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> deletePublisherById(@PathVariable("id") String id) ;
}
