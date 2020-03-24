package br.com.casadocodigo.factory;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class DriverFactory {

	private static WebDriver driver;

	private static final String DRIVER_CHROME = "chrome";
	private static final String DRIVER_IE = "ie";
	private static final String DRIVER_FIREFOX = "firefox";
	private static final String HEADLESS_CHROME = "chrome-headless";
	private static final String DRIVER_PHANTOM = "phantom";

	public static WebDriver createDriver(String browserName) {
		switch (browserName) {
		case DRIVER_CHROME:
			if (SystemUtils.IS_OS_LINUX) {
				System.setProperty("webdriver.chrome.driver", "WebDrivers/linux/chromedriver");
			}else if(SystemUtils.IS_OS_WINDOWS){
				System.setProperty("webdriver.chrome.driver", "WebDrivers/windows/chromedriver.exe");
			}else {
				System.setProperty("webdriver.chrome.driver", "WebDrivers/macos/chromedriver");
			}
			driver = new ChromeDriver();
			break;
		case DRIVER_IE:
			if (SystemUtils.IS_OS_LINUX) {
				System.out.println("Não existem navegadores para esse sistema");
			}else if(SystemUtils.IS_OS_WINDOWS){
				System.setProperty("webdriver.ie.driver", "WebDrivers/windows/IEDriverServer.exe");
			}else {
				System.out.println("Não existem navegadores para esse sistema");
			}
			driver = new InternetExplorerDriver();
			break;
		case DRIVER_FIREFOX:
			if (SystemUtils.IS_OS_LINUX) {
				System.setProperty("webdriver.gecko.driver", "WebDrivers/linux/geckodriver");
			}else if(SystemUtils.IS_OS_WINDOWS){
				System.setProperty("webdriver.gecko.driver", "WebDrivers/windows/geckodriver.exe");
			}else {
				System.setProperty("webdriver.gecko.driver", "WebDrivers/macos/geckodriver");
			}
			driver = new FirefoxDriver();
			break;
		case HEADLESS_CHROME:
			if (SystemUtils.IS_OS_LINUX) {
				System.setProperty("webdriver.chrome.driver", "WebDrivers/linux/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}else if(SystemUtils.IS_OS_WINDOWS){
				System.setProperty("webdriver.chrome.driver", "WebDrivers/windows/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}else {
				System.setProperty("webdriver.chrome.driver", "WebDrivers/macos/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}
			break;
		case DRIVER_PHANTOM:
			if (SystemUtils.IS_OS_LINUX) {
				System.setProperty("phantomjs.binary.path", "WebDrivers/linux/phantomjs");
			}else if(SystemUtils.IS_OS_WINDOWS){
				System.setProperty("phantomjs.binary.path", "WebDrivers/windows/phantomjs.exe");
			}else {
				System.setProperty("phantomjs.binary.path", "WebDrivers/macos/phantomjs");
			}
			driver = new PhantomJSDriver();
			break;
		default:
			System.out.println("Nenhum navegador selecionado ou navegador invalido!!!");
			break;
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

}
