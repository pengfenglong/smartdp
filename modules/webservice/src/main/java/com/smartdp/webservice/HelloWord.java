package com.smartdp.webservice;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.databinding.utils.reader.OMElementKey;


public class HelloWord {
	  public OMElement sayHello(OMElement element) throws XMLStreamException {
		  
          element.build();

          // Secondly the OMElement should be detached from the current OMTree so

          // that it can be attached

          // some other OM Tree. Once detached the OmTree will remove its

          // connections to this OMElement.

          element.detach();

          return element;

}

}
