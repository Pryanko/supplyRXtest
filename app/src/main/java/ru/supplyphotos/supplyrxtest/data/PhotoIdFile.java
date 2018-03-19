package ru.supplyphotos.supplyrxtest.data;

import java.io.File;

/**
 * @author libgo (19.03.2018)
 */

public class PhotoIdFile {

    private File file;
    private Integer photo_id;
    private String uploadUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }



    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }



    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }
}
