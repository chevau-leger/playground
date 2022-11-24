package lvd.excel;

public class KpiValueRange {

    private String beginTime;

    private String endTime;

    private String maxValue;

    private String minValue;

    public KpiValueRange() {
    }

    public KpiValueRange(String beginTime, String endTime, String maxValue, String minValue) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }
}
