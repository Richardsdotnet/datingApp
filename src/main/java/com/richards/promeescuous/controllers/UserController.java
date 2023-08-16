package com.richards.promeescuous.controllers;


import com.github.fge.jsonpatch.JsonPatch;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import com.richards.promeescuous.dtos.responses.GetUserResponse;
import com.richards.promeescuous.dtos.responses.RegisterUserResponse;
import com.richards.promeescuous.dtos.responses.UpdateUserResponse;
import com.richards.promeescuous.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userServices;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest registerUserRequest) {
        var response = userServices.register(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable Long id){
        GetUserResponse user = userServices.getUserById(id);
        return ResponseEntity.ok().body(user);
        }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUserAccount(@RequestBody JsonPatch jsonPatch, @PathVariable Long id){
        UpdateUserResponse response = userServices.updateUserProfile(jsonPatch, id);
        return ResponseEntity.ok(response);
    }


    }