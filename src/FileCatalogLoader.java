import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Lawrence on 15/10/31.
 */
public class FileCatalogLoader implements CatalogLoader {
    public static String path = "./data";
    public FileCatalogLoader() {

    }
    private Product readProduct (String line) throws DataFormatException {
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        String code, description;
        double price;
        stringTokenizer.nextToken();
        code = stringTokenizer.nextToken();
        description = stringTokenizer.nextToken();
        price = Double.parseDouble(stringTokenizer.nextToken());
        return new Product(code, description, price);
    }
    private Coffee readCoffee(String line) throws DataFormatException {
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        String code, description, origin, roast, flavor, aroma, acidity, body;
        double price;
        stringTokenizer.nextToken();
        code = stringTokenizer.nextToken();
        description = stringTokenizer.nextToken();
        price = Double.parseDouble(stringTokenizer.nextToken());
        origin = stringTokenizer.nextToken();
        roast = stringTokenizer.nextToken();
        flavor = stringTokenizer.nextToken();
        aroma = stringTokenizer.nextToken();
        acidity = stringTokenizer.nextToken();
        body = stringTokenizer.nextToken();
        return new Coffee(code, description, price, origin, roast, flavor, aroma, acidity, body);
    }
    private CoffeeBrewer readCoffeeBrewer(String line) throws DataFormatException{
        StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
        String code, description, model, waterSupply;
        double price;
        int numberOfCups;
        stringTokenizer.nextToken();
        code = stringTokenizer.nextToken();
        description = stringTokenizer.nextToken();
        price = Double.parseDouble(stringTokenizer.nextToken());
        model = stringTokenizer.nextToken();
        waterSupply = stringTokenizer.nextToken();
        numberOfCups = Integer.parseInt(stringTokenizer.nextToken());
        return new CoffeeBrewer(code, description, price, model, waterSupply, numberOfCups);
    }
    @Override
    public Catalog loadCatalog(String fileName) throws IOException, DataFormatException {
        File file = new File(path, fileName);
        Catalog catalog = new Catalog();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );
        try {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(line.startsWith("Coffee")) {
                    catalog.addProduct(readCoffee(line));
                } else if(line.startsWith("Brewer")) {
                    catalog.addProduct(readCoffeeBrewer(line));
                } else if(line.startsWith("Product")) {
                    catalog.addProduct(readProduct(line));
                }
            }
        } catch (Exception e) {
            //System.err.print(e.toString());
            throw e;
        } finally {
            bufferedReader.close();
        }
        bufferedReader.close();
        return catalog;
    }
}