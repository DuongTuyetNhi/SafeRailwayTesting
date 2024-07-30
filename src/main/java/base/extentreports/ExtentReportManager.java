package base.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports getExtentReports(){
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreports/extentreport.html");
        reporter.config().setReportName("Extent Report | Nhi Tester");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework", "Selenium Java");
        extentReports.setSystemInfo("Author", "Nhi Tester");
        return extentReports;
    }
}
