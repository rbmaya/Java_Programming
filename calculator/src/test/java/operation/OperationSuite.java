package operation;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({ AddTest.class, ContextTest.class, DefineTest.class, DivTest.class,
MulTest.class, PopTest.class, PrintTest.class, PushTest.class, SqrtTest.class, SubTest.class})
@RunWith(Suite.class)
public class OperationSuite extends TestSuite {
}