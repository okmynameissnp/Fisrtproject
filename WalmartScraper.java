package snp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class WalmartScraper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.walmart.com/browse/electronics/dell-gaming-laptops/3944_3951_7052607_1849032_4519159";
        Document document = Jsoup.connect(url).get();

        Elements products = document.select(".search-product-result");

        FileWriter csvWriter = new FileWriter("walmart_dell_gaming_laptops_top10.csv");
        csvWriter.append("Product Name,Product Price,Item Number,Model Number,Product Category,Product Description\n");

        for (int i = 0; i < 10; i++) {
            Element product = products.get(i);

            Element productNameElement = product.select(".search-result-product-title").first();
            String productName = productNameElement.text();

            Element priceElement = product.select(".search-result-price").first();
            String price = priceElement.text();

            Element itemNumberElement = product.select(".product-product-code").first();
            String itemNumber = itemNumberElement.text();

            Element modelNumberElement = product.select(".product-model-number").first();
            String modelNumber = modelNumberElement.text();

            Element productCategoryElement = product.select(".breadcrumb-link").last();
            String productCategory = productCategoryElement.text();

            Element productDescriptionElement = product.select(".product-short-description").first();
            String productDescription = productDescriptionElement.text();

            csvWriter.append(productName + "," + price + "," + itemNumber + "," + modelNumber + "," + productCategory + "," + productDescription + "\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
