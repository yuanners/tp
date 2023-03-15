package utility;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void formatArguments_normal(){
        Parser p = new Parser();

        String arguments = "-p 100 --positive 200 --sentence \"a very long sentence\"";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertEquals("100", argMap.get("p"));
        assertEquals("200", argMap.get("positive"));
        assertEquals("a very long sentence", argMap.get("sentence"));
    }

    @Test
    void formatArguments_dash(){
        Parser p = new Parser();

        String arguments = "-n -100 --negative -200 --middle_dash text-with-middle-dash --trailing_dash some-text-";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertEquals("-100", argMap.get("n"));
        assertEquals("-200", argMap.get("negative"));
        assertEquals("text-with-middle-dash", argMap.get("middle_dash"));
        assertEquals("some-text-", argMap.get("trailing_dash"));
    }

    @Test
    void formatArguments_exist(){
        Parser p = new Parser();

        String arguments = "-a";

        Map<String, String> argMap = p.formatArguments(arguments);

        assertEquals(true, argMap.containsKey("a"));
    }
}