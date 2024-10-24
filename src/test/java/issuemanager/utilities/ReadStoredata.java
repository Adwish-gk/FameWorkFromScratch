package issuemanager.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadStoredata 
{
	 private Properties pro;

	    public ReadStoredata()
	    {
	    	File src=new File("C:\\Users\\DELL\\eclipse-workspace\\FrameWorkFromScratch\\Configuration\\Storedata.properties");
	        pro = new Properties();
	        try {
	            FileInputStream input = new FileInputStream(src);
	           pro=new Properties();
	           pro.load(input);
	        } catch (IOException e) {
	            System.out.print("Exception Is"+e.getMessage());
	        }
	    }
	    public String getApplicationurl() 
	    {
	    	String baseurl=pro.getProperty("vmsstaggingurl");
	    	return baseurl;
	    }
	    public String getusername() 
	    {
	    	String un=pro.getProperty("loginusername");
	    	return un;
	    }
	    }

