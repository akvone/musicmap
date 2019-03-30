package com.akvone.configuration;

import com.akvone.dao.LocationRepository;
import com.akvone.entity.LocationEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class ContextInitializer {

  private final LocationRepository locationRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationStarted() throws IOException {
    loadLocationsToDB();
  }

  private void loadLocationsToDB() throws IOException {
    // TODO
    byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/locations.json"));
    ObjectMapper mapper = new ObjectMapper();
    Locations locations = mapper.readValue(bytes, Locations.class);

    locations.getLocations().forEach(l -> {
      LocationEntity locationEntity = new LocationEntity();
      locationEntity.setId(l.getId());
      locationEntity.setName(l.getName());

      locationRepository.save(locationEntity);
    });
  }

  @Setter
  @Getter
  @NoArgsConstructor
  static class Locations {

    List<Location> locations;
  }

  @Setter
  @Getter
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class Location {

    private Long id;
    private String name;
  }
}
