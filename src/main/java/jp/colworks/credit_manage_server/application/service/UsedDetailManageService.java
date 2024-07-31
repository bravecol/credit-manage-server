package jp.colworks.credit_manage_server.application.service;

import jp.colworks.credit_manage_server.domain.model.UsedDetailManageModel;
import jp.colworks.credit_manage_server.domain.model.UsedDetailManageResponse;

/**
 * 利用明細管理機能サービスインターフェース
 * 
 * @author col
 */
public interface UsedDetailManageService {

    public UsedDetailManageResponse search(UsedDetailManageModel model);
}
