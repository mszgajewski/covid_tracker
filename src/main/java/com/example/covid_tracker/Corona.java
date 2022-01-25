package com.example.covid_tracker;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Corona {

    String combinedKey;
    Long active;
    Long recovered;
    Long confirmed;
    LocalDateTime lastUpdate;
}
