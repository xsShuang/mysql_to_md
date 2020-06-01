### mysql生成MD格式的数据库文档

用法：

1.配置数据库连接（目前只支持mysql）
2.直接启动即可


配置项：

exclude.table：排除的表名，多个以","（英文逗号）隔开

appoint.table：指定要生产的表名，多个以","（英文逗号）隔开

exclude.field：排除要生成的字段，多个以","（英文逗号）隔开
> 目前支持字段：fieldName（字段名），fieldExplain（字段说明），fieldType（字段类型），defaultValue（默认值），isEmpty（是否为空）

file.path：生成的文件名称及地址，默认"d:\\数据库文档(xx年xx月xx日xx时xx).md"

### 生成示例

![mark](http://qiniuyun.iotcode.xyz/pic/20200601/cclRMarmYiYI.png?imageslim)