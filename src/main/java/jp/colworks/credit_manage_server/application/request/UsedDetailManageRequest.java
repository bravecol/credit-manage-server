package jp.colworks.credit_manage_server.application.request;

import java.util.Date;

import lombok.Data;

@Data
public class UsedDetailManageRequest {

    /** カードID */
    private int id;
    /** 支払日 */
    private Date dueDate;
}
