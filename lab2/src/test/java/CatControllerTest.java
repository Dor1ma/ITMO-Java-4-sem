import org.core.dao.CatDAO;
import org.core.models.Cat;
import org.core.models.Owner;
import org.core.service.CatService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatControllerTest {
    private List<Cat> OwnersCats;
    private List<Cat> friends;
    @Test
    public void testGetCat() {
        CatDAO catDAO = Mockito.mock(CatDAO.class);
        Owner owner = new Owner("Test owner", new Date(), OwnersCats);

        Cat cat = new Cat("Test Name",
                new Date(),
                "No breed",
                "White",
                owner,
                friends);

        when(catDAO.get(1L)).thenReturn(cat);

        CatService catService = new CatService(catDAO);

        Cat result = catService.getCat(1L);
        assertEquals(cat, result);

        verify(catDAO, times(1)).get(1L);
    }

    @Test
    public void testSaveCat() {
        CatDAO catDAO = Mockito.mock(CatDAO.class);
        Owner owner = new Owner("Test owner", new Date(), OwnersCats);

        Cat cat = new Cat("Test Name",
                new Date(),
                "No breed",
                "White",
                owner,
                friends);

        CatService catService = new CatService(catDAO);

        catService.saveCat(cat);

        verify(catDAO, times(1)).save(cat);
    }

    @Test
    public void testUpdateCat() {
        CatDAO catDAO = Mockito.mock(CatDAO.class);
        Owner owner = new Owner("Test owner", new Date(), OwnersCats);

        Cat cat = new Cat("Test Name",
                new Date(),
                "No breed",
                "White",
                owner,
                friends);

        CatService catService = new CatService(catDAO);

        catService.updateCat(cat);

        verify(catDAO, times(1)).update(cat);
    }

    @Test
    public void testDeleteCat() {
        CatDAO catDAO = Mockito.mock(CatDAO.class);
        Owner owner = new Owner("Test owner", new Date(), OwnersCats);

        Cat cat = new Cat("Test Name",
                new Date(),
                "No breed",
                "White",
                owner,
                friends);

        CatService catService = new CatService(catDAO);

        catService.deleteCat(cat);

        verify(catDAO, times(1)).delete(cat);
    }
}
