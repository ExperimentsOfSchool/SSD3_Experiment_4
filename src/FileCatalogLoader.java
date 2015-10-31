import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Lawrence on 15/10/31.
 */
public class FileCatalogLoader implements CatalogLoader {
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

    public FileCatalogLoader() {

    }
    @Override
    public Catalog loadCatalog(String fileName) throws IOException, DataFormatException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );
        String line;
        while((line = bufferedReader.readLine()) != null) {
            if(line.startsWith("Coffee")) {
                readCoffee(line);
            } else if(line.startsWith("Brewer")) {
                readCoffeeBrewer(line);
            } else if(line.startsWith("Product")) {
                readProduct(line);
            }
        }
        Catalog catalog = new Catalog();
        bufferedReader.close();
        return null;
    }
}