package in.wptrafficanalyzer.locationgeocodingv2;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 *  This class contains all getter and setter methods to set and retrieve data.
 *  
 **/
public class XMLGettersSettersClass {
	private XMLGettersSettersClass object=null;
	private  String endtime=new String();
	private  String starttime=new String();
	private  String location=new String();
	private  String type=new String();
 
	private   String  date=new String();
	 
  
	public XMLGettersSettersClass()
	{
		
	}
	public XMLGettersSettersClass getobj()
	{
		return object;
	}
	public void setobj(XMLGettersSettersClass obj)
	{
		this.object=obj;
	}
	 
	public void  setdate(String date)
	{
		this.date=(date);
	}
	public String getdate()
	{
		return date;
	}
	public void  setendtime(String endtime)
	{
		this.endtime=(endtime);
	}
	public String getendtime()
	{
		return endtime;
	}
	public void  setstarttime(String starttime)
	{
		this.starttime=(starttime);
	}
	public String getstarttime()
	{
		return starttime;
	}
	public void  settype(String type)
	{
		this.type=(type);
	}
	 
	public void  setlocation(String location)
	{
		this.location=(location);
	}
	public String getlocation()
	{
		return location;
	}
	public String gettype() {
		// TODO Auto-generated method stub
		return type;
	}
	
	
	
}
