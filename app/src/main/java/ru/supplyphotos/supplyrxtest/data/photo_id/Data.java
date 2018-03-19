package ru.supplyphotos.supplyrxtest.data.photo_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("photo_id")
	@Expose
	private int photoId;

	public void setPhotoId(int photoId){
		this.photoId = photoId;
	}

	public int getPhotoId(){
		return photoId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"photo_id = '" + photoId + '\'' + 
			"}";
		}
}
