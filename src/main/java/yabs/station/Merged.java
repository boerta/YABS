package yabs.station;

import lombok.Data;
import yabs.station.information.StationInformation;
import yabs.station.status.StationStatus;

@Data
public class Merged {
    private String stationId;
    private String name;
    private String address;
    private long capacity;
    private long numBikesAvailable;
    private long numDocksAvailable;

    public static Merged merge(StationInformation info, StationStatus status) {
        assert info.getStationId().equals(status.getStationId());
        Merged m = new Merged();
        m.stationId = info.getStationId();
        m.name = info.getName();
        m.address = info.getAddress();
        m.capacity = info.getCapacity();
        m.numBikesAvailable = status.getNumBikesAvailable();
        m.numDocksAvailable = status.getNumDocksAvailable();

        return m;
    }

    public String toString() {
        return name + ", " + address +
                "\n\tkapasitet: " + capacity +
                "\n\tledige sykler: " + numBikesAvailable +
                "\n\tledige låser: " + numDocksAvailable + "\n";
    }
}
