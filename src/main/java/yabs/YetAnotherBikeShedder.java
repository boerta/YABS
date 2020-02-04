package yabs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import yabs.station.information.Information;
import yabs.station.information.StationInformation;
import yabs.station.status.StationStatus;
import yabs.station.status.Status;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class YetAnotherBikeShedder {
    public static void main(String... args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(YetAnotherBikeShedder.class, args);
        ctx.close();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(final RestTemplate restTemplate) {
        return args -> {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Client-Identifier", "boerta-yabs.YetAnotherBikeShedder");

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<Information> response = restTemplate.exchange("https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json", HttpMethod.GET, entity, Information.class);
            Information info = response.getBody();

            ResponseEntity<Status> response2 = restTemplate.exchange("https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json", HttpMethod.GET, entity, Status.class);
            Status status = response2.getBody();

            Map<String, StationStatus> statusMap =
                    status.getData().getStations().stream().collect(Collectors.toMap(StationStatus::getStationId, Function.identity()));

            for (StationInformation item : info.getData().getStations()) {
                printInfo(item, statusMap.get(item.getStationId()));
            }
        };
    }

    private void printInfo(StationInformation item, StationStatus stationStatus) {
        System.out.print(item.toString());
        if(stationStatus == null) {
            System.out.println("Mangler statusinformasjon!");
        } else {
            System.out.println(stationStatus.toString());
        }
    }

}
