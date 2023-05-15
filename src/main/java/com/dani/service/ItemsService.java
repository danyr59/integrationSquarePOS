
package com.dani.service;

import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.ListCatalogResponse;
import java.util.List;


public interface ItemsService {
    public List<CatalogObject> getCatalog();
    public void create_catalog();
}