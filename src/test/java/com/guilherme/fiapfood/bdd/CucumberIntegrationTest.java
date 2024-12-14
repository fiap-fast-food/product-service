package com.guilherme.fiapfood.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.guilherme.fiapfood.bdd", // Pacote onde estão as classes Steps Definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Configuração de relatórios
)
public class CucumberIntegrationTest {

}
