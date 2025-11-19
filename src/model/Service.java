package model;

public class Service {
    private Boolean wifi;
    private Boolean bad;
    private Boolean mad;

    public Service(Boolean wifi, Boolean bad, Boolean mad) {
        this.wifi = wifi;
        this.bad = bad;
        this.mad = mad;
    }

    public Boolean hasWifi() {
        return wifi;
    }

    public Boolean hasBad() {
        return bad;
    }

    public Boolean hasMad() {
        return mad;
    }

    @Override
    public String toString() {
        return "Service{" +
                "wifi=" + wifi +
                ", bad=" + bad +
                ", mad=" + mad +
                '}';
    }
}
