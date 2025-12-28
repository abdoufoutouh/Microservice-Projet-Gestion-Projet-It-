package com.projectit.project_service.dto;

import com.projectit.project_service.domain.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDTO {
    
    private String title;
    private String description;
    private Long ownerUserId;
    private Long categoryId; // ID de catégorie existante
    private String newCategoryName; // Nom de nouvelle catégorie (optionnel)
    
    public Category getCategory() {
        if (newCategoryName != null && !newCategoryName.trim().isEmpty()) {
            // Créer une nouvelle catégorie
            Category newCategory = new Category();
            newCategory.setName(newCategoryName.trim());
            return newCategory;
        } else if (categoryId != null) {
            // Utiliser une catégorie existante
            Category existingCategory = new Category();
            existingCategory.setId(categoryId);
            return existingCategory;
        }
        return null;
    }
}
