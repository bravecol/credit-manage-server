package jp.colworks.credit_manage_server.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class ManageResponse {
    /** カード名称 */
    private String name;
    /** 利用日 */
    private Date useDate;
    /** 利用先 */
    private String useTarget;
    /** 金額 */
    private int price;
    /** 備考 */
    private String remarks;
    /** カテゴリ */
    private int category;
    /** 支払日 */
    private Date dueDate;
}
