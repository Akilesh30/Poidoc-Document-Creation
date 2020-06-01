package com.demo.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocSectionSL {

	@Autowired
	PropInt propInt;

	@Autowired
	DocumentsInt document;
	static List<Prop> listOfProperties= new ArrayList<Prop>();

	/*
	 * public Documents getSection(Integer secId,String name) {
	 * 
	 * Prop prop = new Prop(); Documents doc=new Documents();
	 * 
	 * doc= document.findBydocName(name); int i=doc.getId();
	 * li=prop.findAllByDocCode_Id(i); System.out.println(li); return doc; }
	 */

	public String getprop(DocSectionDTO dto) throws Exception {
		String docType = dto.getDocname();
		Documents doc = null;
		doc = document.findBydocName(docType);
		int i = doc.getId();
		List<Prop> listOfProperties = propInt.findAllByDocuments_Id(i);

		if (i == 2) {
			//System.out.println(listOfProperties);
			createWordoffeletter("C:/Users/Akilesh Abilesh/Desktop/job.docx", listOfProperties, dto);
		} else if(i==1) {

			createWordleaveletter("C:/Users/Akilesh Abilesh/Desktop/leave.docx", listOfProperties, dto);
		}
		else
		{
			createWordbonfiedletter("C:/Users/Akilesh Abilesh/Desktop/bon.docx", listOfProperties, dto);
		
		}
		return "success";
	}
	
	private static void createWordleaveletter(final String outputFileName, List<Prop> listOfProperties, DocSectionDTO dto) throws Exception{

		CTDrawing drawing;
		CTAnchor anchor;// create a document
		XWPFDocument doc = new XWPFDocument();

		// create a paragraph with justify alignment
		XWPFParagraph pH1 = doc.createParagraph();
		XWPFParagraph pH2 = doc.createParagraph();
		XWPFParagraph pH3 = doc.createParagraph();
		XWPFParagraph[] p = new XWPFParagraph[12];
		p[0] = doc.createParagraph();
		p[1] = doc.createParagraph();

		p[2] = doc.createParagraph();
		p[3] = doc.createParagraph();
		p[4] = doc.createParagraph();
		p[5] = doc.createParagraph();
		p[6] = doc.createParagraph();
		p[7] = doc.createParagraph();
		p[8] = doc.createParagraph();
		p[9] = doc.createParagraph();
		p[10] = doc.createParagraph();
		p[11] = doc.createParagraph();

		XWPFRun[] r1 = new XWPFRun[12];

		CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();

		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

		XWPFParagraph pH = header.createParagraph();
		XWPFRun r = pH.createRun();

		pH1 = header.createParagraph();
		pH1.setAlignment(ParagraphAlignment.RIGHT);
		pH2 = header.createParagraph();

		pH2.setAlignment(ParagraphAlignment.RIGHT);
		pH3 = header.createParagraph();
		pH3.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun r10 = pH1.createRun();
		XWPFRun r11 = pH2.createRun();
		XWPFRun r12 = pH3.createRun();
		// CTTabStop tabStop = pH.getCTP().getPPr().addNewTabs().addNewTab();
		// tabStop.setVal(STTabJc.LEFT);

		r10 = pH1.createRun();
		r11 = pH2.createRun();
		r12 = pH3.createRun();
		String t9 = "ThoughtClan\n";
		String t10 = "PrangiPalya HSR Layout";
		String t11 = "Bengaluru 560056";

		r10.setText(t9);
		r11.setText(t10);
		r12.setText(t11);

		// tabStop.setPos(BigInteger.valueOf(4 * twipsPerInch));

		r = pH.createRun();

		r = pH.createRun();
		pH3.setBorderBottom(Borders.BASIC_THIN_LINES);

		String imgFile = "C:/Users/Akilesh Abilesh/Desktop/thought.jpg";
		r.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(100),
				Units.toEMU(60));
		drawing = (CTDrawing) r.getCTR().getDrawingArray(0);

		anchor = getAnchorWithGraphic(drawing,"C:/Users/Akilesh Abilesh/Desktop/thought.jpg", true /* behind text */);

		drawing.setAnchorArray(new CTAnchor[] { anchor });
		((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing).removeInline(0);
		r = pH.createRun();

		for (Prop list : listOfProperties) {
			System.out.println("listss"+list);
			int fsize = list.getFontSize();
			String color = list.getColor();
			String font = list.getFont();
			String fontstyle = list.getFontStyle();
			int indent = list.getIndent();
			int i = list.getId();
System.out.println(i);
			r1[i] = setproperty(font, color, fsize, indent, r1[i], p[i]);
			
		}
	
		
		r1[1].setText(dto.sec1);
		r1[2].setText(dto.sec2);
		r1[3].setText(dto.sec3);
		r1[4].setText(dto.sec4);
		r1[5].setText(dto.sec5);

		FileOutputStream fo = null;
		try {

			fo = new FileOutputStream(outputFileName);

			doc.write(fo);
			System.out.println("Document created");

		} catch (IOException e) {
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}

	//@SuppressWarnings("null")
	public static void createWordoffeletter (String outputFileName, List<Prop> listOfProperties, DocSectionDTO dto) throws Exception{

		CTDrawing drawing;
		CTAnchor anchor;// create a document
		XWPFDocument doc = new XWPFDocument();

		// create a paragraph with justify alignment
		XWPFParagraph pH1 = doc.createParagraph();
		XWPFParagraph pH2 = doc.createParagraph();
		XWPFParagraph pH3 = doc.createParagraph();
		XWPFParagraph[] p = new XWPFParagraph[20];
		
		p[6] = doc.createParagraph();
		p[7] = doc.createParagraph();
		p[8] = doc.createParagraph();
		p[9] = doc.createParagraph();
		p[10] = doc.createParagraph();
		p[11] = doc.createParagraph();

		XWPFRun[] r1 = new XWPFRun[20];

		CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();

		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

		XWPFParagraph pH = header.createParagraph();
		XWPFRun r = pH.createRun();

		pH1 = header.createParagraph();
		pH1.setAlignment(ParagraphAlignment.RIGHT);
		pH2 = header.createParagraph();

		pH2.setAlignment(ParagraphAlignment.RIGHT);
		pH3 = header.createParagraph();
		pH3.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun r10 = pH1.createRun();
		XWPFRun r11 = pH2.createRun();
		XWPFRun r12 = pH3.createRun();
		// CTTabStop tabStop = pH.getCTP().getPPr().addNewTabs().addNewTab();
		// tabStop.setVal(STTabJc.LEFT);

		r10 = pH1.createRun();
		r11 = pH2.createRun();
		r12 = pH3.createRun();
		String t9 = "ThoughtClan\n";
		String t10 = "PrangiPalya HSR Layout";
		String t11 = "Bengaluru 560056";

		r10.setText(t9);
		r11.setText(t10);
		r12.setText(t11);

		// tabStop.setPos(BigInteger.valueOf(4 * twipsPerInch));

		r = pH.createRun();

		r = pH.createRun();
		pH3.setBorderBottom(Borders.BASIC_THIN_LINES);

		String imgFile = "C:/Users/Akilesh Abilesh/Desktop/thought.jpg";
		r.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(100),
				Units.toEMU(60));
		drawing = (CTDrawing) r.getCTR().getDrawingArray(0);

		anchor = getAnchorWithGraphic(drawing, "C:/Users/Akilesh Abilesh/Desktop/thought.jpg", true /* behind text */);

		drawing.setAnchorArray(new CTAnchor[] { anchor });
		((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing).removeInline(0);
		r = pH.createRun();

		for (Prop list : listOfProperties) {
           System.out.println("list: "+list);
			int fsize = list.getFontSize();
			String color = list.getColor();
			String font = list.getFont();
			String fontstyle = list.getFontStyle();
			int indent = list.getIndent();
			int i = list.getId();
			System.out.println(i);

			r1[i] = setproperty(font, color, fsize, indent, r1[i], p[i]);
			

		}
		r1[6].setText(dto.sec1);
		r1[7].setText(dto.sec2);
		r1[8].setText(dto.sec3);
		r1[9].setText(dto.sec4);
		r1[10].setText(dto.sec5);
	//	r1[6].setText(dto.sec6);


		FileOutputStream fo = null;
		try {

			fo = new FileOutputStream(outputFileName);

			doc.write(fo);
			System.out.println("Document created");

		} catch (IOException e) {
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	private static void createWordbonfiedletter(final String outputFileName, List<Prop> listOfProperties, DocSectionDTO dto) throws Exception{

		CTDrawing drawing;
		CTAnchor anchor;// create a document
		XWPFDocument doc = new XWPFDocument();

		// create a paragraph with justify alignment
		XWPFParagraph pH1 = doc.createParagraph();
		XWPFParagraph pH2 = doc.createParagraph();
		XWPFParagraph pH3 = doc.createParagraph();
		XWPFParagraph[] p = new XWPFParagraph[20];
		/*
		 * p[0] = doc.createParagraph(); p[1] = doc.createParagraph();
		 * 
		 * p[2] = doc.createParagraph(); p[3] = doc.createParagraph(); p[4] =
		 * doc.createParagraph(); p[5] = doc.createParagraph();
		 */
		p[6] = doc.createParagraph();
		p[12] = doc.createParagraph();
		p[13] = doc.createParagraph();
		p[14] = doc.createParagraph();
		p[15] = doc.createParagraph();
		p[11] = doc.createParagraph();

		XWPFRun[] r1 = new XWPFRun[20];

		CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();

		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

		XWPFParagraph pH = header.createParagraph();
		XWPFRun r = pH.createRun();

		pH1 = header.createParagraph();
		pH1.setAlignment(ParagraphAlignment.RIGHT);
		pH2 = header.createParagraph();

		pH2.setAlignment(ParagraphAlignment.RIGHT);
		pH3 = header.createParagraph();
		pH3.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun r10 = pH1.createRun();
		XWPFRun r11 = pH2.createRun();
		XWPFRun r12 = pH3.createRun();
		// CTTabStop tabStop = pH.getCTP().getPPr().addNewTabs().addNewTab();
		// tabStop.setVal(STTabJc.LEFT);

		r10 = pH1.createRun();
		r11 = pH2.createRun();
		r12 = pH3.createRun();
		String t9 = "ThoughtClan\n";
		String t10 = "PrangiPalya HSR Layout";
		String t11 = "Bengaluru 560056";

		r10.setText(t9);
		r11.setText(t10);
		r12.setText(t11);

		// tabStop.setPos(BigInteger.valueOf(4 * twipsPerInch));

		r = pH.createRun();

		r = pH.createRun();
		pH3.setBorderBottom(Borders.BASIC_THIN_LINES);

		String imgFile = "C:/Users/Akilesh Abilesh/Desktop/thought.jpg";
		r.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(100),
				Units.toEMU(60));
		drawing = (CTDrawing) r.getCTR().getDrawingArray(0);

		anchor = getAnchorWithGraphic(drawing,"C:/Users/Akilesh Abilesh/Desktop/thought.jpg", true /* behind text */);

		drawing.setAnchorArray(new CTAnchor[] { anchor });
		((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing).removeInline(0);
		r = pH.createRun();

		for (Prop list : listOfProperties) {
			System.out.println(list);
			int fsize = list.getFontSize();
			String color = list.getColor();
			String font = list.getFont();
			String fontstyle = list.getFontStyle();
			int indent = list.getIndent();
			int i = list.getId();
System.out.println(i);
			r1[i] = setproperty(font, color, fsize, indent, r1[i], p[i]);
			
		}
	
		
		r1[11].setText(dto.sec1);
		r1[12].setText(dto.sec2);
		r1[13].setText(dto.sec3);
		r1[14].setText(dto.sec4);
		r1[15].setText(dto.sec5);

		FileOutputStream fo = null;
		try {

			fo = new FileOutputStream(outputFileName);

			doc.write(fo);
			System.out.println("Document created");
		} catch (IOException e) {
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}


	@SuppressWarnings("unused")
	private static int getImageFormat(String imgFileName) {
		int format;
		if (imgFileName.endsWith(".emf"))
			format = XWPFDocument.PICTURE_TYPE_EMF;
		else if (imgFileName.endsWith(".wmf"))
			format = XWPFDocument.PICTURE_TYPE_WMF;
		else if (imgFileName.endsWith(".pict"))
			format = XWPFDocument.PICTURE_TYPE_PICT;
		else if (imgFileName.endsWith(".jpeg") || imgFileName.endsWith(".jpg"))
			format = XWPFDocument.PICTURE_TYPE_JPEG;
		else if (imgFileName.endsWith(".png"))
			format = XWPFDocument.PICTURE_TYPE_PNG;
		else if (imgFileName.endsWith(".dib"))
			format = XWPFDocument.PICTURE_TYPE_DIB;
		else if (imgFileName.endsWith(".gif"))
			format = XWPFDocument.PICTURE_TYPE_GIF;
		else if (imgFileName.endsWith(".tiff"))
			format = XWPFDocument.PICTURE_TYPE_TIFF;
		else if (imgFileName.endsWith(".eps"))
			format = XWPFDocument.PICTURE_TYPE_EPS;
		else if (imgFileName.endsWith(".bmp"))
			format = XWPFDocument.PICTURE_TYPE_BMP;
		else if (imgFileName.endsWith(".wpg"))
			format = XWPFDocument.PICTURE_TYPE_WPG;
		else {
			return 0;
		}
		return format;
	}

	private static CTAnchor getAnchorWithGraphic(CTDrawing drawing /* inline drawing */ , String drawingDescr,
			boolean behind) throws Exception {

		CTGraphicalObject graphicalobject = ((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing)
				.getInlineArray(0).getGraphic();
		long width = ((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing).getInlineArray(0)
				.getExtent().getCx();
		long height = ((org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing) drawing).getInlineArray(0)
				.getExtent().getCy();

		String anchorXML = "<wp:anchor xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
				+ "simplePos=\"0\" relativeHeight=\"0\" behindDoc=\"" + ((behind) ? 1 : 0)
				+ "\" locked=\"0\" layoutInCell=\"1\" allowOverlap=\"1\">" + "<wp:simplePos x=\"0\" y=\"0\"/>"
				+ "<wp:positionH relativeFrom=\"column\"><wp:posOffset>0</wp:posOffset></wp:positionH>"
				+ "<wp:positionV relativeFrom=\"paragraph\"><wp:posOffset>0</wp:posOffset></wp:positionV>"
				+ "<wp:extent cx=\"" + width + "\" cy=\"" + height + "\"/>"
				+ "<wp:effectExtent l=\"0\" t=\"0\" r=\"0\" b=\"0\"/><wp:wrapNone/>"
				+ "<wp:docPr id=\"1\" name=\"Drawing 0\" descr=\"" + drawingDescr + "\"/><wp:cNvGraphicFramePr/>"
				+ "</wp:anchor>";

		drawing = CTDrawing.Factory.parse(anchorXML);
		CTAnchor anchor = (CTAnchor) drawing.getAnchorArray(0);
		anchor.setGraphic(graphicalobject);
		return anchor;
	}

	public static XWPFRun setproperty(String Font, String Color/* ,String Bgcolor */, int fSize, int indent, XWPFRun r,XWPFParagraph p) {

		p.setFirstLineIndent(indent);
		p.setAlignment(ParagraphAlignment.LEFT);
		p.setWordWrapped(true);
		r = p.createRun();
		r.setFontFamily(Font);
		r.setColor(Color);
		/* r.setTextHighlightColor(Bgcolor) */;
		r.setFontSize(fSize);
		System.out.println(r);
		return r;
	}
}
