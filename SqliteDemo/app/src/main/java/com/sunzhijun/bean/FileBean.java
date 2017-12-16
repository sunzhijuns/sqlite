package com.sunzhijun.bean;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.Serializable;

/**
 * Created by sunzhijun on 2017/12/13.
 */

public class FileBean implements Serializable, Comparable<FileBean> {
    private File path;
    private String name;
    private int numOfSonDirs;
    private int numOfSonFiles;
    private String fileType;
    private boolean isDir;

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSonDirs() {
        return numOfSonDirs;
    }

    public void setNumOfSonDirs(int numOfSonDirs) {
        this.numOfSonDirs = numOfSonDirs;
    }

    public int getNumOfSonFiles() {
        return numOfSonFiles;
    }

    public void setNumOfSonFiles(int numOfSonFiles) {
        this.numOfSonFiles = numOfSonFiles;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    @Override
    public String toString() {
        return name + ":\n" + "\tnumOfSonDirs = "+numOfSonDirs
                + "\n\tnumOfSonFiles = "+numOfSonFiles
                + "\n\tfileType = "+fileType;

    }

    @Override
    public int compareTo(@NonNull FileBean o) {
        if (this.isDir && o.isDir){
            return this.name.compareTo(o.name);
        }
        if (!this.isDir && !o.isDir){
            return this.name.compareTo(o.name);
        }
        if (this.isDir){
            return -1;
        }else{
            return 1;
        }

    }
}
