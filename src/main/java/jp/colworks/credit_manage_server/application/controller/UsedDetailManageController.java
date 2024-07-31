package jp.colworks.credit_manage_server.application.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.colworks.credit_manage_server.application.request.UsedDetailManageRequest;
import jp.colworks.credit_manage_server.application.service.UsedDetailManageService;
import jp.colworks.credit_manage_server.domain.model.UsedDetailManageModel;
import jp.colworks.credit_manage_server.domain.model.UsedDetailManageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 利用明細管理機能コントローラークラス
 * 
 * @author col
 */
@RestController
public class UsedDetailManageController {

    @Autowired
    private UsedDetailManageService service;

    @PostMapping("/api/manage/search")
    public UsedDetailManageResponse authenticate(@RequestBody UsedDetailManageRequest request) {

        UsedDetailManageModel model = new UsedDetailManageModel();
        model.setId(request.getId());
        model.setDueDate(request.getDueDate());

        return service.search(model);
    }
}
