package com.example.covid_tracker;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)

public class Corona implements Serializable       {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String combinedKey;
    Long active;
    Long activeChanges;
    Long recovered;
    Long recoveredChanges;
    Long confirmed;
    Long confirmedChanges;
    LocalDateTime lastUpdate;

}
