package org.core.controller;

import org.core.models.Cat;
import org.core.service.CatService;

public class CatController {
    private CatService catService;
    public CatController(CatService catService) {
        this.catService = catService;
    }

    public Cat GetCat(long id) {
        return catService.getCat(id);
    }

    public void SaveCat(Cat cat) {
        catService.saveCat(cat);
    }

    public void UpdateCat(Cat cat) {
        catService.updateCat(cat);
    }

    public void DeleteCat(Cat cat) {
        catService.deleteCat(cat);
    }
}
