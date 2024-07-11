package com.isproj2.regainmobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isproj2.regainmobile.model.RateTag;

public interface RateTagRepository extends JpaRepository<RateTag, Integer> {

    RateTag findByName(String name);

}