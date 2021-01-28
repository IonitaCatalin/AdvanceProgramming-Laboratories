package org.laborator;

import javax.print.Doc;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException, InvalidCatalogException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }
    public static Catalog load(String path) throws InvalidCatalogException, IOException, ClassNotFoundException {
        try (var ios = new ObjectInputStream(new FileInputStream(path))) {
            Catalog catalog = (Catalog) ios.readObject();
            return catalog;
        }

    }

    public static void view(Document document) throws RuntimeException, InvalidDocumentException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop=Desktop.getDesktop();
            String path = document.getLocation().trim().toLowerCase();
            if(path.startsWith("http://") || path.startsWith("https://"))
            {
                if(document.isLocationValidURL()) {
                    try {
                        desktop.browse(new URI(document.getLocation()));
                    } catch (URISyntaxException uriException) {
                        throw new InvalidDocumentException("URL catre resursa specificata este invalid!", uriException);

                    } catch (IOException ioException) {
                        throw new InvalidDocumentException("Resursa de la URL:"+document.getLocation()+"nu poate fi accesata",ioException);
                    }
                }
                else throw new InvalidDocumentException(document.getLocation());
            }
            else
            {
                if(document.existsLocalFile())
                {
                    try {
                        desktop.open(new File(document.getLocation()));
                    }
                    catch(IOException ioException)
                    {
                        throw new InvalidDocumentException("Resursa nu poate fi accesata,verificati accesibilitatea sa!",ioException);
                    }
                }
                else throw new InvalidDocumentException(document.getLocation(),document.getName());
            }

        }
        else throw new RuntimeException();
    }
}
