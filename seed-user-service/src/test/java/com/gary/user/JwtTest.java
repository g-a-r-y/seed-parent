package com.gary.user;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gary on 17/2/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {UserApp.class})
@Transactional
public class JwtTest {

	public static void main(String[] args) {

	}

}
