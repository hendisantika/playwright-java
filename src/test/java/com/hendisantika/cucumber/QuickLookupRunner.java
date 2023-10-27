package com.hendisantika.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * Project : playwright-java
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/27/23
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.hendisantika.stepdefinition"}
        , features = {"src/test/resources/features/QuickLookUp.feature"}
        , plugin = "json:target/cucumber-result/json/QuickLookUp.json"
)
public class QuickLookupRunner {
}
