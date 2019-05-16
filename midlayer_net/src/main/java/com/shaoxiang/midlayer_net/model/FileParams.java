package com.shaoxiang.midlayer_net.model;

import java.io.File;

/**
 * Created by LK on 2017/3/15.
 */

public class FileParams {
    private String key;
    private String fileName;
    private File file;

    public FileParams() {
    }

    public FileParams(String key, String fileName, File file) {
        this.key = key;
        this.fileName = fileName;
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
