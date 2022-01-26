package com.example.covid_tracker;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Corona {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String combinedKey;
    Long active;
    Long recovered;
    Long confirmed;
    LocalDateTime lastUpdate;

    @Override
    public String toString() {
        return "Corona{" +
                "combinedKey='" + combinedKey + '\'' +
                ", active=" + active +
                ", recovered=" + recovered +
                ", confirmed=" + confirmed +
                ", lastUpdate=" + lastUpdate +
                '}';
    }


}
