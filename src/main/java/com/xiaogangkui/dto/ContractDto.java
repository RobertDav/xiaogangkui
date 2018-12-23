package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Integer id;
    private Integer customerId;
    private String name;
    private Integer projectId;
    private String projectName;
    private String projectManager;
    private String clientName;
    private Date contractDate;
    private String contractor;
    private Date startTime;
    private Date endTime;
    private Date borrowDate;
    private Double totalAmount;
    private Integer type;
    private Integer financeType;
    private String purchaseContent;
    private int contractCategory;
    private int contractSubCategory;
    private Integer status;
    private String memo;
    private String contractDoc;
    private String secrecyUrl;
    private int collectStatus;
    private String collectStatusDesc;
    private double collectAmount;
    private double payAmount;
    private List<Integer> applierList;
    private String applyers;
    private String typeDesc;
    private String statusDesc;

    private int forceCollectStatus;
    private String forceCollectStatusDesc;

    public enum ContractTypeEnums {
        UNCHECKED(0, "不需要审核"),
        CHECKED(2, "需要审核"),
        PUCHASE_CONTRACT(3, "采购合同");

        int code;
        String text;

        ContractTypeEnums(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static String getTextByCode(int code) {
            String result = null;
            for (ContractTypeEnums eums : ContractTypeEnums.values()) {
                if (eums.getCode() == code) {
                    result = eums.getText();
                }
            }
            return result;
        }
    }


    public enum FinanceTypeEnums {
        RECEIVE_MONEY(1, "收款"),
        PAY_MONEY(2, "付款");

        int code;
        String text;

        FinanceTypeEnums(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static String getTextByCode(int code) {
            String result = null;
            for (FinanceTypeEnums enums : FinanceTypeEnums.values()) {
                if (enums.getCode() == code) {
                    result = enums.getText();
                }
            }
            return result;
        }
    }


    public enum ContractStatusEnums {
        PENDING(1, "审核中"),
        APPROVED(2, "审核通过"),
        REJECTED(3, "驳回"),
        CLOSED(4, "关闭");

        int code;
        String text;

        ContractStatusEnums(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static String getTextByCode(int code) {
            String result = null;
            for (ContractStatusEnums enums : ContractStatusEnums.values()) {
                if (enums.getCode() == code) {
                    result = enums.getText();
                }
            }
            return result;
        }
    }

    public enum CollectStatusEnum{
        NO(0,"未结清"),
        YES(1,"已结清"),
        APPLY_FORCE(2,"申请强制结清"),
        ON_FORCE(3,"强制结清中"),
        FORCE_END(4,"强制结清完成");
        private  int code;
        private String desc;

        CollectStatusEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }
        public int getCode(){
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public static String getByCode(int code){
            String desc = Arrays.stream(CollectStatusEnum.values()).filter(collectStatusEnum -> collectStatusEnum.code == code).findFirst().get().getDesc();
            return desc;
        }
    }


    public enum StatusEnum{
        YES(1,"正常"),
        NO(2,"禁用");
        private int code;
        private String desc;
        StatusEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }
    }

    public enum FirstCategroyEnum{
        ALL(1,"总承包合同"),
        SPECIALITY(2,"专业承包合同");
        private int code;
        private String desc;
        FirstCategroyEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
    public enum SecondCategoryEnum{
        SUB(1,"附属合同"),
        SPECIALITY_SPLITE(2,"专业分包合同"),
        STUFF(3,"材料采购合同"),
        LABOUR(4,"劳务分包合同"),
        MANAGER(5,"管理协议书");
        private int code;
        private String desc;
        SecondCategoryEnum(int code,String desc){
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
