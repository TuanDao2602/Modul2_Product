package ra.bussiness.design;

import ra.bussiness.entity.Catalog;

public interface ICatalog {
    boolean update(Catalog catalog);

    void searchByName (String name);
}
