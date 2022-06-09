package com.testmasuk.testmasuktdi.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tennis_score")
@SequenceGenerator(
        name = "tennis_score_id_seq",
        sequenceName = "tennis_score_id_seq",
        allocationSize = 1,
        initialValue = 2)
public class TennisScore {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "tennis_score_id_seq")
    private BigDecimal id;

    @Column(name = "player_a")
    private Integer player_a;

    @Column(name = "player_b")
    private Integer player_b;
}