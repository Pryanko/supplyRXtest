package ru.supplyphotos.supplyrxtest.data;

import java.io.File;

/**
 * @author libgo (19.03.2018)
 */

public class ImageFile {


    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    private File file;

    private String path;

    private String nameImage;

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
