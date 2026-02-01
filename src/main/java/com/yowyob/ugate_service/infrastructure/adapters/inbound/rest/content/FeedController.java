package com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.content;

import com.yowyob.ugate_service.domain.ports.out.syndicate.dto.PublicationResponseDTO;
import com.yowyob.ugate_service.infrastructure.adapters.inbound.rest.dto.response.EventResponseDTO;
import com.yowyob.ugate_service.domain.ports.in.content.GetFeedUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
@Tag(name = "Feed", description = "API for retrieving a combined feed of publications and events")
public class FeedController {

    private final GetFeedUseCase getFeedUseCase;

    @Operation(summary = "Get a paginated feed of publications and events",
               description = "Retrieves a combined and sorted feed of recent publications and events. Each item in the feed is a map containing a 'type' (publication or event) and 'data' (either a PublicationResponseDTO or an EventResponseDTO).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved feed items",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(
                                                description = "A list of feed items, where each item is a map with 'type' and 'data'. 'data' can be PublicationResponseDTO or EventResponseDTO.",
                                                example = """
                                                [
                                                  {
                                                    "type": "publication",
                                                    "data": {
                                                      "id": "123e4567-e89b-12d3-a456-426614174000",
                                                      "branchId": "123e4567-e89b-12d3-a456-426614174001",
                                                      "authorFullName": "John Doe",
                                                      "content": "My latest thoughts.",
                                                      "nLikes": 10,
                                                      "nComments": 2,
                                                      "createdAt": "2023-01-01T12:00:00Z",
                                                      "fileUrlAndType": [
                                                        {"url": "http://example.com/pub_img.jpg", "type": "IMAGE"}
                                                      ]
                                                    }
                                                  },
                                                  {
                                                    "type": "event",
                                                    "data": {
                                                      "id": "123e4567-e89b-12d3-a456-426614174002",
                                                      "creatorId": "123e4567-e89b-12d3-a456-426614174003",
                                                      "branchId": "123e4567-e89b-12d3-a456-426614174001",
                                                      "title": "Community Meetup",
                                                      "description": "A gathering for local developers.",
                                                      "location": "Community Hall",
                                                      "date": "2023-01-15",
                                                      "startTime": "18:00:00",
                                                      "endTime": "20:00:00",
                                                      "createdAt": "2023-01-05T10:00:00Z",
                                                      "updatedAt": "2023-01-05T10:00:00Z",
                                                      "participantCount": 50,
                                                      "imageUrls": ["http://example.com/event_banner.jpg"]
                                                    }
                                                  }
                                                ]
                                                """,
                                                oneOf = { PublicationResponseDTO.class, EventResponseDTO.class }
                                            )))
            ,
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(produces = "application/json")
    public Flux<Map<String, Object>> getFeed(
            @Parameter(description = "Page number (0-indexed)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        return getFeedUseCase.getFeed(page, size);
    }
}
