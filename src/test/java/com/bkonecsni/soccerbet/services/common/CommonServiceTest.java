package com.bkonecsni.soccerbet.services.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonServiceTest {

    private CommonService commonService = new CommonServiceImpl();

    @Test
    public void shouldReturnTheCorrectIdFromUrl() {
        //GIVEN
        String url = "root/example/url/23";

        //WHEN
        Long actualId = commonService.getIdFromUrl(url);

        //THEN
        Assert.assertEquals(Long.valueOf(23), actualId);
    }
}
