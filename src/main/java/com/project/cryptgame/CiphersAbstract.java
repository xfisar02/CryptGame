package com.project.cryptgame;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author fisar
 */
public abstract class CiphersAbstract {

    String key;
    Resource res;
    String encrypedKey;

    public CiphersAbstract(Resource res) {
        this.res = res;
        setKey();
    }
    public void setKey(){
        int index = (int) Math.round(Math.random() * (res.cryptResource.size() - 1));
        key = res.cryptResource.get(index);
    }

    public abstract String task();

    public abstract String guess();

    public abstract String win();

    public abstract String hint();

    public abstract boolean solve(String answer);

    public abstract String encrypt();

    public abstract boolean insertAnswer(TextArea consoleOutTextArea, TextArea consoleInfTextArea, TextField consoleInTextField);

    public abstract void prepareOutArea(TextArea consoleOutTextArea);
}

