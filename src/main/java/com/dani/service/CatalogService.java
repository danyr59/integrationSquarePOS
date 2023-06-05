
package com.dani.service;

import com.helpers.Utils;
import com.squareup.square.models.CatalogObject;
import java.util.List;


public interface CatalogService {
    public List<CatalogObject> getCatalog(Utils.TypeCatalog type);
    public void create_catalog();
}