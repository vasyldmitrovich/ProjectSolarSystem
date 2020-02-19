package org.solarsystem.web.view;

public class CalculatorView {
    public String getCalculatorPage() {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--calculator-->",
                        indexSingleton.getCalculator());
}
}


