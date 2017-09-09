package com.test.spring.batch.test;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

public class ItemProcessorImpl implements ItemProcessor<FieldSet, StringBuilder> {

	private static final Logger log = LoggerFactory.getLogger(ItemProcessorImpl.class);
	
	@Override
	public StringBuilder process(FieldSet item) throws Exception {
		List<String> sList = Arrays.asList(item.getValues());
		StringBuilder sb = new StringBuilder();
		for (String s : sList) {
			sb.append(s).append(",");
		}
		log.debug(sb.toString());
		sb.append("TT").append(",").append("YY");
		return sb;
	}

}
