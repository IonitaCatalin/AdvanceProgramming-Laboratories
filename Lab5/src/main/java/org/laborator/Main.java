package org.laborator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main
{
    private void testCreateSave() {
        Catalog catalog=new Catalog("Completely New Catalog","D:\\catalog.ser");
        Document onlineDocument=new Document("java1","https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        Document localDocument=new Document("text1","D:\\ipsum.txt");
        onlineDocument.addTag("type","Slides");
        localDocument.addTag("type","Text");
        catalog.add(onlineDocument);
        catalog.add(localDocument);
        try
        {
            CatalogUtil.save(catalog);
        }
        catch(IOException ioException)
        {
            System.out.println("IO:"+ioException.getMessage());
        } catch (InvalidCatalogException e) {
            System.out.printf("Catalog specificat este invalid si de aceea nu poate fi serializat!");
        }

    }
    private void testLoadView()  {
        try {

            Catalog catalog = CatalogUtil.load("D:\\catalog.ser");
            Document document = catalog.findByName("java1");
            CatalogUtil.view(document);
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InvalidDocumentException invalidDocException) {
            invalidDocException.printStackTrace();
        }
    }
    public static void main(String[]args)  {
        Main app=new Main();
        app.testCreateSave();
        app.testLoadView();
    }
}