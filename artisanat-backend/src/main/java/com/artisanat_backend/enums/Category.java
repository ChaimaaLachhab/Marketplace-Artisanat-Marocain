package com.artisanat_backend.enums;

import lombok.*;

@Getter
public enum Category {
    WALL_ART(Collection.HOME_DECOR), RUGS(Collection.HOME_DECOR), VASES(Collection.HOME_DECOR),
    CANDLES(Collection.HOME_DECOR), LANTERNS(Collection.HOME_DECOR),

    JEWELRY(Collection.ACCESSORIES), BAGS(Collection.ACCESSORIES), SCARVES(Collection.ACCESSORIES),
    BELTS(Collection.ACCESSORIES), HATS(Collection.ACCESSORIES),

    DRESSES(Collection.CLOTHING), SHIRTS(Collection.CLOTHING), PANTS(Collection.CLOTHING),
    JACKETS(Collection.CLOTHING), KAFTANS(Collection.CLOTHING),

    PLATES(Collection.KITCHENWARE), BOWLS(Collection.KITCHENWARE), TEAPOTS(Collection.KITCHENWARE),
    CUTLERY(Collection.KITCHENWARE), GLASSWARE(Collection.KITCHENWARE),

    TABLES(Collection.FURNITURE), CHAIRS(Collection.FURNITURE), CABINETS(Collection.FURNITURE),
    SOFAS(Collection.FURNITURE), BEDS(Collection.FURNITURE);

    private final Collection collection;

    Category(Collection collection) {
        this.collection = collection;
    }
}