package com.whitestein.vaadin.demo.wtpdfviewerdemo;

import java.io.InputStream;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.whitestein.vaadin.widgets.wtpdfviewer.WTPdfViewer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("pdfdemotheme")
public class PdfDemoUI extends UI {

	private static final long serialVersionUID = -4099063806060815077L;
	private PdfFiles pdfFiles = new PdfFiles();

	@Override
	protected void init(VaadinRequest request) {

		final WTPdfViewer firstViewer = new WTPdfViewer();
		firstViewer.setSizeFull();

		final WTPdfViewer secondViewer = new WTPdfViewer();
		secondViewer.setSizeFull();

		HorizontalLayout viewersLayout = new HorizontalLayout(firstViewer, secondViewer);
		viewersLayout.setSizeFull();

		HorizontalLayout controllPanelLayout = createControllPanel(firstViewer, secondViewer);
		VerticalLayout totalLayout = new VerticalLayout(viewersLayout, controllPanelLayout);
		totalLayout.setSizeFull();
		totalLayout.setExpandRatio(viewersLayout, (float) 1.0);
		setContent(totalLayout);
	}

	private HorizontalLayout createControllPanel(final WTPdfViewer firstViewer, final WTPdfViewer secondViewer) {
		VerticalLayout firstViewerButtonsLayout = controllButtons(firstViewer);
		VerticalLayout secondViewerButtonsLayout = controllButtons(secondViewer);
		HorizontalLayout controllPanelLayout = new HorizontalLayout(firstViewerButtonsLayout, secondViewerButtonsLayout);
		controllPanelLayout.setWidth("100%");
		controllPanelLayout.setComponentAlignment(firstViewerButtonsLayout, Alignment.TOP_LEFT);
		controllPanelLayout.setComponentAlignment(secondViewerButtonsLayout, Alignment.BOTTOM_RIGHT);
		return controllPanelLayout;
	}

	private VerticalLayout controllButtons(final WTPdfViewer firstViewer) {
		return new VerticalLayout(basicButtonsLayout(firstViewer), jumpToPage(firstViewer));
	}

	private HorizontalLayout jumpToPage(final WTPdfViewer pdfViewer) {
		TextField field = new TextField();
		field.addValidator(new IntegerRangeValidator("Nope", 1, 1000));
		field.setValue("1");

		Button jumpPage = new Button("Jump");
		jumpPage.addClickListener((event) -> {
			String value = field.getValue();
			pdfViewer.setPage(Integer.valueOf(value));
		});

		HorizontalLayout jumpToPageLayout = new HorizontalLayout();
		jumpToPageLayout.addComponents(field, jumpPage);
		return jumpToPageLayout;
	}

	private HorizontalLayout basicButtonsLayout(final WTPdfViewer pdfViewer) {
		Button changeFileBtn = new Button("Change file");
		changeFileBtn.addClickListener((event) -> {
			String filename = pdfFiles.getNextFile();
			InputStream dataStream = getClass().getClassLoader().getResourceAsStream(filename);
			pdfViewer.setResource(new StreamResource(new InputStreamSource(dataStream), filename));

		});

		Button previousPage = new Button("Previous Page");
		previousPage.addClickListener((event) -> pdfViewer.previousPage());

		Button nextPage = new Button("Next Page");
		nextPage.addClickListener((event) -> pdfViewer.nextPage());
		HorizontalLayout basicButtonsLayout = new HorizontalLayout();
		basicButtonsLayout.addComponents(changeFileBtn, previousPage, nextPage);
		return basicButtonsLayout;
	}

	@WebServlet(urlPatterns = "/*", name = "PdfDemoUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = PdfDemoUI.class, productionMode = false)
	public static class PdfDemoUIServlet extends VaadinServlet {

		private static final long serialVersionUID = 2654889965543717501L;
	}
}
