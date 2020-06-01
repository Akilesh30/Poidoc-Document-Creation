package com.demo.example;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Documents {
	
	
		Documents(){
		}
		
		@Id				
		@GeneratedValue(strategy = GenerationType.AUTO)	
		private int id;	
	/*
	 * @ManyToOne(targetEntity= docType.class )
	 * 
	 * @JoinColumn private List<docType> docTypeCode;
	 */	

	
	/*
	 * @OneToMany( targetEntity= Prop.class )
	 * 
	 * @JoinColumn private List<Prop> docCode;
	 */
		 		
	/*
	 * public List<Prop> getDocCode() { return docCode; } public void
	 * setDocCode(List<Prop> docCode) { this.docCode = docCode; }
	 */

		private String docName;	
			
		private String docDescription;		
			
			
		public int getId() {
			return id;
			}
		public void setId(int id) {
			this.id = id;
			}		

	/*
	 * public List<docType> getdocTypeCode() { return docTypeCode; } public void
	 * setdocTypeCode(List<docType> docTypeCode) { this.docTypeCode = docTypeCode; }
	 */
		public String getdocName() {
			return docName;
			}
		public void setdocName(String docName) {
			this.docName = docName;	
			}
		public String getdocDescription() {
			return docDescription;		
			}		
		public void setdocDescription(String docDescription) {
			this.docDescription = docDescription;	
			}

	/*
	 * public String getdocStructureDescription() { return docStructureDescription;
	 * } public void setdocStructureDescription(String docStructureDescription) {
	 * this.docStructureDescription = docStructureDescription; }
	 */
		@Override
		public String toString() {			
		return "Documents [Id=" + id +", docName=" + docName+ ", docDescription=" + docDescription + "]";		}
}
