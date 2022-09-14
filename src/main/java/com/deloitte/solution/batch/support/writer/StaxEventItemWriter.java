package com.deloitte.solution.batch.support.writer;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaxEventItemWriter<T> extends org.springframework.batch.item.xml.StaxEventItemWriter<T> {

	@Override
	protected void startDocument(XMLEventWriter writer) throws XMLStreamException {

		if (!StringUtils.isBlank(getRootTagName())) {
			super.startDocument(writer);			
		}else {
			
			XMLEventFactory factory = createXmlEventFactory();
			// write start document
			writer.add(factory.createStartDocument(getEncoding(), getVersion()));
		}
	}
	
	@Override
	protected void endDocument(XMLEventWriter writer) throws XMLStreamException {
		if (!StringUtils.isBlank(getRootTagName())) {
			super.endDocument(writer);
		}
	}
}
