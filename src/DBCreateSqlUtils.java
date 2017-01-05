import model.DbRow;
import model.MyFieldAnnotation;
import test.Trade1;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by is_yo on 2017/1/5.
 */
public class DBCreateSqlUtils {

    public static void main(String args[]) {

        try {
            String s = toCreateTable("new_table_name", new Trade1());
            writeToFile(s);
            LogUtils.i("创建完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void writeToFile(String s) {
        String fileName = "C:\\Users\\is_yo\\Desktop\\new_creat_" + new Date().getTime() + ".sql";
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            writer.write(s);
            writer.close();

            LogUtils.i("文件位置 :" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toCreateTable(String table_name, Object obj) throws Exception {
        LogUtils.i("开始...");
        String Source_Server = "mysql";
        String Source_Server_Version = "50716";
        String Source_Host = "localhost:3306";

        String Target_Server_Type = "MYSQL";
        String Target_Server_Version = "50716";
        String File_Encoding = "65001";

        String Date = new Date().toGMTString();


        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("/*\n");
        stringBuffer.append("Source Server:");
        stringBuffer.append(Source_Server);
        stringBuffer.append("\n");
        stringBuffer.append("Source Server Version:");
        stringBuffer.append(Source_Server_Version);
        stringBuffer.append("\n");
        stringBuffer.append("Source Host:");
        stringBuffer.append(Source_Host);
        stringBuffer.append("\n");
        stringBuffer.append("Target Server Type:");
        stringBuffer.append(Target_Server_Type);
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("Target Server Version:");
        stringBuffer.append(Target_Server_Version);
        stringBuffer.append("\n");
        stringBuffer.append("File Encoding:");
        stringBuffer.append(File_Encoding);
        stringBuffer.append("\n");
        stringBuffer.append("\n");
        stringBuffer.append("Date");
        stringBuffer.append(Date);
        stringBuffer.append("\n");
        stringBuffer.append("*/\n");

        stringBuffer.append("SET FOREIGN_KEY_CHECKS=0;");
        stringBuffer.append("\n");
        stringBuffer.append("-- ----------------------------");
        stringBuffer.append("\n");
        stringBuffer.append("-- Table structure for " + table_name + "");
        stringBuffer.append("\n");
        stringBuffer.append("-- ----------------------------");
        stringBuffer.append("\n");
        stringBuffer.append("DROP TABLE IF EXISTS `" + table_name + "`;");
        stringBuffer.append("\n");
        stringBuffer.append("CREATE TABLE `" + table_name + "` (");
        stringBuffer.append("\n");


        stringBuffer.append(toCreateSqlByObj(obj));

        stringBuffer.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");


        return stringBuffer.toString();
    }

    private static String toCreateSqlByObj(Object object) throws Exception {
        Class clazz = object.getClass();
        Field field[] = clazz.getDeclaredFields();

        List<DbRow> myList = new ArrayList<>();

        for (Field field1 : field) {
            String field_name = field1.getName();
            Type genericType = field1.getGenericType();

            MyFieldAnnotation myFieldAnnotation = field1.getAnnotation(MyFieldAnnotation.class);

            DbRow dbRow = new DbRow(field_name, genericType);
            if (myFieldAnnotation != null) {
                dbRow.isPrimary = myFieldAnnotation.primary_key();
                dbRow.o_default = myFieldAnnotation.default_value();
                dbRow.decs = myFieldAnnotation.desc();
            }
            myList.add(dbRow);
        }

        DbRow row_primary = null;
        StringBuffer sqlStr = new StringBuffer();
        for (DbRow mRow : myList) {
            String typeo = getTypeStr(mRow.name, mRow.type);
            if (typeo == null)
                continue;
            sqlStr.append("`");
            sqlStr.append(mRow.name);
            sqlStr.append("`");
            sqlStr.append(" ");
            sqlStr.append(typeo);
            sqlStr.append(" ");
            sqlStr.append(getDefultStr(mRow));
            sqlStr.append(getCommentStr(mRow));
            sqlStr.append(',');
            sqlStr.append("\n");
            if (mRow.isPrimary) {
                if (row_primary != null)
                    throw new Exception("不支持多个主键");

                row_primary = mRow;
            }
        }

        if (row_primary != null) {
            sqlStr.append("PRIMARY KEY (`" + row_primary.name + "`)");
        } else {
            sqlStr.delete(sqlStr.length(), sqlStr.length());
        }
        sqlStr.append("\n");
        return sqlStr.toString();

    }

    private static String getCommentStr(DbRow mRow) {
        if (mRow.decs == null)
            return "";

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("COMMENT '");
        stringBuffer.append(mRow.decs);
        stringBuffer.append("'");
        return stringBuffer.toString();
    }

    private static String getDefultStr(DbRow mRow) {

        if (mRow.o_default == null || mRow.o_default.equalsIgnoreCase("null") || mRow.o_default.trim().isEmpty()) {

            if (mRow.isPrimary)
                return "NOT NULL ";

            return "DEFAULT NULL ";
        } else if (mRow.o_default.equalsIgnoreCase("notnull") || mRow.o_default.equalsIgnoreCase("not null")) {
            return "NOT NULL ";
        } else {
            return "DEFAULT " + mRow.o_default.trim() + " ";
        }
    }

    private static String getTypeStr(String name, Type type) {

        if (type.getTypeName().equals("java.lang.String")) {
            return "varchar(255)";
        } else if (type.getTypeName().equals("int") || type.getTypeName().equals("java.lang.Integer")) {
            return "int(11)";
        } else if (type.getTypeName().equals("double")) {
            return "double";
        } else if (type.getTypeName().equals("float")) {
            return "float";
        } else {
            LogUtils.i("[" + name + "] 字段不插入到表中,因为当前类型不支持:[类型为]" + type.getTypeName());
            return null;
        }
    }
}
