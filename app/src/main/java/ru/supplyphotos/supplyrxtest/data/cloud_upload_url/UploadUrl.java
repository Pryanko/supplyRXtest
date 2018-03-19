package ru.supplyphotos.supplyrxtest.data.cloud_upload_url;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadUrl{

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
			"UploadUrl{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}
