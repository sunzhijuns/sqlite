package com.sunzhijun.bean;

/**
 * Created by sunzhijun on 2017/12/14.
 */


//            String sql = "create table "+Constant.TABLE_NAME+"("
//                    +Constant._ID+" INTEGER NOT NULL,"
//                    +Constant.GLOBAL_PATH+" TEXT NOT NULL,"
//                    +Constant.PARENT_PATH+" TEXT,"
//                    +Constant.LAST_MODE_TIME+" INTEGER,"
//                    +Constant.FILE_NAME+" TEXT,"
//                    +Constant.FILE_TYPE+" TEXT,"
//                    +"PRIMARY KEY ("+Constant.GLOBAL_PATH+","+Constant._ID+"))";
public class CursorBean {
    private int id;
    private String globalPath;
    private String parentPath;
    private long lastModTime;
    private String fileName;
    private String fileType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlobalPath() {
        return globalPath;
    }

    public void setGlobalPath(String globalPath) {
        this.globalPath = globalPath;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public long getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(long lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public CursorBean(int id, String globalPath, String parentPath, long lastModTime, String fileName, String fileType) {
        this.id = id;
        this.globalPath = globalPath;
        this.parentPath = parentPath;
        this.lastModTime = lastModTime;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
