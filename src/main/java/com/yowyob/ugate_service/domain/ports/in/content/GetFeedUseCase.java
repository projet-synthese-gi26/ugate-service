package com.yowyob.ugate_service.domain.ports.in.content;

import java.util.Map;
import reactor.core.publisher.Flux;

public interface GetFeedUseCase {
    Flux<Map<String, Object>> getFeed(int page, int size);
}
