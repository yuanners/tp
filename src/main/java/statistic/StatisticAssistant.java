package statistic;

import ui.StatisticUi;

public class StatisticAssistant {

    StatisticUi ui = new StatisticUi();
    public StatisticAssistant(){
        promptReportType();
        promptSalesMode();
        promptRankMode();
        promptDateType();
        promptYearEntry();
        promptDateRangeEntry();
    }

    public void promptReportType(){
        System.out.println("Select report type: ");
        System.out.println("1. Rank");
        System.out.println("2. Sales");
    }

    public void promptSalesMode(){
        System.out.println("Select sales mode: ");
        System.out.println("1. Daily");
        System.out.println("2. Monthly");
    }

    public void promptRankMode(){
        System.out.println("Select rank mode: ");
        System.out.println("1. Sales");
        System.out.println("2. Popular");
    }

    public void promptDateType() {
        System.out.println("Select date type: ");
        System.out.println("1. Year");
        System.out.println("2. Date range");
    }

    public void promptYearEntry(){
        System.out.println("Enter year of report you like to generate: ");
    }
    public void promptDateRangeEntry(){
        System.out.println("Enter the date range for the report you like to generate ");
        System.out.println("Enter starting date: ");
        System.out.println("Enter ending date: ");
    }
}
