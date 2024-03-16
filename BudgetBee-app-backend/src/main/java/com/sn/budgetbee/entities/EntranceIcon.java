package com.sn.budgetbee.entities;

import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.*;

@Entity
@Table(name="table_entrances_icons")
public class EntranceIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="link_icon")
    private String link;

    @Column(name="category_icon")
    @Enumerated(EnumType.STRING)
    private EntranceCategories entranceCategoriesLink;

    public EntranceIcon(String link, EntranceCategories entranceCategoriesLink) {
        this.link = link;
        this.entranceCategoriesLink = entranceCategoriesLink;
    }

    public EntranceIcon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EntranceCategories getEntranceCategoriesLink() {
        return entranceCategoriesLink;
    }

    public void setEntranceCategoriesLink(EntranceCategories entranceCategoriesLink) {
        this.entranceCategoriesLink = entranceCategoriesLink;
    }

}
