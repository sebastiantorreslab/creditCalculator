package com.app.creditCalculator.views;



import com.vaadin.flow.component.applayout.AppLayout;

import com.vaadin.flow.component.html.*;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;


@Route("")
@PageTitle("CreditCaculator")
public class MainLayout extends AppLayout  {


        public MainLayout() {

            createHeader();
            createContent();


        }


    private void createHeader() {
            H1 title = new H1("Credit calculator APP");

            title.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

            RouterLink form = new RouterLink("Calculate", CreditForm.class);

        var header = new HorizontalLayout(new Nav(),title,form);
        header.setWidthFull();
        addToNavbar(header);

    }

    private void createContent() {

        H2 viewTitle = new H2("View title");
        Paragraph viewContent = new Paragraph("View content");

        Div content = new Div();
        content.add(viewTitle, viewContent);

        setContent(content);
    }
}



