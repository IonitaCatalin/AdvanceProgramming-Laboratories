package org.laborator;

public class InvalidDocumentException extends Exception {
    public InvalidDocumentException(String path,String name)
    {
        super("Resursa locala "+name+" de la calea "+path+" nu exista sau nu poate fi accesata!");
    }
    public InvalidDocumentException(String url)
    {
        super("Resursa de la adresa "+url+" nu poate fi accesata!");
    }

    public InvalidDocumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
