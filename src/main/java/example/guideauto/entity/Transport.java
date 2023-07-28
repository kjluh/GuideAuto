package example.guideauto.entity;

import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transport {

    @Id
    private Long id;

    private String mark;
    private String model;
    private Category category;
    private String GOSNumber;
    private Types types;
    private int yearRelease;
    private boolean availabilityTrailer;

}
