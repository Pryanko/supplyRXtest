package ru.supplyphotos.supplyrxtest.data.photo_id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoId{

	@SerializedName("data")
	@Expose
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"PhotoId{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}
