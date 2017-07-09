package com.max;

import java.util.ArrayList;
import java.util.List;

public class ParserPool{

	private List<Parser> parsers = new ArrayList<>();
	
	public void add(Parser parser){
		parsers.add(parser);
	}
	
	public void startAll(){
		for(Parser parser : parsers){
			parser.start();
		}
	}
	
	public void stopAll(){
		for(Parser parser : parsers){
			parser.interrupt();
		}	
	}
	
	public List<Parser> getParsers(){
		return parsers;
	}
}
