package com.wjs.produce.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileController {

    private String PAGE_EXPORT_FILENAME = "text.txt";

    public void exportFile(HttpServletResponse response) throws IOException {

        byte[] bytes = createZipFile();
        if (bytes.length == 0) {
            throw new IOException();
        }

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + PAGE_EXPORT_FILENAME + "\"");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(bytes, response.getOutputStream());
        response.getOutputStream().close();
    }

    private byte[] createZipFile() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        /**
         * 存在的文件名与对应的内容（行）
         */
        Map<String, List<String>> sqlMap = new HashMap<>();

        try {
            Set<String> keys = sqlMap.keySet();
            if (!keys.isEmpty()) {
                ZipOutputStream zip = new ZipOutputStream(outputStream);
                for (String key : keys) {
                    //添加到zip
                    zip.putNextEntry(new ZipEntry(key));
                    List<String> sqls = sqlMap.get(key);
                    StringWriter sw = new StringWriter();
                    sqls.forEach(s -> {
                        sw.write(s + "\r\n");
                    });
                    IOUtils.write(sw.toString(), zip, "UTF-8");
                    sw.close();
                }
                zip.closeEntry();
                zip.close();
            }
        } catch (Exception e) {

        }

        return outputStream.toByteArray();
    }


    private MultipartFile toMultipartFile(String fileName) {
        byte[] zipFile = createZipFile();
        MultipartFile multipartFile = null;
        try {

            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.ALL_VALUE, true, fileName);

            try (InputStream input = new ByteArrayInputStream(zipFile); OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid file: " + e, e);
            }

            multipartFile = new CommonsMultipartFile(fileItem);

        } catch (Exception e) {
        }
        return multipartFile;
    }

}
