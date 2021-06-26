package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        CheckAppConditionTests.class,
        GetStartedTest.class,
        MyListsTest.class,
        SearchTests.class,
        lesson9.TaskOneTest.class,
        lesson9.TaskTwoTest.class,
})

public class TestSuite {
}
