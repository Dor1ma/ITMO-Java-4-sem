import org.core.models.Cat;
import org.core.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.core.models.Owner;
import org.core.dao.OwnerDAO;

import java.util.Date;
import java.util.List;

public class OwnerControllerTest {
    private List<Cat> cats;
    @Test
    public void testGetOwner() {
        OwnerDAO ownerDAO = Mockito.mock(OwnerDAO.class);
        OwnerService ownerService = new OwnerService(ownerDAO);

        Owner owner = new Owner("Test Owner", new Date(), cats);

        when(ownerDAO.get(1L)).thenReturn(owner);

        Owner result = ownerService.getOwner(1L);
        assertEquals(owner, result);

        verify(ownerDAO, times(1)).get(1L);
    }

    @Test
    public void testSaveOwner() {
        OwnerDAO ownerDAO = Mockito.mock(OwnerDAO.class);
        OwnerService ownerService = new OwnerService(ownerDAO);

        Owner owner = new Owner("Test Owner", new Date(), cats);

        when(ownerDAO.get(1L)).thenReturn(owner);

        ownerService.saveOwner(owner);

        verify(ownerDAO, times(1)).save(owner);
    }

    @Test
    public void testUpdateOwner() {
        OwnerDAO ownerDAO = Mockito.mock(OwnerDAO.class);
        OwnerService ownerService = new OwnerService(ownerDAO);

        Owner owner = new Owner("Test Owner", new Date(), cats);

        when(ownerDAO.get(1L)).thenReturn(owner);

        ownerService.updateOwner(owner);

        verify(ownerDAO, times(1)).update(owner);
    }

    @Test
    public void testDeleteOwner() {
        OwnerDAO ownerDAO = Mockito.mock(OwnerDAO.class);
        OwnerService ownerService = new OwnerService(ownerDAO);

        Owner owner = new Owner("Test Owner", new Date(), cats);

        when(ownerDAO.get(1L)).thenReturn(owner);

        ownerService.deleteOwner(owner);

        verify(ownerDAO, times(1)).delete(owner);
    }
}
