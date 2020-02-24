package org.solarsystem.web.view;

public class AdditionalView {
    public String getAdditionalPage() {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--Additional-->",
                        indexSingleton.getAdditional());
    }
}
