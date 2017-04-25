package com.whitestein.vaadin.demo.wtpdfviewerdemo;

public class PdfFiles {

	private String[] files = new String[] { "pdf.pdf", "listener-def-cheatsheet.pdf", "ieee_guide.pdf" };
	private int filesIndx = files.length;

	public String getNextFile() {
		filesIndx++;
		if (filesIndx >= files.length || filesIndx < 0)
			filesIndx = 0;

		return files[filesIndx];
	}

}
