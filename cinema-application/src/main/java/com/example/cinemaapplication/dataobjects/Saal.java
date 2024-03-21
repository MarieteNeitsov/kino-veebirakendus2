package com.example.cinemaapplication.dataobjects;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity
@Table(name="Saalid")
public class Saal {
    @Id
    @GeneratedValue
    private int id;
    private String nimi;

    @Transient
    private final int istekohtadeArv = 135;
    @Transient
    private final int dridadeArv = 9;
    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL)
    private final List<Istekoht> istekohad = new ArrayList<>();
    public Saal( String nimi) {
        this.id = id;
        this.nimi = nimi;

    }

    public Saal() {

    }

    private void lisaIstekohad(){
        int[] hõivatudKohad = genereeriHõivatudKohad();
        int reaCounter =1;

        for (int i = 1; i <= istekohtadeArv; i++) {
            boolean vaba = true;
            if (Arrays.asList(hõivatudKohad).contains(i)) vaba = false;
            istekohad.add(new Istekoht(reaCounter,i,vaba,this));

            if(i % 15 == 0) reaCounter++;
        }
    }
    private int[] genereeriHõivatudKohad(){

        int protsentHõivatud = 30;
        int arvHõivatud = (int) Math.round(istekohtadeArv * (protsentHõivatud / 100));
        int[] hõivatudKohad = new int[arvHõivatud];

        for (int i = 0; i < arvHõivatud; i++) {
            int hõivatudKohaNr;

            while(true) {
                hõivatudKohaNr = (int) Math.round(Math.random() * istekohtadeArv +1);

                if(!Arrays.asList(hõivatudKohad).contains(hõivatudKohaNr)) break;
            }

            hõivatudKohad[i] =hõivatudKohaNr;
        }
        return hõivatudKohad;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public int getIstekohtadeArv() {
        return istekohtadeArv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}

