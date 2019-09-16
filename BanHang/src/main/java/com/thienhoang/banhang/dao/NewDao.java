package com.thienhoang.banhang.dao;

import com.thienhoang.banhang.model.New;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewDao extends JpaRepository<New, Integer> {
}
