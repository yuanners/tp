package utility;

import exception.DuplicateArgumentFoundException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void formatArguments_normal() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "-p 100 --positive 200 --sentence \"a very long sentence\"";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertEquals("100", argMap.get("p"));
        assertEquals("200", argMap.get("positive"));
        assertEquals("a very long sentence", argMap.get("sentence"));
    }

    @Test
    void formatArguments_dash() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "-n -100 --negative -200 --middle_dash text-with-middle-dash --trailing_dash some-text-";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertEquals("-100", argMap.get("n"));
        assertEquals("-200", argMap.get("negative"));
        assertEquals("text-with-middle-dash", argMap.get("middle_dash"));
        assertEquals("some-text-", argMap.get("trailing_dash"));
    }

    @Test
    void formatArguments_exist() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "-a";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertTrue(argMap.containsKey("a"));
    }

    @Test
    void formatArguments_printMap() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "-p 2sdkfnds -b 123/234/345";

        Map<String, String> argMap = p.formatArguments(arguments);

        System.out.println(argMap);
    }

    @Test
    void formatArgument_date() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "--date 20/20/2022";

        Map<String, String> argMap = p.formatArguments(arguments);
        assertEquals("20/20/2022", argMap.get("date"));
    }

    @Test
    void formatArgument_arrayOfOrders() throws DuplicateArgumentFoundException {
        Parser p = new Parser();

        String arguments = "-I [Chicken rice:2,Noodle:1, 3:4]";

        Map<String, String> argMap = p.formatArguments(arguments);
        assertEquals("[Chicken rice:2,Noodle:1, 3:4]", argMap.get("I"));
    }
}
