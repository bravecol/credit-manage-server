package jp.colworks.credit_manage_server.domain.service;

import org.springframework.stereotype.Service;

import jp.colworks.credit_manage_server.application.service.ManageService;
import jp.colworks.credit_manage_server.domain.model.ManageModel;
import jp.colworks.credit_manage_server.domain.model.ManageResponse;

@Service
public class ManageServiceImpl implements ManageService {

    @Override
    public ManageResponse search(ManageModel model) {
        // TODO: 検索処理記載
        return new ManageResponse();
    }

}
