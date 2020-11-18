package com.wjs.produce.watch.model;

public class FileModifyDto {
    private String flag;

    private String path;

    private String fileName;

    public FileModifyDto() {
        this("","","");
    }

    public FileModifyDto(String flag, String path, String fileName) {
        this.flag = flag;
        this.path = path;
        this.fileName = fileName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
