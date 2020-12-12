package test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.xmlbeans.XmlException;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.support.SoapUIException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class testRunner {

	@Test
	public void runtest() throws IOException, XmlException, SoapUIException {			
		//Getting Date
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yy_HHmmss");
		String strDate = sdf.format(cal.getTime());
	    //Setting Path
		System.out.println("--------Setting Up Execution Path--------");
		String baseDir = System.getProperty("user.dir");
	    String PATH = baseDir+"/ExecutionReport/Run_"+strDate;
	    Files.createDirectories(Paths.get(PATH));
	    System.out.println("--------Setting Up Reporting Directory--------");
		String reportPath = PATH;
		System.out.println("--------Setting Up Project--------");
		//Setting up SoapUI 		
		WsdlProject project = new WsdlProject(baseDir +"\\Digital-API.xml");
		project.setPropertyValue("basePath", reportPath);
		WsdlTestSuite tSuite = project.getTestSuiteByName("ExecuteScenarios");
		System.out.println("--------Starting Digital-API Execution--------");
		tSuite.getTestCaseByName("ExecutionScript").run(new PropertiesMap(), false);
		System.out.println("--------Completed Digital-API Execution--------");
		System.out.println("------Execution Report is Available under: Run_"+strDate+" Folder------");
	}	

}
