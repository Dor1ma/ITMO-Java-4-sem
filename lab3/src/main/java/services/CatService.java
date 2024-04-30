package services;

import dto.CatDto;
import org.core.models.Cat;
import org.core.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CatRepository;
import repository.OwnerRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CatService(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    public CatDto getCat(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found"));
        return convertToDto(cat);
    }

    public List<CatDto> getAllCats() {
        List<Cat> cats = catRepository.findAll();
        return cats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CatDto createCat(CatDto catDto) {
        Cat cat = convertToEntity(catDto);
        Cat savedCat = catRepository.save(cat);
        return convertToDto(savedCat);
    }

    public CatDto updateCat(Long id, CatDto catDto) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found"));
        // update cat fields here
        Cat updatedCat = catRepository.save(cat);
        return convertToDto(updatedCat);
    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }

    private CatDto convertToDto(Cat cat) {
        CatDto catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setDateOfBirth(cat.getDateOfBirth());
        catDto.setBreed(cat.getBreed());
        catDto.setColor(cat.getColor());
        if (cat.getOwner() != null) {
            catDto.setOwnerId(cat.getOwner().getId());
        }
        if (cat.getListOfFriends() != null) {
            catDto.setListOfFriendsIds(cat.getListOfFriends().stream().map(Cat::getId).collect(Collectors.toList()));
        }
        return catDto;
    }

    private Cat convertToEntity(CatDto catDto) {
        long id = catDto.getId();
        String name = catDto.getName();
        Date dateOfBirth = catDto.getDateOfBirth();
        String breed = catDto.getBreed();
        String color = catDto.getColor();
        Owner owner = null;
        List<Cat> listOfFriends = null;

        if (catDto.getOwnerId() != null) {
            owner = ownerRepository.findById(catDto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
        }

        if (catDto.getListOfFriendsIds() != null) {
            listOfFriends = catDto.getListOfFriendsIds().stream()
                    .map(friendId -> catRepository.findById(friendId).orElseThrow(() -> new RuntimeException("Friend not found")))
                    .collect(Collectors.toList());
        }

        return new Cat(
                name,
                dateOfBirth,
                breed,
                color,
                owner,
                listOfFriends
        );
    }
}