package in.wptrafficanalyzer.locationgeocodingv2;


public class ListModel {
	private  String Location="";
	private  String Time=""; 
	private  String Date="";
	private   String Taskno="";
	private   String Taskspecification="";
	/*********** Set Methods ******************/
	public void setLocation(String Location)
	{
		this.Location = Location;
	}
	
	public void setTime(String Time)
	{
		this.Time = Time;
	}
	
	public void setDate(String Date)
	{
		this.Date = Date;
	}
	public void setTaskno(String Taskno)
	{
		this.Taskno = Taskno;
	}
	public void setTaskspecification(String Taskspecification)
	{
		this.Taskspecification = Taskspecification;
	}
	/*********** Get Methods ****************/
	public String getLocation()
	{
		return this.Location;
	}
	
	public String getTime()
	{
		return this.Time;
	}

	public String getDate()
	{
		return this.Date;
	}	
	public String getTaskno()
	{
		return this.Taskno ;
	}
	public String getTaskspecification()
	{
		return this.Taskspecification ;
	}
}
