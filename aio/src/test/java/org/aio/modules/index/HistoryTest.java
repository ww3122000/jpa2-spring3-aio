package org.aio.modules.index;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "/**/app-*.xml"}) 
public class HistoryTest {

	@PersistenceContext
	private EntityManager em;
	@Test
	public void test() {
		try {
			System.out.println(em.createNativeQuery("select count(*) from Article").getResultList());
			System.out.println(em.createQuery("select count(*) from Article").getResultList());
			System.out.println("get"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
