package com.demo.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity

public class Images {
	Images()
	{
	}
	@Id
	
	private int Id;
	
		private String imageName;
	
	private String imageUrl;
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
	}
	public String getimageName() {
		return imageName;
	}
	public void setimageName(String imageName) {
		this.imageName = imageName;
	}
	public String getimageUrl() {
		return imageUrl;
	}
	public void setimageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Images [Id=" + Id + ", imageName=" + imageName + ", imageUrl=" + imageUrl
				+ "]";
	}
	
}
