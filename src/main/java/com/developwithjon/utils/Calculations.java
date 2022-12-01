package com.developwithjon.utils;
import com.developwithjon.Profile.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.formula.functions.Irr;
import org.apache.logging.log4j.*;

public class Calculations {

    public static double calculateAverage(ArrayList<String> marks) {
        Double sum = 0.0;
        if(!marks.isEmpty()) {
            for (String mark : marks) {
                sum += Double.parseDouble(mark);
            }
            return sum / marks.size();
        }
        return sum;
    }

    public static double calculateSum(ArrayList<String> marks){
        Double sum = 0.0;
        if(!marks.isEmpty()) {
            for (String mark : marks) {
                sum += Double.parseDouble(mark);
            }
            return sum;
        }
        return sum;
    }

    public static double calculateSumDouble(ArrayList<Double> marks){
        Double sum = 0.0;
        if(!marks.isEmpty()) {
            for (Double mark : marks) {
                sum += mark;
            }
            return sum;
        }
        return sum;
    }

    public static void calculateDebt(){
    }
    public static ArrayList calculateAllTax(Profile profile) {
        ArrayList<Double> taxArray = new ArrayList<>();
        Double taxValue = profile.getPropertyTax();
        taxArray.add(taxValue);
        int i = 9;
        do {
            taxValue = calculateCompound(taxValue, profile.getAnnualizedExpenseGrowth());
            taxArray.add(taxValue);
            i -= 1;
        }
            while (i>0);
        return taxArray;
    }

    public static ArrayList calculateAllInsurance(Profile profile) {
        ArrayList<Double> insuranceArray = new ArrayList<>();
        Double insuranceValue = profile.getInsurance();
        insuranceArray.add(insuranceValue);
        int i = 9;
        do {
            insuranceValue = calculateCompound(insuranceValue, profile.getAnnualizedExpenseGrowth());
            insuranceArray.add(insuranceValue);
            i -= 1;
        }
        while (i>0);
        return insuranceArray;
    }

    public static ArrayList calculateAllWater(Profile profile) {
        ArrayList<Double> waterArray = new ArrayList<>();
        Double waterValue = profile.getWater();
        waterArray.add(waterValue);
        int i = 9;
        do {
            waterValue = calculateCompound(waterValue, profile.getAnnualizedExpenseGrowth());
            waterArray.add(waterValue);
            i -= 1;
        }
        while (i>0);
        return waterArray;
    }
    public static ArrayList calculateAllElectricity(Profile profile) {
        ArrayList<Double> electricityArray = new ArrayList<>();
        Double electricityValue = profile.getElectric();
        electricityArray.add(electricityValue);
        int i = 9;
        do {
            electricityValue = calculateCompound(electricityValue, profile.getAnnualizedExpenseGrowth());
            electricityArray.add(electricityValue);
            i -= 1;
        }
        while (i>0);
        return electricityArray;
    }
    public static ArrayList calculateAllRM(Profile profile) {
        ArrayList<Double> rmArray = new ArrayList<>();
        Double rmValue = profile.getRepairsMaintenance() * profile.getPurchasePrice();
        rmArray.add(rmValue);
        int i = 9;
        do {
            rmValue = calculateCompound(rmValue, profile.getAnnualizedExpenseGrowth());
            rmArray.add(rmValue);
            i -= 1;
        }
        while (i>0);
        return rmArray;
    }
    public static ArrayList calculateAllVacancy(Profile profile) {
        ArrayList<Double> vacancyArray = new ArrayList<>();
        Double vacancyValue = profile.getVacancy() * profile.getGrossMonthlyIncome();
        vacancyArray.add(vacancyValue);
        int i = 9;
        do {
            vacancyArray.add(vacancyValue);
            i -= 1;
        }
        while (i>0);
        return vacancyArray;
    }
    public static ArrayList calculateAllRevenue(Profile profile) {
        ArrayList<Double> revenueArray = new ArrayList<>();
        Double revenueValue = profile.getGrossMonthlyIncome();
        revenueArray.add(revenueValue);
        int i = 9;
        do {
            revenueValue = calculateCompound(revenueValue, profile.getAnnualizedIncomeGrowth());
            revenueArray.add(revenueValue);
            i -= 1;
        }
        while (i>0);
        return revenueArray;
    }
    public static ArrayList calculateAllManagement(Profile profile) {
        ArrayList<Double> managementArray = new ArrayList<>();
        Double managementValue = profile.getManagement() * profile.getPurchasePrice();;
        managementArray.add(managementValue);
        int i = 9;
        do {
            managementValue = calculateCompound(managementValue, profile.getAnnualizedExpenseGrowth());
            managementArray.add(managementValue);
            i -= 1;
        }
        while (i>0);
        return managementArray;
    }

    public static ArrayList calculateAllNOI(Profile profile){
        ArrayList<Double> noiArray = new ArrayList<>();
        ArrayList<Double> totalExpenseArray = calculateAllTotalExpenses(profile);
        return noiArray;
    }

