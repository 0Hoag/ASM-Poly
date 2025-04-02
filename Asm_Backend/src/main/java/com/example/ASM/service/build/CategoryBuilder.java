package com.example.ASM.service.build;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.ASM.exception.AppException;
import com.example.ASM.exception.ErrorCode;
import com.example.ASM.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Builder
@Service
@Slf4j
@FieldDefaults(makeFinal = true)
public class CategoryBuilder {
    CategoryRepository repo;

    public boolean wouldCreateCircularReference(int categoryID, int parentID) {
        if (categoryID == parentID) return true;

        Set<Integer> visitedCategoryIDs = new HashSet<>();
        visitedCategoryIDs.add(categoryID);

        int currentParentID = parentID;
        while (currentParentID != 0) {
            if (visitedCategoryIDs.contains(currentParentID)) return true;

            var parent = repo.findById(currentParentID)
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORIES_NOT_EXISTED));

            if (parent.getParentCategory() == null) {
                break;
            }

            currentParentID = parent.getParentCategory().getId();
            visitedCategoryIDs.add(currentParentID);
        }

        return false;
    }
}
