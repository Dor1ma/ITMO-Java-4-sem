package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class OwnerDto {
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
    private List<Long> catsIds;
}
