package com.westcatr.rd.base.mysqltomd;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Neo.Zzj
 */
@Configuration
public class InitRunner {

    @Autowired
    private TableService tableService;
    @Value("${file.path:本地}")
    private String filePath;
    @Value("${exclude.field:}")
    private String excludeField;

    @PostConstruct
    public void postConstruct() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("fieldName");
        list.add("fieldExplain");
        list.add("fieldType");
        list.add("defaultValue");
        list.add("isEmpty");
        if (excludeField != null && !"".equals(excludeField)){
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(excludeField.split(",")));
            list.removeIf(strings::contains);
        }

        List<TableInfo> tableList = tableService.getTableList();
        StringBuilder stringBuilder = new StringBuilder();
        for (TableInfo tableInfo : tableList) {
            stringBuilder.append("### ");
            stringBuilder.append(tableInfo.getTableComment());
            stringBuilder.append('(');
            stringBuilder.append(tableInfo.getTableName());
            stringBuilder.append(')');
            stringBuilder.append('\n');
            stringBuilder.append('|');
            if (list.contains("fieldName")){
                stringBuilder.append(" 字段名 |");
            }
            if (list.contains("fieldExplain")){
                stringBuilder.append(" 字段说明 |");
            }
            if (list.contains("fieldType")){
                stringBuilder.append(" 字段类型 |");
            }
            if (list.contains("defaultValue")){
                stringBuilder.append(" 默认值 |");
            }
            if (list.contains("isEmpty")){
                stringBuilder.append(" 是否为空 |");
            }
            stringBuilder.append('\n');
            stringBuilder.append('|');
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append("------ |");
            }
            stringBuilder.append('\n');
            List<FieldInfo> fieldInfoList = tableService.getFieldInfoList(tableInfo.getTableName());
            for (FieldInfo fieldInfo : fieldInfoList) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(fieldInfo);
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0){
                        stringBuilder.append('|');
                    }
                    stringBuilder.append(jsonObject.get(list.get(i)));
                    stringBuilder.append(" |");
                }
                stringBuilder.append('\n');
            }
            stringBuilder.append('\n');
        }

        if ("本地".equals(filePath)){
            filePath = "D:\\数据库文档(" + DateTime.now().toString("yyyy年MM月dd日HH时mm") + ").md";
        }
        System.out.println(filePath);
        File writeName = new File(filePath);
        writeName.createNewFile();
        FileWriter writer = new FileWriter(writeName);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(stringBuilder.toString());
        out.flush();
        out.close();
    }
}