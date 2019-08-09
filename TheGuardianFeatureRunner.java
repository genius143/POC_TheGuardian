package com.cucumber.framework.theGurdian.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "classpath:featurefile/theGuardian.feature" }, glue = {
		"classpath:com.cucumber.framework.theGuardian.stepdefinition",
		"classpath:com.cucumber.framework.helper" }, plugin = {"html:target/cucumber-html-report"}
,tags={ "@theGuardian,~@onGoogle"})
public class TheGuardianFeatureRunner extends AbstractTestNGCucumberTests {

}
