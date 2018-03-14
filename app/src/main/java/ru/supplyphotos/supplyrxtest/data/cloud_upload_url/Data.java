package ru.supplyphotos.supplyrxtest.data.cloud_upload_url;

public class Data{
	private String path;
	private String url;

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"path = '" + path + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}
