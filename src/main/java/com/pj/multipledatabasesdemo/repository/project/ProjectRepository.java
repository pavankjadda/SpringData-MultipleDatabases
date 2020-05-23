package com.pj.multipledatabasesdemo.repository.project;

import com.pj.multipledatabasesdemo.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
}
