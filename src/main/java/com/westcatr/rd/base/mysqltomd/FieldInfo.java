package com.westcatr.rd.base.mysqltomd;

/**
 * @author xieshuang
 * @date 2020-05-31 18:39
 */
public class FieldInfo {

    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否为空
     */
    private String isEmpty;
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 字段说明
     */
    private String fieldExplain;
    /**
     * 字段详情
     */
    private String fieldDetails;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(String isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldExplain() {
        return fieldExplain;
    }

    public void setFieldExplain(String fieldExplain) {
        this.fieldExplain = fieldExplain;
    }

    public String getFieldDetails() {
        return fieldDetails;
    }

    public void setFieldDetails(String fieldDetails) {
        this.fieldDetails = fieldDetails;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", isEmpty='" + isEmpty + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldExplain='" + fieldExplain + '\'' +
                ", fieldDetails='" + fieldDetails + '\'' +
                '}';
    }
}
