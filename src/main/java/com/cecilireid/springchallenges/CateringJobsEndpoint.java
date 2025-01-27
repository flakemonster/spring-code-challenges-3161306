package com.cecilireid.springchallenges;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@Endpoint(id = "catering-jobs")
public class CateringJobsEndpoint {
    private final CateringJobRepository cateringJobRepository;

    public CateringJobsEndpoint(CateringJobRepository cateringJobRepository) {
        this.cateringJobRepository = cateringJobRepository;
    }

    @ReadOperation
    public Map<Status, Long> getStatusCount() {
        return cateringJobRepository.findAll()
                .stream()
                .collect(groupingBy(CateringJob::getStatus, Collectors.counting()));
    }
}
