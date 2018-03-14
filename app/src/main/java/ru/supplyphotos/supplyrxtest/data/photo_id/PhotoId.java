package ru.supplyphotos.supplyrxtest.data.photo_id;

public class PhotoId{

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
