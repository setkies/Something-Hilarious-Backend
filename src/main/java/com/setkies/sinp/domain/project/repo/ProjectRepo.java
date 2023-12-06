package com.setkies.sinp.domain.project.repo;

import com.setkies.sinp.domain.project.Project;
import com.setkies.sinp.domain.project.Status;
import com.setkies.sinp.domain.user.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    Page<Project> findByStatus(Status status, Pageable pageable);
    List<Project> findByAuthor(User author);
}
