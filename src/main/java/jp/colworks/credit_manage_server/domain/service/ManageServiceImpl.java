package jp.colworks.credit_manage_server.domain.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import jp.colworks.credit_manage_server.application.service.ManageService;
import jp.colworks.credit_manage_server.domain.model.ManageModel;
import jp.colworks.credit_manage_server.domain.model.ManageResponse;

@Service
public class ManageServiceImpl implements ManageService {

    @Override
    public ManageResponse search(ManageModel model) {
        // TODO: 検索処理記載
        ManageResponse response = new ManageResponse();

        response.setName("カード名称");
        response.setUseDate(new Date());
        response.setUseTarget("利用先");
        response.setPrice(100);
        response.setRemarks("備考");
        response.setCategory(100);
        response.setDueDate(new Date());

        return response;
    }

}
