package com.dani.service;

import com.helpers.ClientSquare;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.BatchUpsertCatalogObjectsRequest;
import com.squareup.square.models.CatalogCategory;
import com.squareup.square.models.CatalogItem;
import com.squareup.square.models.CatalogItemVariation;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CatalogObjectBatch;
import com.squareup.square.models.CatalogTax;
import com.squareup.square.models.Money;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CatalogServiceImpl implements CatalogService {

    public CatalogApi catalog;
    
    public CatalogServiceImpl(){
        this.catalog = ClientSquare.client.getCatalogApi();
    }

    @Override
    public List<CatalogObject> getCatalog() {
        
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

    public void  create_catalog() {
        LinkedList<String> taxIds = new LinkedList<>();
        taxIds.add("#SalesTax");

        Money priceMoney = new Money.Builder()
                .amount(150L)
                .currency("USD")
                .build();

        CatalogItemVariation itemVariationData = new CatalogItemVariation.Builder()
                .itemId("#Tea")
                .name("Mug")
                .pricingType("FIXED_PRICING")
                .priceMoney(priceMoney)
                .build();

        CatalogObject catalogObject1 = new CatalogObject.Builder("ITEM_VARIATION", "#Tea_Mug")
                .presentAtAllLocations(true)
                .itemVariationData(itemVariationData)
                .build();

        LinkedList<CatalogObject> variations = new LinkedList<>();
        variations.add(catalogObject1);

        CatalogItem itemData = new CatalogItem.Builder()
                .name("Tea")
                .description("Hot Leaf Juice")
                .categoryId("#Beverages")
                .taxIds(taxIds)
                .variations(variations)
                .build();

        CatalogObject catalogObject = new CatalogObject.Builder("ITEM", "#Tea")
                .presentAtAllLocations(true)
                .itemData(itemData)
                .build();

        LinkedList<String> taxIds1 = new LinkedList<>();
        taxIds1.add("#SalesTax");

        Money priceMoney1 = new Money.Builder()
                .amount(250L)
                .currency("USD")
                .build();

        CatalogItemVariation itemVariationData1 = new CatalogItemVariation.Builder()
                .itemId("#Coffee")
                .name("Regular")
                .pricingType("FIXED_PRICING")
                .priceMoney(priceMoney1)
                .build();

        CatalogObject catalogObject3 = new CatalogObject.Builder("ITEM_VARIATION", "#Coffee_Regular")
                .presentAtAllLocations(true)
                .itemVariationData(itemVariationData1)
                .build();

        Money priceMoney2 = new Money.Builder()
                .amount(350L)
                .currency("USD")
                .build();

        CatalogItemVariation itemVariationData2 = new CatalogItemVariation.Builder()
                .itemId("#Coffee")
                .name("Large")
                .pricingType("FIXED_PRICING")
                .priceMoney(priceMoney2)
                .build();

        CatalogObject catalogObject4 = new CatalogObject.Builder("ITEM_VARIATION", "#Coffee_Large")
                .presentAtAllLocations(true)
                .itemVariationData(itemVariationData2)
                .build();

        LinkedList<CatalogObject> variations1 = new LinkedList<>();
        variations1.add(catalogObject3);
        variations1.add(catalogObject4);

        CatalogItem itemData1 = new CatalogItem.Builder()
                .name("Coffee")
                .description("Hot Bean Juice")
                .categoryId("#Beverages")
                .taxIds(taxIds1)
                .variations(variations1)
                .build();

        CatalogObject catalogObject2 = new CatalogObject.Builder("ITEM", "#Coffee")
                .presentAtAllLocations(true)
                .itemData(itemData1)
                .build();

        CatalogCategory categoryData = new CatalogCategory.Builder()
                .name("Beverages")
                .build();

        CatalogObject catalogObject5 = new CatalogObject.Builder("CATEGORY", "#Beverages")
                .presentAtAllLocations(true)
                .categoryData(categoryData)
                .build();

        CatalogTax taxData = new CatalogTax.Builder()
                .name("Sales Tax")
                .calculationPhase("TAX_SUBTOTAL_PHASE")
                .inclusionType("ADDITIVE")
                .percentage("5.0")
                .appliesToCustomAmounts(true)
                .enabled(true)
                .build();

        CatalogObject catalogObject6 = new CatalogObject.Builder("TAX", "#SalesTax")
                .presentAtAllLocations(true)
                .taxData(taxData)
                .build();

        LinkedList<CatalogObject> objects = new LinkedList<>();
        objects.add(catalogObject);
        objects.add(catalogObject2);
        objects.add(catalogObject5);
        objects.add(catalogObject6);

        CatalogObjectBatch catalogObjectBatch = new CatalogObjectBatch.Builder(objects)
                .build();

        LinkedList<CatalogObjectBatch> batches = new LinkedList<>();
        batches.add(catalogObjectBatch);

        BatchUpsertCatalogObjectsRequest body = new BatchUpsertCatalogObjectsRequest.Builder("asdfasdf1234addfse", batches)
                .build();

        catalog.batchUpsertCatalogObjectsAsync(body)
                .thenAccept(result -> {
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });

    }
}
