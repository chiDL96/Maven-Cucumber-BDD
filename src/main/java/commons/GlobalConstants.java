package commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";
    public static final String OS_NAME = System.getProperty("os.name");

    public static final String BANK_GURU_URL = "https://demo.guru99.com/";
    public static final String BANK_GURU_LOGIN_URL = "https://demo.guru99.com/v4/";

    public static final String REPORT_FILE_UPLOAD = PROJECT_PATH + File.separator + "ReportNGImages" + File.separator;

    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "ExtentReportImage" + File.separator;
    public static final long LONG_TIMEOUT = 30;

    public static final long SHORT_TIMEOUT = 5;
    public static final String JAVA_VERSION = System.getProperty("java.version");
}
