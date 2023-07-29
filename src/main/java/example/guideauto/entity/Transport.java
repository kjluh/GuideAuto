package example.guideauto.entity;

import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Transport {
    /**
     * ПК для идентификации объекта в бд
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Марка транспорта
     */
    private String mark;
    /**
     * Модель
     */
    private String model;
    /**
     * Категория транспорта (в виде енам)
     */
    @Enumerated(EnumType.STRING)
    private Category category;
    /**
     * ГОС Номер транспорта
     */
    private String gosNumber;
    /**
     * Тип транспорта (в виде енам)
     */
    @Enumerated(EnumType.STRING)
    private Types types;
    /**
     * Год выпуска транспорта
     */
    private int yearRelease;
    /**
     * Наличие прицепа у транспорта
     */
    private boolean availabilityTrailer;

}
