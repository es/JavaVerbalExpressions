import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class RealWorldUnitTests {

    @Test
    public void testUrl() {
        VerbalExpression testRegex = new VerbalExpression.Builder()
                .startOfLine()
                .then("http")
                .maybe("s")
                .then("://")
                .maybe("www.")
                .anythingButNot(" ")
                .endOfLine()
                .build();

        // Create an example URL
        String testUrl = "https://www.google.com";
        assertTrue("Matches Google's url", testRegex.test(testUrl)); //True
        assertEquals("Regex doesn't match same regex as in example", testRegex.toString(), "^(http)(s)?(\\:\\/\\/)(www\\.)?([^\\ ]*)$");
   }

   @Ignore
   @Test
   public void testEmail () {
      VerbalExpression testRegex = new VerbalExpression.Builder()
                                       .startOfLine()
                                       .range("a","z","A","Z","0","9") //
                                       .multiple("*")
                                       .anyOf("_.+-")
                                       .then("@")
                                       .range("a","z","A","Z","0","9")
                                       .multiple("+")
                                       .then(".")
                                       .range("a","z","A","Z","0","9")
                                       .multiple("+")
                                       /*.anythingButNot(" ")*/
                                       .endOfLine()
                                       /*.withAnyCase()*/
                                       .build();
                                       
      System.out.println(testRegex.toString());
      int counter = 0;
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.test("john@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john-123@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john.123@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john111@john.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john-123@john.net"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john.123@john.uk.co"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john@1.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john@gmail.com.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john+123@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertTrue(testRegex.testExact("john-123@gmail-test.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("must contains '@' symbol", testRegex.testExact("johnDoe"));
      System.out.println("Counter: "+counter++);
      assertFalse("tld can not start with dot '.'", testRegex.testExact("johnDoe@.com.my"));
      System.out.println("Counter: "+counter++);
      assertFalse("'.a' is not a valid tld, last tld must contains at least two characters", testRegex.testExact("johnDoe123@gmail.a"));
      System.out.println("Counter: "+counter++);
      assertFalse("tld can not start with dot '.'", testRegex.testExact("johnDoe123@.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("tld can not start with dot '.'", testRegex.testExact("johnDoe123@.com.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("email’s first character can not start with dot '.'", testRegex.testExact(".johnDoe@johnDoe.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("email’s is only allow character, digit, underscore and dash", testRegex.testExact("johnDoe()*@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("email’s tld is only allow character and digit", testRegex.testExact("johnDoe@%*.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("double dots '.' are not allow", testRegex.testExact("johnDoe..2002@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("email’s last character can not end with dot '.'", testRegex.testExact("johnDoe.@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("double '@' is not allow", testRegex.testExact("johnDoe@johnDoe@gmail.com"));
      System.out.println("Counter: "+counter++);
      assertFalse("email’s tld which has two characters can not contains digit", testRegex.testExact("johnDoe@gmail.com.u2"));
   }
}