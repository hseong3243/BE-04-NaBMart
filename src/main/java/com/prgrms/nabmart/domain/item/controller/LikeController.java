package com.prgrms.nabmart.domain.item.controller;

import com.prgrms.nabmart.domain.item.controller.request.RegisterLikeItemRequest;
import com.prgrms.nabmart.domain.item.service.LikeService;
import com.prgrms.nabmart.domain.item.service.request.RegisterLikeItemCommand;
import com.prgrms.nabmart.global.auth.LoginUser;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {

    private static final String BASE_URI = "/api/v1/likes/";

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> registerLikeItem(
        @RequestBody @Valid RegisterLikeItemRequest registerLikeItemRequest,
        @LoginUser Long userId) {
        RegisterLikeItemCommand registerLikeItemCommand
            = RegisterLikeItemCommand.of(userId, registerLikeItemRequest.itemId());
        Long likeItemId = likeService.registerLikeItem(registerLikeItemCommand);
        URI location = URI.create("/api/v1/likes/" + likeItemId);
        return ResponseEntity.created(location).build();
    }
}
