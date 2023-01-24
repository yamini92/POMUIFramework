package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkExceptions;


/**
 * This method is use to initialize browser 
 * @author kapad
 *
 */
public class DriverFactory {

	public WebDriver driver;
	Properties prop;
	
	public OptionsManager op;
	
	public static  String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger LOG = Logger.getLogger(DriverFactory.class);
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		//String browserName=System.getProperty("browser");
		System.out.println("running on ---"+ browserName);
		highlight = prop.getProperty("highlight").trim();
		op = new OptionsManager(prop);
		LOG.info("BrowserName is" + browserName);
		switch (browserName.toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			break;

		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(op.getFirefoxOptions()));
			break;
			
		case "Edge":
			//driver = new SafariDriver();
			tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
			break;
			
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
			
		default:
			System.out.println("Please select appropriate browser");
			LOG.error("Please select appropriate browser" + browserName);
		
			//throw new FrameworkExceptions(AppErrors.BROWESER_NOT_FOUND);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is used to initialize the properties and return properties
	 * @return
	 */
	public Properties initProp() {
	 prop = new Properties();
	 FileInputStream ip = null;
	 
	 //mvn clean install -Denv="qa"
	 
	 //String envName = System.getenv("env");//qa,dev,uat,stage
	 String envName=System.getProperty("env");
	 System.out.println("running ---"+ envName);

	 if(envName == null) {
		 System.out.println("No env is set Hence running in QA env");
		 try {
			ip = new FileInputStream("src/test/resources/Config/qa.config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 else {

		 try {
		 switch (envName) {
		case "qa":
			ip = new FileInputStream("./src/test/resources/Config/qa.config.properties");
			break;
			
		case  "dev":
			ip = new FileInputStream("./src/test/resources/Config/dev.config.properties");
			break;
		case "uat": 
			ip = new FileInputStream("./src/test/resources/Config/config.properties");
			break;
		default:
			System.out.println("Please pass right env");
			throw new FrameworkExceptions(AppErrors.ENV_NOT_FOUND);
			
		}
		 
	 }catch(FileNotFoundException e){
		 e.printStackTrace();
	 }
		 
	 }
	 try {
		prop.load(ip);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return prop;
	 
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
