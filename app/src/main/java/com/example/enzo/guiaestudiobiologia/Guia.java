package com.example.enzo.guiaestudiobiologia;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 27/05/2017.
 */

public class Guia {

    public String description_guia;
    public String relevant_fact_1;
    public String relevant_fact_2;
    public String image_guia;

    /**
     * Constructor
     */
    public Guia() {}

    /**
     * Constructor with parameters
     * @param description_guia
     * @param relevant_fact_1
     * @param relevant_fact_2
     * @param image_guia
     */
    public Guia(String description_guia, String relevant_fact_1,String relevant_fact_2, String image_guia) {
        this.description_guia = description_guia;
        this.relevant_fact_1 = relevant_fact_1;
        this.relevant_fact_2 = relevant_fact_2;
        this.image_guia = image_guia;
    }

    /**
     * GET AND SET
     * @return
     */
    public String getDescription_guia() {
        return description_guia;
    }

    public void setDescription_guia(String description_guia) {
        this.description_guia = description_guia;
    }

    public String getRelevant_fact_1() {
        return relevant_fact_1;
    }

    public void setRelevant_fact_1(String relevant_fact_1) {
        this.relevant_fact_1 = relevant_fact_1;
    }

    public String getRelevant_fact_2() {
        return relevant_fact_2;
    }

    public void setRelevant_fact_2(String relevant_fact_2) {
        this.relevant_fact_2 = relevant_fact_2;
    }

    public String getImage_guia() {
        return image_guia;
    }

    public void setImage_guia(String image_guia) {
        this.image_guia = image_guia;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description_guia", description_guia);
        result.put("relevant_fact_1", relevant_fact_1);
        result.put("image_guia", image_guia);

        return result;
    }

}
