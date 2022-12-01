package com.developwithjon.GUI;

import com.developwithjon.Profile.Profile;
import com.developwithjon.Webscrape.MortageRateScrape;
import com.developwithjon.Webscrape.PropertyTax;
import com.developwithjon.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

public class Gui {
    static StringParser parser = new StringParser();
    private static JFrame frame;
    private static JTextArea textArea;
    private static JTextArea textArea2;
    private static JTextArea textArea3;
    private static JTextArea textArea4;
    private static JTextArea textArea5;
    private static JScrollPane panel;
    private static JScrollPane panel2;
    private static JScrollPane panel3;
    private static JScrollPane panel4;
    private static JScrollPane panel5;
    private static JTabbedPane tabbedPane;
    private static String[] frequencies;
    private static SpinnerModel spinnerModel1;
    private static SpinnerModel spinnerModel2;
    private static JButton scrapeWebsiteButton;
    private static GuiFunctions functions;
    private String URLField;


    public static void main(String[] args) {
        Gui gui = new Gui();
    }

    public Gui() {
        frequencies = new String[]{"annual", "monthly"};
        spinnerModel1 = new SpinnerNumberModel(0.0, 0.0, null, 1.0);
        spinnerModel2 = new SpinnerNumberModel(0.0, 0.0, null, 1.0);

        // Initialize Frame
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Initialize First Tab
        textArea = new JTextArea(600, 600);
        textArea.setBackground(Color.LIGHT_GRAY);

        textArea2 = new JTextArea(600, 600);
        textArea2.setBackground(Color.LIGHT_GRAY);

        textArea3 = new JTextArea(600, 600);
        textArea3.setBackground(Color.LIGHT_GRAY);

        textArea4 = new JTextArea(600, 600);
        textArea4.setBackground(Color.LIGHT_GRAY);

        textArea5 = new JTextArea(600, 600);
        textArea5.setBackground(Color.LIGHT_GRAY);

        JLabel topLabel = new JLabel("House Analysis");
        topLabel.setBounds(250, 0, 200, 25);
        textArea.add(topLabel);

        JLabel URLLabel = new JLabel("Listing Link (Optional)");
        URLLabel.setBounds(20, 20, 200, 25);
        textArea.add(URLLabel);

        JTextField URLField = new JTextField(20);
        URLField.setBounds(20, 45, 200, 25);
        textArea.add(URLField);

        scrapeWebsiteButton = new JButton("Analyze listing");
        scrapeWebsiteButton.setBounds(350, 55, 200, 25);
        scrapeWebsiteButton.setBackground(Color.BLACK);
        scrapeWebsiteButton.setForeground(Color.BLUE);
        textArea.add(scrapeWebsiteButton);

        JLabel streetAddressLabel = new JLabel("Street Address");
        streetAddressLabel.setBounds(20, 150, 200, 25);
        textArea.add(streetAddressLabel);

        JTextField streetAddressField = new JTextField(20);
        streetAddressField.setBounds(20, 190, 200, 25);
        textArea.add(streetAddressField);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(20, 230, 140, 25);
        textArea.add(cityLabel);

        JTextField cityField = new JTextField(20);
        cityField.setBounds(20, 250, 140, 25);
        textArea.add(cityField);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(200, 230, 140, 25);
        textArea.add(stateLabel);

        Constants constants = new Constants();
        String[] statesList = constants.statesList();
        String[] countyStatesList = constants.countyStates();

        JComboBox<String> stateCombo = new JComboBox(statesList);
        stateCombo.setBounds(200, 250, 140, 25);
        textArea.add(stateCombo);

        JLabel zipLabel = new JLabel("Zip");
        zipLabel.setBounds(370, 230, 140, 25);
        textArea.add(zipLabel);

        JTextField zipField = new JTextField(20);
        zipField.setBounds(370, 250, 140, 25);
        textArea.add(zipField);

        JLabel OptionalRoomFeaturesLabel = new JLabel("Optional Features");
        OptionalRoomFeaturesLabel.setBounds(200, 320, 140, 25);
        textArea.add(OptionalRoomFeaturesLabel);

        JLabel bedroomLabel = new JLabel("bedroom");
        bedroomLabel.setBounds(85, 380, 140, 25);
        textArea.add(bedroomLabel);

        JTextField bedroomField = new JTextField(20);
        bedroomField.setBounds(85, 405, 90, 25);
        textArea.add(bedroomField);

        JLabel bathroomLabel = new JLabel("bathroom");
        bathroomLabel.setBounds(200, 380, 140, 25);
        textArea.add(bathroomLabel);

        JTextField bathroomField = new JTextField(20);
        bathroomField.setBounds(200, 405, 90, 25);
        textArea.add(bathroomField);

        JLabel sqftLabel = new JLabel("sq Ft");
        sqftLabel.setBounds(330, 380, 140, 25);
        textArea.add(sqftLabel);

        JTextField sqftField = new JTextField(20);
        sqftField.setBounds(330, 405, 90, 25);
        textArea.add(sqftField);

        JLabel yearBuiltLabel = new JLabel("Year Built");
        yearBuiltLabel.setBounds(120, 440, 140, 25);
        textArea.add(yearBuiltLabel);

        JTextField yearBuiltField = new JTextField(20);
        yearBuiltField.setBounds(120, 460, 100, 25);
        textArea.add(yearBuiltField);

        JButton pageOneButton = new JButton("Next");
        pageOneButton.setBounds(500, 1000, 80, 25);
        pageOneButton.setBackground(Color.BLACK);
        pageOneButton.setForeground(Color.BLUE);
        pageOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textArea.add(pageOneButton);

        //Second Tab
        JLabel purchaseTitleLabel = new JLabel("Purchase Details");
        purchaseTitleLabel.setBounds(240, 20, 200, 25);
        textArea2.add(purchaseTitleLabel);

        JLabel purchasePriceLabel = new JLabel("Purchase Price");
        purchasePriceLabel.setBounds(20, 50, 120, 25);
        textArea2.add(purchasePriceLabel);

        JTextField purchasePriceField = new JTextField(20);
        purchasePriceField.setBounds(20, 70, 120, 25);
        textArea2.add(purchasePriceField);

        JLabel aRVLabel = new JLabel("After Repair Value");
        aRVLabel.setBounds(20, 100, 120, 25);
        textArea2.add(aRVLabel);

        JTextField aRVField = new JTextField(20);
        aRVField.setBounds(20, 120, 120, 25);
        textArea2.add(aRVField);

        JLabel repairCostLabel = new JLabel("Repair Cost");
        repairCostLabel.setBounds(200, 100, 120, 25);
        textArea2.add(repairCostLabel);

        JTextField repairCostField = new JTextField(20);
        repairCostField.setBounds(200, 120, 120, 25);
        textArea2.add(repairCostField);

        JLabel propertyValueGrowthLabel = new JLabel("Annual property value growth (%)");
        propertyValueGrowthLabel.setBounds(20, 160, 300, 25);
        textArea2.add(propertyValueGrowthLabel);

        JTextField propertyValueGrowthField = new JTextField(20);
        propertyValueGrowthField.setBounds(20, 180, 120, 25);
        textArea2.add(propertyValueGrowthField);

        JLabel closingCostLabel = new JLabel("Anticipated closing cost (%)");
        closingCostLabel.setBounds(20, 220, 300, 25);
        textArea2.add(closingCostLabel);

        JTextField closingCostField = new JTextField(20);
        closingCostField.setBounds(20, 240, 120, 25);
        textArea2.add(closingCostField);

        // Third Tab
        JLabel loanTitleLabel = new JLabel("Loan Details");
        loanTitleLabel.setBounds(240, 5, 150, 25);
        textArea3.add(loanTitleLabel);

        JLabel cashPurchaseLabel = new JLabel("Cash Purchase?");
        cashPurchaseLabel.setBounds(240, 50, 150, 25);
        textArea3.add(cashPurchaseLabel);

        JCheckBox cashPurchaseCheckBox = new JCheckBox();
        cashPurchaseCheckBox.setBounds(210, 50, 25, 25);
        textArea3.add(cashPurchaseCheckBox);

        JLabel downPaymentLabel = new JLabel("Down Payment");
        downPaymentLabel.setBounds(50, 90, 150, 25);
        textArea3.add(downPaymentLabel);

        JTextField downPaymentField = new JTextField();
        downPaymentField.setBounds(50, 110, 100, 25);
        textArea3.add(downPaymentField);

        JLabel interestRateLabel = new JLabel("Interest Rate (%)");
        interestRateLabel.setBounds(50, 150, 150, 25);
        textArea3.add(interestRateLabel);

        JTextField interestRateField = new JTextField();
        interestRateField.setBounds(50, 170, 100, 25);
        textArea3.add(interestRateField);

        JLabel loanTermLabel = new JLabel("Loan Term");
        loanTermLabel.setBounds(200, 150, 150, 25);
        textArea3.add(loanTermLabel);

        JTextField loanTermField = new JTextField();
        loanTermField.setBounds(200, 170, 100, 25);
        textArea3.add(loanTermField);

        // Fourth Page

        JLabel rentalTitleLabel = new JLabel("Rental Income Details");
        rentalTitleLabel.setBounds(240, 5, 150, 25);
        textArea4.add(rentalTitleLabel);

        JLabel monthlyIncomeLabel = new JLabel("Gross Monthly Income");
        monthlyIncomeLabel.setBounds(20, 50, 200, 25);
        textArea4.add(monthlyIncomeLabel);

        JTextField monthlyIncomeField = new JTextField(20);
        monthlyIncomeField.setBounds(20, 70, 120, 25);
        textArea4.add(monthlyIncomeField);

        JLabel incomeGrowthLabel = new JLabel("Annualized Income Growth");
        incomeGrowthLabel.setBounds(20, 100, 300, 25);
        textArea4.add(incomeGrowthLabel);

        JTextField incomeGrowthField = new JTextField("%", 20);
        incomeGrowthField.setBounds(20, 120, 120, 25);
        textArea4.add(incomeGrowthField);

        JLabel expenseGrowthLabel = new JLabel("Annualized Expense Growth");
        expenseGrowthLabel.setBounds(260, 100, 300, 25);
        textArea4.add(expenseGrowthLabel);

        JTextField expenseGrowthField = new JTextField("%", 20);
        expenseGrowthField.setBounds(260, 120, 120, 25);
        textArea4.add(expenseGrowthField);

        // Fifth Page
        JLabel expenseTitleLabel = new JLabel("Expense Details");
        expenseTitleLabel.setBounds(240, 5, 150, 25);
        textArea5.add(expenseTitleLabel);

        JLabel propertyTaxesLabel = new JLabel("Property Taxes");
        propertyTaxesLabel.setBounds(20, 50, 120, 25);
        textArea5.add(propertyTaxesLabel);

        JSpinner propertyTaxSpinner = new JSpinner(spinnerModel1);
        propertyTaxSpinner.setBounds(20, 70, 100, 25);
        propertyTaxSpinner.setValue(0.0);
        textArea5.add(propertyTaxSpinner);

        JComboBox<String> propertyTaxesFrequencyCombo = new JComboBox(frequencies);
        propertyTaxesFrequencyCombo.setBounds(120, 70, 110, 25);
        textArea5.add(propertyTaxesFrequencyCombo);

        JLabel insuranceLabel = new JLabel("Insurance");
        insuranceLabel.setBounds(20, 100, 120, 25);
        textArea5.add(insuranceLabel);

        JSpinner insuranceSpinner = new JSpinner(spinnerModel2);
        insuranceSpinner.setBounds(20, 120, 100, 25);
        insuranceSpinner.setValue(0.0);
        textArea5.add(insuranceSpinner);

        JComboBox<String> insuranceFrequencyCombo = new JComboBox(frequencies);
        insuranceFrequencyCombo.setBounds(120, 120, 110, 25);
        textArea5.add(insuranceFrequencyCombo);

        JLabel sectionDescriptionLabel = new JLabel("Below fees are expressed as a percent of monthly income");
        sectionDescriptionLabel.setBounds(20, 155, 400, 25);
        textArea5.add(sectionDescriptionLabel);

        JLabel repairesMaintenanceLabel = new JLabel("Repairs & maintenance");
        repairesMaintenanceLabel.setBounds(20, 210, 200, 25);
        textArea5.add(repairesMaintenanceLabel);

        JTextField repairesMaintenanceField = new JTextField("%", 20);
        repairesMaintenanceField.setBounds(20, 230, 120, 25);
        textArea5.add(repairesMaintenanceField);

        JLabel vacancyLabel = new JLabel("Vacancy");
        vacancyLabel.setBounds(20, 260, 300, 25);
        textArea5.add(vacancyLabel);

        JTextField vacancyField = new JTextField("%", 20);
        vacancyField.setBounds(20, 280, 120, 25);
        textArea5.add(vacancyField);

        JLabel capitalExpendituresLabel = new JLabel("Capital Expenditures");
        capitalExpendituresLabel.setBounds(20, 310, 300, 25);
        textArea5.add(capitalExpendituresLabel);

        JTextField capitalExpendituresField = new JTextField("%", 20);
        capitalExpendituresField.setBounds(20, 330, 120, 25);
        textArea5.add(capitalExpendituresField);

        JLabel managementFeesLabel = new JLabel("Management Fees");
        managementFeesLabel.setBounds(20, 360, 300, 25);
        textArea5.add(managementFeesLabel);

        JTextField managementFeesField = new JTextField("%", 20);
        managementFeesField.setBounds(20, 380, 120, 25);
        textArea5.add(managementFeesField);

        JLabel section2DescriptionLabel = new JLabel("Below fees are monthly utilies");
        section2DescriptionLabel.setBounds(20, 420, 400, 25);
        textArea5.add(section2DescriptionLabel);

        int shift = 60;

        JLabel gasLabel = new JLabel("Gas");
        gasLabel.setBounds(20, 400 + shift, 300, 25);
        textArea5.add(gasLabel);

        JTextField gasField = new JTextField("$", 20);
        gasField.setBounds(20, 420 + shift, 120, 25);
        textArea5.add(gasField);

        JLabel electricityLabel = new JLabel("electricity");
        electricityLabel.setBounds(20, 450 + shift, 300, 25);
        textArea5.add(electricityLabel);

        JTextField electricityField = new JTextField("$", 20);
        electricityField.setBounds(20, 470 + shift, 120, 25);
        textArea5.add(electricityField);

        JLabel waterSewageLabel = new JLabel("Water & Sewage");
        waterSewageLabel.setBounds(20, 500 + shift, 300, 25);
        textArea5.add(waterSewageLabel);

        JTextField waterSewageField = new JTextField("$", 20);
        waterSewageField.setBounds(20, 520 + shift, 120, 25);
        textArea5.add(waterSewageField);

        JLabel hOAFeesLabel = new JLabel("HOA fees");
        hOAFeesLabel.setBounds(20, 550 + shift, 300, 25);
        textArea5.add(hOAFeesLabel);

        JTextField hOAFeesField = new JTextField("$", 20);
        hOAFeesField.setBounds(20, 570 + shift, 120, 25);
        textArea5.add(hOAFeesField);

        JLabel otherLabel = new JLabel("other");
        otherLabel.setBounds(20, 600 + shift, 300, 25);
        textArea5.add(otherLabel);

        JTextField otherField = new JTextField("$", 20);
        otherField.setBounds(20, 620 + shift, 120, 25);
        textArea5.add(otherField);

        JButton evaluateButton = new JButton("evaluate");
        evaluateButton.setBounds(400, 700 + shift, 120, 25);
        evaluateButton.setBackground(Color.BLACK);
        evaluateButton.setBackground(Color.BLUE);
        textArea5.add(evaluateButton);

        // Button Action Listener Logic

        scrapeWebsiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functions = new GuiFunctions();
                RegexParser parser = new RegexParser();

                if (e.getSource() == scrapeWebsiteButton) {
                    String URL = URLField.getText();
                    String domain = parser.getDomain(URL, "[a-zA-Z0-9]*\\.[a-zA-Z0-9]*"); // TODO: add feature to web scrape based on domain
                    try {
                        Convert converter = new Convert();

                        ArrayList scrapedOutput = functions.webScrapeFunction(URL);

                        String scrapedFeaturesString = new Gson().toJson(scrapedOutput.get(0));
                        ArrayList<String> scrapedTableData = (ArrayList) scrapedOutput.get(2);
                        HashMap scrapedFundamentals = (HashMap) scrapedOutput.get(1);
                        HashMap scrapedPropertyFeatures = (HashMap) scrapedOutput.get(3);

                        JSONObject scrapedAddress = new JSONObject(new Gson().toJson(scrapedOutput.get(1)));
                        JSONObject scrapedFeatures = new JSONObject(scrapedFeaturesString);

                        HashMap<String, String> address = new StringParser().parseAddress(scrapedAddress.get("address").toString());
                        String streetAddress = address.get("streetAddress").toString();
                        String city = address.get("city").toString();
                        String state = address.get("state").toString();
                        String zip = address.get("zip").toString();
                        String yearBuilt = scrapedPropertyFeatures.get("yearBuilt").toString();
                        String offerPrice = scrapedFundamentals.get("offerPrice").toString();
                        String county = scrapedFeatures.get("County").toString();
                        String totalRent = scrapedPropertyFeatures.get("totalRent").toString();

                        if (Arrays.asList(countyStatesList).contains(state)){
                            System.out.println("adding ` County`");
                            county += " County";
                        }
                        PropertyTax propertyTaxAPI = new PropertyTax();
                        MortageRateScrape mortageRateScrape = new MortageRateScrape();
                        String mortgageRate = Float.toString(mortageRateScrape.mortgageRateAPIRequest());

                        JSONObject propertyTaxInfo = propertyTaxAPI.requestPropertyTaxInfo(state);
                        float propertyTax = converter.convertToDecimal(propertyTaxInfo.getJSONObject(county).getFloat("Average Effective Property Tax Rate")); // TODO: Florida has `county` in all country names
                        System.out.printf("propertytax = %f", propertyTax);
                        int propertyTaxAmount = Math.round(propertyTax * Float.parseFloat(offerPrice)); //TODO: test

                        //Page One Setter
                        setField("Bedrooms", scrapedFeatures, bedroomField);
                        setField("Bathrooms", scrapedFeatures, bathroomField);
                        setField(streetAddress, streetAddressField);
                        setField(city, cityField);
                        setField(state, stateCombo);
                        setField(zip, zipField);
                        setField(yearBuilt, yearBuiltField);

                        // Page Two Setter
                        setField(offerPrice, purchasePriceField);
                        setField("3", propertyValueGrowthField);

                        // Page Three Setter
                        setField(mortgageRate, interestRateField);
                        setField("30", loanTermField);
                        setField(Double.toString(Double.parseDouble(offerPrice)*.2), downPaymentField);

                        // Page Four Setter

                        setField(totalRent, monthlyIncomeField);
                        setField("3", incomeGrowthField);
                        setField("3", expenseGrowthField);

                        // Page Five Setter
                        setField(propertyTaxAmount, propertyTaxSpinner);

                    } catch (Exception er) {
                        if (domain == null){
                            System.out.println("domain is null");
                        }
                        er.printStackTrace();
                    }
                }
            }
        });
        evaluateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == evaluateButton){
                    Profile profile = new Profile();


                    collectStreetAddress(profile, streetAddressField);
                    collectCity(profile, cityField);
                    collectState(profile, stateCombo);
                    collectZip(profile, zipField);
                    collectSqrt(profile, sqftField);
                    collectYearBuilt(profile, yearBuiltField);
                    collectBathroom(profile, bathroomField);
                    collectBedroom(profile, bedroomField);

                    collectPurchasePrice(profile, purchasePriceField);
                    collectAfterRepairValue(profile, aRVField);
                    collectRepairCost(profile, repairCostField);
                    collectAnnualizedPropertyValueGrowth(profile, propertyValueGrowthField);
                    collectAnticipatedClosingCost(profile, closingCostField);

                    collectCashPurchase(profile, cashPurchaseCheckBox);
                    collectDownPayment(profile, downPaymentField);
                    collectInterestRate(profile, interestRateField);
                    collectLoanTerm(profile, loanTermField);

                    collectGrossMonthlyIncome(profile, monthlyIncomeField);
                    collectAnnualizedIncomeGrowth(profile, incomeGrowthField);
                    collectAnnualizedExpenseGrowth(profile, expenseGrowthField);

                    collectPropertyTax(profile, propertyTaxSpinner);
                    collectPropertyTaxFrequency(profile, propertyTaxesFrequencyCombo);
                    collectInsurance(profile, insuranceSpinner);
                    collectInsuranceFrequency(profile, insuranceFrequencyCombo);
                    collectRepairsMaintenance(profile, repairesMaintenanceField);
                    collectVacancy(profile, vacancyField);
                    collectCapitalEx(profile, capitalExpendituresField);
                    collectManagement(profile, managementFeesField);
                    collectGas(profile, gasField);
                    collectElectric(profile, electricityField);
                    collectWater(profile, waterSewageField);
                    collectHOA(profile, hOAFeesField);
                    collectOther(profile, otherField);

                    Calculations calculations = new Calculations();

                    System.out.printf("capex: %s\n", profile.getCapitalEx());
                    System.out.printf("income g: %s\n", profile.getAnnualizedIncomeGrowth());
                    System.out.printf("insurance: %s\n", profile.getInsurance());
                    System.out.printf("NOI: %s\n", calculations.calculateAllNOI(profile));
                    System.out.printf("TaxArray: %s\n", calculations.calculateAllTax(profile).toString());
                    System.out.printf("ElectricityArray: %s\n", calculations.calculateAllElectricity(profile).toString());
                    System.out.printf("InsuranceArray: %s\n", calculations.calculateAllInsurance(profile).toString());
                    System.out.printf("WaterArray: %s\n", calculations.calculateAllWater(profile).toString());
                }
            }});

        // Frame Construction
        tabbedPane = new JTabbedPane();

        panel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel3 = new JScrollPane(textArea3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel4 = new JScrollPane(textArea4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel5 = new JScrollPane(textArea5, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabbedPane.add(panel);
        tabbedPane.add(panel2);
        tabbedPane.add(panel3);
        tabbedPane.add(panel4);
        tabbedPane.add(panel5);

        frame.add(tabbedPane);
        frame.setVisible(true);

    }
    public static void setField(String key, JSONObject object, JTextField field){
        if (object.has(key)){
            field.setText(object.get(key).toString());
        }
    }
    public static void setField(String value, JTextField field){
        field.setText(value);
    }
    public static void setField(String value, JComboBox field){
        field.setSelectedItem(value);
    }
    public static void setField(Float value, JSpinner field){
        field.setValue(value);
    }
    public static void setField(Integer value, JSpinner field){
        field.setValue(value);
    }
    public static void collectStreetAddress(Profile profile, JTextField field){
        profile.setStreetAddress(field.getText());
    }
    public static void collectCity(Profile profile, JTextField field){
        profile.setCity(field.getText());
    }
    public static void collectState(Profile profile, JComboBox field){
        profile.setState(field.getSelectedItem().toString());
    }
    public static void collectZip(Profile profile, JTextField field){
        profile.setZip(field.getText());
    }
    public static void collectBedroom(Profile profile, JTextField field) {
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0";
        }
        profile.setBedroom(Integer.parseInt(fieldResult));
    }
    public static void collectBathroom(Profile profile, JTextField field) {
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0";
        }
        profile.setBathroom(Integer.parseInt(fieldResult));
    }
    public static void collectSqrt(Profile profile, JTextField field) {
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setSqft(Double.parseDouble(fieldResult));
    }
    public static void collectYearBuilt(Profile profile, JTextField field) {
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0";
        }
        profile.setYearBuilt(Integer.parseInt(fieldResult));
    }
    public static void collectPurchasePrice(Profile profile, JTextField field) {
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setPurchasePrice(Double.parseDouble(fieldResult));
    }
    public static void collectAfterRepairValue(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setAfterRepairValue(Double.parseDouble(fieldResult));
    }
    public static void collectAnnualizedPropertyValueGrowth(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setAnnualPropertyValueGrowth(Float.parseFloat(fieldResult));
    }
    public static void collectAnticipatedClosingCost(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "6.0";
        }
        profile.setClosingCost(Double.parseDouble(fieldResult));
    }
    public static void collectRepairCost(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setRepairCost(Double.parseDouble(fieldResult));
    }
    public static void collectDownPayment(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setDownPayment(Double.parseDouble(fieldResult));
    }
    public static void collectInterestRate(Profile profile, JTextField field){
        String fieldResult = field.getText();
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setInterestRate(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectLoanTerm(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0";
        }
        profile.setLoanTerm(Integer.parseInt(fieldResult));
    }
    public static void collectCashPurchase(Profile profile, JCheckBox field){
        profile.setCashPurchase(field.isSelected());
    }
    public static void collectGrossMonthlyIncome(Profile profile, JTextField field){
        String fieldResult = field.getText();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setGrossMonthlyIncome(Double.parseDouble(fieldResult));
    }
    public static void collectAnnualizedIncomeGrowth(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setAnnualizedIncomeGrowth(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectAnnualizedExpenseGrowth(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setAnnualizedExpenseGrowth(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectPropertyTax(Profile profile, JSpinner field){
        String fieldResult = field.getValue().toString();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setPropertyTax(Double.parseDouble(fieldResult));
    }
    public static void collectPropertyTaxFrequency(Profile profile, JComboBox field){
        profile.setPropertyTaxFrequency(field.getSelectedItem().toString());
    }
    public static void collectInsurance(Profile profile, JSpinner field){
        String fieldResult = field.getValue().toString();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            System.out.println("empty");
            fieldResult = "0.0";
        }
        profile.setInsurance(Double.parseDouble(fieldResult));
    }
    public static void collectInsuranceFrequency(Profile profile, JComboBox field){
        profile.setInsuranceFrequency(field.getSelectedItem().toString());
    }
    public static void collectRepairsMaintenance(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setRepairsMaintenance(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectVacancy(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setVacancy(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectCapitalEx(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setCapitalEx(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectManagement(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "%");
        Convert convert = new Convert();
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setManagement(convert.convertToDecimal(Float.parseFloat(fieldResult)));
    }
    public static void collectGas(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "$");
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setGas(Double.parseDouble(fieldResult));
    }
    public static void collectElectric(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "$");
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setElectric(Double.parseDouble(fieldResult));
    }
    public static void collectWater(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "$");
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setWater(Double.parseDouble(fieldResult));
    }
    public static void collectHOA(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "$");
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setHOA(Double.parseDouble(fieldResult));
    }
    public static void collectOther(Profile profile, JTextField field){
        String fieldResult = parser.removeFix(field.getText(), "$");
        if (fieldResult.trim().isEmpty() || fieldResult == null) {
            fieldResult = "0.0";
        }
        profile.setOther(Double.parseDouble(fieldResult));
    }
}

