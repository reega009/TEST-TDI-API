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
@Table(name = "tennis")
@SequenceGenerator(
        name = "tennis_id_seq",
        sequenceName = "tennis_id_seq",
        allocationSize = 1,
        initialValue = 2)
public class Tennis {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "tennis_id_seq")
    private BigDecimal id;

    @Column(name = "id_set_1")
    private BigDecimal id_set_1;

    @Column(name = "id_set_2")
    private BigDecimal id_set_2;

    @Column(name = "id_set_3")
    private BigDecimal id_set_3;

    @Column(name = "id_skor")
    private BigDecimal id_skor;

    @Column(name = "n_player_a")
    private String n_player_a;

    @Column(name = "n_player_b")
    private String n_player_b;

    @Column(name = "skor_player_a")
    private Integer skor_player_a;

    @Column(name = "skor_player_b")
    private Integer skor_player_b;
}