    public static Double calculateFinancing(Profile profile){
        Double financing = profile.getPurchasePrice() - profile.getDownPayment();
        return financing;
    }
    public static Double calculateUpfrontCost(Profile profile){
        Double upfrontCost = profile.getDownPayment() + profile.getRepairCost() + (profile.getPurchasePrice() * profile.getClosingCost()); // TODO: Add deal cost and reserves
        return upfrontCost;
    }
    public static Double calculateProjectedIncome(Profile profile){
        Double ProjectedIncome = profile.getGrossMonthlyIncome() * 12;
        return ProjectedIncome;
    }
    public static Double calculateVacancy(Profile profile){
        Double vacancyCost = profile.getGrossMonthlyIncome() * profile.getVacancy();
        return vacancyCost;
    }
    public static ArrayList<Double> calculateAllTotalExpenses(Profile profile) {
        int dispositionYear = 10;
        double sumExpenses;

        ArrayList<Double> taxArray = calculateAllTax(profile);
        ArrayList<Double> insuranceArray = calculateAllInsurance(profile);
        ArrayList<Double> waterArray = calculateAllWater(profile);
        ArrayList<Double> utilitiesArray = calculateAllElectricity(profile);
        ArrayList<Double> rmArray = calculateAllRM(profile);
        ArrayList<Double> managementArray = calculateAllManagement(profile);

        ArrayUtils arrayUtils = new ArrayUtils();
        ArrayList<Double> totalExpenseArray = new ArrayList();
        for (int i = 0; i < dispositionYear; i++) {
            sumExpenses = arrayUtils.getArrayValue(taxArray, i) +
                    arrayUtils.getArrayValue(insuranceArray, i) +
                    arrayUtils.getArrayValue(waterArray, i) +
                    arrayUtils.getArrayValue(utilitiesArray, i) +
                    arrayUtils.getArrayValue(rmArray, i) +
                    arrayUtils.getArrayValue(managementArray, i);
            totalExpenseArray.add(i, sumExpenses);
        }
        return totalExpenseArray;
    }

    public static double calculateTotalExpenses(Profile profile){
        Double totalExpenses = calculateAnnualizedMetric(profile.getInsurance()) +
                calculateAnnualizedMetric(profile.getPropertyTax()) +
                calculateAnnualizedMetric(profile.getCapitalEx()) +
                calculateAnnualizedMetric(profile.getRepairsMaintenance()) +
                calculateAnnualizedMetric(profile.getManagement()) +
                calculateAnnualizedMetric(profile.getElectric()) +
                calculateAnnualizedMetric(profile.getHOA()) +
                calculateAnnualizedMetric(profile.getGas());
        return totalExpenses;
    }
    public static Double calculateNOI(Profile profile){
        Double NOI = profile.getAfterRepairValue() - calculateAnnualizedMetric(calculateVacancy(profile)) - calculateTotalExpenses(profile);
        return NOI;
    }
    public static Double calculateNOIMargin(Profile profile) {
        Double NOIMargin = calculateNOI(profile) / calculateAnnualizedMetric(profile.getGrossMonthlyIncome());
        return NOIMargin;
    }

    public static Double calculateYear1Cap(Profile profile){
        Double yearOneCap = calculateNOI(profile) / profile.getPurchasePrice();
        return yearOneCap;
    }
    public static double calculateUnleveredIRR(Profile profile){
        double[] cashFlow = {-551900.0, 9584.0, 10570.7, 11596.2, 12661.8, 13769.0,	14919.2, 16113.9, 17354.6, 18642.9, 309986.7 };
        return new Irr().irr(cashFlow);
    }
    public static void calculateUnleveredProfit(){

    }
    public static void calculateUnleveredMoM(){
    }
    public static void calculateLeveredIRR(){
    }
    public static void calculateLeveredProfit(){
    }
    public static void calculateLeveredMoM(){
    }
    public static void calculateProjectedRevenue(){
    }
    public static double calculateCompound(double baseValue, float rate){
        Double compundOutput = baseValue * ( 1 + rate );
        return compundOutput;
    }
    public static double calculateMonthlyInterestPayment(Profile profile){
        Double interestPayment = profile.getInterestRate() * profile.getPurchasePrice();
        return interestPayment;
    }
    public static double calculateInitialExpenses(Profile profile){
        Double initialExpenses = profile.getDownPayment() + profile.getRepairCost();
        return initialExpenses;
    }

    public static void calculateAnnualExpenses(
            double taxes,
            double insurance,
            double water,
            double electric,
            double gas,
            double repairs,
            double capitalEx,
            double management,
            double HOA,
            double other){
        double[] expenseArray = new double[] {taxes, insurance, water, electric, gas, repairs, capitalEx, management, HOA, other};
        List<Double> expenseList = new ArrayList<>();
        ArrayList<Double> expenseArrayList = new ArrayList<>(expenseList);
        double totalExpenses = calculateSumDouble(expenseArrayList);

    }
    public static double calculateAnnualizedMetric(double amount){
        return amount * 12;
    }
    public static double calculateDollarAmount(float rate, double base){
        return rate * base;
    }
    public Calculations() {
    }

}
