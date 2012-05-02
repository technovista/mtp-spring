package com.array.testprep.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Convrter {
	
	
	
	
	public List<String> StringToArray(String csv,String delm)
	{
		List<String> str = new ArrayList<String>();
		if (csv== null)
			return str;
		
		StringBuffer sb = new StringBuffer();
		StringTokenizer token = new StringTokenizer(csv);
		while(token.hasMoreElements())
		{
			str.add(token.nextToken(delm));
			
		}
		
		return str;
		
	}

}
