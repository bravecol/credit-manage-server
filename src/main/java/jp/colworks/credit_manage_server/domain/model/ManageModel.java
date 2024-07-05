package jp.colworks.credit_manage_server.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class ManageModel {
    /** カードID */
    private int id;
    /** 支払日 */
    private Date dueDate;
}
