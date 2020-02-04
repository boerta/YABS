
package yabs.station.status;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class StationStatus {


    private long isInstalled;
    private long isRenting;
    private long isReturning;
    private long lastReported;
    private long numBikesAvailable;
    private long numDocksAvailable;
    private String stationId;

    public String toString() {
        return "ledige sykler: " + numBikesAvailable +
        "\n\tledige låser: " + numDocksAvailable + "\n";
    }

}
