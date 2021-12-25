package com.zzh.dream.studymongodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Slf4j
@SpringBootTest
class StudyMongodbApplicationTests {

	@Autowired
	private MongoTemplate mongoOps;

	@Test
	void testMongoDb() {


		Person p = new Person("zzh", 88888);
		// 插入文档
		mongoOps.insert(p);
		log.info("Insert: " + p);

		// 查询文档
		p = mongoOps.findById(p.getId(), Person.class);
		log.info("Found: " + p);

		List<Person> persons = mongoOps.find(Query.query(where("name").is("zzh")), Person.class);
		log.info("Found: " + persons);

		//更新
		mongoOps.updateFirst(Query.query(where("name").is("zzh")), Update.update("age","1111"),Person.class);
		p = mongoOps.findOne(Query.query(where("name").is("zzh")), Person.class);
		log.info("Updated: " + p);

		// 删除文档
//		mongoOps.remove(p);

		// Check that deletion worked
		List<Person> people =  mongoOps.findAll(Person.class);
		log.info("Number of people = : " + people.size());
//		mongoOps.dropCollection(Person.class);


		List<Person> insert = new ArrayList<>();
		for (int i = 0; i <10 ; i++) {
			Person p1 = new Person("zzh"+i, i);
			insert.add(p1);
		}
		Collection<Person> all = mongoOps.insertAll(insert);
		// 插入文档

		log.info("Insert: " + all);
	}

}
