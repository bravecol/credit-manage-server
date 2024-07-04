package jp.colworks.credit_manage_server.application.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.colworks.credit_manage_server.application.form.ManageRequest;
import jp.colworks.credit_manage_server.application.service.ManageService;
import jp.colworks.credit_manage_server.domain.model.ManageModel;
import jp.colworks.credit_manage_server.domain.model.ManageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * クレカ明細編集機能コントローラークラス
 * 
 * @author col
 */
@RestController
public class ManageController {

    @Autowired
    private ManageService service;

    @PostMapping("/api/manage/search")
    public ManageResponse authenticate(@RequestBody ManageRequest request) {

        ManageModel model = new ManageModel();
        model.setId(request.getId());
        model.setDueDate(request.getDueDate());

        return service.search(model);
    }
}
