package org.solarsystem.web.view;

public class LandingView {
    public String getLandingPage() {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--#######-->",
                        indexSingleton.getLanding());
    }
}
