package jp.colworks.credit_manage_server.application.form;

import java.util.Date;

import lombok.Data;

@Data
public class ManageRequest {

    /** カードID */
    private int id;
    /** 支払日 */
    private Date dueDate;
}
