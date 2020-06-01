package com.westcatr.rd.base.mysqltomd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.westcatr.rd.base.mysqltomd.dao.mapper.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xieshuang
 * @date 2020-05-31 18:46
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private IMapper iMapper;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${exclude.table:}")
    private String excludeTable;
    @Value("${appoint.table:}")
    private String appointTable;
    @Override
    public List<TableInfo> getTableList() {
        String[] split = url.split("\\?");
        String s = split[0];
        String[] split1 = s.split("/");
        String sql = "SELECT\n" +
               "\tTABLE_NAME AS tableName,\n" +
               "\tTABLE_COMMENT AS tableComment \n" +
               "FROM\n" +
               "\tinformation_schema.TABLES \n" +
               "WHERE\n" +
               "\ttable_schema='" + split1[split1.length-1] + "'";
        List<JSONObject> jsonObjects = iMapper.selectList(sql);
        List<TableInfo> list = new ArrayList<>(jsonObjects.size());
        for (JSONObject jsonObject : jsonObjects) {
            list.add(JSON.toJavaObject(jsonObject, TableInfo.class));
        }
        if (excludeTable != null && !"".equals(excludeTable)){
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(excludeTable.split(",")));
            list.removeIf(temp -> strings.contains(temp.getTableName()));
        }

        if (appointTable != null && !"".equals(appointTable)){
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(appointTable.split(",")));
            list.removeIf(temp -> !strings.contains(temp.getTableName()));
        }
        return list;
    }

    @Override
    public List<FieldInfo> getFieldInfoList(String tableName) {
        String[] split = url.split("\\?");
        String s = split[0];
        String[] split1 = s.split("/");
        String sql = "SELECT\n" +
                "\t\tCOLUMN_NAME as fieldName,\n" +
                "\t\tCOLUMN_DEFAULT as  defaultValue,\n" +
                "\t\t ( CASE IS_NULLABLE WHEN 'YES' THEN '是' ELSE '不为空' END ) AS isEmpty,\n" +
                "\t\tDATA_TYPE as fieldType,\n" +
                "\t\tCOLUMN_COMMENT as fieldExplain,\n" +
                "\t\tCOLUMN_TYPE as fieldDetails\n" +
                "FROM\n" +
                "\tinformation_schema.COLUMNS \n" +
                "WHERE\n" +
                "\ttable_schema = '" + split1[split1.length-1] + "'\n" +
                "\tAND table_name = '" + tableName+ "'";
        List<JSONObject> jsonObjects = iMapper.selectList(sql);
        List<FieldInfo> list = new ArrayList<>(jsonObjects.size());
        for (JSONObject jsonObject : jsonObjects) {
            if ("id".equals(jsonObject.getString("fieldName"))){
                jsonObject.put("fieldExplain", "id");
            }
            if ("create_time".equals(jsonObject.getString("fieldName"))){
                jsonObject.put("fieldExplain", "创建时间");
            }
            if ("update_time".equals(jsonObject.getString("fieldName"))){
                jsonObject.put("fieldExplain", "更新时间");
            }
            list.add(JSON.toJavaObject(jsonObject, FieldInfo.class));
        }
        return list;
    }
}
