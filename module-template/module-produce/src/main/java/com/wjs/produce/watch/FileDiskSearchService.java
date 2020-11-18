package com.wjs.produce.watch;//package com.wjs.produce.watch;
//
//import com.isky.common.utils.StringUtil;
//import com.isky.front.base.dao.IMachineFileLogDao;
//import com.isky.front.base.dao.IMachineTableInfoDao;
//import com.isky.front.base.dao.ISqlExecuteDao;
//import com.isky.front.utils.FileUtil;
//import com.isky.front.utils.PropUtil;
//import com.isky.front.utils.constant.Constant;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @ClassName: FileDiskSearchService
// * @Description: TODO 文件磁盘搜索服务
// * 项目启动完成后运行
// * @author: jswen
// * @date: 2020年1月17日10:53:29
// */
//@Component
// public class FileDiskSearchService implements ApplicationRunner {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FileWacthService.class);
//    private final String rootPath = "D:/test";
//
//    /**
//     * 初始化
//     */
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        fileSearch(rootPath);
//    }
//
//    /**
//     * 递归检索文件夹
//     *
//     * @param path
//     */
//    private void fileSearch(String path) {
//        if(LOGGER.isDebugEnabled()) {
//            LOGGER.debug(String.format("当前文件夹路径：%s", path));
//        }
//
//        File file = new File(path);
//        File[] tempList = file.listFiles();
//
//        List<String> fileNameList = new ArrayList<String>();
//
//        for (int i = 0; i < tempList.length; i++) {
//            if (tempList[i].isFile()) {
//
//                if(LOGGER.isDebugEnabled()) {
//                    LOGGER.debug(String.format("文件名称：%s", tempList[i].getName()));
//                }
//
//                fileNameList.add(tempList[i].getName());
//            } else if (tempList[i].isDirectory()) {
//                fileSearch(tempList[i].getAbsolutePath());
//            }
//        }
//
//        //做匹配，不管文件夹里有没有文件都需要匹配
//        doMatch(path, fileNameList);
//    }
//
//    private void doMatch(String path, List<String> fileNameList) {
//
//        //根据文件路径查询对应的表名称
//        String sourcePath = path.replace("\\", "/")
//                .replace(rootPath, "");
//
//        //获取物理表表名称
//        String physicalTableName = machineTableInfoDao.queryPhysicalTableName(sourcePath, 2);
//        if (StringUtils.isBlank(physicalTableName)) {
//            if(LOGGER.isDebugEnabled()) {
//                LOGGER.debug(String.format("文件夹路径匹配当前物理表失败：%s", path));
//            }
//            return;
//        }
//
//        try {
//            //匹配删除
//            doDeleteMatch(physicalTableName, path, fileNameList);
//
//            //匹配更新
//            doModifyMatch(physicalTableName, path, fileNameList);
//
//            //匹配新增
//            doAddMatch(physicalTableName, path, fileNameList);
//        } catch (Exception e) {
//            LOGGER.error("文件检索", e);
//        }
//    }
//
//    private void doDeleteMatch(String physicalTableName,String path,  List<String> fileNameList) {
//        try{
//            String sql = "";
//            //查找未在当前文件夹的文件，之后删除
//            String fileNameIn = sqlAssemble(fileNameList);
//            if (StringUtil.isBlank(fileNameIn)) {
//                sql = String.format(" select file_name from %s ", physicalTableName);
//            } else {
//                sql = String.format(" select file_name from %s where file_name not in (%s)"
//                        , physicalTableName, fileNameIn);
//            }
//
//            List<Map<String, Object>> selectList = exeSelectSql(sql);
//
//            //组装需要删除的文件信息
//            if (selectList == null || selectList.size() == 0) {
//                return;
//            }
//
//            fileNameIn = sqlAssemble(selectList);
//            if (StringUtil.isBlank(fileNameIn)) {
//                return;
//            }
//
//            /**
//             * 这里是资源文件目录与对应的资源文件表的数据处理
//             */
//            sql = String.format(" delete from %s where file_name in (%s) "
//                    , physicalTableName, fileNameIn);
//            exeDeleteSql(sql);
//
//            /**
//             * 这里是处理资源文件目录与总表 MACHINE_FILE_LOG_T 的数据处理
//             */
//            sql = String.format(" update MACHINE_FILE_LOG_T set FILE_OPER_TYPE = %s where FILE_NAME in (%s) and FILE_PATH = '%s' "
//                    ,Constant.FILE_MONITOR_OPER_TYPE_DELETE, fileNameIn, path);
//            exeUpdateSql(sql);
//
//        }catch(Exception e) {
//        }
//    }
//
//    private void doAddMatch(String physicalTableName, String path, List<String> fileNameList) {
//        String sql = String.format(" select file_name from %s ", physicalTableName);
//        List<Map<String, Object>> selectList = exeSelectSql(sql);
//        if(selectList == null || selectList.size() == 0) {
//            return ;
//        }
//
//        List<String> tableFileName = selectList.stream().map(m -> String.format("%s", m.get("file_name"))).collect(Collectors.toList());
//
//        //找出需要新增的文件名称
//        List<String> newFile = fileNameList.stream().filter(f -> !tableFileName.contains(f)).collect(Collectors.toList());
//        if (newFile != null && newFile.size() > 0) {
//            for (String fileName : newFile) {
//                Map<String, Object> fileInfo = createFileData(path, fileName);
//                if (fileInfo == null) {
//                    return;
//                }
//
//                fileInfo.put("physical_table_name", physicalTableName);
//                //执行子表
//                insert(fileInfo);
//                //执行总表
//                insertGeneralTable(fileInfo);
//            }
//        }
//    }
//
//    private void doModifyMatch(String physicalTableName, String path, List<String> fileNameList) {
//        String sql = String.format(" select file_name from %s ", physicalTableName);
//        List<Map<String, Object>> selectList = exeSelectSql(sql);
//        if(selectList == null || selectList.size() == 0) {
//            return ;
//        }
//
//        List<String> tableFileName = selectList.stream().map(m -> String.format("%s", m.get("file_name"))).collect(Collectors.toList());
//
//        //找出需要新增的文件名称
//        List<String> newFile = fileNameList.stream().filter(f -> tableFileName.contains(f)).collect(Collectors.toList());
//        if (newFile != null && newFile.size() > 0) {
//            for (String fileName : newFile) {
//                Map<String, Object> fileInfo = createFileData(path, fileName);
//                if (fileInfo == null) {
//                    return;
//                }
//
//                fileInfo.put("physical_table_name", physicalTableName);
//                //执行子表
//                update(fileInfo);
//                //执行总表
//                updateGeneralTable(fileInfo);
//
//            }
//        }
//    }
//
//    /**
//     * sql拼接
//     *
//     * @param fileNameList
//     * @param <T>
//     * @return
//     */
//    private <T> String sqlAssemble(List<T> fileNameList) {
//        if (fileNameList == null || fileNameList.size() == 0)
//            return null;
//
//        String fileNameIn = "";
//        for (T fileName : fileNameList) {
//            if (fileName instanceof String) {
//                fileNameIn += String.format("'%s',", fileName);
//            } else if (fileName instanceof Map) {
//                fileNameIn += String.format("'%s',", ((Map) fileName).get("file_name"));
//            }
//        }
//        fileNameIn = fileNameIn.substring(0, fileNameIn.length() - 1);
//        return fileNameIn;
//    }
//
//    /**
//     * 创建文件信息
//     *
//     * @param path
//     * @param fileName
//     * @return
//     */
//    private Map<String, Object> createFileData(String path, String fileName) {
//        File file = new File(String.format("%s/%s", path, fileName));
//        if (!file.isFile()) {
//            return null;
//        }
//        //根据文件路径查询对应的表名称
//        String sourcePath = path.replace("\\", "/")
//                .replace(rootPath, "");
//
//        Map<String, Object> fileInfo = new HashMap<String, Object>();
//        long fileSize = file.length();
//        fileSize = FileUtil.getKbLength(fileSize);
//
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//        fileInfo.put("file_name", fileName);
//        fileInfo.put("file_ddr", sourcePath);
//        fileInfo.put("file_size", fileSize);
//        fileInfo.put("file_format", fileType);
//        fileInfo.put("creation_time", fileType);
//        fileInfo.put("file_id", fileType);
//
//        return fileInfo;
//    }
//
//    /**
//     * 新增
//     *
//     * @param fileInfo
//     * @return
//     */
//    private int insert(Map<String, Object> fileInfo) {
//        if (fileInfo == null) {
//            return 0;
//        }
//
//        try{
//            //新增
//            String uuid = UUID.randomUUID().toString();
//            String sql = String.format("INSERT INTO `%s`(`file_id`, `file_name`, `file_format`, `file_ddr`, `creation_time`, `file_size`) VALUES ('%s', '%s', '%s', '%s', now(), '%s')",
//                    fileInfo.get("physical_table_name"),
//                    uuid,
//                    fileInfo.get("file_name"),
//                    fileInfo.get("file_format"),
//                    fileInfo.get("file_ddr"),
//                    fileInfo.get("file_size")
//            );
//            return sqlExecuteDao.exeInsertSql(sql);
//        }catch(Exception e) {
//        }
//        return 0;
//    }
//
//    /**
//     * 插入总表 MACHINE_FILE_LOG_T
//     * @return
//     */
//    private int insertGeneralTable(Map<String, Object> fileInfo){
//        if (fileInfo == null) {
//            return 0;
//        }
//
//        try{
//            Map<String, Object> fileLog = new HashMap<String, Object>();
//            fileLog.put("fileName", fileInfo.get("file_name") );
//            fileLog.put("filePath", fileInfo.get("file_ddr"));
//            fileLog.put("fileSize", fileInfo.get("file_size"));
//            fileLog.put("fileType", fileInfo.get("file_format"));
//            fileLog.put("fileOperType", Constant.FILE_MONITOR_OPER_TYPE_INSERT);
//
//            //先查看下是否存在，存在则更新
//            List<Map<String, Object>> ids = fileMonitorLog.queryFileLog(fileLog);
//            if(ids != null && ids.size()>0){
//                return  updateGeneralTable(fileInfo);
//            }
//
//            return fileMonitorLog.insertFileLogOne(fileLog);
//        }catch(Exception e) {
//        }
//
//        return 0;
//    }
//
//    /**
//     * 更新
//     *
//     * @param fileInfo
//     * @return
//     */
//    private int update(Map<String, Object> fileInfo) {
//        if (fileInfo == null) {
//            return 0;
//        }
//        try{
//            String sql = String.format("UPDATE `%s` SET `file_name` = '%s', `file_format` = '%s', `file_ddr` = '%s', `creation_time` = now(), `file_size` = '%s' where file_name  = '%s' ",
//                fileInfo.get("physical_table_name"),
//                fileInfo.get("file_name"),
//                fileInfo.get("file_format"),
//                fileInfo.get("file_ddr"),
//                fileInfo.get("file_size"),
//                fileInfo.get("file_name"));
//
//            return exeUpdateSql(sql);
//        }catch(Exception e) {
//        }
//        return 0;
//    }
//    /**
//     * 修改总表 MACHINE_FILE_LOG_T
//     * @return
//     */
//    private int updateGeneralTable(Map<String, Object> fileInfo){
//        if (fileInfo == null) {
//            return 0;
//        }
//
//        try{
//            Map<String, Object> fileLog = new HashMap<String, Object>();
//            fileLog.put("fileName", fileInfo.get("file_name") );
//            fileLog.put("filePath", fileInfo.get("file_ddr"));
//            fileLog.put("fileSize", fileInfo.get("file_size"));
//            fileLog.put("fileType", fileInfo.get("file_format"));
//            fileLog.put("fileOperType", Constant.FILE_MONITOR_OPER_TYPE_UPDATE);
//
//            return fileMonitorLog.updateFileLog(fileLog);
//        }catch(Exception e) {
//        }
//        return 0;
//    }
//
//    private List<Map<String, Object>>  exeSelectSql(String sql){
//        try{
//            return sqlExecuteDao.exeSelectSql(sql);
//        }catch(Exception e) {
//        }
//        return null;
//    }
//
//    private int exeUpdateSql(String sql){
//        int count = 0;
//        try{
//            count = sqlExecuteDao.exeDDLSql(sql);
//        }catch(Exception e) {
//        }
//        return count;
//    }
//
//    private int exeDeleteSql(String sql){
//        int count = 0;
//        try{
//            count = sqlExecuteDao.exeDeleteSql(sql);
//        }catch(Exception e) {
//        }
//        return count;
//    }
//
//}