package br.cefetmg.address.view;

import java.io.InputStream;
import java.io.OutputStream;

@FunctionalInterface
public interface ViewMethod {

    void handle(InputStream in, OutputStream out) throws ViewException;

}
