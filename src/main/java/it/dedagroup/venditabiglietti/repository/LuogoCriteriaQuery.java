package it.dedagroup.venditabiglietti.repository;

import it.dedagroup.venditabiglietti.model.Luogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LuogoCriteriaQuery {

    @Autowired
    private EntityManager manager;

    public List<Luogo> findFiltrati(Map<String, String> parametriLuogo){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=builder.createQuery(Tuple.class);
        Root<Luogo> root=query.from(Luogo.class);
        List<Predicate> predicate=new ArrayList<>();
        if(parametriLuogo==null) parametriLuogo=new HashMap<>();
        for(String nomeParametro:parametriLuogo.keySet()){
            Predicate p=builder.like(root.get(nomeParametro),"%"+parametriLuogo.get(nomeParametro)+"%");
            predicate.add(p);
        }
        Predicate[] predicateArray=predicate.toArray(new Predicate[predicate.size()]);
        List<Tuple> list=manager.createQuery(query).getResultList();
        return list.stream().map(t->t.get(0, Luogo.class)).toList();
    }
}
