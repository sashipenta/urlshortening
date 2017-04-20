// Author : Sashi Penta

package shortening;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;

@RestController
public class ShorteningController
{
    private final Map<String, String> CodeToUrl = new HashMap<String, String>();
    private final Map<String, String> UrlToCode = new HashMap<String, String>();

    private final AtomicLong index = new AtomicLong();

    private final char[] encode =  {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private final int base = 62;

    // return 7 character long code
    private String getCode(long idx)
    {
        char[] code = {'0', '0', '0', '0','0', '0', '0'};

        int lastIndex = 6;

        while(idx > 0)
        {
            long remainder = idx % base;
            code[lastIndex] = encode[(int)remainder];
            idx /= base;
            lastIndex--;            
        }

        return String.valueOf(code);
    }

    @RequestMapping(value="/shortening", method=RequestMethod.POST)
    public URLObject create(@RequestParam(value="url") String url)
    {

        if(UrlToCode.containsKey(url))
        {
            String code = UrlToCode.get(url);
            return new URLObject(code, url);
        }
        else
        {
            String code = getCode(index.incrementAndGet());    
            
            CodeToUrl.put(code, url);
            UrlToCode.put(url, code);

            return new URLObject(code, url);
        }            
    }

    @RequestMapping(value="/shortening", method=RequestMethod.GET)
    public URLObject shortening1(@RequestParam(value="code") String code)
    {
        if(CodeToUrl.containsKey(code))
        {
            String url = CodeToUrl.get(code);
            return new URLObject(code, url);
        }
        else
        {
            String error = String.format("%s code doesn't exist", code);
            throw new IllegalArgumentException(error);
        }            
    }

    @RequestMapping(value="/{code}", method=RequestMethod.GET)
    public URLObject shortening2(@PathVariable String code)
    {
        if(CodeToUrl.containsKey(code))
        {
            String url = CodeToUrl.get(code);
            return new URLObject(code, url);
        }
        else
        {
            String error = String.format("%s code doesn't exist", code);
            throw new IllegalArgumentException(error);
        }            
    }
}
