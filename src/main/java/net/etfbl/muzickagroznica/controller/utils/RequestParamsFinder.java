package net.etfbl.muzickagroznica.controller.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class RequestParamsFinder {

	public RequestParamsFinder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Extracts integer value from parameter with name like this:
	 * 
	 * _logicalName_123
	 * 
	 * @param request HttpServletRequest to extract parameter from.
	 * @param logicalParamName logical name of parameter
	 * @return Integer value parsed from first parameter with name starting with:
	 *  "_" + logicalParamName + "_". Integer is parsed from remaining chars that come after.
	 * @throws Exception 
	 */
	public int findIntParam(HttpServletRequest request, String logicalParamName) throws Exception{
		
		String prefix = "_" + logicalParamName + "_";
		

		Enumeration<String> ps = request.getParameterNames();
		
		while(ps.hasMoreElements()){
			String param = ps.nextElement();
			if(param.startsWith(prefix)){
				return Integer.parseInt(param.substring(param.lastIndexOf('_') + 1));			
			}
		}

		throw new Exception("No such parameter in request.");
	}

}
