package com.isproj2.regainmobile.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reportcategories")
public class ReportCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_category_id")
    private Integer reportCategoryID;

    @Column(name = "category")
    private String name;

    @OneToMany(mappedBy = "reasonCategory", fetch=FetchType.EAGER)
    private Collection<ListingReport> listingReport;

    @OneToMany(mappedBy = "reasonCategory", fetch = FetchType.EAGER)
    private Collection<EQListingReport> eqlistingReport;

    @OneToMany(mappedBy = "reasonCategory", fetch = FetchType.EAGER)
    private Collection<UserReport> userReport;

    public ReportCategory (String category) {
        this.name = category;
    }
}
