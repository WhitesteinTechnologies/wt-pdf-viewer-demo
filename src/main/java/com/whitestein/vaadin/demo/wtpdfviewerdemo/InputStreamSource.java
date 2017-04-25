package com.whitestein.vaadin.demo.wtpdfviewerdemo;

import java.io.InputStream;

import com.vaadin.server.StreamResource.StreamSource;

public class InputStreamSource implements StreamSource {

	private static final long serialVersionUID = 7774585149770326086L;

	private final InputStream data;

	public InputStreamSource(InputStream data) {
		super();
		this.data = data;
	}

	@Override
	public InputStream getStream() {
		return data;
	}

}
