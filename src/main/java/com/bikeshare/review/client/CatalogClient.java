package com.bikeshare.review.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "http://localhost:8082")
public interface CatalogClient {
    @GetMapping("/api/bikes/{id}")
    BikeResponse getBike(@PathVariable("id") Long id);
}