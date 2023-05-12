package com.dani.service;

import com.helpers.ClientSquare;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.Customer;
import com.squareup.square.models.ListCatalogResponse;
import java.util.ArrayList;
import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    CatalogApi catalog;

    @Override
    public List<CatalogObject> getCatalog() {
        this.catalog/*.customers_api*/ = ClientSquare.client.getCatalogApi();
        // System.out.println(this.customer.customers_api);

        List<CatalogObject> catalogo = new ArrayList<>();
        this.catalog./*.customers_api.*/listCatalogAsync(null, null, null).thenAccept(result -> {
            // TODO success callback handler

            List<CatalogObject> aux = result.getObjects();
            for (CatalogObject item : aux) {
                catalogo.add(item);
            }

        }).exceptionally(exception -> {
            // TODO failure callback handler
            exception.printStackTrace();
            return null;
        }).join();
        return catalogo;
    }
}
