package commons;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class BaseTest {

    protected WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }



    @BeforeSuite
    public void initBeforeSuit() {
        deleteAllureFileInFolder();
        createAllurePropertiesFileInFolder();
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }



    public int generateNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    public void deleteAllureFileInFolder() {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + "allure-json";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void createAllurePropertiesFileInFolder(){
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + "allure-json" + File.separator + "environment.properties";
            FileWriter myWriter = new FileWriter(pathFolderDownload);
            myWriter.write("OS= MacOS\n" +
                    "Browser=Firefox\n" +
                    "Browser.Version=111\n" +
                    "Stand=Dev\n" +
                    "Java.Version=11");
            myWriter.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Set<Cookie> getAllCookie(WebDriver driver){
        return driver.manage().getCookies();
    }

    public void setCookie(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(2);
    }

    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME;
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            //Xoa toan bo phien dang nhap de tranh cac class test khac lai chay lai bang cookie cu
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getCurrentDate() {
        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return String.valueOf(month);
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime(DateTimeZone.UTC);
        int year = now.getYear();
        return String.valueOf(year);
    }

    protected String getCurrentDay() {
        return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDate();
    }
}
