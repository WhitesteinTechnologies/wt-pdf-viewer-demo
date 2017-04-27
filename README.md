# wt-pdf-viewer-demo

Demo project for [WT PDF Viewer Vaadin addon](https://vaadin.com/directory#!addon/wt-pdf-viewer). Source code for addon is in [wt-pdf-viewer](https://github.com/WhitesteinTechnologies/wt-pdf-viewer) repository.


## Usage
Compile and run the project:
* Compile the project using `mvn install` command. 
* Run the application using `mvn jetty:run`. 
* Open `http://localhost:8080/` url in browser.

It shows two empty pdf viewer widgets side by side. The buttons below are not part of the component, they are there to demonstrate Java API capabilities. 

![Two empty widgets](/screenshots/demo-empty.png?raw=true "Two empty widgets")

Use `Change file` button loads pdf file from `/src/main/resources/` directory and shows it in component. You can load different pdf file into each component, test whether component reacts nicely when window is resized, search inside the pdf text, ...

![Search inside pdf](/screenshots/demo-search.png?raw=true "Search inside document")

... see document properties, zoom pdf ...

![Show pdf properties](/screenshots/demo-document-properties.png?raw=true "Show pdf properties")

... or open sidebar with thumbnails ...

![Thumbnails in sidebar](/screenshots/demo-sidebar-thumbnails.png?raw=true "Thumbnails in sidebar")


## Compile WAR
To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

## Client-Side compilation
The generated maven project is using an automatically generated widgetset by default. 
When you add a dependency that needs client-side compilation, the maven plugin will 
automatically generate it for you. Your own client-side customisations can be added into
package "client".

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application
