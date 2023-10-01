package com.example.diplom.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class Cargo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;

    private String consignee;
    private String shipper;
    private String consignment_note;
    private Double weight;
    private Integer number_of_packages;
    private String name;
    private Double price;
    private Double hs_code;
    private LocalDateTime date;
    private String truck_plate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String filename;






    public Cargo(String text, String consignee, String shipper, String consignment_note, Double weight, Integer number_of_packages, String name, Double price, Double hs_code, LocalDateTime date, String truck_plate, User user) {
        this.text = text;
        this.consignee = consignee;
        this.shipper = shipper;
        this.consignment_note = consignment_note;
        this.weight = weight;
        this.number_of_packages = number_of_packages;
        this.name = name;
        this.price = price;
        this.hs_code = hs_code;
        this.date = date;
        this.truck_plate = truck_plate;
        this.user = user;
    }
}