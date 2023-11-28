package it.dedagroup.venditabiglietti.repository;

import it.dedagroup.venditabiglietti.dto.request.FiltroLuogoDTORequest;
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

    public List<Luogo> findFiltrati(FiltroLuogoDTORequest request){
        CriteriaBuilder builder=manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query=builder.createQuery(Tuple.class);
        Root<Luogo> root=query.from(Luogo.class);
        List<Predicate> predicate=new ArrayList<>();
        if(request.getProvincia() != null) predicate.add(builder.like(builder.lower(root.get("provincia")), "%"+ request.getProvincia().toLowerCase()+"%"));
        if(request.getComune() != null) predicate.add(builder.like(builder.lower(root.get("comune")), "%"+ request.getComune().toLowerCase()+"%"));
        Predicate[] predicateArray=predicate.toArray(new Predicate[predicate.size()]);
        query.where(predicateArray);
        List<Tuple> list = manager.createQuery(query).getResultList();
        return list.stream().map(t->t.get(0, Luogo.class)).toList();
    }
    						  		 //nomeAttributo  //valore
    public List<Luogo> filtraLuoghiMap(Map<String, String> mapLuogo){
    	CriteriaBuilder builder = manager.getCriteriaBuilder();
    	CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
    	Root<Luogo> root = query.from(Luogo.class);
    	
    	List<Predicate> predicate = new ArrayList<>();
    	if(mapLuogo == null) mapLuogo = new HashMap<>();
    	for(String nomeArgomento: mapLuogo.keySet()) {
    		Predicate p = builder.like(root.get(nomeArgomento).as(String.class), "%"+mapLuogo.get(nomeArgomento)+"%");
    		predicate.add(p);
    	}
    	Predicate[] predicateArray = predicate.toArray(new Predicate[predicate.size()]);
    	query.multiselect(root).where(predicateArray);
    	List<Tuple> list = manager.createQuery(query).getResultList();
    	return list.stream().map(t -> t.get(0, Luogo.class)).toList();
    }
}
