package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.impl;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.AuthorizationService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api.CommentService;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.client.CommentsClient;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentAddModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentsClient commentsClient;

    @Autowired
    AuthorizationService authorizationService;

    @Override
    public List<CommentResponseModel> list(UUID userId) {
        ResponseEntity<List<CommentResponseModel>> response = commentsClient.list(userId);
        if(response.getStatusCode() == HttpStatus.OK) return response.getBody();
        throw new RuntimeException("Unknown Response code from comments service: " + response.getStatusCode().value());
    }

    @Override
    public void delete(UUID userId) {
        ResponseEntity<?> response = commentsClient.delete(userId);
        if(response.getStatusCode() == HttpStatus.NO_CONTENT) return;
        throw new RuntimeException("Unknown Response code from comments service: " + response.getStatusCode().value());
    }

    @Override
    public List<CommentResponseModel> add(CommentAddModel commentAddModel) {
        ResponseEntity<List<CommentResponseModel>> response = commentsClient.add(commentAddModel);
        if(response.getStatusCode() == HttpStatus.CREATED) return response.getBody();
        throw new RuntimeException("Unknown Response code from comments service: " + response.getStatusCode().value());
    }
}
