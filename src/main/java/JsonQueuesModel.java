import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayDeque;

public class JsonQueuesModel {

    @JsonProperty(value="minutesQueue")
    private ArrayDeque <Integer> minutesQueue;
    @JsonProperty(value="fiveMinutesQueue")
    private ArrayDeque <Integer> fiveMinutesQueue;
    @JsonProperty(value="hoursQueue")
    private ArrayDeque <Integer> hoursQueue;
    @JsonProperty(value="mainQueue")
    private ArrayDeque <Integer> mainQueue;

    public JsonQueuesModel(ArrayDeque <Integer> mainQueue, ArrayDeque <Integer> minutesQueue, ArrayDeque <Integer> fiveMinutesQueue, ArrayDeque <Integer> hoursQueue){
        this.minutesQueue=minutesQueue;
        this.fiveMinutesQueue=fiveMinutesQueue;
        this.hoursQueue=hoursQueue;
        this.mainQueue=mainQueue;
    }

    public ArrayDeque<Integer> getMainQueue() {
        return mainQueue;
    }

    public void setMainQueue(ArrayDeque<Integer> mainQueue) {
        this.mainQueue = mainQueue;
    }

    public ArrayDeque<Integer> getMinutesQueue() {
        return minutesQueue;
    }

    public void setMinutesQueue(ArrayDeque<Integer> minutesQueue) {
        this.minutesQueue = minutesQueue;
    }

    public ArrayDeque<Integer> getFiveMinutesQueue() {
        return fiveMinutesQueue;
    }

    public void setFiveMinutesQueue(ArrayDeque<Integer> fiveMinutesQueue) {
        this.fiveMinutesQueue = fiveMinutesQueue;
    }

    public ArrayDeque<Integer> getHoursQueue() {
        return hoursQueue;
    }

    public void setHoursQueue(ArrayDeque<Integer> hoursQueue) {
        this.hoursQueue = hoursQueue;
    }

}
