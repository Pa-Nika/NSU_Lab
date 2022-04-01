import Factory.Calculator;
import com.Main.Context;
import com.Main.Parser;
import com.Main.RecordWord;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommentTest {
    @Test
    public void TestMath2() throws Exception {
        Context context = new Context();
        ArrayList<RecordWord> all_operations = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser(reader);
        Double answer = 7.0;

        parser.parsLine("DEFINE a 4", all_operations);
        parser.parsLine("DEFINE b 3", all_operations);
        parser.parsLine("#Some very useful or useless information :-)", all_operations);
        parser.parsLine("PUSH a", all_operations);
        parser.parsLine("PUSH b", all_operations);
        parser.parsLine("+", all_operations);

        Calculator calculator = new Calculator(all_operations, context);
        calculator.work();

        assertEquals(answer, context.getStack().pop());
    }
}
