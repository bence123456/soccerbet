package com.bkonecsni.soccerbet;

import com.bkonecsni.soccerbet.common.service.CommonServiceImpl;
import com.bkonecsni.soccerbet.domain.Bet;
import com.bkonecsni.soccerbet.domain.Match;
import com.bkonecsni.soccerbet.domain.User;
import com.bkonecsni.soccerbet.repositories.BetRepository;
import com.bkonecsni.soccerbet.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class CommonServiceTest {

    private CommonServiceImpl commonService;

    @Mock
    private BetRepository betRepository = Mockito.mock(BetRepository.class);

    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        commonService = new CommonServiceImpl();
        commonService.setBetRepository(betRepository);
        commonService.setUserRepository(userRepository);
    }

    @Test
    public void shouldCalculatePointsCorrectlyWhenMatchResultIsDraw() {
        //GIVEN
        Match match = createMatch(2, 2);

        List<BetWrapper> betWrapperList = Arrays.asList(
                new BetWrapper(new Bet(createDefaultUser(), match, 2, 2), 3),
                new BetWrapper(new Bet(createDefaultUser(), match, 1, 1), 1),
                new BetWrapper(new Bet(createDefaultUser(), match, 3, 1), 0),
                new BetWrapper(new Bet(createDefaultUser(), match, 0, 4), 0)
        );

        test(match, betWrapperList);
    }

    @Test
    public void shouldCalculatePointsCorrectlyWhenHomeTeamWon() {
        //GIVEN
        Match match = createMatch(3, 1);

        List<BetWrapper> betWrapperList = Arrays.asList(
                new BetWrapper(new Bet(createDefaultUser(), match, 3, 1), 3),
                new BetWrapper(new Bet(createDefaultUser(), match, 1, 0), 1),
                new BetWrapper(new Bet(createDefaultUser(), match, 2, 2), 0),
                new BetWrapper(new Bet(createDefaultUser(), match, 0, 1), 0)
        );

        test(match, betWrapperList);
    }

    @Test
    public void shouldCalculatePointsCorrectlyWhenAwayTeamWon() {
        //GIVEN
        Match match = createMatch(0, 2);

        List<BetWrapper> betWrapperList = Arrays.asList(
                new BetWrapper(new Bet(createDefaultUser(), match, 0, 2), 3),
                new BetWrapper(new Bet(createDefaultUser(), match, 2, 4), 1),
                new BetWrapper(new Bet(createDefaultUser(), match, 2, 2), 0),
                new BetWrapper(new Bet(createDefaultUser(), match, 3, 0), 0)
        );

        test(match, betWrapperList);
    }

    @Test
    public void shouldUpdateUserPointsCorrectly() {
        //GIVEN
        Match match = createMatch(2, 2);
        int userPoints = 6;
        User user = createUser(userPoints);
        Bet bet = new Bet(user, match, 2, 2);

        Mockito.when(betRepository.findByMatch(match)).thenReturn(Arrays.asList(bet));

        //WHEN
        commonService.calculateAndSavePoints(match);

        //THEN
        Assert.assertEquals(bet.getGainedPoints().intValue(), 3);
        Assert.assertEquals(user.getPoints(), userPoints + 3);
    }

    @Test
    public void shouldReturnTheCorrectIdFromUrl() {
        //GIVEN
        String url = "root/example/url/23";

        //WHEN
        Long actualId = commonService.getIdFromUrl(url);

        //THEN
        Assert.assertEquals(Long.valueOf(23), actualId);
    }

    private void test(Match match, List<BetWrapper> betWrapperList) {
        mockBetList(match, betWrapperList);

        //WHEN
        commonService.calculateAndSavePoints(match);

        //THEN
        for (BetWrapper betWrapper : betWrapperList) {
            Assert.assertEquals(betWrapper.getBet().getGainedPoints(), betWrapper.getExpectedGainedPoints());
        }
    }

    private void mockBetList(Match match, List<BetWrapper> betWrapperList) {
        List<Bet> betList = new ArrayList<>();
        for (BetWrapper betWrapper : betWrapperList) {
            betList.add(betWrapper.bet);
        }
        Mockito.when(betRepository.findByMatch(match)).thenReturn(betList);
    }


    private Match createMatch(int homeTeamGoal, int awayTeamGoal) {
        return new Match(1L, null, null, null, null, homeTeamGoal, awayTeamGoal, "FINISHED", 1, null);
    }

    private User createUser(int points) {
        return new User("id1", "testUser", points);
    }

    private User createDefaultUser() {
        return createUser(0);
    }
}