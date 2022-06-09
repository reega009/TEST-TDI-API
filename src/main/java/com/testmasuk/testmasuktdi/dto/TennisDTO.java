package com.testmasuk.testmasuktdi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TennisDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tennis {
        private BigDecimal id;
        private BigDecimal id_set_1;
        private BigDecimal id_set_2;
        private BigDecimal id_set_3;
        private BigDecimal id_skor;
        private String n_player_a;
        private String n_player_b;
        private Integer scoreSementaraPlayerA;
        private Integer scoreSementaraPlayerB;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataTennis {
        private BigDecimal id;
        private String n_player_a;
        private String n_player_b;
        private Integer skor_player_a;
        private Integer skor_player_b;
        private Integer scoreSementaraPlayerA;
        private Integer scoreSementaraPlayerB;
        private Integer Set1A;
        private Integer Set1B;
        private Integer Set2A;
        private Integer Set2B;
        private Integer Set3A;
        private Integer Set3B;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NilaiAwal {
        private BigDecimal id;
        private String n_player_a;
        private String n_player_b;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateSkorSementara {
        private Integer playerA;
        private Integer playerB;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateSet {
        private BigDecimal id;
        private Integer playerA;
        private Integer playerB;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateSkor {
        private BigDecimal id;
        private Integer skor_player_a;
        private Integer skor_player_b;
    }



}
