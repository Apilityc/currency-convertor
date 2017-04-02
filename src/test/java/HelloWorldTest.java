import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by g on 4/2/17.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class HelloWorldTest {

	@Test
	public void failed() {
		Assert.fail();
	}
}
