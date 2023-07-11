package de.electi.academy.usermanagement.microservice.usermanagement.service.v1.api;

import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentAddModel;
import de.electi.academy.usermanagement.microservice.usermanagement.service.v1.models.CommentResponseModel;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentResponseModel> list(UUID userId);
    void delete(UUID userId);
    List<CommentResponseModel> add(CommentAddModel commentAddModel);
}
