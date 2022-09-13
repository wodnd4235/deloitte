package com.deloitte.solution.batch.support.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateAdapter extends XmlAdapter<String, Date>{
	
    private String pattern = "yyyy-MM-dd hh:mm:ss";
    
    public DateAdapter() {
    	
    }
    
    public DateAdapter(String pattern) {
		this.pattern = pattern;
	}

    @Override
    public Date unmarshal(String dateStr) throws Exception {
        Date date = new SimpleDateFormat(pattern).parse(dateStr);
        
        return date;
    }

    @Override
    public String marshal(Date date) throws Exception {
        String dateStr = new SimpleDateFormat(pattern).format(date);
        return dateStr;
    }
    
}