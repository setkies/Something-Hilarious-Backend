package com.setkies.sinp.domain.funding.repo;

import com.setkies.sinp.domain.funding.Funding;
import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepo extends JpaRepository<Funding,Long> {
    List<Funding> findByProject(Project project);
    List<Funding> findByUser(User user);
}
