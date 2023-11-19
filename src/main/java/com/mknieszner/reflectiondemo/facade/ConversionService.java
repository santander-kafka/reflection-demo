package com.mknieszner.reflectiondemo.facade;

import com.mknieszner.reflectiondemo.drools.DroolsBeanFactory;
import com.mknieszner.reflectiondemo.input.CollateralDto;
import com.mknieszner.reflectiondemo.input.CustomerDto;
import com.mknieszner.reflectiondemo.input.InputWrapper;
import com.mknieszner.reflectiondemo.input.ProductDto;
import com.mknieszner.reflectiondemo.xml.DocumentInput;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;

@Slf4j
public class ConversionService {

    public DocumentInput getXml(InputWrapper input) {
        DocumentInput document = new DocumentInput();

        this.applyInitialData(document);

        input.getCustomers().forEach(customer -> {

            this.applyCustomerData(document, customer);

            customer.getProducts().forEach(product -> {
                this.applyProductData(document, product.getProduct());
                product.getCollaterals().forEach(collateral -> this.applyCollateralData(document, collateral));
            });

        });

        return document;
    }

    private void applyInitialData(DocumentInput document) {
        KieSession kieSession = this.getInitialSession();
        kieSession.setGlobal("document", document);
        kieSession.insert(log);
        kieSession.fireAllRules();
    }

    private void applyCustomerData(DocumentInput document, CustomerDto customer) {
        KieSession kieSession = this.getCustomerSession();
        kieSession.setGlobal("document", document);
        kieSession.insert(customer);
        kieSession.insert(log);
        kieSession.fireAllRules();
    }

    private void applyProductData(DocumentInput document, ProductDto product) {
        KieSession kieSession = this.getProductSession(product.getType());
        kieSession.setGlobal("document", document);
        kieSession.insert(product);
        kieSession.insert(log);
        kieSession.fireAllRules();
    }

    private void applyCollateralData(DocumentInput document, CollateralDto collateral) {
        KieSession kieSession = this.getProductSession(collateral.getType());
        kieSession.setGlobal("document", document);
        kieSession.insert(collateral);
        kieSession.insert(log);
        kieSession.fireAllRules();
    }

    private KieSession getInitialSession() {
        return new DroolsBeanFactory().getKieSession("rules/initial/Initial.drl");
    }

    private KieSession getCustomerSession() {
        return new DroolsBeanFactory().getKieSession("rules/customer/Customer.drl");
    }

    private KieSession getProductSession(String type) {
        return new DroolsBeanFactory().getKieSession("rules/product/Product" + type + ".drl");
    }

    private KieSession getCollateralSession(String type) {
        return new DroolsBeanFactory().getKieSession("rules/collateral/Collateral" + type + ".drl");
    }
}
