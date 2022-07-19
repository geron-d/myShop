package by.it.academy.comparators.product;

import by.it.academy.entities.Product;

import java.util.Comparator;

public class ProductIdDescComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return Math.toIntExact(o2.getId() - o1.getId());
    }

}
