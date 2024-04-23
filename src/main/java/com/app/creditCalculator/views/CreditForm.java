package com.app.creditCalculator.views;


import com.app.creditCalculator.domain.Credit;
import com.app.creditCalculator.service.CreditServiceImpl;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Route("form")
@PageTitle("Calculate credit")
public class CreditForm extends FormLayout {

    ArrayList<NumberField> numberFormFields = new ArrayList<>();
    String[] creditTypes = {
            "Personal loan",
            "Mortgage loan",
            "Car loan / Auto loan",
            "Student loan",
            "Business loan",
            "Credit card",
            "Line of credit",
            "Home equity loan",
            "Payday loan",
            "Secured loan",
            "Unsecured loan",
            "Installment loan",
            "Revolving credit"
    };
    String[] labels = {
            "interestRate",
            "amount",
            "installmentCount",
            "monthlyPayment",
            "futureValue",
            "interestValue"
    };
    private final CreditServiceImpl service;

    public CreditForm(CreditServiceImpl service) {
        this.service = service;
        createForm();

    }

    private void createForm() {

        FormLayout creditForm = new FormLayout();
        setFormLabels(labels);
        Credit credit = generateCredit();
        Credit credit1 = setElementsForm(creditForm,credit);
        setButtons(creditForm, credit1);
        add(creditForm);

    }

    private ComboBox<String> configureComboBox(FormLayout form,String... options) {
        ComboBox<String> creditField = new ComboBox<>("Credit Types");
        creditField.setItems(options);
        form.add(creditField );
        return creditField;
    }

    private void  setFormLabels(String... labels) {
        for (String label : labels) {
            numberFormFields.add(new NumberField(label));
            numberFormFields.forEach(this::add);
        }

    }

    private Credit setElementsForm(FormLayout form, Credit credit) {
        ComboBox<String> creditValues = configureComboBox(form, creditTypes);

        creditValues.addValueChangeListener(event -> {
            String cv = event.getValue();
            credit.setCreditType(cv);
        });
        numberFormFields.forEach(field -> field.addValueChangeListener(event -> {
            switch (field.getLabel()){
                case "interestRate":{
                    credit.setInterestRate(event.getValue());
                }
                case "amount":{
                    credit.setAmount(event.getValue());
                }
                case "installmentCount":{
                    credit.setLoanTerm(event.getValue().intValue());
                }
                default:

            }

        }));

        return credit;


    }

    private void setButtons(FormLayout form, Credit credit) {

        Button button1 = new Button("Calculate");
        Button button2 = new Button("Cancel");

        button1.addClickListener(event -> setNewCredit(credit));
        button2.addClickListener(this::cancel);
        button1.addClickShortcut(Key.ENTER);
        form.add(button1, button2);


    }




    private Credit setCalculatedValues(Credit credit) {
     try{
         Double futureValue = service.calculateFutureValue(credit);
         Double payment = service.calculatePayment(credit);
         credit.setFutureValue(futureValue);
         credit.setMonthlyPayment(payment);
         return credit;
     }catch (Exception e){

         e.printStackTrace();
     }
        return credit;
    }

    private void cancel(ClickEvent<Button> event) {
        event.getSource().setText("Canceled!");
    }

    private Credit generateCredit(){
        return new Credit();
    }

    private void setNewCredit(Credit credit) {

        Credit credit1 = setCalculatedValues(credit);

        if(credit1 != null){
            service.create(credit1);
        }


    }


}
