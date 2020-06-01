package com.westcatr.rd.base.mysqltomd;

/**
 * 数据库表信息
 * @author xieshuang
 * @date 2020-05-31 18:32
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 描述
     */
    private String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                '}';
    }
}
