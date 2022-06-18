package org.liu.search.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.search.pojo.SearchResp;
import org.liu.search.service.CloudSearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索入口
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final CloudSearchService cloudSearchService;

    @PostMapping("/search")
    public Response<SearchResp> search() {
        return Response.ok();
    }

}
