package fr.ma.facturation.pdf;

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;

public class RoundRectangleCellEvent implements PdfPCellEvent {

	public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
		PdfContentByte cb = canvas[PdfPTable.LINECANVAS];
		cb.roundRectangle(rect.getLeft() + 1.5f, rect.getBottom() + 1.5f,  rect.getWidth() - 4,  rect.getHeight() - 4, 4);
		cb.setLineWidth(0.5f);
		cb.stroke();
	}

}
