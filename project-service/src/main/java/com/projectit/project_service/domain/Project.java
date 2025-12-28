package com.projectit.project_service.domain;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    private LocalDate dueDate;

    @Column(nullable = false)
    private Long ownerUserId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
