package com.mongs.premierzone.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="prem_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "nation")
    private String nation;
    @Column(name = "pos")
    private String pos;
    @Column(name = "age")
    private Integer age;
    @Column(name = "mp")
    private Integer mp;
    @Column(name = "starts")
    private Integer starts;
    @Column(name = "min")
    private Double min;
    @Column(name = "gls")
    private Double gls;
    @Column(name = "ast")
    private Double ast;
    @Column(name = "pk")
    private Double pk;
    @Column(name = "crdy")
    private Double crdy;
    @Column(name = "crdr")
    private Double crdr;
    @Column(name = "xg")
    private Double xg;
    @Column(name = "xag")
    private Double xag;
    @Column(name = "team")
    private String team;


}
