package ru.supplyphotos.supplyrxtest.data.photo_id;

public class Data{
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
