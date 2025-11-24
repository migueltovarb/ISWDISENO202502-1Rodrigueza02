package com.academia.controller;

import com.academia.model.Group;
import com.academia.repository.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        group.setCreatedAt(LocalDateTime.now());
        Group created = groupRepository.save(group);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<?> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroup(@PathVariable String id) {
        return groupRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateGroup(@PathVariable String id, @RequestBody Group group) {
        return groupRepository.findById(id)
                .map(existing -> {
                    existing.setName(group.getName());
                    existing.setGrade(group.getGrade());
                    existing.setSection(group.getSection());
                    existing.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(groupRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteGroup(@PathVariable String id) {
        groupRepository.deleteById(id);
        return ResponseEntity.ok("Grupo eliminado");
    }
}
