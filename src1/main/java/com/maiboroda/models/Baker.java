package com.maiboroda.models;

import java.util.ArrayList;
import java.util.List;

public class Baker extends Student implements Parachutist {
    private List<String> specialties;
    private boolean parachuteLicense;

    public Baker(String lastName, String firstName, int course, String studentId, String birthDate) {
        super(lastName, firstName, course, studentId, birthDate);
        this.specialties = new ArrayList<>();
        this.parachuteLicense = false;
    }

    public String prepareCake(List<String> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Потрібно хоча б один інгредієнт!");
        }

        String cakeName = "Тістечко з " + String.join(", ", ingredients);
        specialties.add(cakeName);
        return cakeName + " готове!";
    }

    /**
     * Готує торт з декількох шарів
     */
    public String prepareLayeredCake(List<String> layers) {
        if (layers == null || layers.size() < 2) {
            throw new IllegalArgumentException("Торт повинен мати щонайменше 2 шари!");
        }

        String cakeName = layers.size() + "-шаровий торт";
        specialties.add(cakeName);
        return cakeName + " спечений!";
    }

    public List<String> getSpecialties() {
        return new ArrayList<>(specialties);
    }

    public int getSpecialtiesCount() {
        return specialties.size();
    }

    @Override
    public void jumpWithParachute(int altitude) {
        if (!parachuteLicense) {
            throw new IllegalStateException("Немає ліцензії на стрибки з парашутом!");
        }
        if (altitude < 1000) {
            throw new IllegalArgumentException("Мінімальна висота для стрибка - 1000 метрів!");
        }
        System.out.println(getFirstName() + " стрибає з висоти " + altitude + " метрів!");
    }

    @Override
    public boolean hasParachuteLicense() {
        return parachuteLicense;
    }

    public void obtainParachuteLicense() {
        this.parachuteLicense = true;
    }

    @Override
    public String toString() {
        return "Baker{" +
                "name='" + getFirstName() + " " + getLastName() + '\'' +
                ", course=" + getCourse() +
                ", specialties=" + specialties.size() +
                ", hasLicense=" + parachuteLicense +
                '}';
    }
}
