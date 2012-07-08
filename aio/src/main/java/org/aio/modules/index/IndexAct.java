package org.aio.modules.index;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexAct {
	private Log log = LogFactory.getLog(IndexAct.class);
	@PersistenceContext
	private EntityManager em;
	@RequestMapping(value="/index.do")
	public String history(ModelMap model){
		log.info("history.do ...");
		log.info(em);
		System.out.println(em.createNativeQuery("select count(*) from Article").getResultList());
		System.out.println(em.createQuery("select count(*) from Article").getResultList());
		return "index";
	}
	
}
