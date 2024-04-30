package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class CatDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Date dateOfBirth;
    @Getter
    @Setter
    private String breed;
    @Getter
    @Setter
    private String color;
    @Getter
    @Setter
    private Long ownerId;
    @Getter
    @Setter
    private List<Long> listOfFriendsIds;
}
