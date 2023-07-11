package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.client;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentAddModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "CommentsClient", url = "${comments-management-url}/api/v1/comment")
public interface CommentsClient {
    @GetMapping()
    ResponseEntity<List<CommentResponseModel>> list(@RequestParam(name = "userId") UUID userId);

    @PostMapping()
    ResponseEntity<List<CommentResponseModel>> add(@RequestBody CommentAddModel commentPostModel);

    @DeleteMapping()
    ResponseEntity<?> delete(@RequestParam(name = "userId") UUID userId);
}
