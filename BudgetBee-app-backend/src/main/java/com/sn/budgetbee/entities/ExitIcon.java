package com.sn.budgetbee.entities;

import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.*;

@Entity
@Table(name="table_exits_icons")
public class ExitIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="link_icon")
    private String link;

    @Column(name="category_icon")
    @Enumerated(EnumType.STRING)
    private ExitCategories exitCategoriesLink;

    public ExitIcon(String link, ExitCategories exitCategoriesLink) {
        this.link = link;
        this.exitCategoriesLink = exitCategoriesLink;
    }

    public ExitIcon() {
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

    public ExitCategories getExitCategoriesLink() {
        return exitCategoriesLink;
    }

    public void setExitCategoriesLink(ExitCategories exitCategoriesLink) {
        this.exitCategoriesLink = exitCategoriesLink;
    }
}
