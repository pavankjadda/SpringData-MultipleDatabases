package com.pj.multipledatabasesdemo.web;

import com.pj.multipledatabasesdemo.domain.project.Project;
import com.pj.multipledatabasesdemo.repository.project.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/project")
public class ProjectController
{
	private final ProjectRepository projectRepository;

	public ProjectController(ProjectRepository projectRepository)
	{
		this.projectRepository = projectRepository;
	}

	@GetMapping("/test/find/all")
	public List<Project> findAll()
	{
		return projectRepository.findAll();
	}
}
