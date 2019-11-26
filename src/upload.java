package server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
/* 
 Upload files and parse them into JSON format.
*/
public class upload {
	public static String xml2jsonString(String path) throws JSONException, IOException {
	    	File file = new File(path);
	    	InputStream in = new FileInputStream(file);
	        String xml = IOUtils.toString(in);
	        JSONObject xmlJSONObj = XML.toJSONObject(xml);
	       
	        return xmlJSONObj.toString();
	    }

	    public String uploadFile(String path) throws JSONException, IOException {
	        String string = xml2jsonString(path);
	        return string;
	       
	
	    }
	    
	    
}
