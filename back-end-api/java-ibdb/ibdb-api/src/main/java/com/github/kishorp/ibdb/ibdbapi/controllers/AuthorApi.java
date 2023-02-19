package com.github.kishorp.ibdb.ibdbapi.controllers;

import com.github.kishorp.ibdb.ibdbapi.config.ProblemDetail;
import com.github.kishorp.ibdb.ibdbdomain.dto.AuthorDto;
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

@RequestMapping("/authors")
@CrossOrigin("http://localhost:4200")
@Tag(name = "Author API",description = "Operations related Book Authors")
public interface AuthorApi {


    @Operation(
            summary = "Lists all available Authors",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "List of Authors",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AuthorDto.class)))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<AuthorDto>> getAllAuthors(@Parameter(required = false) @RequestParam(value="name", required = false) String name,
                                                  @Parameter(required = false) @RequestParam(value="email", required = false) String email);

    @Operation(
            summary = "Fetches a Published based on ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Author based on id in path param",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") String id) throws IbdbServiceException;

    @Operation(
            summary = "Adds a new Author",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created Author",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<AuthorDto> postNewAuthor(@RequestBody AuthorDto publisherDto) throws IbdbServiceException ;

    @Operation(
            summary = "Updates an existing Author",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "updated Author",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthorDto.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
            }
    )
    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<AuthorDto> putUpdatedAuthor(@RequestBody AuthorDto publisherDto) throws IbdbServiceException;

    @Operation(
            summary = "Deletes an existing Author",
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
    ResponseEntity<String> deleteAuthorById(@PathVariable("id") String id) ;
}
