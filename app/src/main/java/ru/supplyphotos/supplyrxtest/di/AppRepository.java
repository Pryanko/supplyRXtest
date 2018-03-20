package ru.supplyphotos.supplyrxtest.di;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supplyphotos.supplyrxtest.data.ImageFile;


/**
 * @author libgo (19.03.2018)
 */

public class AppRepository {


    private Integer orderId;
    private List<File> fileList;
    private File fileImage;
    private String nameFile;
    private Integer orderItemId;
    private List<String> listPathImages;


    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }

    public Observable<File> getFike(){
        return Observable.just(fileImage);
    }


    public AppRepository() {
        this.listPathImages = new ArrayList<>();
        this.fileList = new ArrayList<>();
    }

    public void addFileList(ImageFile imageFile){
        fileList.add(imageFile.getFile());
    }

    public Observable<List<File>> getListFile(){
        return Observable.just(fileList);
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
