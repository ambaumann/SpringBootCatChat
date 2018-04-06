package com.example.catchat.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.example.catchat.model.CatResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatRepositoryTest {
    @Autowired
    private CatRepository catRepository;

    @Before
    public void setup() {
        CatResource cat = new CatResource();
        cat.setName("Derpy");
        cat.setURL("https://media.giphy.com/media/12ipPASHq1luj6/giphy.gif");
        catRepository.save(cat);
    }

    @Test
    public void testFindAllCats() {
        List<CatResource> cats = catRepository.findAll();
        assertThat(cats.size(), is(3));
    }
}
