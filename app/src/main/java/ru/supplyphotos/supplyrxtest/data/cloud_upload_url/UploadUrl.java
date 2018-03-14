package ru.supplyphotos.supplyrxtest.data.cloud_upload_url;

public class UploadUrl{
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
