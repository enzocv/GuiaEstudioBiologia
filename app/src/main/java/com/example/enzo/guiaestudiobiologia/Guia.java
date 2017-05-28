package com.example.enzo.guiaestudiobiologia;

/**
 * Created by Asus on 27/05/2017.
 */

public class Guia {

    public String description_guia;
    public String relevant_fact_1;
    public String image_guia;

    /**
     * Constructor
     */
    public Guia() {}

    /**
     * Constructor with parameters
     * @param description_guia
     * @param relevant_fact_1
     * @param image_guia
     */
    public Guia(String description_guia, String relevant_fact_1, String image_guia) {
        this.description_guia = description_guia;
        this.relevant_fact_1 = relevant_fact_1;
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

    public String getImage_guia() {
        return image_guia;
    }

    public void setImage_guia(String image_guia) {
        this.image_guia = image_guia;
    }
}
