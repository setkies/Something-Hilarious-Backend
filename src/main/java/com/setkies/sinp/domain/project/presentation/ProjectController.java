package com.setkies.sinp.domain.project.presentation;

import com.setkies.sinp.domain.project.dto.ChangeStatusReq;
import com.setkies.sinp.domain.project.dto.ProjectCreateReq;
import com.setkies.sinp.domain.project.dto.ProjectRes;
import com.setkies.sinp.domain.project.service.ProjectDef;
import com.setkies.sinp.domain.project.service.ProjectRead;
import com.setkies.sinp.infrastructure.security.util.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectDef projectDef;
    private final ProjectRead projectRead;

    @PostMapping()
    public Long createProject(@RequestBody ProjectCreateReq projectCreateReq){
        return projectDef.createProject(projectCreateReq, SecurityUtil.getCurrentUserWithLogin());
    }

    @PutMapping("/{id}")
    public Long updateProject(@PathVariable Long id, @RequestBody ProjectCreateReq projectCreateReq){
        return projectDef.updateProject(id,projectCreateReq, SecurityUtil.getCurrentUserWithLogin());
    }

    @PatchMapping("/{id}")
    public Long updateStatus(@PathVariable Long id, @RequestBody ChangeStatusReq changeStatusReq){
        return projectDef.changeProjectStatus(id,changeStatusReq.getStatus() ,SecurityUtil.getCurrentUserWithLogin());
    }

    @DeleteMapping("/{id}")
    public Long updateStatus(@PathVariable Long id){
        return projectDef.deleteProject(id ,SecurityUtil.getCurrentUserWithLogin());
    }

    @GetMapping()
    public Page<ProjectRes> getProjectList(@RequestParam(name= "status") String status, @PageableDefault(size = 20) Pageable pageable){
        return projectRead.getProjectList(status,pageable);
    }
    @GetMapping("/{id}")
    public ProjectRes getProjectList(@PathVariable Long id){
        return projectRead.getProject(id);
    }

    @GetMapping("/me")
    public List<ProjectRes> getMyProject(){
        return projectRead.getMyProject(SecurityUtil.getCurrentUserWithLogin());
    }
}
