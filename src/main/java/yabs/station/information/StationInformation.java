package yabs.station.information;

import lombok.Data;

@Data
public class StationInformation {

    private String address;
    private long capacity;
    private double lat;
    private double lon;
    private String name;
    private String stationId;

}
