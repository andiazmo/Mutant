package com.mima.rest.webservices.restfulwebservices;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mima.rest.webservices.restfulwebservices.repos.DnaRepository;
import com.mima.rest.webservices.restfulwebservices.user.Dna;

@RunWith(SpringRunner.class)
@SpringBootTest
class RestfulWebServicesApplicationTests {
	
	@Autowired
	private DnaRepository repository;

	@Test
	void testSaveDna() {
		Dna dna = new Dna();
		String[] arr = new String[] {"AGGCGA","CAGTGC","TTAGGT","AGAAGG","CCCTTA","TCACTG"};
	    dna.setId(1l);
		dna.setDna(arr);
		repository.save(dna);
		
		Dna savedDna = repository.findById(1l).get();
		assertNotNull(savedDna);
	}

}
