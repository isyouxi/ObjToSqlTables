# ObjToSqlTables
这是个 Java程序 , 根据 class 类自动生成 .sql 的文件 用于生成数据库表

### 使用步骤:

 - 1  找到 DBCreateSqlUtils 文件
    ```
    public static void main(String args[]) {
       
               try {
                   String s = toCreateTable("new_table_name", new Trade1());
                   writeToFile(s);
               } catch (Exception e) {
                   e.printStackTrace();
               }
       
           }
               
   ``` 
   把自己的类对象 与 Trade1 替换,比如你的类是 Person 代码变成:
   ```
       public static void main(String args[]) {
          
                  try {
                      String s = toCreateTable("new_table_name", new Person());
                      writeToFile(s);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
          
              }
                  
      ``` 
    
 - 2 更改 文件的存储位置 在 DBCreateSqlUtils 文件下的 writeToFile 方法内
 有个 fileName 变量,修改这个就可以了
 
 - 3 运行 DBCreateSqlUtils 的 main ,生成后缀为sql的文件.
    (此 sql 文件可以通过 Navicat Premium 导入sql)
 
### 说明
- 1 当前支持的类型 目前支持的类型是
    String int Integer float (如果不是这几个类型的字段,是不添加进表里的)
    
    如果自己还有其他复杂的类型需求的话,可以在 DBCreateSqlUtils 类里的 getTypeStr() 方法里 添加自己的判断方式
    
    
- 2 如何配置主键,备注,not null,默认值
    采用了注解的方式:可以查看 Trade1 类 里 有使用的方式 如:
    ```
           //表明的是  为主键 说明为这个是 num 可以为空[如果是主键的话 此处设置无效,后续会处理 成 not null]
           @MyFieldAnnotation(primary_key = true, desc = "这个是num", default_value = "")
           int num;
           
           //表明的是  不为主键 说明这个是 goods_kind 默认值 为 0
           @MyFieldAnnotation(primary_key = false, desc = "这个是goods_kind", default_value = "0")
           int goods_kind;
           
           //表明的是  不为主键 说明这个是 num_iid 不能为 null
           @MyFieldAnnotation(primary_key = false, desc = "这个是 num_iid", default_value = "notnull")
           String num_iid;
           
     ```
     说明: 如果定义了两个主键 会抛异常,报错
     
- 3 如何生成数据库表
    我使用的是 Navicat Premium 的数据库管理工具,以此做说明:
        在左边的列表栏
            打开数据库
            选中数据库,右键 选择 '运行SQL 文件'
        在弹出的对话框 选择 生成出来的sql文件,点击开始
        成功后,刷新数据库即可
        

        
- 4 有些数据库的具体的配置如果有不一样的地方 可以查看下代码里的 DBCreateSqlUtils 里的 toCreateTable 方法里的一些参数
     
     
### 效果

![Navicat Premium 效果](http://img1.ph.126.net/9hJAT6IuhHY3MyVrcbZmVA==/6631875906746313328.png)

![类](http://img1.ph.126.net/dUWWKRmsnP3hWmZI9RcSQg==/6631984758397463254.png)

