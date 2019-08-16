package view;

import model.language.ApplicationStrings;

/**
 *
 */
public abstract class PageController extends GUIImpl {

    /**
     * the translation method.
     * @param t - the translation class
     */
    public abstract void translate(ApplicationStrings t);

}
