package br.com.navita.commons.utils;

public abstract class GeralUtils {
    
    public static boolean isNullOrBlank(String valor) {
        return valor == null || valor.isBlank();
    }
}
