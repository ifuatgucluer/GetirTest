package com.Getir.runners;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target"},
        features = "src/test/resources/features",
        glue = "com/sites/step_defs",

        dryRun = false,

        tags = "@all"
)
public class CukesRunner {
}



