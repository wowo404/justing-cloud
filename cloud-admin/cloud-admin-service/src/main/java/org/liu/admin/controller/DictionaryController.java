package org.liu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.admin.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(dictionaryService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(dictionaryService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(dictionaryService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        dictionaryService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        dictionaryService.delete(ids);
        return Response.ok();
    }
}

