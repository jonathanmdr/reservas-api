package com.teste.reservasapi.repository.Cliente;

import com.teste.reservasapi.model.Cliente;
import com.teste.reservasapi.model.Cliente_;
import com.teste.reservasapi.repository.filter.ClienteFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Cliente> findAllFiltered(ClienteFilter clienteFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);

        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Cliente> query = entityManager.createQuery(criteriaQuery);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, totalRegistrosFiltrados(clienteFilter));
    }

    private Predicate[] criarRestricoes(ClienteFilter clienteFilter, CriteriaBuilder builder, Root<Cliente> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(clienteFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(Cliente_.nome)), "%".concat(clienteFilter.getNome().toLowerCase()).concat("%")));
        }

        if (!StringUtils.isEmpty(clienteFilter.getDocumento())) {
            predicates.add(builder.like(builder.lower(root.get(Cliente_.documento)), "%".concat(clienteFilter.getDocumento().toLowerCase()).concat("%")));
        }

        if (!StringUtils.isEmpty(clienteFilter.getTelefone())) {
            predicates.add(builder.like(builder.lower(root.get(Cliente_.telefone)), "%".concat(clienteFilter.getTelefone().toLowerCase()).concat("%")));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Cliente> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long totalRegistrosFiltrados(ClienteFilter clienteFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cliente> root = criteria.from(Cliente.class);

        Predicate[] predicates = criarRestricoes(clienteFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return entityManager.createQuery(criteria).getSingleResult();
    }

}
