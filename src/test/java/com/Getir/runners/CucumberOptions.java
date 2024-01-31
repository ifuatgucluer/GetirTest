package com.Getir.runners;

public @interface CucumberOptions {
    String[] plugin();

    boolean dryRun();

    String glue();

    String tags();
}
