package ru.supplyphotos.supplyrxtest.di;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


/**
 * @author libgo (19.03.2018)
 */

public class AppRepository {


    private Integer orderId;


    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }

    private File fileImage;
    private String nameFile;
    private Integer orderItemId;
    private List<String> listPathImages;

    public AppRepository() {
        this.listPathImages = new ArrayList<>();
    }


    public Observable<List<String>> getObservableListPathImage(){
        return Observable.just(listPathImages);
    }


    public List<String> getListPathImages() {
        return listPathImages;
    }

    public Integer getListImageSize(){
        return listPathImages.size();
    }

    public void addItemListImage(String path){
        listPathImages.add(path);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }


}
