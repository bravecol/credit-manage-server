package jp.colworks.credit_manage_server.application.service;

import jp.colworks.credit_manage_server.domain.model.ManageModel;
import jp.colworks.credit_manage_server.domain.model.ManageResponse;

public interface ManageService {
    
    public ManageResponse search(ManageModel model);
}
