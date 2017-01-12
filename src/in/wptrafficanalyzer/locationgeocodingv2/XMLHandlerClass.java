package in.wptrafficanalyzer.locationgeocodingv2;

import java.util.ArrayList;

import javax.xml.transform.Templates;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLHandlerClass extends DefaultHandler {

	String tmpValue = null;
	Boolean elementOn = false;
	public static XMLGettersSettersClass obj,obj_2=null;
	public static ArrayList<XMLGettersSettersClass> data = new ArrayList<XMLGettersSettersClass>();

	public static XMLGettersSettersClass getXMLData(int i) {
		return obj.getobj();
	}

	public static void setXMLData(XMLGettersSettersClass data) {
		XMLHandlerClass.data.add(data);
	}

	/** 
	 * This will be called when the tags of the XML starts.
	 **/
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		elementOn = true;

	 
       
		if (localName.equals("elements"))
		{
			
		 	
		} else if (localName.equals("CD")) {
			/** 
			 * We can get the values of attributes for eg. if the CD tag had an attribute( <CD attr= "band">Akon</CD> ) 
			 * we can get the value "band". Below is an example of how to achieve this.
			 * 
			 * String attributeValue = attributes.getValue("attr");
			 * data.setAttribute(attributeValue);
			 * 
			 * */
		}
	 
		if (localName.equalsIgnoreCase("element")) {
			obj = new XMLGettersSettersClass();
			//obj_2.setobj(obj);
			   setXMLData(obj);
  	
  }
	}

	/** 
	 * This will be called when the tags of the XML end.
	 **/
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		elementOn = false;
		//data.setcolor("#00ff00");
		/** 
		 * Sets the values after retrieving the values from the XML tags
		 * */ 
	
		
        if (localName.equalsIgnoreCase("date")) {
        	obj.setdate(tmpValue);
        }
        if (localName.equalsIgnoreCase("type")) {
        	obj.settype(tmpValue);
        }
    
     
        if(localName.equalsIgnoreCase("starttime"))
        {
        	obj.setstarttime(tmpValue) ;
        }
        //  if(!localName.equalsIgnoreCase("padding-left")){	data.setpaddingleft ("1") ;}
       
         if(localName.equalsIgnoreCase("endtime")){
        	 obj.setendtime(tmpValue);
         }
         //else{ obj.setimageurl("0");}
         if(localName.equalsIgnoreCase("location")){
        	 obj.setlocation(tmpValue);
          }
       
    
    
        
	}

	/** 
	 * This is called to get the tags value
	 **/
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (elementOn) {
			tmpValue = new String(ch, start, length);
			elementOn = false;
		}

	}

}
