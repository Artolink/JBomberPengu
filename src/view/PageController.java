package view;

import model.language.ApplicationStrings;

/**
 *
 */
public abstract class PageController extends GUIImpl {

    /**
     * the translation method.
     * @param translator - the translation class
     */
    public abstract void translate(ApplicationStrings translator);

}
