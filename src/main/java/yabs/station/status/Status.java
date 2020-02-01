
package yabs.station.status;


@lombok.Data
@SuppressWarnings("unused")
public class Status {

    private yabs.station.status.Data data;
    private long lastUpdated;
    private long ttl;

}
