import org.testng.annotations.Test;

public class RunMethods extends BaseTest {

    /**
     * We can use before and after methods to measure execution time from the moment of execution start to the finish.
     */
    @Test
    public void showDaysForQueue(){
        ClockUtils.clock(30);
    }

    @Test
    public void showStateOfQueue(){
        ClockUtils.clock(30, 325);
    }

}
