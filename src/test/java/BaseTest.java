import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private double startTime;

    @BeforeMethod
    protected void noteTime(){
        startTime = System.currentTimeMillis();
    }

    @AfterMethod(alwaysRun = true)
    protected void printTime(){
        double result = System.currentTimeMillis()-startTime;
        System.out.println(String.format("Completed in %s milliseconds (%s seconds)", result, result/1000));
    }

}
