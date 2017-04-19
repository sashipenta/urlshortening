// Author : Sashi Penta

package shortening;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class ShorteningController
{
    private final Map<String, String> CodeToUrl = new HashMap<String, String>();
    private final Map<String, String> UrlToCode = new HashMap<String, String>();

    private final AtomicLong index = new AtomicLong();

    private String getCode(long idx)
    {
        return "abcdefg";
        // return 7 character long code
    }

    // TODO: restrict to post
    // TODO: make url mandatory
    @RequestMapping("/shortening")
    public URLObject shortening(@RequestParam(value="url", defaultValue="defaultValue") String url)
    {

        if(UrlToCode.containsKey(url))
        {
            String code = UrlToCode.get(url);
            return new URLObject(code, url);
        }
        else
        {
            String code = getCode(index.incrementAndGet());    
            
            CodeToUrl.put(url, code);
            UrlToCode.put(code, url);

            return new URLObject(code, url);
        }            
    }
}
