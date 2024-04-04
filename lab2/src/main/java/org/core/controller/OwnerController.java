package org.core.controller;

import org.core.models.Owner;
import org.core.service.OwnerService;

public class OwnerController {
    private OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Owner GetOwner(Long id) {
        return ownerService.getOwner(id);
    }

    public void SaveOwner(Owner owner) {
        ownerService.saveOwner(owner);
    }

    public void UpdateOwner(Owner owner) {
        ownerService.updateOwner(owner);
    }

    public void DeleteOwner(Owner owner) {
        ownerService.deleteOwner(owner);
    }
}
