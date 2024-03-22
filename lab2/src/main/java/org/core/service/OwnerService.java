package org.core.service;

import org.core.dao.OwnerDAO;
import org.core.models.Owner;

public class OwnerService {
    private final OwnerDAO ownerDAO;

    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public Owner getOwner(Long id) {
        return ownerDAO.get(id);
    }

    public void saveOwner(Owner owner) {
        ownerDAO.save(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDAO.update(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDAO.delete(owner);
    }
}
