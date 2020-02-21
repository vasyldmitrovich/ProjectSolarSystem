package org.solarsystem.web.view;

public class FeedbackView {
    public String getFeedbackPage() {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--FeedBack-->",
                        indexSingleton.getFeedback());
    }
}
