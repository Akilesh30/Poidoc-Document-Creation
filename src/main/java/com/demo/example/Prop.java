package com.demo.example;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Prop {
	Prop(){
	}
		@Id		
		
		@GeneratedValue(strategy = GenerationType.AUTO)	
		private int id;		
		
		@ManyToOne
		private Documents documents;
		
	
		 
		 public Documents getDocuments() {
			return documents;
		}
		public void setDocuments(Documents documents) {
			this.documents = documents;
		}

	/*
	 * @OneToMany( targetEntity= Table1.class )
	 * 
	 * @JoinColumn private List<Table1> secIdt;
	 * 
	 * @OneToMany( targetEntity= Images.class )
	 * 
	 * @JoinColumn private List<Images> secIdi;
	 */
		private String font;		
			
		private int fontSize;
		
			
		private String fontStyle;
		private String color;
		private int indent;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			id = id;
		}
		
		public String getFont() {
			return font;
		}
		public void setFont(String font) {
			this.font = font;
		}
		public int getFontSize() {
			return fontSize;
		}
		public void setFontSize(int fontSize) {
			this.fontSize = fontSize;
		}
		public String getFontStyle() {
			return fontStyle;
		}
		public void setFontStyle(String fontStyle) {
			this.fontStyle = fontStyle;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public int getIndent() {
			return indent;
		}
		public void setIndent(int indent) {
			this.indent = indent;
		}
		@Override
		public String toString() {
		return "Prop [Id=" + id + /* ", secIdt=" + secIdt + ", secIdi=" + secIdi + */", font=" + font + ", fontSize="
					+ fontSize + ", fontStyle=" + fontStyle + ", color=" + color + ", indent=" + indent + "]";
		}
	
	
		
		
}



