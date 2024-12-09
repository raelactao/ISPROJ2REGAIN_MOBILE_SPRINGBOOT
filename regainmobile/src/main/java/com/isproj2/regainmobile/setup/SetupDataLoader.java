package com.isproj2.regainmobile.setup;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.isproj2.regainmobile.model.Category;
import com.isproj2.regainmobile.model.RateTag;
import com.isproj2.regainmobile.model.ReportCategory;
import com.isproj2.regainmobile.model.Role;
import com.isproj2.regainmobile.repo.CategoryRepository;
import com.isproj2.regainmobile.repo.RateTagRepository;
import com.isproj2.regainmobile.repo.ReportCategoryRepository;
import com.isproj2.regainmobile.repo.RoleRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RateTagRepository rateTagRepository;

    @Autowired
    private ReportCategoryRepository reportCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // INSERT ROLES HERE
        // CREATES ROLES UPON RUNNING
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_USER_WS");

        // CREATE PRODUCT CATEGORIES
        createCategoryIfNotFound("Plastic");
        createCategoryIfNotFound("Metal");
        createCategoryIfNotFound("Paper");
        createCategoryIfNotFound("Glass");
        createCategoryIfNotFound("Electronics");

        // Pickup/Drop-off
        createRateTagIfNotFound("On-time Pickup");
        createRateTagIfNotFound("Delayed Pickup");
        createRateTagIfNotFound("Convenient Drop-off Location");
        createRateTagIfNotFound("Missed Pickup");
        // Product Quality
        createRateTagIfNotFound("Accurate Description");
        createRateTagIfNotFound("Misrepresented Item");
        createRateTagIfNotFound("High-quality Recyclables");
        createRateTagIfNotFound("Low-quality waste");
        // Pricing
        createRateTagIfNotFound("Fair Pricing");
        createRateTagIfNotFound("Overpriced Items");
        createRateTagIfNotFound("Good Value");
        // Overall Experience
        createRateTagIfNotFound("Smooth Transaction");
        createRateTagIfNotFound("Complicated Process");
        createRateTagIfNotFound("Highly Satisfied");
        createRateTagIfNotFound("Disappointed");
        // Payment
        createRateTagIfNotFound("Prompt Payment");
        createRateTagIfNotFound("Delayed Payment");
        // Condition of Waste
        createRateTagIfNotFound("Well-sorted Waste");
        createRateTagIfNotFound("Clean Recylables");
        createRateTagIfNotFound("Contaminated Waste");
        createRateTagIfNotFound("Mixed Quality Items");

        // Report Categories
        createReportCategoryIfNotFound("Suspicious Account");
        createReportCategoryIfNotFound("Fake Location");
        createReportCategoryIfNotFound("Item Wrongly Categorized");
        createReportCategoryIfNotFound("Prohibited Listing");
        createReportCategoryIfNotFound("Mispriced Listings");
        createReportCategoryIfNotFound("Offensive Behavior");
        createReportCategoryIfNotFound("Chat Spam");
        createReportCategoryIfNotFound("False Transactions attempts");
        createReportCategoryIfNotFound("Spam Listing");
        createReportCategoryIfNotFound("Improper Product Name");
        createReportCategoryIfNotFound("Empty Parcel");
        createReportCategoryIfNotFound("Outside Transaction");
        createReportCategoryIfNotFound("Improper Username");
        createReportCategoryIfNotFound("Selling Prohibited Items");
        createReportCategoryIfNotFound("Phishing Scammer");
        createReportCategoryIfNotFound("Cancelling On Deal");

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    Category createCategoryIfNotFound(String name) {

        Category category = categoryRepository.findByName(name);
        if (category == null) {
            category = new Category(name);
            categoryRepository.save(category);
        }
        return category;
    }

    @Transactional
    RateTag createRateTagIfNotFound(String name) {

        RateTag rateTag = rateTagRepository.findByName(name);
        if (rateTag == null) {
            rateTag = new RateTag(name);
            rateTagRepository.save(rateTag);
        }
        return rateTag;
    }

    @Transactional
    ReportCategory createReportCategoryIfNotFound(String name) {

        ReportCategory reportCategory = reportCategoryRepository.findByName(name);
        if (reportCategory == null) {
            reportCategory = new ReportCategory(name);
            reportCategoryRepository.save(reportCategory);
        }
        return reportCategory;
    }

}
