package com.projectmanagement.repository.implementation;

import com.projectmanagement.domain.entity.ProjectEntity;
import com.projectmanagement.domain.model.FilterProjectModel;


import com.projectmanagement.repository.interfaces.ProjectRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public  class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
        public List<ProjectEntity> filterrProjects(FilterProjectModel filter ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        PageRequest pageRequest = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        // Sorgu: data için
        CriteriaQuery<ProjectEntity> cq = cb.createQuery(ProjectEntity.class);
        Root<ProjectEntity> root = cq.from(ProjectEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
        }

        if (filter.getActualStartDate() != null && filter.getActualEndDate() != null) {
            predicates.add(cb.between(root.get("actualStartDate"), filter.getActualStartDate(), filter.getActualEndDate()));
        }

        if (filter.getPlannedStartDate() != null && filter.getPlannedEndDate() != null) {
            predicates.add(cb.between(root.get("plannedStartDate"), filter.getPlannedStartDate(), filter.getPlannedEndDate()));
        }

        if (filter.getStatusId() != null) {
            predicates.add(cb.equal(root.get("statusId"), filter.getStatusId()));
        }

        if (filter.getCategoryId() != null) {
            predicates.add(cb.equal(root.get("categoryId"), filter.getCategoryId()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("id"))); // cb = CriteriaBuilder
        // Order varsa eklenebilir, örn:
        // cq.orderBy(cb.asc(root.get("name")));

        // Sayfalama için sorgu
        TypedQuery<ProjectEntity> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageRequest.getOffset());
        query.setMaxResults(pageRequest.getPageSize());

        List<ProjectEntity> resultList = query.getResultList();



        return resultList;
    }
}
