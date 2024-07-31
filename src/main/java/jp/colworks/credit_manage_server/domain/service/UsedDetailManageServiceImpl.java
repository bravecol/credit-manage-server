package jp.colworks.credit_manage_server.domain.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import jp.colworks.credit_manage_server.application.service.UsedDetailManageService;
import jp.colworks.credit_manage_server.domain.model.UsedDetailManageModel;
import jp.colworks.credit_manage_server.domain.model.UsedDetailManageResponse;

/**
 * 利用明細管理機能サービスクラス
 * 
 * @author col
 */
@Service
public class UsedDetailManageServiceImpl implements UsedDetailManageService {

    @Override
    public UsedDetailManageResponse search(UsedDetailManageModel model) {
        // TODO: 検索処理記載
        UsedDetailManageResponse response = new UsedDetailManageResponse();

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
