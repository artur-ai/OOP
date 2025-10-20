package com.maiboroda.models;

import java.util.ArrayList;
import java.util.List;

class Entrepreneur extends Student implements Parachutist {
    private List<String> businesses;
    private double capital;
    private boolean parachuteLicense;

    public Entrepreneur(String lastName, String firstName, int course, String studentId, String birthDate) {
        super(lastName, firstName, course, studentId, birthDate);
        this.businesses = new ArrayList<>();
        this.capital = 0.0;
        this.parachuteLicense = false;
    }

    public void startBusiness(String businessName, double initialCapital) {
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва бізнесу не може бути порожньою!");
        }
        if (initialCapital < 0) {
            throw new IllegalArgumentException("Початковий капітал не може бути від'ємним!");
        }

        businesses.add(businessName);
        capital += initialCapital;
    }

    public void earnProfit(double profit) {
        if (profit < 0) {
            throw new IllegalArgumentException("Прибуток не може бути від'ємним!");
        }
        capital += profit;
    }

    public List<String> getBusinesses() {
        return new ArrayList<>(businesses);
    }

    public double getCapital() {
        return capital;
    }

    public int getBusinessCount() {
        return businesses.size();
    }

    @Override
    public void jumpWithParachute(int altitude) {
        if (!parachuteLicense) {
            throw new IllegalStateException("Немає ліцензії на стрибки з парашутом!");
        }
        if (altitude < 1000) {
            throw new IllegalArgumentException("Мінімальна висота для стрибка - 1000 метрів!");
        }
        System.out.println(getFirstName() + " стрибає з висоти " + altitude + " метрів (екстремальний підприємець)!");
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
        return "Entrepreneur{" +
                "name='" + getFirstName() + " " + getLastName() + '\'' +
                ", course=" + getCourse() +
                ", businesses=" + businesses.size() +
                ", capital=" + capital +
                ", hasLicense=" + parachuteLicense +
                '}';
    }
}
