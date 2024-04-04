package org.core.service;

import org.core.dao.CatDAO;
import org.core.models.Cat;

public class CatService {
    private final CatDAO catDAO;

    public CatService(CatDAO catDAO) {
        this.catDAO = catDAO;
    }

    public Cat getCat(Long id) {
        return catDAO.get(id);
    }

    public void saveCat(Cat cat) {
        catDAO.save(cat);
    }

    public void updateCat(Cat cat) {
        catDAO.update(cat);
    }

    public void deleteCat(Cat cat) {
        catDAO.delete(cat);
    }
}
