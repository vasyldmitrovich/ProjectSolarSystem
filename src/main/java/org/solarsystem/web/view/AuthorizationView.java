package org.solarsystem.web.view;

public class AuthorizationView {
    public String getAuthorizationPage() {
        IndexSingleton indexSingleton = IndexSingleton.getInstance();
        return indexSingleton.getIndex()
                .replace("<!--authorization-->",
                        indexSingleton.getAuthorization());
    }
}
