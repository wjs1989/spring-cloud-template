package com.wjs.seata4.auto.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SqlExecuteMapper {
/*	<!--
	1. 增删改表的结构DDL
		1. 创建表格
		2. 复制表格
		3. 删除表格
		4. 增删改表的结构
	2. 增删改表中的数据DML
		1. 向表中添加数据
		2. 修改表中的数据
		3. 删除表中的数据
	-->*/

    /**
     * @param ddlSql 操作表结构的sql语句
     * @return Integer
     * @Title: 执行操作表结构的sql语句
     */
    Integer exeDDLSql(@Param(value = "ddlSql") String ddlSql);

    /**
     * @param insertSql
     * @return
     * @Title: exeDMLInsertSql
     * @Description: TODO
     * @return: Integer
     */
    Integer exeDMLInsertSql(@Param(value = "insertSql") String insertSql);

    /**
     * @param deleteSql
     * @return
     * @Title: exeDMLDeleteSql
     * @Description: TODO
     * @return: Integer
     */
    Integer exeDMLDeleteSql(@Param(value = "deleteSql") String deleteSql);

    /**
     * @param ddlSql 表查询的sql语句
     * @return Integer
     * @Title: 执行操作表结构的sql语句
     */
    List<Map<String, Object>> exeSelectSql(@Param(value = "selectSql") String selectSql);

    /**
     * @param dmlSql 操作表数据的sql语句
     * @return Integer 操作的条数
     * @Title: 执行操作数据的sql语句
     */
    Integer exeDMLUpdateSql(@Param(value = "updateSql") String updateSql);
}
